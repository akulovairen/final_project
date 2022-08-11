package com.example.servletstest.dao;

import com.example.servletstest.util.sessionManager.SessionManager;
import com.example.servletstest.model.Course;
import com.example.servletstest.model.Topic;
import com.example.servletstest.model.User;
import com.example.servletstest.exception.serviceException.course.CourseNotCreatedException;
import com.example.servletstest.exception.serviceException.course.CourseNotDeletedException;
import com.example.servletstest.exception.serviceException.course.CourseNotFoundException;
import com.example.servletstest.exception.serviceException.course.CourseNotUpdatedException;
import com.example.servletstest.exception.serviceException.user.UserNotFoundException;
import com.example.servletstest.util.ConnectionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Data access object for working with course models.
 */
public class CourseDao {
    private ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
    private SessionManager sessionManager = new SessionManager(connectionUtil.getHikariDataSource());
    private CourseUserDao courseUserDao = new CourseUserDao();
    private Logger log = LogManager.getLogger(CourseDao.class);

    public CourseDao() {
    }

    public int getNumberOfRows(String sql, String status) {
        log.info("Getting count of users/teachers in DB");
        int count = 0;
        Connection connection = sessionManager.beginSession();

        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, status);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("counter");
                }
            }
        } catch (SQLException ex) {
            log.error("Cannot get user by id from DB");
            throw new UserNotFoundException("Cannot get user by id from DB", ex);
        } finally {
            sessionManager.close();
        }
        return count;
    }

    public int getNumberOfRowsTeacherStatus(String sql, int teacherId, String status) {
        log.info("Getting count courses with teacher_id,status in DB");
        int count = 0;
        Connection connection = sessionManager.beginSession();

        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, teacherId);
            pst.setString(2, status);
            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {
                    count = rs.getInt("counter");
                }
            }
        } catch (SQLException ex) {
            log.error("Cannot get user by id from DB");
            throw new UserNotFoundException("Cannot get user by id from DB", ex);
        } finally {
            sessionManager.close();
        }
        return count;
    }

    public int getNumberOfRowsTeacher(String sql, int teacherId) {
        log.info("Getting count of users/teachers in DB");
        int count = 0;
        Connection connection = sessionManager.beginSession();

        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, teacherId);
            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {
                    count = rs.getInt("counter");
                }
            }
        } catch (SQLException ex) {
            log.error("Cannot get user by id from DB");
            throw new UserNotFoundException("Cannot get user by id from DB", ex);
        } finally {
            sessionManager.close();
        }
        return count;
    }

    public int getNumberOfRowsAdminCourseTopicStatus(String sql, int topicId, String status) {
        log.info("Getting count courses for admin with topic_id, status in DB");
        int count = 0;
        Connection connection = sessionManager.beginSession();

        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, topicId);
            pst.setString(2, status);
            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {
                    count = rs.getInt("counter");
                }
            }
        } catch (SQLException ex) {
            log.error("Cannot get user by id from DB");
            throw new UserNotFoundException("Cannot get user by id from DB", ex);
        } finally {
            sessionManager.close();
        }
        return count;
    }

    /**
     * Creates course record in DB.
     *
     * @param course - {@link Course} model
     * @throws CourseNotCreatedException - if the course is not created
     * @return course id
     */
    public int createCourse(Course course) {
        log.info("Creating course");
        Connection connection = sessionManager.beginSession();
        try (PreparedStatement pst = connection.prepareStatement(SQLTask.INSERT_COURSE.QUERY, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, course.getName());
            pst.setDate(2, Date.valueOf(course.getDateStart()));
            pst.setInt(3, course.getDuration());
            pst.setString(4, course.getDescription());
            pst.setInt(5, course.getTopic().getId());
            pst.setInt(6, course.getTeacher().getId());
            pst.setString(7, course.getStatus());

            pst.executeUpdate();
            sessionManager.commitSession();
            try (ResultSet rs = pst.getGeneratedKeys()) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            log.error("Cannot create course in DB");
            sessionManager.rollbackSession();
            throw new CourseNotCreatedException("Cannot create course in DB", ex);
        } finally {
            sessionManager.close();
        }
    }

    /**
     * Finds course by id in DB.
     *
     * @param id - course id
     * @throws CourseNotFoundException - if the course is not found
     * @return the course object wrapped in an {@link Optional}
     */
    public Optional<Course> findCourse(int id) {
        log.info("Getting course from DB");
        Optional<Course> optionalCourse = Optional.empty();
        Connection connection = sessionManager.beginSession();

        try (PreparedStatement pst = connection.prepareStatement(SQLTask.GET_COURSE.QUERY)) {
            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {
                    Course dbCourse = createCourseFromResultSet(rs);
                    String status = rs.getString("status");
                    dbCourse.setStatus(status);
                    String userSurname = rs.getString("user_surname");
                    dbCourse.getTeacher().setSurname(userSurname);
                    String userName = rs.getString("user_name");
                    dbCourse.getTeacher().setName(userName);
                    optionalCourse = Optional.of(dbCourse);
                }
                return optionalCourse;
            }
        } catch (SQLException ex) {
            log.error("Cannot get course from DB");
            throw new CourseNotFoundException("Cannot get course from DB", ex);
        } finally {
            sessionManager.close();
        }
    }

    /**
     * Finds courses by id, status in DB.
     *
     * @param id - course id
     * @param status - course status
     * @throws CourseNotFoundException - if the course is not found
     * @return list of courses
     */
    public List<Course> findCourseByTeacher(int id, String status) {
        log.info("Getting courses for teacher from DB");
        List<Course> courseForTeacher = new ArrayList<>();
        Connection connection = sessionManager.beginSession();

        try (PreparedStatement pst = connection.prepareStatement(SQLTask.GET_COURSE_BY_TEACHER_STATUS.QUERY)) {
            pst.setInt(1, id);
            pst.setString(2, status);

            try (ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {
                    Course dbCourse = createCourseFromResultSet(rs);
                    courseForTeacher.add(dbCourse);
                }
            }
            return courseForTeacher;
        } catch (SQLException ex) {
            log.error("Cannot get courses for teacher from DB");
            throw new CourseNotFoundException("Cannot get courses for teacher from DB", ex);
        } finally {
            sessionManager.close();
        }
    }

    /**
     * Finds courses by id in DB.
     *
     * @param id - course id
     * @throws CourseNotFoundException - if the course is not found
     * @return list of courses
     */
    public List<Course> findCourseTeacher(int id, int limit, int offset, String sortingColumn, String sortingMode) {
        log.info("Getting courses for teacher from DB");
        List<Course> courseForTeacher = new ArrayList<>();
        Connection connection = sessionManager.beginSession();

        String query = String.format(SQLTask.GET_COURSE_BY_TEACHER.QUERY , " ORDER BY " + sortingColumn + " " + sortingMode);
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, id);
            pst.setInt(2, limit);
            pst.setInt(3, offset);

            try (ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {
                    Course dbCourse = createCourseFromResultSet(rs);
                    String status = rs.getString("status");
                    dbCourse.setStatus(status);
                    courseForTeacher.add(dbCourse);
                }
            }
            return courseForTeacher;
        } catch (SQLException ex) {
            log.error("Cannot get courses for teacher from DB");
            throw new CourseNotFoundException("Cannot get courses for teacher from DB", ex);
        } finally {
            sessionManager.close();
        }
    }

