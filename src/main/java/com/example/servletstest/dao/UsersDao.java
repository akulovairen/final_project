package com.example.servletstest.dao;

import com.example.servletstest.util.sessionManager.SessionManager;
import com.example.servletstest.exception.serviceException.user.*;
import com.example.servletstest.model.User;
import com.example.servletstest.util.ConnectionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Data access object for working with user models.
 */
public class UsersDao {
    private ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
    private SessionManager sessionManager = new SessionManager(connectionUtil.getHikariDataSource());
    private Logger log = LogManager.getLogger(this);

    public UsersDao() {
    }

    /**
     * Creates user record in DB.
     *
     * @param user - {@link User} model
     * @throws UserNotCreatedException - if not created user
     * @return user id
     */
    public int createUser(User user){
        Optional<User> optionalUser = Optional.empty();
        Connection connection=sessionManager.beginSession();

        try (PreparedStatement pst = connection.prepareStatement(SQLTask.INSERT_USER.QUERY, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, user.getName());
            pst.setString(2, user.getSurname());
            pst.setDate(3, Date.valueOf(user.getBirthday().toLocalDate()));
            pst.setString(4, user.getEmail());
            pst.setString(5, user.getPassword());
            pst.setString(6, user.getRole());
            pst.setBoolean(7,user.getLocked());

            pst.executeUpdate();
            try (ResultSet rs = pst.getGeneratedKeys()) {
                rs.next();
                int id = rs.getInt(1);
                sessionManager.commitSession();
                return id;
            }

        } catch (SQLException ex) {
            log.error("Cannot create user in DB");
            sessionManager.rollbackSession();
            throw new UserNotCreatedException("Cannot create user in DB",ex);
        }finally {
            sessionManager.close();
        }
    }

    /**
     * Finds user by id in DB.
     *
     * @param id - user id
     * @throws UserNotFoundException - if user not found
     * @return the user object wrapped in an {@link Optional}
     */

