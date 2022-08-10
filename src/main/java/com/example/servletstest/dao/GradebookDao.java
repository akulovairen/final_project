package com.example.servletstest.dao;

import com.example.servletstest.util.sessionManager.SessionManager;
import com.example.servletstest.model.Course;
import com.example.servletstest.model.Gradebook;
import com.example.servletstest.model.Topic;
import com.example.servletstest.model.User;
import com.example.servletstest.exception.serviceException.gradebook.GradebookNotCreatedException;
import com.example.servletstest.exception.serviceException.gradebook.GradebookNotFoundException;
import com.example.servletstest.exception.serviceException.gradebook.GradebookNotUpdatedException;
import com.example.servletstest.util.ConnectionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GradebookDao {
    private ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
    private SessionManager sessionManager = new SessionManager(connectionUtil.getHikariDataSource());
    private Logger log = LogManager.getLogger(GradebookDao.class);


    public GradebookDao() {
    }

    public int createGradebook(Gradebook gradebook) {
        log.info("Creating gradebook in DB");
        Connection connection=sessionManager.beginSession();

        try (PreparedStatement pst = connection.prepareStatement(SQLTask.INSERT_GRADEBOOK.QUERY, Statement.RETURN_GENERATED_KEYS)) {

            pst.setInt(1, gradebook.getId());
            pst.setInt(2, gradebook.getStudent().getId());
            pst.setInt(3, 0);
            pst.setInt(4, 0);
            pst.setInt(5, 0);
            pst.setInt(6, 0);
            pst.setDouble(7, 0);
            pst.setInt(8, gradebook.getCourse().getId());

            pst.executeUpdate();
            try (ResultSet rs = pst.getGeneratedKeys()) {
                rs.next();
                int id = rs.getInt(1);
                sessionManager.commitSession();
                return id;
            }
        } catch (SQLException ex) {
            log.error("Cannot create gradebook in DB");
            sessionManager.rollbackSession();
            throw new GradebookNotCreatedException("Cannot create gradebook in DB",ex);
        }finally {
            sessionManager.close();
        }
    }

    public List<Gradebook> findGradebook(int courseId) {
        log.info("Getting gradebooks by courseId={}",courseId);
        List<Gradebook> gradebookList=new ArrayList<>();
        Connection connection=sessionManager.beginSession();

        try (PreparedStatement pst = connection.prepareStatement(SQLTask.GET_GRADEBOOK.QUERY)) {
            pst.setInt(1, courseId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Gradebook gradebook = createGradebookFromResultSet(rs);
                    String courseName = rs.getString("courseName");
                    gradebook.getCourse().setName(courseName);
                    String userName = rs.getString("userName");
                    gradebook.getStudent().setName(userName);
                    String userSurname = rs.getString("userSurname");
                    gradebook.getStudent().setSurname(userSurname);
                    gradebookList.add(gradebook);
                }
            }
            return gradebookList;
        } catch (SQLException ex) {
            log.error("Cannot get gradebooks by course");
            throw new GradebookNotFoundException("Cannot get gradebooks by course",ex);
        }finally {
            sessionManager.close();
        }
    }

    public Gradebook updateGradebook(Gradebook gradebook) {
        log.info("Updating gradebook in DB");
        Connection connection=sessionManager.beginSession();
        try (PreparedStatement pst = connection.prepareStatement(SQLTask.UPDATE_GRADEBOOK.QUERY)) {

            pst.setInt(1, gradebook.getId());
            pst.setInt(2, gradebook.getStudent().getId());
            pst.setInt(3, gradebook.getTest1());
            pst.setInt(4, gradebook.getTest2());
            pst.setInt(5, gradebook.getTest3());
            pst.setInt(6, gradebook.getTest4());
            pst.setDouble(7, gradebook.getTotalScore());
            pst.setInt(8, gradebook.getCourse().getId());

            pst.executeUpdate();
            sessionManager.commitSession();
            return gradebook;
        } catch (SQLException ex) {
            log.error("Cannot update gradebook in DB");
            sessionManager.rollbackSession();
            throw new GradebookNotUpdatedException("Cannot update gradebook in DB",ex);
        }finally {
            sessionManager.close();
        }
    }

    public void updateGradebookByCourse(List<Gradebook> gradebook) {
        log.info("Updating gradebook by courseId in DB");
        Connection connection=sessionManager.beginSession();
        try (PreparedStatement pst = connection.prepareStatement(SQLTask.UPDATE_GRADEBOOK_BY_COURSE.QUERY)) {

            for (Gradebook gb : gradebook) {

                pst.setInt(1, gb.getStudent().getId());
                pst.setInt(2, gb.getTest1());
                pst.setInt(3, gb.getTest2());
                pst.setInt(4, gb.getTest3());
                pst.setInt(5, gb.getTest4());
                pst.setDouble(6, gb.getTotalScore());
                pst.setInt(7, gb.getId());
                pst.addBatch();
            }
            int[] rows = pst.executeBatch();
            log.info("Updated {} rows in gradebook table", rows.length);
            sessionManager.commitSession();

        } catch (SQLException ex) {
            log.error("Cannot update gradebook by courseId in DB");
            sessionManager.rollbackSession();
            throw new GradebookNotUpdatedException("Cannot update gradebook by courseId in DB",ex);
        }finally {
            sessionManager.close();
        }
    }

    public List<Gradebook> findAllByStudent(int student_id, String sortingColumn, String sortingMode) {
        log.info("Getting all gradebooks for student by student_id={}", student_id);
        List<Gradebook> gradebookList=new ArrayList<>();
        Connection connection=sessionManager.beginSession();

        String query = SQLTask.GET_ALL_BY_USER.QUERY + " ORDER BY " + sortingColumn + " " + sortingMode;
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, student_id);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Gradebook gradebook = createGradebookFromResultSet(rs);

