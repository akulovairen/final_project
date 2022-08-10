package com.example.servletstest.service;

import com.example.servletstest.dao.CourseDao;
import com.example.servletstest.dao.GradebookDao;
import com.example.servletstest.dto.GradebookDto;
import com.example.servletstest.exception.CustomValidationException;
import com.example.servletstest.model.Course;
import com.example.servletstest.model.Gradebook;
import com.example.servletstest.model.User;
import com.example.servletstest.util.LocalizedValidatorUtil;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Business logic for gradebook functionality
 */
public class GradebookService {
    GradebookDao gradebookDao;
    private Logger log = LogManager.getLogger(GradebookService.class);
//    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//    private Validator validator = factory.getValidator();


    /**
     * Create gradebook service.
     *
     * @param gradebookDao - {@link GradebookDao} model
     */
    public GradebookService(GradebookDao gradebookDao) {
        this.gradebookDao=gradebookDao;
    }

    /**
     * Finds all grades by student id.
     *
     * @param student_id - user id
     * @return {@link List<Gradebook>} with sorting by name
     */
    public List<Gradebook> findAllGradesByStudent(int student_id, String sortingColumn, String sortingMode) {
        log.info("Getting all gradebooks for student by student_id={}", student_id);
        List<Gradebook> allGradesByStudent = gradebookDao.findAllByStudent(student_id, sortingColumn, sortingMode);
        return allGradesByStudent;
    }

    public Map<String, List<Gradebook>> findGradebookForTeacher(int courseId) {
        log.info("Getting gradebooks for teacher by course_id={}", courseId);
        List<Gradebook> gradebookList = gradebookDao.findGradebook(courseId);
        gradebookList.sort(Comparator.comparing(o -> o.getCourse().getName()));
        Map<String, List<Gradebook>> mapGradebook = gradebookList.stream().collect(Collectors.groupingBy(gradebook1 -> gradebook1.getCourse().getName(), Collectors.mapping(gradebook1 -> gradebook1, Collectors.toList())));
        Map<String, List<Gradebook>> sortedGradebookMap = new TreeMap<>(mapGradebook);
        return sortedGradebookMap;
    }

    /**
     * Finds grades by student id, course id
     *
     * @param student_id - user id
     * @param course_id - course id
     * @return
     */
    public Optional<Gradebook> findGradeByUserAndCourse(int student_id, int course_id) {
        log.info("Getting grades by student_id={} and course_id={}", student_id, course_id);
        return gradebookDao.findGradeByUserAndCourse(student_id, course_id);

    }

    public List<Gradebook> findAllGradesByCourse(int courseId) {
        log.info("Getting all grades by course_id={}", courseId);
        return gradebookDao.findAllGradesByCourse(courseId);

    }

    public double totalScore(double test1,double test2, double test3, double test4) {
        log.info("Counting total score for student");
        double totalScore = (double) (test1 + test2 + test3 + test4) / 4;
        return totalScore;
    }

    /**
     * Creates gradebook. Data validation.
     *
     * @param student - student id
     * @param courseId - course id
     * @return gradebook id
     */
    public int createGradebook(int student, int courseId) {
        log.info("Creating gradebook");
//        Gradebook gradebook = new Gradebook();

//        User user = new User();
//        user.setId(student);
//        gradebook.setStudent(user);
        User user = new User.UserBuilder().withId(student).build();

        Course course = new Course();
        course.setId(courseId);
//        gradebook.setCourse(course);

        Gradebook gradebook = new Gradebook.GradebookBuilder().withStudent(user).withCourse(course).build();

        return gradebookDao.createGradebook(gradebook);
    }

    public Gradebook updateGradebook(Gradebook gradebook) {
        log.info("Updating gradebook");
        double totalScore = totalScore(gradebook.getTest1(),gradebook.getTest2(),gradebook.getTest3(),gradebook.getTest4());
        gradebook.setTotalScore(totalScore);
        return gradebookDao.updateGradebook(gradebook);
    }

    /**
     * Updates gradebook by course. Data validation.
     *
     * @param gradebookDto - {@link GradebookDto} model
     * @throws CustomValidationException if validation fails
     */
    public void updateGradebookByCourse(GradebookDto gradebookDto, Locale locale){
        log.info("Updating gradebook by courseId");

        Integer courseId = gradebookDto.getCourseId();

        List<Gradebook> gradebookList = gradebookDto.getData().stream().map(gradesObj -> {
//            Gradebook gradebook = new Gradebook();
            Map<String, Integer> gradesMap = gradesObj.getGrades();
//            gradebook.setTest1(gradesMap.getOrDefault("test1", 0));
//            gradebook.setTest2(gradesMap.getOrDefault("test2", 0));
//            gradebook.setTest3(gradesMap.getOrDefault("test3", 0));
//            gradebook.setTest4(gradesMap.getOrDefault("test4", 0));

//            Course course = new Course();
//            course.setId(courseId);

            Course course = new Course.CourseBuilder().withId(courseId).build();

//            User user = new User();
//            user.setId(gradesObj.getStudentId());

            User user = new User.UserBuilder().withId(gradesObj.getStudentId()).build();

//            gradebook.setCourse(course);
//            gradebook.setStudent(user);
//
//            gradebook.setId(gradesObj.getGradebookId());

            Gradebook gradebook = new Gradebook.GradebookBuilder().withId(gradesObj.getGradebookId())
                    .withTest1(gradesMap.getOrDefault("test1", 0))
                    .withTest2(gradesMap.getOrDefault("test2", 0))
                    .withTest3(gradesMap.getOrDefault("test3", 0))
                    .withTest4(gradesMap.getOrDefault("test4", 0))
                    .withCourse(course)
                    .withStudent(user)
                    .build();

            double totalScore = totalScore(gradebook.getTest1(),gradebook.getTest2(),gradebook.getTest3(),gradebook.getTest4());
            gradebook.setTotalScore(totalScore);


            Validator validator = LocalizedValidatorUtil.getValidatorByLocale(locale);
            Set<ConstraintViolation<Gradebook>> constraintViolationSet = validator.validate(gradebook);
            Map<String, String> errorMap =
                    constraintViolationSet
                            .stream()
                            .collect(Collectors.toMap(userConstraintViolation -> userConstraintViolation.getPropertyPath().toString(), ConstraintViolation::getMessage));

            if (!errorMap.isEmpty()) {
                throw new CustomValidationException(errorMap);
            }

            return gradebook;
        }).collect(Collectors.toList());

        gradebookDao.updateGradebookByCourse(gradebookList);
    }
// Todo: Сделать валидацию в JSP
}
