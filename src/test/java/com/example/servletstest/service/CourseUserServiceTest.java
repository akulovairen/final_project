package com.example.servletstest.service;

import com.example.servletstest.dao.CourseUserDao;
import com.example.servletstest.dao.GradebookDao;
import com.example.servletstest.model.CourseUser;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

class CourseUserServiceTest {
    @Mock
    CourseUserDao courseUserDao;
    CourseUserService courseUserService;

    public CourseUserServiceTest() {
        MockitoAnnotations.initMocks(this);
        this.courseUserService = new CourseUserService(courseUserDao);
    }

    @Test
    void countStudentTest(){
        given(courseUserDao.countStudent(anyInt())).willReturn(5);
        int countStudent = courseUserService.countStudent(5);
        assertEquals(5,countStudent);
    }

    @Test
    void createCourseUserTest(){
        given(courseUserDao.createCourseUser(any())).willReturn(2);
        int serviceCourse = courseUserService.createCourseUser(3,2,"progress");
        assertEquals(2,serviceCourse);
    }

}