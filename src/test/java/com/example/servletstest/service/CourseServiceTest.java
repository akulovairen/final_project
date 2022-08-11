package com.example.servletstest.service;

import com.example.servletstest.dao.CourseDao;
import com.example.servletstest.dao.UsersDao;
import com.example.servletstest.dto.CourseDto;
import com.example.servletstest.exception.CustomValidationException;
import com.example.servletstest.model.Course;
import com.example.servletstest.util.LocalizedValidatorUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

class CourseServiceTest {
    @Mock
    CourseDao courseDao;
    CourseService courseService;

    public CourseServiceTest() {
        MockitoAnnotations.initMocks(this);
        this.courseService=new CourseService(courseDao);
    }

    @Test
    void findByUserIdAndStatusSuccess() {
        Course courseExpected=new Course();
        List<Course> expectedList = new ArrayList<>();
        expectedList.add(courseExpected);

        given(courseDao.findByUserIdAndStatus(anyInt(),anyString())).willReturn(expectedList);
        List<Course> actualList = courseService.findByUserIdAndStatus(1, "available");
        assertNotNull(actualList);
        assertEquals(expectedList.size(),actualList.size());
    }

    @Test
    void findByStatusForGuestSuccess() {
        Course courseExpected=new Course();
        List<Course> expectedList = new ArrayList<>();
        expectedList.add(courseExpected);

        given(courseDao.findCourseForGuest()).willReturn(expectedList);
        List<Course> actualList = courseService.findByStatusForGuest();
        assertNotNull(actualList);
        assertEquals(expectedList.size(),actualList.size());
    }

    @Test
    void findByStatusAvailableByTopicSuccess(){
        Course courseExpected=new Course();
        List<Course> expectedList = new ArrayList<>();
        expectedList.add(courseExpected);

        given(courseDao.findByStatusAvailableByTopic(anyInt(),anyInt())).willReturn(expectedList);
        List<Course> actualList = courseService.findByStatusAvailableByTopic(2, 3);
        assertNotNull(actualList);
        assertEquals(expectedList.size(),actualList.size());
    }

    @Test
    void findCourseByUserRegisterSuccess(){
        Course courseExpected=new Course();
        List<Course> expectedList = new ArrayList<>();
        expectedList.add(courseExpected);

        given(courseDao.findCourseByUserRegister(anyInt())).willReturn(expectedList);
        List<Course> actualList = courseService.findCourseByUserRegister(3);
        assertNotNull(actualList);
        assertEquals(expectedList.size(),actualList.size());
    }

    @Test
    void findCourseNotFinishedSuccess(){
        Course courseExpected=new Course();
        List<Course> expectedList = new ArrayList<>();
        expectedList.add(courseExpected);

        given(courseDao.findCourseNotFinished(anyInt(),anyInt(),anyInt())).willReturn(expectedList);
        List<Course> actualList=courseService.findCourseNotFinished(2,1,1);
        assertNotNull(actualList);
        assertEquals(expectedList.size(),actualList.size());
    }

    @Test
    void findCourseSuccess(){
//        Course course=new Course();
//        course.setId(2);

        Course course = new Course.CourseBuilder().withId(2).build();

        given(courseDao.findCourse(anyInt())).willReturn(Optional.of(course));

        Optional<Course> actualCourse = courseService.findCourse(2);
        assertTrue(actualCourse.isPresent());
        Course courseActualObject = actualCourse.get();

        assertEquals(course.getId(),courseActualObject.getId());
    }

    @Test
    void findCourseStatusSuccess(){
        Course courseExpected=new Course();
        List<Course> expectedList = new ArrayList<>();
        expectedList.add(courseExpected);

        given(courseDao.findCourseStatus(anyString(),anyInt(),anyInt(),anyString(),anyString())).willReturn(expectedList);
        List<Course> actualList = courseService.findCourseStatus("available", 10, 1,"c.name","DESC");
        assertNotNull(actualList);
        assertEquals(expectedList.size(),actualList.size());
    }

    @Test
    void deleteCourseSuccess(){
        given(courseDao.deleteCourse(anyInt())).willReturn(true);
        boolean deleteCourse = courseService.deleteCourse(4);
        assertThat(deleteCourse).isTrue();
    }

    @Test
    void deleteCourseFailure(){
        given(courseDao.deleteCourse(anyInt())).willReturn(false);
        boolean deleteCourse = courseService.deleteCourse(4);
        assertThat(deleteCourse).isFalse();
    }

    @Test
    void findCourseTeacherTest(){
        Course courseExpected=new Course();
        List<Course> expectedList = new ArrayList<>();
        expectedList.add(courseExpected);

        given(courseDao.findCourseTeacher(anyInt(),anyInt(),anyInt(),anyString(),anyString())).willReturn(expectedList);
        List<Course> actualList = courseService.findCourseTeacher(4, 1, 1, "c.name", "DESC");
        assertNotNull(actualList);
        assertEquals(expectedList.size(),actualList.size());
    }

