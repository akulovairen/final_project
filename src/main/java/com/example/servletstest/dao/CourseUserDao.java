package com.example.servletstest.dao;

import com.example.servletstest.util.sessionManager.SessionManager;
import com.example.servletstest.model.CourseUser;
import com.example.servletstest.exception.serviceException.courseUser.CourseUserNotCountException;
import com.example.servletstest.exception.serviceException.courseUser.CourseUserNotCreatedException;
import com.example.servletstest.exception.serviceException.courseUser.CourseUserNotUpdatedException;
import com.example.servletstest.util.ConnectionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class CourseUserDao {
    private ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
    private SessionManager sessionManager = new SessionManager(connectionUtil.getHikariDataSource());
    private Logger log = LogManager.getLogger(CourseUserDao.class);

    public CourseUserDao() {
    }

    public int createCourseUser(CourseUser courseUser) {
        log.info("Creating course_user ");
        Connection connection = sessionManager.beginSession();
        try (PreparedStatement pst = connection.prepareStatement(SQLTask.CREATE_USER_COURSE.QUERY, Statement.RETURN_GENERATED_KEYS)) {

            pst.setInt(1, courseUser.getCourse().getId());
            pst.setInt(2, courseUser.getUser().getId());
            pst.setString(3, courseUser.getCourseStatus());

            pst.executeUpdate();
            try (ResultSet rs = pst.getGeneratedKeys()) {
                rs.next();
                int id = rs.getInt(1);
                sessionManager.commitSession();
                return id;
            }
        } catch (SQLException ex) {
            log.error("Cannot create course_user");
            sessionManager.rollbackSession();
            throw new CourseUserNotCreatedException("Cannot create course_user", ex);
        } finally {
            sessionManager.close();
        }
    }

    public CourseUser updateCourseUserByStatus(CourseUser courseUser) {
        log.info("Getting update status courseUser");
        Connection connection = sessionManager.beginSession();
        try (PreparedStatement pst = connection.prepareStatement(SQLTask.UPDATE_USER_COURSE_BY_STATUS.QUERY)) {
            pst.setInt(1, courseUser.getId());
            pst.setInt(2, courseUser.getUser().getId());
            pst.setInt(3, courseUser.getCourse().getId());
            pst.setString(4, courseUser.getCourseStatus());
            pst.executeUpdate();
            sessionManager.commitSession();
            return courseUser;
        } catch (SQLException ex) {
            log.error("Cannot update status courseUser");
            sessionManager.rollbackSession();
            throw new CourseUserNotUpdatedException("Cannot update status courseUser", ex);
        }finally {
            sessionManager.close();
        }
    }

    public void setCompletedStatusByCourseId(int courseId) throws SQLException {
        sessionManager.beginSession();
        try (Connection connection = sessionManager.getCurrentSession();
             PreparedStatement pst = connection.prepareStatement(SQLTask.UPDATE_USER_COURSE_BY_STATUS.QUERY)) {

            pst.setInt(1, courseId);

            pst.executeUpdate();
            sessionManager.commitSession();

        } catch (SQLException ex) {
            sessionManager.rollbackSession();
            throw ex;
        }
    }

    public int countStudent(int courseId) {
        log.info("Getting count of student in DB");
        int count = 0;
        Connection connection = sessionManager.beginSession();

        try (PreparedStatement pst = connection.prepareStatement(SQLTask.COUNT_STUDENT.QUERY)) {
            pst.setInt(1, courseId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("counter");
                }
            }
            return count;
        } catch (SQLException ex) {
            log.error("Cannot count student from DB");
            throw new CourseUserNotCountException("Cannot count student", ex);
        }finally {
            sessionManager.close();
        }
    }

    enum SQLTask {
        UPDATE_USER_COURSE_STATUS_BY_COURSE_ID("UPDATE course_user SET course_status='completed' WHERE course_id=?"),
        CREATE_USER_COURSE("INSERT INTO course_user (course_id, user_id, course_status)" +
                " VALUES (?,?,?) "),
        UPDATE_USER_COURSE_BY_STATUS("UPDATE course_user SET course_status=? WHERE course_id=? AND user_id=?"),
        COUNT_STUDENT("SELECT count(*) AS counter FROM course_user WHERE course_id=?");
        String QUERY;

        SQLTask(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
