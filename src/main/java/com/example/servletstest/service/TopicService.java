package com.example.servletstest.service;

import com.example.servletstest.dao.TopicDao;
import com.example.servletstest.exception.CustomValidationException;
import com.example.servletstest.model.Course;
import com.example.servletstest.model.Topic;
import com.example.servletstest.util.LocalizedValidatorUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Business logic for topic functionality
 */
public class TopicService {
    TopicDao topicDao = new TopicDao();
    private Logger log = LogManager.getLogger(TopicService.class);
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator = factory.getValidator();

    /**
     * Create gradebook service.
     *
     * @param topicDao - {@link TopicDao} model
     */
    public TopicService(TopicDao topicDao) {
        this.topicDao=topicDao;
    }

    /**
     * Gets all topic.
     *
     * @return {@link List<Topic>} with sorting by name
     */
    public List<Topic> getAllTopic() {
        log.info("Getting all topic");
        List<Topic> allTopic = topicDao.findAllTopic();
        allTopic.sort(Comparator.comparing(Topic::getName));
        return allTopic;
    }

    /**
     * Creates topic. Data validation.
     *
     * @param name - name
     * @return {@link Topic} id
     * @throws CustomValidationException - if validation fails
     */
    public int createTopic(String name, Locale locale) throws CustomValidationException {
//        Topic topic=new Topic();
//        topic.setName(name);
        Topic topic = new Topic.TopicBuilder().withName(name).build();

        Validator validator = LocalizedValidatorUtil.getValidatorByLocale(locale);
        Set<ConstraintViolation<Topic>> constraintViolationSet = validator.validate(topic);
        Map<String, String> errorMap =
                constraintViolationSet
                        .stream()
                        .collect(Collectors.toMap(userConstraintViolation -> userConstraintViolation.getPropertyPath().toString(), ConstraintViolation::getMessage));

        if (!errorMap.isEmpty()) {
            throw new CustomValidationException(errorMap);
        }
        return topicDao.createTopic(topic);
    }

    public boolean deleteTopic(int topicId){
        return topicDao.deleteTopic(topicId);
    }
}
