package com.zetta.memo.configuration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DatabaseConnectTest {

    @Test
    public void databaseConnectionTest() throws ClassNotFoundException {

        Class.forName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
//        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection("jdbc:log4jdbc:mysql:220.76.63.77:3306/map?" +
                        "autoReconnect=true","root", "1234")) {
            System.out.println("connection is : " + conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