    @Test
    void findCoursesByTopicAndStatusTest(){
        Course courseExpected=new Course();
        List<Course> expectedList = new ArrayList<>();
        expectedList.add(courseExpected);

        given(courseDao.findCoursesByTopicAndAvailable(anyInt(),anyString(),anyInt(),anyInt(),anyString(),anyString())).willReturn(expectedList);
        List<Course> actualList = courseService.findCoursesByTopicAndStatus(3, "available", 10,1, "c.name", "DESC");
        assertNotNull(actualList);
        assertEquals(expectedList.size(),actualList.size());
    }

    @Test
    void findCourseByTopicSuccess(){
        Course courseExpected=new Course();
        List<Course> expectedList = new ArrayList<>();
        expectedList.add(courseExpected);

        given(courseDao.findCourseByTopic(anyInt())).willReturn(expectedList);
        List<Course> actualList = courseService.findCourseByTopic(5);
        assertNotNull(actualList);
        assertEquals(expectedList.size(),actualList.size());
    }

    @Test
    void getNumberOfRowsTest(){
        given(courseDao.getNumberOfRows(anyString(),anyString())).willReturn(3);
        int numberOfRowsActual = courseService.getNumberOfRows(UsersDao.SQLTask.GET_ALL_USERS_COUNT.getQUERY(),"progress");
        assertEquals(3,numberOfRowsActual);
    }

    @Test
    void getNumberOfRowsTeacherStatusTest(){
        given(courseDao.getNumberOfRowsTeacherStatus(anyString(),anyInt(),anyString())).willReturn(5);
        int numberOfRowsTeacherStatus = courseService.getNumberOfRowsTeacherStatus(CourseDao.SQLTask.GET_COURSE_BY_TEACHER_AND_STATUS_COUNT.getQUERY(), 4, "available");
        assertEquals(5,numberOfRowsTeacherStatus);
    }

    @Test
    void findCourseByTeacherTest(){
        Course courseExpected=new Course();
        List<Course> expectedList = new ArrayList<>();
        expectedList.add(courseExpected);

        given(courseDao.findCourseByTeacher(anyInt(),anyString())).willReturn(expectedList);
        List<CourseDto> actualList = courseService.findCourseByTeacher(3, "available");
//        courseUserService.countStudent(course.getId());

        assertNotNull(actualList);
        assertEquals(expectedList.size(),actualList.size());

        CourseDto courseDto = actualList.get(0);
        assertEquals(courseExpected.getId(),courseDto.getId());
        assertEquals(courseExpected.getName(),courseDto.getName());
        assertEquals(courseExpected.getTeacher(),courseDto.getTeacher());
        assertEquals(courseExpected.getDateStart(),courseDto.getDateStart());
        assertEquals(courseExpected.getDuration(),courseDto.getDuration());
        assertEquals(courseExpected.getDescription(),courseDto.getDescription());
        assertEquals(courseExpected.getStatus(),courseDto.getStatus());
//        assertEquals(courseExpected.getCountStudent(),courseDto.getCountStudent());

    }

    @Test
    void updateStatusCourseTest(){
        doNothing().when(courseDao).updateStatusCourse(anyString(),anyInt());
        courseService.updateStatusCourse("progress",5);
        verify(courseDao).updateStatusCourse(anyString(),anyInt());
    }

    @Test
    void createCourseSuccess(){

        given(courseDao.createCourse(any())).willReturn(2);
        int serviceCourse = courseService.createCourse("course", LocalDate.now().plusDays(1L), 4, "course", 3, 2, "progress",LocalizedValidatorUtil.UKRAINE_LOCALE);
        assertEquals(2,serviceCourse);
    }

    @Test
    void createCourseFailure(){
        try {
            courseService.createCourse("",LocalDate.of(2000,8,23),0," ",5,2,"progress",LocalizedValidatorUtil.UKRAINE_LOCALE);
            fail();
        }catch (CustomValidationException e){
            Map<String, String> errorsMap = e.getErrorsMap();
            assertEquals("Курс повинен мати назву більше 1 символа", errorsMap.get("name"));
            assertEquals("Курс має бути у майбутнєму часі",errorsMap.get("dateStart"));
            assertEquals("Курс повинен мати тривалість не меньш 1 тижня",errorsMap.get("duration"));
        }
    }

    @Test
    void updateCourseSuccess(){

        doNothing().when(courseDao).updateCourse(any());
        courseService.updateCourse(2,"course",LocalDate.now().plusDays(1L),3,"course",3,2, LocalizedValidatorUtil.UKRAINE_LOCALE);
        verify(courseDao).updateCourse(any());
    }

    @Test
    void updateCourseFailure(){
        try {
            courseService.updateCourse(2,"",LocalDate.of(2000,8,23),0," ",5,2, LocalizedValidatorUtil.UKRAINE_LOCALE);
            fail();
        }catch (CustomValidationException e){
            Map<String, String> errorsMap = e.getErrorsMap();
            assertEquals("Курс повинен мати назву більше 1 символа", errorsMap.get("name"));
            assertEquals("Курс має бути у майбутнєму часі",errorsMap.get("dateStart"));
            assertEquals("Курс повинен мати тривалість не меньш 1 тижня",errorsMap.get("duration"));
        }
    }


}