//                    Topic topic = new Topic();
//                    int topicId = rs.getInt("topic_id");
//                    String topicName = rs.getString("topicName");
//                    topic.setId(topicId);
//                    topic.setName(topicName);

                    Topic topic = new Topic.TopicBuilder().withId(rs.getInt("topic_id"))
                            .withName(rs.getString("topicName")).build();
                    gradebook.getCourse().setTopic(topic);

                    String courseName = rs.getString("courseName");
                    gradebook.getCourse().setName(courseName);
                    String userName = rs.getString("userName");
                    gradebook.getStudent().setName(userName);
                    String userSurname = rs.getString("userSurname");
                    gradebook.getStudent().setSurname(userSurname);
                    gradebookList.add(gradebook);
                }
            }
            return gradebookList;
        } catch (SQLException e) {
            log.error("Cannot all grsdebooks for student");
            throw new GradebookNotFoundException("Cannot all grsdebooks for student",e);
        }finally {
            sessionManager.close();
        }
    }

    public Optional<Gradebook> findGradeByUserAndCourse(int student_id, int course_id) {
        log.info("Getting grades by student={} and course={}",student_id,course_id);
        Optional<Gradebook> optionalGradebook=Optional.empty();
        Connection connection=sessionManager.beginSession();
        try (PreparedStatement pst = connection.prepareStatement(SQLTask.GET_GRADE_BY_USER_AND_COURSE.QUERY)) {
            pst.setInt(1, student_id);
            pst.setInt(2,course_id);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Gradebook gradebook = createGradebookFromResultSet(rs);
                    String courseName = rs.getString("courseName");
                    gradebook.getCourse().setName(courseName);

                    optionalGradebook = Optional.of(gradebook);
                }
            }
            return optionalGradebook;
        } catch (SQLException e) {
            log.error("Cannot get grades by student and course");
            throw new GradebookNotFoundException("Cannot get grades by course for student",e);
        }finally {
            sessionManager.close();
        }
    }

    public List<Gradebook> findAllGradesByCourse(int courseId){
        log.info("Getting all grades by course={}", courseId);
        List<Gradebook> gradebookList =new ArrayList<>();
        Connection connection=sessionManager.beginSession();

        try (PreparedStatement pst = connection.prepareStatement(SQLTask.GET_ALL_GRADES_BY_COURSES_ID.QUERY)) {
            pst.setInt(1, courseId);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Gradebook gradebook = createGradebookFromResultSet(rs);

                    String userName = rs.getString("userName");
                    String userSurname = rs.getString("userSurname");
                    gradebook.getStudent().setName(userName);
                    gradebook.getStudent().setSurname(userSurname);
                    gradebookList.add(gradebook);
                }
            }
            return gradebookList;
        } catch (SQLException e) {
            throw new GradebookNotFoundException("Cannot get grades by course",e);
        }finally {
            sessionManager.close();
        }
    }

    public Gradebook createGradebookFromResultSet(ResultSet rs) throws SQLException {
//        Gradebook gradebook = new Gradebook();

//        Course course = new Course();
//        int courseId = rs.getInt("course_id");
//        course.setId(courseId);

        Course course = new Course.CourseBuilder().withId(rs.getInt("course_id")).build();

//        User student = new User();
//        int student_id = rs.getInt("student_id");
//        student.setId(student_id);

        User student = new User.UserBuilder().withId(rs.getInt("student_id")).build();

        Gradebook gradebook = new Gradebook.GradebookBuilder().withId(rs.getInt("id"))
                .withStudent(student)
                .withTest1(rs.getInt("test1"))
                .withTest2(rs.getInt("test2"))
                .withTest3(rs.getInt("test3"))
                .withTest4(rs.getInt("test4"))
                .withTotalScore(rs.getDouble("totalScore"))
                .withCourse(course).build();

//        gradebook.setId(rs.getInt("id"));
//        gradebook.setStudent(student);
//        gradebook.setTest1(rs.getInt("test1"));
//        gradebook.setTest2(rs.getInt("test2"));
//        gradebook.setTest3(rs.getInt("test3"));
//        gradebook.setTest4(rs.getInt("test4"));
//        gradebook.setTotalScore(rs.getDouble("totalScore"));
//        gradebook.setCourse(course);

        return gradebook;
    }

    enum SQLTask {
        GET_ALL_GRADES_BY_COURSES_ID("SELECT g.id, g.student_id, g.test1, g.test2, g.test3, g.test4, g.totalScore, g.course_id, u.name as userName, u.surname as userSurname  " +
                "FROM gradebook g " +
                "JOIN users u ON u.id=g.student_id " +
                "WHERE g.course_id=?"),
        GET_GRADEBOOK("SELECT SELECT g.id, g.student_id, g.test1, g.test2, g.test3, g.test4, g.totalScore, g.course_id, u.name as userName, u.surname as userSurname " +
                "FROM gradebook g " +
                "JOIN users u ON u.id=g.student_id " +
                "JOIN courses c ON c.id=g.course_id " +
                "WHERE id =?"),
        INSERT_GRADEBOOK("INSERT into gradebook (id, student_id ,test1 ,test2, test3 ,test4 ,totalScore ,course_id)" +
                " VALUES (?, ? , ? , ? , ? ,? ,? ,?)"),
        GET_ALL_BY_USER("SELECT g.id, g.student_id, g.test1, g.test2, g.test3, g.test4, g.totalScore, g.course_id, c.topic_id, t.name as topicName, c.name as courseName, u.name as userName, u.surname as userSurname " +
                "FROM gradebook g " +
                "JOIN courses c ON c.id=g.course_id " +
                "JOIN topics t ON c.topic_id=t.id " +
                "JOIN users u ON u.id=g.student_id " +
                "WHERE g.student_id=? "),
        GET_GRADE_BY_USER_AND_COURSE("SELECT g.id, g.student_id, g.test1, g.test2, g.test3, g.test4, g.totalScore, g.course_id, c.name as courseName " +
                "FROM gradebook g " +
                "JOIN courses c ON c.id=g.course_id " +
                "WHERE student_id=? AND course_id=?"),
        UPDATE_GRADEBOOK("UPDATE gradebook SET student_id=?, test1=?, test2=?, test3=?, test4=?, totalScore=?, course_id=? WHERE id=?"),
        UPDATE_GRADEBOOK_BY_COURSE("UPDATE gradebook SET student_id=?, test1=?, test2=?, test3=?, test4=?, totalScore=? WHERE id=? ");

        String QUERY;

        SQLTask(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