    public Optional<User> findUser(int id) {
        log.info("Getting user by id={} from DB",id);
        Optional<User> optionalUser = Optional.empty();
        Connection connection=sessionManager.beginSession();

        try (PreparedStatement pst = connection.prepareStatement(SQLTask.GET_USER.QUERY)) {
            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    User dbUser = createUserFromResultSet(rs);
                    optionalUser = Optional.of(dbUser);
                }
            }
            return optionalUser;
        } catch (SQLException ex) {
            log.error("Cannot get user by id from DB");
            throw new UserNotFoundException("Cannot get user by id from DB",ex);
        }finally {
            sessionManager.close();
        }
    }

    /**
     * Counts amount of users in the system.
     *
     * @param sql query
     * @return number of users
     */

    public int getNumberOfRows(String sql) {
        log.info("Getting count of users/teachers in DB");
        int count = 0;
        Connection connection=sessionManager.beginSession();

        try (PreparedStatement pst = connection.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt("counter");
            }
            return count;
        } catch (SQLException ex) {
            log.error("Cannot get user by id from DB");
            throw new UserNotFoundException("Cannot get user by id from DB",ex);
        }finally {
            sessionManager.close();
        }
    }

    /**
     * Finds all users in DB
     *
     * @param offset
     * @param limit
     * @throws UserNotFoundException - if not found users
     * @return list of users with pagination
     */
    public List<User> findAllUsers(int offset, int limit) {
        log.info("Getting all users from DB");
        List<User> userList = new ArrayList<>();
        Connection connection=sessionManager.beginSession();

        try (PreparedStatement pst = connection.prepareStatement(SQLTask.GET_ALL_USERS.QUERY)) {
            pst.setInt(1,limit);
            pst.setInt(2,offset);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
//                    User user = new User();
//                    user.setId(Integer.parseInt(rs.getString("id")));
//                    user.setName(rs.getString("name"));
//                    user.setSurname(rs.getString("surname"));
//                    user.setBirthday(rs.getDate("birthday").toLocalDate());
//                    user.setEmail(rs.getString("email"));
//                    user.setLocked(rs.getBoolean("locked"));

                    User user = new User.UserBuilder().withId(Integer.parseInt(rs.getString("id")))
                            .withName(rs.getString("name"))
                            .withSurname(rs.getString("surname"))
                            .withBirthday(rs.getDate("birthday").toLocalDate())
                            .withEmail(rs.getString("email"))
                            .withLocked(rs.getBoolean("locked")).build();
                    userList.add(user);
                }
            } catch (SQLException ex) {
                log.error("Cannot get all users from DB");
                throw new UserNotFoundException("Cannot get all users from DB",ex);
            }
            return userList;
        } catch (SQLException e) {
            throw new UserNotFoundException(e);
        }finally {
            sessionManager.close();
        }
    }

    private User createUserFromResultSet(ResultSet rs) throws SQLException {
//        User user = new User();
//        user.setId(Integer.parseInt(rs.getString("id")));
//        user.setName(rs.getString("name"));
//        user.setSurname(rs.getString("surname"));
//        user.setBirthday(rs.getDate("birthday").toLocalDate());
//        user.setEmail(rs.getString("email"));
//        user.setPassword(rs.getString("password"));
//        user.setRole(rs.getString("role"));

        User user = new User.UserBuilder().withId(Integer.parseInt(rs.getString("id")))
                .withName(rs.getString("name"))
                .withSurname(rs.getString("surname"))
                .withBirthday(rs.getDate("birthday").toLocalDate())
                .withEmail(rs.getString("email"))
                .withPassword(rs.getString("password"))
                .withRole(rs.getString("role"))
                .withLocked(rs.getBoolean("locked"))
                .build();
        return user;
    }

    public boolean deleteUser(int userId) {
        log.info("Deleting user from DB");
        int delete_rows;
        Connection connection=sessionManager.beginSession();
        try (PreparedStatement pst = connection.prepareStatement(SQLTask.DELETE_USER.QUERY)) {
            pst.setInt(1, userId);

            delete_rows = pst.executeUpdate();
            sessionManager.commitSession();

            return delete_rows > 0;
        } catch (SQLException ex) {
            log.error("Cannot delete user from DB");
            sessionManager.rollbackSession();
            throw new UserNotDeletedException("Cannot delete user from DB",ex);
        }finally {
            sessionManager.close();
        }
    }

    public Optional<User> findByEmail(String email) {
        log.info("Getting user by email={} from DB",email);
        Optional<User> optionalUser = Optional.empty();
        Connection connection=sessionManager.beginSession();

        try (PreparedStatement pst = connection.prepareStatement(SQLTask.GET_USER_BY_EMAIL.QUERY)) {
            pst.setString(1, email);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    User dbUser = createUserFromResultSet(rs);
                    optionalUser = Optional.of(dbUser);
                }
            }
            return optionalUser;
        } catch (SQLException ex) {
            log.info("Cannot get user by email from DB");
            throw new UserNotFoundException("Cannot get user by email from DB", ex);
        }finally {
            sessionManager.close();
        }
    }

    public List<User> findAllTeacher(int offset, int limit) {
        log.info("Getting all teachers from DB");
        List<User> userList = new ArrayList<>();
        Connection connection=sessionManager.beginSession();
        try (PreparedStatement pst = connection.prepareStatement(SQLTask.GET_ALL_TEACHER.QUERY)) {
            pst.setInt(1, limit);
            pst.setInt(2, offset);
            try (ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {
//                    User user = new User();
//                    user.setId(Integer.parseInt(rs.getString("id")));
//                    user.setName(rs.getString("name"));
//                    user.setSurname(rs.getString("surname"));
//                    user.setBirthday(rs.getDate("birthday").toLocalDate());

                    User user = new User.UserBuilder().withId(Integer.parseInt(rs.getString("id")))
                            .withName(rs.getString("name"))
                            .withSurname(rs.getString("surname"))
                            .withBirthday(rs.getDate("birthday").toLocalDate()).build();
                    userList.add(user);
                }
            }
            return userList;
        } catch (SQLException ex) {
            log.error("Cannot get all teachers from DB");
            throw new UserNotFoundException("Cannot get all teachers from DB", ex);
        }finally {
            sessionManager.close();
        }
    }

