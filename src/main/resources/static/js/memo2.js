window.onload = () => {
    document.querySelector('#memo-ta').focus();

    // const HOST_ADDRESS = "http://localhost:8080";
    const HOST_ADDRESS = "http://220.76.63.77:8080";
    const $memoTextarea = document.querySelector('#memo-ta');

    document.querySelector('#submit-memo').addEventListener('click', registerMemo);
    // 이벤트 위임. 클래스에 이벤트를 바인딩 하려면, 상위요소에 리스너를 걸고. event 객체에서 눌려진 요소를 찾아서 작업.
    document.querySelector('#memo-output').addEventListener('click', deleteMemo);

    selectMemo();

    function outputMemo(memo, outType, memoId) {
        if (outType === 'register') {
            memo = convertEnterToDiv($memoTextarea.value);
        }
        document.querySelector('#memo-output').insertAdjacentHTML('afterbegin', '<div class="memo-item"><div '
        + 'class="memo-contents">' + memo + '</div><div class="memo-del-div"><button id="memo-num-' 
        + memoId + '" class="memo-del-btn">삭제</button></div></div>');

        $memoTextarea.value = '';
        $memoTextarea.focus();
    }

    function convertEnterToDiv(memo) {
        memo = '<div>' + memo.replaceAll('\n', '</div><div>&#8302;') + '</div>';
        return memo;
    }

    function registerMemo() {
        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === xhr.DONE) {
                if (xhr.status === 200 || xhr.status === 201) {
                    const registeredMemoId = xhr.responseText;
                    outputMemo(null, 'register', registeredMemoId);
                }
                else {
                    console.error(xhr.responseText);
                }
            }
        }
        xhr.open('POST', HOST_ADDRESS + '/memo/register');
        xhr.setRequestHeader('Content-Type', 'application/json');
        let memo = $memoTextarea.value;
        memo = convertEnterToDiv(memo);
        xhr.send(jsonParser(memo));
    }

    function jsonParser(memo) {
        let parsedJson = '{ "writerId" : 1,' +
            '"memo": "' + memo + '"}';
        return parsedJson;
    }

    function selectMemo() {
        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === xhr.DONE) {
                if (xhr.status === 200 || xhr.status === 201) {
                    let allMemo = xhr.response;
                    showAllMemo(allMemo);
                }
                else {
                    console.error(xhr.response);
                }
            }
        }
        xhr.open('POST', HOST_ADDRESS + '/memo/search');
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.responseType = 'json';
        let memo = $memoTextarea.value;
        xhr.send(jsonParser(memo));
    }

    function showAllMemo(memoList) {
        for(let i = 0; i < memoList.length; i++) {
            outputMemo(memoList[i]["memo"], null, memoList[i]["id"]);
        }
    }

    function deleteMemo() {
        console.log("event target : " + event.target.get);
        if (event.target.getAttribute('class') !== 'memo-del-btn') {
            return;
        }
        const memoId = event.target.getAttribute('id').replace('memo-num-','');

        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === xhr.DONE) {
                if (xhr.status === 200 || xhr.status === 201) {
                    deleteMemoFromDOM(memoId);
                }
                else {
                    console.error(xhr.response);
                }
            }
        }
        xhr.open('DELETE', HOST_ADDRESS + '/memo/delete/' + memoId);
        // xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send();
    }

    function deleteMemoFromDOM(memoId) {
        // DOM에서 memo id에 해당하는 요소를 기준으로 첫번째 .memo-item 요소를 가져온다.
        var $memoItemElem = document.querySelector('#memo-num-' + memoId).closest('.memo-item').remove();
    }

}
