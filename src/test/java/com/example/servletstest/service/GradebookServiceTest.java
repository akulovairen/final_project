package com.example.servletstest.service;

import com.example.servletstest.dao.GradebookDao;
import com.example.servletstest.dao.TopicDao;
import com.example.servletstest.dto.GradebookDto;
import com.example.servletstest.dto.Grades;
import com.example.servletstest.exception.CustomValidationException;
import com.example.servletstest.model.Course;
import com.example.servletstest.model.Gradebook;
import com.example.servletstest.model.User;
import com.example.servletstest.util.LocalizedValidatorUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

class GradebookServiceTest {
    @Mock
    GradebookDao gradebookDao;
    GradebookService gradebookService;

    public GradebookServiceTest() {
        MockitoAnnotations.initMocks(this);
        this.gradebookService = new GradebookService(gradebookDao);
    }

    @Test
    void findAllGradesByStudentSuccess() {
        Gradebook gradebook = new Gradebook();
        ArrayList<Gradebook> expectedList = new ArrayList<>();
        expectedList.add(gradebook);

        given(gradebookDao.findAllByStudent(anyInt(), anyString(),anyString())).willReturn(expectedList);
        List<Gradebook> actualList = gradebookService.findAllGradesByStudent(5, "t.name", "DESC");
        assertNotNull(actualList);
        assertEquals(expectedList.size(), actualList.size());
    }

    @Test
    void findGradeByUserAndCourseTest() {
//        User userExpected = new User();
//        userExpected.setId(5);
//        Gradebook gradebook = new Gradebook();
//        gradebook.setStudent(new User(2, "Ivan", "Fomov", LocalDate.of(2000, 05, 11), anyString(), "123", "student"));
//        gradebook.setCourse(new Course());
//        gradebook.setStudent(userExpected);

        User userExpected = new User.UserBuilder().withId(5).build();

        Gradebook gradebook = new Gradebook.GradebookBuilder().withCourse(new Course()).withStudent(userExpected).build();

        given(gradebookDao.findGradeByUserAndCourse(anyInt(), anyInt())).willReturn(Optional.of(gradebook));

        Optional<Gradebook> gradebookActual = gradebookService.findGradeByUserAndCourse(4, 6);
        assertTrue(gradebookActual.isPresent());
        Gradebook gradebookActualObject = gradebookActual.get();

        assertEquals(gradebook.getId(), gradebookActualObject.getId());
    }

    @Test
    void findAllGradesByCourseTest() {
        Gradebook gradebook = new Gradebook();
        ArrayList<Gradebook> gradebookExpected = new ArrayList<>();
        gradebookExpected.add(gradebook);

        given(gradebookDao.findAllGradesByCourse(anyInt())).willReturn(gradebookExpected);
        List<Gradebook> actualList = gradebookService.findAllGradesByCourse(5);
        assertNotNull(actualList);
        assertEquals(gradebookExpected.size(), actualList.size());
    }

    @Test
    void totalScoreTest() {
//        when(gradebookService.totalScore(5.0, 1.0, 5.0, 0.0)).thenReturn(2.75);
        assertEquals(2.75,gradebookService.totalScore(5.0, 1.0, 5.0, 0.0));
    }

    @Test
    void createGradebookTest(){
        given(gradebookDao.createGradebook(any())).willReturn(2);
        int serviceGradebook = gradebookService.createGradebook(2,3);
        assertEquals(2,serviceGradebook);
    }

    @Test
    void updateGradebookByCourseSuccess(){
        GradebookDto gradebookDto=new GradebookDto();

//        Grades grades=new Grades();
        Map<String, Integer> gradesMap = new HashMap<>();
        gradesMap.put("test1", 4);
        gradesMap.put("test2", 5);
        gradesMap.put("test3", 3);
        gradesMap.put("test4", 3);

        Grades grades = new Grades.GradesBuilder().withGrades(gradesMap).withGradebookId(3).withStudentId(5).build();

//        grades.setGrades(gradesMap);
//        grades.setGradebookId(3);
//        grades.setStudentId(5);
        ArrayList<Grades> gradesList=new ArrayList<>();
        gradesList.add(grades);

        gradebookDto.setCourseId(3);
        gradebookDto.setData(gradesList);

        doNothing().when(gradebookDao).updateGradebookByCourse(any());
        gradebookService.updateGradebookByCourse(gradebookDto, LocalizedValidatorUtil.UKRAINE_LOCALE);
        verify(gradebookDao).updateGradebookByCourse(any());
    }

    @Test
    void updateGradebookByCourseFailure(){
        GradebookDto gradebookDto=new GradebookDto();

//        Grades grades=new Grades();
        Map<String, Integer> gradesMap = new HashMap<>();
        gradesMap.put("test1", 7);
        gradesMap.put("test2", 6);
        gradesMap.put("test3", 6);
        gradesMap.put("test4", 6);

        Grades grades = new Grades.GradesBuilder().withGrades(gradesMap).withGradebookId(3).withStudentId(5).build();

//        grades.setGrades(gradesMap);
//        grades.setGradebookId(3);
//        grades.setStudentId(5);
        ArrayList<Grades> gradesList=new ArrayList<>();
        gradesList.add(grades);

        gradebookDto.setCourseId(3);
        gradebookDto.setData(gradesList);
        try {
            gradebookService.updateGradebookByCourse(gradebookDto,LocalizedValidatorUtil.UKRAINE_LOCALE);
            fail();
        }catch (CustomValidationException e){
            Map<String, String> errorsMap = e.getErrorsMap();
            assertEquals("Оцінка повинна бути у межах від 0 до 5", errorsMap.get("test1"));
            assertEquals("Оцінка повинна бути у межах від 0 до 5", errorsMap.get("test2"));
            assertEquals("Оцінка повинна бути у межах від 0 до 5", errorsMap.get("test3"));
            assertEquals("Оцінка повинна бути у межах від 0 до 5", errorsMap.get("test4"));
        }
    }
}