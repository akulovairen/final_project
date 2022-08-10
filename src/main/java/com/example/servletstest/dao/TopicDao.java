package com.example.servletstest.dao;

import com.example.servletstest.util.sessionManager.SessionManager;
import com.example.servletstest.model.Topic;
import com.example.servletstest.exception.serviceException.course.CourseNotDeletedException;
import com.example.servletstest.exception.serviceException.topic.TopicNotCreatedException;
import com.example.servletstest.exception.serviceException.topic.TopicNotFoundException;
import com.example.servletstest.exception.serviceException.topic.TopicNotUpdatedException;
import com.example.servletstest.util.ConnectionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TopicDao {
    private ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
    private SessionManager sessionManager = new SessionManager(connectionUtil.getHikariDataSource());
    private Logger log = LogManager.getLogger(TopicDao.class);

    public TopicDao() {
    }

    public int createTopic (Topic topic){
        log.info("Creating topic in DB");
        Connection connection=sessionManager.beginSession();
        try (PreparedStatement pst = connection.prepareStatement(SQLTask.INSERT_TOPIC.QUERY, Statement.RETURN_GENERATED_KEYS)) {

            pst.setInt(1, topic.getId());
            pst.setString(2, topic.getName());
            pst.executeUpdate();
            try (ResultSet rs = pst.getGeneratedKeys()) {
                rs.next();
                int id = rs.getInt(1);
                sessionManager.commitSession();
                return id;
            }
        } catch (SQLException ex) {
            log.error("Cannot create topic in DB");
            sessionManager.rollbackSession();
            throw new TopicNotCreatedException("Cannot create topic in DB",ex);
        }finally {
            sessionManager.close();
        }
    }

    public Optional<Topic> findTopic (int topicId) {
        log.info("Getting topic by topicId={}",topicId);
        Optional<Topic> optionalTopic = Optional.empty();
        sessionManager.beginSession();

        try (Connection connection = sessionManager.getCurrentSession();
             PreparedStatement pst = connection.prepareStatement(SQLTask.GET_TOPIC.QUERY)) {
            pst.setInt(1, topicId);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {
                    Topic dbTopic = createTopicFromResultSet(rs);
                    optionalTopic = Optional.of(dbTopic);
                }
            }
        } catch (SQLException ex) {
            log.error("Cannot get topic by topicId");
            throw new TopicNotFoundException("Cannot get topic",ex);
        }
        return optionalTopic;
    }

    public List<Topic> findAllTopic(){
        log.info("Getting all topics");
        List<Topic> topicList = new ArrayList<>();
        Connection connection=sessionManager.beginSession();

        try (PreparedStatement pst=connection.prepareStatement(SQLTask.GET_ALL_TOPICS.QUERY)){

            try (ResultSet rs = pst.executeQuery()){
                while (rs.next()) {
                    Topic topic = createTopicFromResultSet(rs);
                    topicList.add(topic);
                }
            } catch (SQLException ex) {
                log.error("Cannot get all topics");
                throw new TopicNotFoundException("Cannot get all topic",ex);
            }
            return topicList;
        } catch (SQLException e) {
            throw new TopicNotFoundException(e);
        }finally {
            sessionManager.close();
        }
    }

    public void updateTopic(Topic topic){
        log.info("Updated topic");
        Connection connection=sessionManager.beginSession();
        try (PreparedStatement pst = connection.prepareStatement(SQLTask.UPDATE_TOPIC.QUERY)) {
            pst.setInt(1, topic.getId());
            pst.setString(2, topic.getName());
            pst.executeUpdate();
            sessionManager.commitSession();

        } catch (SQLException ex) {
            log.error("Cannot update topic");
            sessionManager.rollbackSession();
            throw new TopicNotUpdatedException("Cannot update topic",ex);
        }finally {
            sessionManager.close();
        }
    }

    public boolean deleteTopic(int topicId) {
        log.info("Deleting topic in DB");
        int delete_rows;
        Connection connection=sessionManager.beginSession();
        try (PreparedStatement pst = connection.prepareStatement(SQLTask.DELETE_TOPIC.QUERY)) {
            pst.setInt(1, topicId);

            delete_rows = pst.executeUpdate();
            sessionManager.commitSession();
            return delete_rows > 0;
        } catch (SQLException ex) {
            log.error("Cannot delete topic from DB");
            sessionManager.rollbackSession();
            throw new CourseNotDeletedException("Cannot delete topic in DB",ex);
        }finally {
            sessionManager.close();
        }
    }

    private Topic createTopicFromResultSet(ResultSet rs) throws SQLException {
//        Topic topic=new Topic();
//        topic.setId(Integer.parseInt(rs.getString("id")));
//        topic.setName(rs.getString("name"));

        Topic topic = new Topic.TopicBuilder().withId(Integer.parseInt(rs.getString("id")))
                .withName(rs.getString("name"))
                .build();
        return topic;
    }

    enum SQLTask {
        GET_TOPIC("SELECT * FROM topics WHERE id = ?"),
        INSERT_TOPIC("INSERT into topics (id, name) VALUES (?, ?)"),
        GET_ALL_TOPICS("SELECT * FROM topics"),
        DELETE_TOPIC("DELETE FROM topics WHERE id=? "),
        UPDATE_TOPIC("UPDATE topics SET id=?, name=? WHERE id=?");
        String QUERY;

        SQLTask(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