//    public List<Course> coursesByUserId(int userId) throws SQLException {
//        List<Course> courseList = new ArrayList<>();
//        sessionManager.beginSession();
//
//        try (Connection connection = sessionManager.getCurrentSession();
//             PreparedStatement pst = connection.prepareStatement(SQLTask.SELECT_COURSE_USER.QUERY)) {
//            pst.setInt(1,userId);
//            try (ResultSet rs = pst.executeQuery()) {
//                while (rs.next()) {
//                    Course course = new Course();
//
//                    int id = rs.getInt("id");
//                    String name = rs.getString("name");
//                    Date date_start = rs.getDate("date_start");
//                    int duration = rs.getInt("duration");
//                    String description = rs.getString("description");
//                    int topicId = rs.getInt("topic_id");
//                    int teacher_id = rs.getInt("teacher_id");
//
//                    Topic topic = new Topic();
//                    topic.setId(topicId);
//
//                    User teacher = new User();
//                    teacher.setId(teacher_id);
//
//                    course.setId(id);
//                    course.setName(name);
//                    course.setDateStart(date_start.toLocalDate());
//                    course.setDuration(duration);
//                    course.setDescription(description);
//                    course.setTopic(topic);
//                    course.setTeacher(teacher);
//
//                    courseList.add(course);
//                    sessionManager.commitSession();
//                }
//            } catch (SQLException ex) {
//                sessionManager.rollbackSession();
//                throw ex;
//            }
//            return courseList;
//        }
//    }

    public User updateUser(User user){
        log.info("Updating user in DB");
        Connection connection=sessionManager.beginSession();
        try (PreparedStatement pst = connection.prepareStatement(SQLTask.UPDATE_USER.QUERY)) {

            pst.setString(1, user.getName());
            pst.setString(2, user.getSurname());
            pst.setDate(3, Date.valueOf(user.getBirthday().toLocalDate()));
            pst.setInt(4, user.getId());

            pst.executeUpdate();
            sessionManager.commitSession();

            return user;
        } catch (SQLException ex) {
            log.error("Cannot update user in DB");
            sessionManager.rollbackSession();
            throw new UserNotUpdatedException("Cannot update user in DB",ex);
        }finally{
            sessionManager.close();
        }
    }

    public boolean userBlock(int userId){
        log.info("Blocking student by user_id={}",userId);
        int delete_rows;
        Connection connection=sessionManager.beginSession();
        try (PreparedStatement pst = connection.prepareStatement(SQLTask.USER_BLOCK.QUERY)) {
            pst.setInt(1, userId);

            delete_rows = pst.executeUpdate();
            sessionManager.commitSession();
            return delete_rows > 0;
        } catch (SQLException ex) {
            log.error("Cannot block student by userId");
            sessionManager.rollbackSession();
            throw new UserNotBlockedException("Cannot block student due to DB problem",ex);
        }finally {
            sessionManager.close();
        }
    }

    public boolean userUnblock(int userId){
        log.info("Unblocked student by userId={} in DB",userId);
        int delete_rows;
        Connection connection=sessionManager.beginSession();
        try (PreparedStatement pst = connection.prepareStatement(SQLTask.USER_UNBLOCK.QUERY)) {

            pst.setInt(1, userId);

            delete_rows = pst.executeUpdate();
            sessionManager.commitSession();
            return delete_rows > 0;
        } catch (SQLException ex) {
            log.error("Cannot unblocked student by userId in DB");
            sessionManager.rollbackSession();
            throw new UserNotUnblockedException("Cannot unblock student due to DB problem",ex);
        }finally {
            sessionManager.close();
        }
    }

    public List<User> searchTeacher(String text) {
        log.info("Getting all teachers from DB");
        List<User> userList = new ArrayList<>();
        sessionManager.beginSession();

        try (Connection connection = sessionManager.getCurrentSession();
             PreparedStatement pst = connection.prepareStatement(SQLTask.GET_ALL_TEACHER_SEARCH.QUERY)) {
            pst.setString(1, "%" + text + "%");
            pst.setString(2, "%" + text + "%");


            try (ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {
//                    User user = new User();
//                    user.setId(Integer.parseInt(rs.getString("id")));
//                    user.setName(rs.getString("name"));
//                    user.setSurname(rs.getString("surname"));
//                    user.setBirthday(rs.getDate("birthday").toLocalDate());

                    User user = new User.UserBuilder().withId(Integer.parseInt(rs.getString("id")))
                            .withName(rs.getString("name"))
                            .withSurname(rs.getString("surname"))
                            .withBirthday(rs.getDate("birthday").toLocalDate()).build();
                    userList.add(user);
                }
            }
        } catch (SQLException ex) {
            log.error("Cannot find any teacher");
            throw new UserNotFoundException("Cannot find any teacher in DB", ex);
        }
        return userList;
    }


    public enum SQLTask {
        GET_ALL_USERS("SELECT id,name,surname,birthday,email,locked FROM users WHERE role='student' LIMIT ? OFFSET ?"), //Pagination
        GET_ALL_USERS_COUNT("SELECT count(*) AS counter FROM users WHERE role='student'"),
        GET_USER_BY_EMAIL("SELECT * FROM users WHERE email = (?)"),
        INSERT_USER("INSERT into users (name, surname, birthday, email, password, role, locked ) VALUES (?, ? , ? , ? ,?,?, ? )"),
        SELECT_COURSE_USER("SELECT c.id, c.name, c.date_start,c.duration, c.description, c.status, c.topic_id,  c.teacher_id " +
                "FROM courses c " +
                "JOIN course_user cu ON c.id=cu.course_id " +
                "WHERE cu.user_id=?"),
        GET_USER("SELECT * FROM users WHERE id=?"),
        USER_BLOCK("UPDATE users SET locked=1 WHERE id=? AND role='student' "),
        USER_UNBLOCK("UPDATE users SET locked=0 WHERE id=? AND role='student' "),
        DELETE_USER("DELETE FROM users WHERE id=?"),
        GET_ALL_TEACHER("SELECT id, surname, name, birthday FROM users WHERE role='teacher' LIMIT ? OFFSET ?"),  //Pagination
        GET_ALL_TEACHER_SEARCH("SELECT id, surname, name, birthday FROM users WHERE role='teacher' AND (surname LIKE ? OR name LIKE ?)"),
        GET_ALL_TEACHER_COUNT("SELECT count(*) AS counter FROM users WHERE role='teacher'"),

        UPDATE_USER("UPDATE users SET name=?, surname=?, birthday=? WHERE id=?");
        String QUERY;

        SQLTask(String QUERY) {
            this.QUERY = QUERY;
        }

        public String getQUERY() {
            return QUERY;
        }
    }
}
