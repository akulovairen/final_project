package com.example.servletstest.service;

import com.example.servletstest.dao.CourseDao;
import com.example.servletstest.dao.TopicDao;
import com.example.servletstest.exception.CustomValidationException;
import com.example.servletstest.model.Topic;
import com.example.servletstest.util.LocalizedValidatorUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

class TopicServiceTest {
    @Mock
    TopicDao topicDao;
    TopicService topicService;

    public TopicServiceTest() {
        MockitoAnnotations.initMocks(this);
        this.topicService=new TopicService(topicDao);
    }

    @Test
    void getAllTopic(){
        Topic topic=new Topic();
        ArrayList<Topic> expectedList=new ArrayList<>();
        expectedList.add(topic);

        given(topicDao.findAllTopic()).willReturn(expectedList);
        List<Topic> actualList = topicService.getAllTopic();
        assertNotNull(actualList);
        assertEquals(expectedList.size(),actualList.size());
    }

    @Test
    void deleteTopicSuccess(){
        given(topicDao.deleteTopic(anyInt())).willReturn(true);
        boolean deleteTopic = topicService.deleteTopic(4);
        assertThat(deleteTopic).isTrue();
    }

    @Test
    void deleteTopicFailure(){
        given(topicDao.deleteTopic(anyInt())).willReturn(false);
        boolean deleteTopic = topicService.deleteTopic(4);
        assertThat(deleteTopic).isFalse();
    }

    @Test
    void createTopicSuccess(){
        given(topicDao.createTopic(any())).willReturn(2);
        int serviceCourse = topicService.createTopic("topic", LocalizedValidatorUtil.UKRAINE_LOCALE);
        assertEquals(2,serviceCourse);
    }

    @Test
    void createTopicFailure(){
        try {
            topicService.createTopic("", LocalizedValidatorUtil.UKRAINE_LOCALE);
            fail();
        }catch (CustomValidationException e){
            Map<String, String> errorsMap = e.getErrorsMap();
            assertEquals("Тема повинна мати назву довжиною від 3 до 40 символів", errorsMap.get("name"));
        }
    }
}