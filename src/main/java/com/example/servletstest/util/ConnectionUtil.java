package com.example.servletstest.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Helper class with common logic related to Database
 */
public class ConnectionUtil {
    private static final String USER = "root";
    static final String PASSWORD="root";
    static final String URL="jdbc:mysql://localhost:3306/final_project";
    private static ConnectionUtil instance;

    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource dataSource;

    private ConnectionUtil() {
        // util class has only static methods and should not have constructors
    }

    /**
     * Create connection util if not exists and returns it.
     *
     * @return connection util
     */
    public static synchronized ConnectionUtil getInstance() {
        if (instance == null) {
            instance=new ConnectionUtil();
        }
        return instance;
    }

    static {
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);

//        config.setDriverClassName(org.mysql.Driver.class.getName());
        config.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        config.setConnectionTimeout(15000); //ms
        config.setIdleTimeout(60000); //ms
        config.setMaxLifetime(600000);//ms
        config.setAutoCommit(false);
        config.setMinimumIdle(5);
        config.setMaximumPoolSize(10);
        config.setPoolName("serviceDbPool");
        config.setRegisterMbeans(true);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");


        dataSource = new HikariDataSource(config);
    }

    public DataSource getHikariDataSource() {
        return dataSource;
    }
}