//    public List<Course> findAllCourse() throws SQLException {
//        List<Course> courseList = new ArrayList<>();
//        sessionManager.beginSession();
//
//        try (Connection connection=sessionManager.getCurrentSession();
//             PreparedStatement pst=connection.prepareStatement(SQLTask.GET_ALL_COURSES.QUERY)){
//
//            try (ResultSet rs = pst.executeQuery()){
//                while (rs.next()) {
//                    Course course = createCourseFromResultSet(rs);
//                    courseList.add(course);
//                }
//            } catch (SQLException ex) {
//                throw ex;
//            }
//            return courseList;
//        }
//    }

    /**
     * Deletes course by course id in DB.
     *
     * @param courseId - course id
     * @throws CourseNotDeletedException - if the course is not delete
     * @return boolean
     */
    public boolean deleteCourse(int courseId) {
        log.info("Deleting course in DB");
        int delete_rows;
        Connection connection = sessionManager.beginSession();
        try (PreparedStatement pst = connection.prepareStatement(SQLTask.DELETE_COURSE.QUERY)) {
            pst.setInt(1, courseId);

            delete_rows = pst.executeUpdate();
            sessionManager.commitSession();
            return delete_rows > 0;
        } catch (SQLException ex) {
            log.error("Cannot delete course from DB");
            sessionManager.rollbackSession();
            throw new CourseNotDeletedException("Cannot delete course in DB", ex);
        } finally {
            sessionManager.close();
        }
    }

    /**
     * Updates course in DB.
     *
     * @param course - {@link Course} model
     * @throws CourseNotUpdatedException - if the course not update
     */
    public void updateCourse(Course course) {
        log.info("Updating course in DB");
        Connection connection = sessionManager.beginSession();
        try (PreparedStatement pst = connection.prepareStatement(SQLTask.UPDATE_COURSE.QUERY)) {

            pst.setString(1, course.getName());
            pst.setDate(2, Date.valueOf(course.getDateStart()));
            pst.setInt(3, course.getDuration());
            pst.setString(4, course.getDescription());
            pst.setInt(5, course.getTopic().getId());
            pst.setInt(6, course.getTeacher().getId());
            pst.setInt(7, course.getId());
            pst.executeUpdate();
            sessionManager.commitSession();

        } catch (SQLException ex) {
            log.error("Cannot update course in DB");
            sessionManager.rollbackSession();
            throw new CourseNotUpdatedException("Cannot update course in DB", ex);
        } finally {
            sessionManager.close();
        }
    }

    /**
     * Updates status course by course id in DB.
     *
     * @param status - course status
     * @param courseId - course id
     * @throws CourseNotUpdatedException - if not update status course in DB
     */
    public void updateStatusCourse(String status, int courseId) {
        log.info("Updating course by status={} and course_id={}", status, courseId);
        Connection connection = sessionManager.beginSession();
        try (PreparedStatement pst = connection.prepareStatement(SQLTask.UPDATE_STATUS_COURSE.QUERY)) {

            pst.setString(1, status);
            pst.setInt(2, courseId);
            pst.executeUpdate();
            sessionManager.commitSession();

        } catch (SQLException ex) {
            log.error("Cannot update status course in DB");
            sessionManager.rollbackSession();
            throw new CourseNotUpdatedException("Cannot update status course in DB", ex);
        } finally {
            sessionManager.close();
        }
    }

    /**
     * Finds courses with available status by user id, topic id in DB.
     *
     * @param topicId - topic id
     * @param userId - user id
     * @throws CourseNotFoundException - if the course is not found
     * @return list of courses
     */
    public List<Course> findByStatusAvailableByTopic(int topicId, int userId) {
        log.info("Getting courses with status available by userId={} and topicId={}", userId, topicId);
        List<Course> courseList = new ArrayList<>();

        Connection connection = sessionManager.beginSession();

        try (PreparedStatement pst = connection.prepareStatement(SQLTask.FIND_AVAILABLE_COURSES_BY_TOPIC.QUERY)) {
            pst.setInt(1, topicId);
            pst.setInt(2, userId);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Course course = createCourseFromResultSet(rs);
                    String userName = rs.getString("user_name");
                    course.getTeacher().setName(userName);
                    String userSurname = rs.getString("user_surname");
                    course.getTeacher().setSurname(userSurname);
                    courseList.add(course);
                }
//            } catch (SQLException ex) {
//                log.error("Cannot get courses with status available");
//                throw new CourseNotFoundException("Cannot get courses with status available", ex);
            }
            return courseList;
        } catch (SQLException e) {
            log.error("Cannot get course with status available from DB");
            throw new CourseNotFoundException("Cannot get course with status available from DB", e);
        } finally {
            sessionManager.close();
        }
    }
    /**
     * Finds courses with available status by user id in DB.
     *
     * @param userId - user id
     * @throws CourseNotFoundException - if the course is not found
     * @return list of courses
     */
    public List<Course> findByStatusAvailable(int userId) {
        log.info("Getting courses with status available by userId={}", userId);
        List<Course> courseList = new ArrayList<>();
        Connection connection = sessionManager.beginSession();

        try (PreparedStatement pst = connection.prepareStatement(SQLTask.FIND_AVAILABLE_COURSES.QUERY)) {
            pst.setInt(1, userId);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Course course = createCourseFromResultSet(rs);
                    String userName = rs.getString("user_name");
                    course.getTeacher().setName(userName);
                    String userSurname = rs.getString("user_surname");
                    course.getTeacher().setSurname(userSurname);
                    courseList.add(course);
                }
            }
            return courseList;
        } catch (SQLException e) {
            log.error("Cannot get course with status available from DB");
            throw new CourseNotFoundException("Cannot get course with status available from DB", e);
        } finally {
            sessionManager.close();
        }
    }

    /**
     * Finds courses by status in DB.
     *
     * @param status - course status
     * @throws CourseNotFoundException - if the course is not found
     * @return
     */
    public List<Course> findCourseStatus(String status, int limit, int offset, String sortingColumn, String sortingMode) {
        log.info("Getting courses by status={}", status);
        List<Course> courseList = new ArrayList<>();
        Connection connection = sessionManager.beginSession();

        String query = String.format(SQLTask.FIND_COURSE_STATUS.QUERY, " ORDER BY " + sortingColumn + " " + sortingMode);
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, status);
            pst.setInt(2, limit);
            pst.setInt(3, offset);
