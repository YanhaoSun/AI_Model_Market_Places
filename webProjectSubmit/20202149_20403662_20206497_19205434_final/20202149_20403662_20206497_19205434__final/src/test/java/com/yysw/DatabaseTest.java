package com.yysw;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class DatabaseTest {
    @Test
    void contextLoads() {
        System.out.println("project running");
    }
    @Autowired
    private DataSource dataSrc;

    @Test
    void dataConnectCheck() throws SQLException {
        System.out.println(dataSrc.getConnection());
    }
}
