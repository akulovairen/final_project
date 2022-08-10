package com.example.servletstest.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ConnectionUtil {
    private static final String USER = "root";
    static final String PASSWORD="root";
    static final String URL="jdbc:mysql://localhost:3306/final_project";
    static final String DATABASE_DRIVER="jdbc:mysql://localhost:3306/final_project";
    private static ConnectionUtil instance;

    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource dataSource;

    private ConnectionUtil() {
    }

    public static synchronized ConnectionUtil getInstance() {
        if (instance == null) {
            instance=new ConnectionUtil();
        }
        return instance;
    }

    static {
        config.setJdbcUrl("jdbc:h2:mem:myDb;DB_CLOSE_DELAY=-1");
        config.setUsername("sa");
        config.setPassword("sa");
        config.setDriverClassName("org.h2.Driver");
//        config.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
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

        try {
            createDbStructure();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public java.sql.Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void createDbStructure() throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE topics (id int PRIMARY KEY, name VARCHAR(30)); ");
        sql.append("CREATE TABLE courses (id int PRIMARY KEY, name VARCHAR(30)); ");
        sql.append("CREATE TABLE users (id int PRIMARY KEY, name VARCHAR(30), surname VARCHAR(30), email VARCHAR(30)); ");


        String s = "create table users " +
                "(id bigint AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(40) NOT NULL, " +
                "surname VARCHAR(60) NOT NULL, " +
                "birthday DATE NOT NULL, " +
                "email VARCHAR(61) UNIQUE, " +
                "password VARCHAR(100), " +
                "role VARCHAR(20), " +
                "locked boolean, " +
                "CHECK(role IN ('admin','teacher','student'))); " +
                "create table topics " +
                "( " +
                "id bigint AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(65) NOT NULL " +
                "); " +
                "create table courses " +
                "(id bigint AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(65) NOT NULL, " +
                "date_start DATE NOT NULL, " +
                "duration LONG, " +
                "description VARCHAR(250), " +
                "status VARCHAR(40), " +
                "CHECK(status IN('available','in progress','registered','completed')), " +
                "topic_id BIGINT, " +
                "teacher_id BIGINT, " +
                "FOREIGN KEY (topic_id) REFERENCES topics(id)  ON DELETE CASCADE, " +
                "FOREIGN KEY (teacher_id) REFERENCES users(id)  ON DELETE CASCADE " +
                "); " +
                "create table course_user " +
                "(id bigint AUTO_INCREMENT PRIMARY KEY, " +
                "course_id bigint, " +
                "user_id bigint, " +
                "course_status varchar(30), " +
                "FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE, " +
                "FOREIGN KEY (user_id) REFERENCES users(id)  ON DELETE CASCADE " +
                "); " +
                "create table gradebook " +
                "(id bigint AUTO_INCREMENT PRIMARY KEY, " +
                "student_id bigint, " +
                "test1 int, " +
                "test2 int, " +
                "test3 int, " +
                "test4 int, " +
                "totalScore double, " +
                "course_id bigint, " +
                "FOREIGN KEY (student_id) REFERENCES users(id) " +
                "ON delete cascade, " +
                "FOREIGN KEY (course_id) REFERENCES courses(id) " +
                "ON delete cascade);"+
                "" +
                " INSERT INTO topics (name) VALUES ('Topic 1');" +
                " INSERT INTO users (name, surname, birthday, email, password, role, locked) " +
                "VALUES ('Ira', 'Akulova', '1997-03-17', 'akulova@gmail.com', 'qwerty', 'teacher', false);"+
                " INSERT INTO courses (name, date_start, duration, description, status, topic_id, teacher_id) " +
                "VALUES ('course', '2030-03-01', '5', 'Про курси', 'available', '1', '1');";

        dataSource.getConnection().prepareStatement(s)
                .executeUpdate();
    }

    public DataSource getHikariDataSource() {
        return dataSource;
    }

}