//            pst.setString(2, sortingColumn);
//            pst.setString(3, sortingMode);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Course course = createCourseFromResultSet(rs);
                    String userName = rs.getString("user_name");
                    course.getTeacher().setName(userName);
                    String userSurname = rs.getString("user_surname");
                    course.getTeacher().setSurname(userSurname);
                    courseList.add(course);
                }
            }
            return courseList;
        } catch (SQLException e) {
            log.error("Cannot get courses by status from DB");
            throw new CourseNotFoundException("Cannot get courses by status from DB", e);
        } finally {
            sessionManager.close();
        }
    }

    /**
     * Finds courses by topic id and status in DB.
     *
     * @param topicId - topic id
     * @param status - course status
     * @throws CourseNotFoundException - if the course is not found
     * @return list of courses
     */
    public List<Course> findCoursesByTopicAndAvailable(int topicId, String status,int limit, int offset, String sortingColumn, String sortingMode) {
        log.info("Getting courses by topic={} and status", topicId);
        List<Course> courseList = new ArrayList<>();
        Connection connection = sessionManager.beginSession();

        String query = String.format(SQLTask.GET_ALL_COURSES_BY_TOPIC_AND_STATUS.QUERY, " ORDER BY " + sortingColumn + " " + sortingMode);
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, topicId);
            pst.setString(2, status);
            pst.setInt(3, limit);
            pst.setInt(4, offset);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Course course = createCourseFromResultSet(rs);
                    String userName = rs.getString("user_name");
                    course.getTeacher().setName(userName);
                    String userSurname = rs.getString("user_surname");
                    course.getTeacher().setSurname(userSurname);
                    courseList.add(course);
                }
            }
            return courseList;
        } catch (SQLException e) {
            log.error("Cannot get courses by topic and status from DB");
            throw new CourseNotFoundException("Cannot get courses by topic and status from DB", e);
        } finally {
            sessionManager.close();
        }
    }

    /**
     * Finds courses by topic  in DB.
     *
     * @param topicId - topic id
     * @throws CourseNotFoundException - if the course not found
     * @return list of course
     */
    public List<Course> findCourseByTopic(int topicId) {
        log.info("Getting courses by topic={}", topicId);
        List<Course> courseList = new ArrayList<>();
        Connection connection = sessionManager.beginSession();

        try (PreparedStatement pst = connection.prepareStatement(SQLTask.GET_COURSE_BY_TOPIC.QUERY)) {
            pst.setInt(1, topicId);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Course course = createCourseFromResultSet(rs);
                    String userName = rs.getString("user_name");
                    course.getTeacher().setName(userName);
                    String userSurname = rs.getString("user_surname");
                    course.getTeacher().setSurname(userSurname);
                    courseList.add(course);
                }
            }
            return courseList;
        } catch (SQLException e) {
            log.error("Cannot get courses by topic from DB");
            throw new CourseNotFoundException("Cannot get courses by topic from DB", e);
        } finally {
            sessionManager.close();
        }
    }

    /**
     * Finds courses for guests in DB.
     *
     * @throws UserNotFoundException - if the course is not found
     * @return list of courses
     */
    public List<Course> findCourseForGuest() {
        log.info("Getting courses by status='available' for guest");
        List<Course> courseList = new ArrayList<>();
        Connection connection = sessionManager.beginSession();

        try (PreparedStatement pst = connection.prepareStatement(SQLTask.GET_COURSES_AVAILABLE_GUEST.QUERY)) {

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Course course = createCourseFromResultSet(rs);
                    String userName = rs.getString("user_name");
                    course.getTeacher().setName(userName);
                    String userSurname = rs.getString("user_surname");
                    course.getTeacher().setSurname(userSurname);
                    courseList.add(course);
                }
            }
            return courseList;
        } catch (SQLException e) {
            log.error("Cannot get courses by status='available' for guest from DB");
            throw new CourseNotFoundException(e);
        } finally {
            sessionManager.close();
        }
    }

    /**
     * Finds course with status not finished by teacher id in DB.
     *
     * @param teacherId - teacher id
     * @param limit
     * @param offset
     * @throws UserNotFoundException - if the course is not found
     * @return list of courses
     */
    public List<Course> findCourseNotFinished(int teacherId, int limit, int offset) {
        log.info("Getting all courses with status 'not finished' by teacher_id={}", teacherId);
        List<Course> courseList = new ArrayList<>();
        Connection connection = sessionManager.beginSession();


        String query = String.format(SQLTask.FIND_COURSE_NOT_FINISHED.QUERY);
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, teacherId);
            pst.setInt(2, limit);
            pst.setInt(3, offset);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Course course = createCourseFromResultSet(rs);
                    courseList.add(course);
                }
            }
            return courseList;
        } catch (SQLException e) {
            log.error("Cannot get all courses with status 'not finished' from DB");
            throw new CourseNotFoundException("Cannot get all courses with status 'not finished' from DB", e);
        } finally {
            sessionManager.close();
        }
    }

    /**
     * Finds courses by user id, course status in DB.
     *
     * @param userId - user id
     * @param status - course status
     * @throws CourseNotFoundException - if the course is not found
     * @return list of courses
     */
    public List<Course> findByUserIdAndStatus(int userId, String status) {
        log.info("Getting courses by status={} and user_id={}", status, userId);
        List<Course> courseList = new ArrayList<>();
        Connection connection = sessionManager.beginSession();

        try (PreparedStatement pst = connection.prepareStatement(SQLTask.GET_COURSES_BY_STATUS_AND_USER_ID.QUERY)) {
            pst.setInt(1, userId);
            pst.setString(2, status);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Course course = createCourseFromResultSet(rs);
                    String userName = rs.getString("user_name");
                    course.getTeacher().setName(userName);
                    String userSurname = rs.getString("user_surname");
                    course.getTeacher().setSurname(userSurname);
                    courseList.add(course);
                }
//                if(rs.next()){
////                    int noOfRecords = rs.getInt(1);
//                }
            }
            return courseList;
        } catch (SQLException e) {
            log.error("Cannot get courses by status and user from DB");
            throw new CourseNotFoundException("Cannot get courses by status and user from DB", e);
        } finally {
            sessionManager.close();
        }
    }

    /**
     * Finds courses with status register by user id in DB.
     *
     * @param userId - user id
     * @throws CourseNotFoundException - if the course is not found
     * @return list of courses
     */
    public List<Course> findCourseByUserRegister(int userId) {
        log.info("Getting courses by user_id={} register", userId);
        List<Course> courseList = new ArrayList<>();
        Connection connection = sessionManager.beginSession();

        try (PreparedStatement pst = connection.prepareStatement(SQLTask.GET_COURSE_BY_USER_ID_AND_REGISTER.QUERY)) {
            pst.setInt(1, userId);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Course course = createCourseFromResultSet(rs);
                    String userName = rs.getString("user_name");
                    course.getTeacher().setName(userName);
                    String userSurname = rs.getString("user_surname");
                    course.getTeacher().setSurname(userSurname);
                    courseList.add(course);
                }
            }
            return courseList;

        } catch (SQLException e) {
            log.error("Cannot get courses by status and user from DB");
            throw new CourseNotFoundException("Cannot get courses by status and user from DB", e);
        } finally {
            sessionManager.close();
        }
    }

    /**
     * Search course with status progress by teacher in DB.
     *
     * @param text - text
     * @throws UserNotFoundException - if the user is not found
     * @return list of courses
     */
    public List<Course> searchCoursesProgressTeacher(String text) {
        log.info("Getting courses progress for teacher from DB");
        List<Course> courseList = new ArrayList<>();
        Connection connection = sessionManager.beginSession();

        try (PreparedStatement pst = connection.prepareStatement(SQLTask.GET_COURSES_PROGRESS_TEACHER_SEARCH.QUERY)) {
            pst.setString(1, "%" + text + "%");
//            pst.setString(2, "%" + text + "%");
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
//                    Course course=new Course();
//                    User teacher = new User();
//                    int teacher_id = rs.getInt("teacher_id");
//                    teacher.setId(teacher_id);

                    User teacher = new User.UserBuilder().withId(rs.getInt("teacher_id")).build();

                    Course course = new Course.CourseBuilder().withId(rs.getInt("id"))
                            .withName(rs.getString("course_name"))
                            .withDateStart(rs.getDate("date_start").toLocalDate())
                            .withDuration(rs.getInt("duration"))
                            .withDescription(rs.getString("description"))
                            .withStatus("status")
                            .withTeacher(teacher).build();

//                    course.setId(rs.getInt("id"));
//                    course.setName(rs.getString("course_name"));
//                    course.setDateStart(rs.getDate("date_start").toLocalDate());
//                    course.setDuration(rs.getInt("duration"));
//                    course.setDescription(rs.getString("description"));
//                    course.setStatus("status");
//                    course.setTeacher(teacher);
                    courseList.add(course);
                }
            }
            return courseList;
        } catch (SQLException ex) {
            log.error("Cannot find any teacher");
            throw new UserNotFoundException("Cannot find any teacher in DB", ex);
        } finally {
            sessionManager.close();
        }
    }

