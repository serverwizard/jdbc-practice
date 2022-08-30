package org.example;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:mem://localhost/~/jdbc-practice;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PW = "";
    private static final int MAX_POOL_SIZE = 40;

    private static final DataSource ds;

    static {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(DB_DRIVER);
        hikariDataSource.setJdbcUrl(DB_URL);
        hikariDataSource.setUsername(DB_USERNAME);
        hikariDataSource.setPassword(DB_PW);
        hikariDataSource.setMaximumPoolSize(MAX_POOL_SIZE);
        hikariDataSource.setMinimumIdle(MAX_POOL_SIZE);

        ds = hikariDataSource;
    }

    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static DataSource getDataSource() {
        return ds;
    }
}