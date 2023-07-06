package com.zetta.memo.page.memo;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.transaction.annotation.Transactional;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class MemoRepositoryTest {

    @Autowired
    private MemoMapper memoMapper;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    @DisplayName("insert memo test")
    void insertTest() {
        MemoDTO memo = new MemoDTO();
        memo.setMemberId(1);
        memo.setMemo("insert 테스트 입니다.");

        memoMapper.insertMemo(memo);

        assertNotNull(memo.getId());

        MemoDTO.Search search = new MemoDTO.Search();
        search.setId(memo.getId());
        List<MemoDTO> memoList = memoMapper.selectMemo(search);

        assertEquals(memo.getMemo(), memoList.get(0).getMemo());
    }

    @Test
    @DisplayName("search condition is activate")
    void searchConditionTest() {
        MemoDTO memo = new MemoDTO();
        memo.setMemberId(1);
        memo.setMemo("insert 테스트 입니다.");

        memoMapper.insertMemo(memo);

        assertNotNull(memo.getId());

        MemoDTO.Search search = new MemoDTO.Search();
        search.setMemo("테스트");

        List<MemoDTO> list = memoMapper.selectMemo(search);

        list.forEach(dto -> assertThat(dto.getMemo()).contains("테스트"));
    }

    @Test
    @DisplayName("multi row insert")
    @Transactional
    void batchInsertTest() {
        printMemoryUsage("리스트 생성 이전");

        final int CHUNK_SIZE = 10_000;
        List<MemoDTO> list = this.getMemoList(CHUNK_SIZE);
        printMemoryUsage("리스트 생성 직후");

        long start = System.currentTimeMillis();

        // insert 이전에 1번 메모 삭제. Throwable 시 Transactional 정상 작동 확인용.
        memoMapper.deleteMemo(1);


        try(SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            MemoMapper mapper = sqlSession.getMapper(MemoMapper.class);

            // 리스트 100만 개. batch insert 시 OOM 발생 재현.
//            mapper.insertMemoBatch(list);

            // subList 생성 방식.
            for (int i = 0; i < list.size() / CHUNK_SIZE; i++) {
                int startIndex = i * CHUNK_SIZE;
                int endIndex = (i + 1) * CHUNK_SIZE;

                mapper.insertMemoBatch(list.subList(startIndex, endIndex));
//                if (i == 5) {
//                    throw new RuntimeException("연산 중간에 오류남.");
//                }

                /*
                0. chunk size 500개 기준. 1000개 이상은 too larget packet 발생.
                객체에 저장된 정보의 크기가 늘어날 수록 chunk size를 적게 잡아야 함.

                1. flushing 이나 clear cache 둘 중 하나만 안해줘도 OOME?
                flushing 을 안하면 old gen 쌓이다가 OOME 발생. chunk size 랑 무관하게 쌓인다.

                2. clearCache 를 안했을 때.
                old gen : 대략 80 ~ 170 MB 범위.
                수행시간 : 152 초.

                3. flushing + clear cache.
                old gen : 80 ~ 170 MB 범위. clear cache 의 on/off 로 차이 없음.
                수행시간 : 153 초.

                clear cache 는 캐시상에 다른 곳에서 사용하고 있는 많은 데이터가 남아 있을 떄 사용하면 좋다.
                flushStatements 만으로도 캐시에서 메모리 해제를 수행한다.
                */
                sqlSession.flushStatements();
//                sqlSession.clearCache();
                printMemoryUsage(CHUNK_SIZE + " 개씩 insert : " + (i + 1) + " 번째.");

            }

            // map 객체를 사용.
//            AtomicInteger i = new AtomicInteger(0);
//            Map<Integer, List<MemoDTO>> grouping = list.stream().collect(
//                    Collectors.groupingBy(m -> i.getAndIncrement() / CHUNK_SIZE)
//            );
//
//            for (List<MemoDTO> groupedList : grouping.values()) {
//                mapper.insertMemoBatch(groupedList);
//                sqlSession.flushStatements();
//                sqlSession.clearCache();
//                printMemoryUsage("5만개씩 insert");
//            }

            sqlSession.commit();
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        long end = System.currentTimeMillis();
        long duration = (end - start) / 1000;
        printMemoryUsage(CHUNK_SIZE + " 개씩 insert 직후. 총 소요 시간 : " + duration + " 초 소요.");
    }

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Test
    @Transactional
    @DisplayName("jdbc template bulk insert")
    void jdbc_template_insert() {
        printMemoryUsage("리스트 생성 이전");

        final int listSize = 1_000_000;
        List<MemoDTO> memoList = this.getMemoList(listSize);
        final String sql = "INSERT INTO memo (member_id, memo, category) VALUES (:memberId, :memo, :category)";
        printMemoryUsage("리스트 생성 직후");

        final int CHUNK_SIZE = 1_000;
        long start = System.currentTimeMillis() / 1000;
        for (int i = 0, j = 1; i < memoList.size(); i += CHUNK_SIZE, j++) {
            List<MemoDTO> chunkList = memoList.subList(i, Math.min(memoList.size(), i + CHUNK_SIZE));

            SqlParameterSource[] batchArgs = SqlParameterSourceUtils.createBatch(chunkList.toArray());
            try {
                namedParameterJdbcTemplate.batchUpdate(sql, batchArgs);
            } catch (Throwable e) {
                e.printStackTrace();
            }
            printMemoryUsage("jdbc template batch insert 시행횟수 : " + j);
        }
        long end = System.currentTimeMillis() / 1000;
        System.out.println("insert 총 소요 시간 : " + (end - start) + " 초");

        // 100만건 한 번에 insert.
//        List<SqlParameterSource> batchArgs = new ArrayList<>();
//        for (MemoDTO memo : memoList) {
//            SqlParameterSource namedParameters = new MapSqlParameterSource()
//                    .addValue("memberId", memo.getMemberId())
//                    .addValue("memo", memo.getMemo())
//                    .addValue("category", memo.getCategory());
//
//            batchArgs.add(namedParameters);
//        }
//
//        try {
//            namedParameterJdbcTemplate.batchUpdate(sql, batchArgs.toArray(new SqlParameterSource[0]));
//        } catch(Throwable e) {
//            e.printStackTrace();
//        }
    }

    private List<MemoDTO> getMemoList(final int CHUNK_SIZE) {
        List<MemoDTO> list = new ArrayList<>();

        for (int i = 0; i < CHUNK_SIZE; i++) {
            MemoDTO memo = new MemoDTO();
            memo.setMemberId(1);
            memo.setMemo("메모 테스트 입니다!메모 테스트 입니다!메모 테스트 입니다!");

            list.add(memo);
        }

        return list;
    }

    private void printMemoryUsage(String msg) {
        Runtime runtime = Runtime.getRuntime();
        NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
        double totalMemory = runtime.totalMemory() / (1024.0 * 1024.0); // 전체 메모리 (메가)
        double freeMemory = runtime.freeMemory() / (1024.0 * 1024.0); // 사용 가능한 메모리 (메가)
        double usedMemory = totalMemory - freeMemory; // 사용 중인 메모리 (메가)

        System.out.println(msg);
        System.out.println("Total Memory: " + format.format(totalMemory) + " MB");
        System.out.println("Free Memory: " + format.format(freeMemory) + " MB");
        System.out.println("Used Memory: " + format.format(usedMemory) + " MB");

        /*
        - JVM Name: Dynamic Code Evolution 64-Bit Server VM
        - JVM Version: 25.71-b01-dcevmlight-26
        - Java Version: 1.8.0_181
        */
        for (MemoryPoolMXBean pool : ManagementFactory.getMemoryPoolMXBeans()) {
            String poolName = pool.getName();
            MemoryUsage usage = pool.getUsage();
            long usedBytes = usage.getUsed();
            double usedMB = usedBytes / (1024.0 * 1024.0);

            if (poolName.contains("Eden Space")) {
                System.out.printf("Eden Space: %.2f MB%n", usedMB);
            } else if (poolName.contains("Survivor Space") || poolName.contains("S0") || poolName.contains("S1")) {
                System.out.printf("Survivor Space: %.2f MB%n", usedMB);
            } else if (poolName.contains("Tenured Gen") || poolName.contains("Old Gen")) {
                System.out.printf("Old Gen: %.2f MB%n", usedMB);
            }
        }
        System.out.println("---------------------------------------------------");
    }

    public static void main(String[] args) {
        String jvmName = System.getProperty("java.vm.name"); // JVM 이름
        String jvmVersion = System.getProperty("java.vm.version"); // JVM 버전
        String javaVersion = System.getProperty("java.version"); // Java 버전

        System.out.println("JVM Name: " + jvmName);
        System.out.println("JVM Version: " + jvmVersion);
        System.out.println("Java Version: " + javaVersion);
    }

}