//    public List<Course> findFinishedCourse(int userId) throws SQLException {
//        List<Course> courseList=new ArrayList<>();
//        sessionManager.beginSession();
//
//        try (Connection connection=sessionManager.getCurrentSession();
//             PreparedStatement pst=connection.prepareStatement(SQLTask.GET_COURSE_FINISHED_AND_TEACHER_ID.QUERY)){
//            pst.setInt(1,userId);
//
//            try (ResultSet rs = pst.executeQuery()){
//                while (rs.next()) {
//                    Course course = createCourseFromResultSet(rs);
//                    courseList.add(course);
//                }
//            } catch (SQLException ex) {
//                throw ex;
//            }
//            return courseList;
//        }
//    }

    private Course createCourseFromResultSet(ResultSet rs) throws SQLException {
//        Course course=new Course();

//        User teacher = new User();
//        int teacher_id = rs.getInt("teacher_id");
//        teacher.setId(teacher_id);

        User teacher = new User.UserBuilder().withId(rs.getInt("teacher_id")).build();

//        Topic topic = new Topic();
//        int topicId = rs.getInt("topic_id");
//        String topicName = rs.getString("topic_name");
//        topic.setId(topicId);
//        topic.setName(topicName);

        Topic topic = new Topic.TopicBuilder().withId(rs.getInt("topic_id"))
                .withName(rs.getString("topic_name")).build();

        Course course = new Course.CourseBuilder()
                .withId(rs.getInt("id"))
                .withName(rs.getString("course_name"))
                .withDateStart(rs.getDate("date_start").toLocalDate())
                .withDuration(rs.getInt("duration"))
                .withDescription(rs.getString("description"))
                .withTopic(topic)
                .withTeacher(teacher).build();


//        course.setId(rs.getInt("id"));
//        course.setName(rs.getString("course_name"));
//        course.setDateStart(rs.getDate("date_start").toLocalDate());
//        course.setDuration(rs.getInt("duration"));
//        course.setDescription(rs.getString("description"));
//        course.setTopic(topic);
//        course.setTeacher(teacher);

        return course;
    }

    public enum SQLTask {
        INSERT_COURSE("INSERT into courses (name, date_start, duration, description, topic_id, teacher_id, status ) " +
                "VALUES (? ,? ,? ,? ,? ,? ,? )"),
        GET_COURSE_BY_TOPIC("SELECT c.id, c.name as course_name, " +
                "c.date_start, c.duration, c.description,c.topic_id, c.teacher_id, t.name as topic_name, u.name as user_name, u.surname as user_surname " +
                "FROM courses c " +
                "JOIN topics t ON c.topic_id=t.id " +
                "JOIN users u ON c.teacher_id=u.id " +
                "WHERE t.id=? "),
        GET_ALL_COURSES_BY_TOPIC_AND_STATUS("SELECT c.id, c.name as course_name, " +
                "c.date_start, c.duration, c.description,c.topic_id , c.teacher_id, t.name as topic_name, u.name as user_name, u.surname as user_surname " +
                "FROM courses c " +
                "JOIN topics t ON c.topic_id=t.id " +
                "JOIN users u ON c.teacher_id=u.id " +
                "WHERE t.id=? AND c.status=? %s LIMIT ? OFFSET ? "),
        GET_COURSE("SELECT c.id, c.name as course_name," +
                "c.date_start, c.duration, c.description,c.topic_id, c.teacher_id, c.status, t.name as topic_name, u.name as user_name, u.surname as user_surname " +
                "FROM courses c LEFT JOIN course_user cu ON c.id=cu.course_id " +
                "JOIN topics t ON c.topic_id=t.id " +
                "JOIN users u ON c.teacher_id=u.id  " +
                "WHERE c.id=?"),
        DELETE_COURSE("DELETE FROM courses WHERE id=? "),
        GET_COURSES_BY_STATUS_AND_USER_ID("SELECT c.id, c.name as course_name," +
                "c.date_start, c.duration, c.description,c.topic_id, c.teacher_id, t.name as topic_name, u.name as user_name, u.surname as user_surname  " +
                "FROM courses c LEFT JOIN course_user cu ON c.id=cu.course_id " +
                "JOIN topics t ON c.topic_id=t.id " +
                "JOIN users u ON c.teacher_id=u.id  " +
                "WHERE cu.user_id=? AND c.status=? "),  //Pagination
        //NEW
        GET_COURSE_BY_USER_ID_AND_REGISTER("SELECT c.id, c.name as course_name, " +
                "c.date_start, c.duration, c.description, c.topic_id, c.teacher_id, t.name as topic_name, u.name as user_name, u.surname as user_surname " +
                "FROM courses c LEFT JOIN course_user cu ON c.id=cu.course_id " +
                "JOIN topics t ON c.topic_id=t.id " +
                "JOIN users u ON c.teacher_id=u.id " +
                "WHERE cu.user_id=? AND c.status='available' "),
        FIND_AVAILABLE_COURSES_BY_TOPIC("SELECT c.id, c.name as course_name, " +
                "c.date_start, c.duration, c.description, c.topic_id, c.teacher_id, t.name as topic_name, u.name as user_name, u.surname as user_surname, cu.user_id " +
                "FROM courses c LEFT JOIN course_user cu ON c.id=cu.course_id " +
                "JOIN topics t ON c.topic_id=t.id " +
                "JOIN users u ON c.teacher_id=u.id " +
                "WHERE t.id=? AND c.status = 'available' AND (cu.user_id IS NULL OR cu.user_id != ?) "), //Pagination
        FIND_AVAILABLE_COURSES("SELECT c.id, c.name as course_name, c.date_start, c.duration, c.description, " +
                "c.topic_id, c.teacher_id, t.name as topic_name, u.name as user_name, u.surname as user_surname " +
                "FROM courses c " +
                "JOIN topics t ON c.topic_id=t.id " +
                "JOIN users u ON c.teacher_id=u.id " +
                "WHERE c.status = 'available' AND c.id NOT IN (select course_id from gradebook where student_id = ?)"),
        FIND_COURSE_NOT_FINISHED("SELECT c.id, c.name as course_name, " +
                "c.date_start, c.duration, c.description,c.topic_id, c.teacher_id, t.name as topic_name " +
                "FROM courses c " +
                "JOIN topics t ON c.topic_id=t.id " +
                "WHERE c.teacher_id=? AND c.status != 'finished' LIMIT ? OFFSET ? "),  //Pagination
        GET_COURSE_BY_TEACHER_AVAILABLE_COUNT("SELECT count(*) AS counter FROM courses " +
                "WHERE teacher_id=? "),
        GET_COURSE_BY_ADMIN_STATUS_AND_TOPIC_COUNT("SELECT count(*) AS counter FROM courses " +
                "WHERE topic_id=? AND status=? "),
        GET_COURSE_BY_ADMIN_STATUS_COUNT("SELECT count(*) AS counter FROM courses " +
                "WHERE status=? "),
        UPDATE_COURSE("UPDATE courses SET name=?, " +
                "date_start=?, duration=?, description=?, topic_id=?, teacher_id=? WHERE id=?"),
        UPDATE_STATUS_COURSE("UPDATE courses SET status=? WHERE id=?"),
        FIND_COURSE_STATUS("SELECT c.id, c.name as course_name, " +
                "c.date_start, c.duration, c.description,c.topic_id, c.teacher_id, t.name as topic_name, u.name as user_name, u.surname as user_surname  " +
                "FROM courses c " +
                "JOIN topics t ON c.topic_id=t.id " +
                "JOIN users u ON c.teacher_id=u.id " +
                "WHERE c.status = ? %s LIMIT ? OFFSET ? "), //Pagination
        GET_COURSE_BY_TEACHER_STATUS("SELECT c.id, c.name as course_name, " +
                "c.date_start, c.duration, c.description,c.topic_id, c.teacher_id, t.name as topic_name " +
                "FROM courses c " +
                "JOIN topics t ON c.topic_id=t.id " +
                "JOIN users u ON c.teacher_id=u.id " +
                "WHERE c.teacher_id=? AND c.status=? "),  //Pagination
        GET_COURSE_BY_TEACHER("SELECT c.id, c.name as course_name, " +
                "c.date_start, c.duration, c.description,c.topic_id , c.teacher_id,c.status , t.name as topic_name " +
                "FROM courses c " +
                "JOIN topics t ON c.topic_id=t.id " +
                "JOIN users u ON c.teacher_id=u.id " +
                "WHERE c.teacher_id=? %s LIMIT ? OFFSET ? "),
        GET_COURSE_BY_TEACHER_AND_STATUS_COUNT("SELECT count(*) AS counter FROM courses " +
                "WHERE teacher_id=? AND status=? "),
        GET_COURSES_BY_TEACHER_COUNT("SELECT count(*) AS counter FROM courses " +
                "WHERE teacher_id=? "),
        GET_COURSES_PROGRESS_TEACHER_SEARCH("SELECT c.id, c.name, c.date_start, c.duration, c.description, c.teacher_id, c.status " +
                "FROM courses с " +
                "JOIN users u ON c.teacher_id=u.id " +
                "WHERE c.teacher_id=? AND c.status != 'finished' AND (c.name LIKE ? )"),

        //TODO: remove and use FIND_COURSE_STATUS instead
        GET_COURSES_AVAILABLE_GUEST("SELECT c.id, c.name as course_name, " +
                "c.date_start, c.duration, c.description, c.topic_id, c.teacher_id, t.name as topic_name, u.name as user_name, u.surname as user_surname " +
                "FROM courses c " +
                "JOIN topics t ON c.topic_id=t.id " +
                "JOIN users u ON c.teacher_id=u.id " +
                "WHERE c.status = 'available' ");  //Pagination
        String QUERY;

        SQLTask(String QUERY) {
            this.QUERY = QUERY;
        }

        public String getQUERY() {
            return QUERY;
        }
    }
}
