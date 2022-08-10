package com.example.servletstest.service;

import com.example.servletstest.dao.CourseDao;
import com.example.servletstest.dao.CourseUserDao;
import com.example.servletstest.dto.CourseDto;
import com.example.servletstest.exception.CustomValidationException;
import com.example.servletstest.model.Course;
import com.example.servletstest.model.Topic;
import com.example.servletstest.model.User;
import com.example.servletstest.util.LocalizedValidatorUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Business logic for course functionality
 */
public class CourseService {
    private CourseDao courseDao = new CourseDao();
    private final CourseUserService courseUserService=new CourseUserService(new CourseUserDao());
    private final Logger log = LogManager.getLogger(CourseService.class);
    private  ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator = factory.getValidator();

    /**
     * Create course service.
     *
     * @param courseDao - {@link CourseDao}
     */
    public CourseService(CourseDao courseDao) {
        this.courseDao=courseDao;
    }

    /**
     * Finds list of courses in DB.
     *
     * @param userId user id
     * @param status status  of course
     * @return {@link List<Course>} with sorting by name
     */
    public List<Course> findByUserIdAndStatus(int userId, String status) {
        log.info("Getting courses by user_id={} and status={}", userId, status);
        List<Course> byUserIdAndStatus = courseDao.findByUserIdAndStatus(userId, status);
        byUserIdAndStatus.sort(Comparator.comparing(Course::getName));
        return byUserIdAndStatus;
    }

    /**
     * Finds list of courses by status for guests in DB.
     *
     * @return {@link List<Course>}
     */
    public List<Course> findByStatusForGuest() {
        log.info("Getting courses by status available");
        return courseDao.findCourseForGuest();
    }

    /**
     * Finds list of courses with status available by topic id, user id.
     *
     * @param topicId - topic id
     * @param userId - user id
     * @return {@link List<Course>} with sorting by name
     */
    public List<Course> findByStatusAvailableByTopic(int topicId, int userId) {
        log.info("Getting courses by status available and topic id");
        List<Course> byStatusAvailable = courseDao.findByStatusAvailableByTopic(topicId, userId);
        byStatusAvailable.sort(Comparator.comparing(Course::getName));
        return byStatusAvailable;
    }

    /**
     * Finds list of courses with status available by topic id, user id.
     *
     * @param userId - user id
     * @return {@link List<Course>} with sorting by name
     */
    public List<Course> findByStatusAvailable(int userId) {
        log.info("Getting courses by status available");
        List<Course> byStatusAvailable = courseDao.findByStatusAvailable(userId);
        byStatusAvailable.sort(Comparator.comparing(Course::getName));
        return byStatusAvailable;
    }

    /**
     * Finds list of courses with status register by user id.
     *
     * @param userId - user id
     * @return {@link List<Course>} with sorting by name
     */
    public List<Course> findCourseByUserRegister(int userId) {
        List<Course> userRegister = courseDao.findCourseByUserRegister(userId);
        userRegister.sort(Comparator.comparing(Course::getName));
        return userRegister;
    }

    public List<Course> findCourseNotFinished(int teacherId, int limit, int offset) {
        log.info("Getting all courses not finished status");
        return courseDao.findCourseNotFinished(teacherId, limit, offset);
    }

    /**
     * Creates course in DB. Data validation.
     *
     * @param name - course name
     * @param dateStart - course dateStart
     * @param duration - course duration
     * @param description - course description
     * @param topicId - topic id
     * @param teacherId - teacher id
     * @param status - course status
     * @return course-{@link Course} model
     * @throws CustomValidationException - if validation fails
     */
    public int createCourse(String name, LocalDate dateStart, int duration, String description, int topicId, int teacherId, String status,Locale locale) throws CustomValidationException {
        log.info("Creating course for parameters: name={}, dateStart={}, duration={}, description={}, topicId={}, teacherId={}, status={}", name, dateStart, duration, description, topicId, teacherId, status);
//        Course course = new Course();

//        Topic topic = new Topic();
//        topic.setId(topicId);
//        course.setTopic(topic);

        Topic topic = new Topic.TopicBuilder().withId(topicId).build();

//        User user = new User();
//        user.setId(teacherId);
//        course.setTeacher(user);
        User user = new User.UserBuilder().withId(teacherId).build();

        Course course = new Course.CourseBuilder()
                .withName(name)
                .withDateStart(dateStart)
                .withDuration(duration)
                .withDescription(description)
                .withTopic(topic)
                .withTeacher(user)
                .withStatus(status).build();

//        course.setName(name);
//        course.setDateStart(dateStart);
//        course.setDuration(duration);
//        course.setDescription(description);
//        course.setStatus(status);

        Validator validator = LocalizedValidatorUtil.getValidatorByLocale(locale);
        Set<ConstraintViolation<Course>> constraintViolationSet = validator.validate(course);
        Map<String, String> errorMap =
                constraintViolationSet
                        .stream()
                        .collect(Collectors.toMap(userConstraintViolation -> userConstraintViolation.getPropertyPath().toString(), ConstraintViolation::getMessage));

        if (!errorMap.isEmpty()) {
            throw new CustomValidationException(errorMap);
        }

        return courseDao.createCourse(course);

    }

    public Optional<Course> findCourse(int courseId) {
        log.info("Getting course by course_id={}", courseId);
        return courseDao.findCourse(courseId);

    }

    public List<Course> findCourseStatus(String status, int limit, int offset, String sortingColumn, String sortingMode) {
        log.info("Getting courses by status={}", status);
        List<Course> courseStatus = courseDao.findCourseStatus(status, limit, offset, sortingColumn, sortingMode);
        return courseStatus;
    }

    public boolean deleteCourse(int courseId) {
        log.info("Deleting course by course_id={}", courseId);
        return courseDao.deleteCourse(courseId);
    }

    public void updateStatusCourse(String status, int courseId) {
        log.info("Updating course by status={} and course_id={}", status, courseId);
        courseDao.updateStatusCourse(status, courseId);
    }

    /**
     * Updates course. Data validation.
     *
     * @param id - course id
     * @param name - course name
     * @param dateStart - course date start
     * @param duration - course duration
     * @param description - course description
     * @param topicId - topic id
     * @param teacherId - teacher id
     * @throws CustomValidationException - if validation fails
     */
    public void updateCourse(int id, String name, LocalDate dateStart, int duration, String description, int topicId, int teacherId, Locale locale) throws CustomValidationException {
        log.info("Updating course by id={},name={},dateStart={},duration={},description={},topicId={},teacherId={}", id, name, dateStart, duration, description, topicId, teacherId);
//        Course course = new Course();

//        Topic topic = new Topic();
//        topic.setId(topicId);
//        course.setTopic(topic);
        Topic topic = new Topic.TopicBuilder().withId(topicId).build();

//        User user = new User();
//        user.setId(teacherId);
//        course.setTeacher(user);
        User user = new User.UserBuilder().withId(teacherId).build();

        Course course = new Course.CourseBuilder()
                .withId(id)
                .withName(name)
                .withDateStart(dateStart)
                .withDuration(duration)
                .withDescription(description)
                .withTopic(topic)
                .withTeacher(user)
                .build();

//        course.setId(id);
//        course.setName(name);
//        course.setDateStart(dateStart);
//        course.setDuration(duration);
//        course.setDescription(description);

        Validator validator = LocalizedValidatorUtil.getValidatorByLocale(locale);
        Set<ConstraintViolation<Course>> constraintViolationSet = validator.validate(course);
        Map<String, String> errorMap =
                constraintViolationSet
                        .stream()
                        .collect(Collectors.toMap(userConstraintViolation -> userConstraintViolation.getPropertyPath().toString(), ConstraintViolation::getMessage));

        if (!errorMap.isEmpty()) {
            throw new CustomValidationException(errorMap);
        }

        courseDao.updateCourse(course);
    }

    /**
     * Finds list of courses for teacher by teacher id, course status.
     *
     * @param id - teacher id
     * @param status - course status
     * @param limit
     * @param offset
     * @return {@link List<CourseDto>} with sorting by name
     */
    public List<CourseDto> findCourseByTeacher(int id, String status, int limit, int offset) {
        log.info("Getting courses for teacher by id={} and status={}", id, status);
        List<Course> courseByTeacher = courseDao.findCourseByTeacher(id, status, limit, offset);
        List<CourseDto> courseDtoList = courseByTeacher.stream().map(course -> {
            CourseDto courseDto = new CourseDto();
            courseDto.setId(course.getId());
            courseDto.setName(course.getName());
            courseDto.setDescription(course.getDescription());
            courseDto.setDuration(course.getDuration());
            courseDto.setDateStart(course.getDateStart());
            courseDto.setTeacher(course.getTeacher());
            courseDto.setStatus(course.getStatus());
            courseDto.setTopic(course.getTopic());
            courseDto.setCountStudent(courseUserService.countStudent(course.getId()));
            return courseDto;
        }).collect(Collectors.toList());
        courseDtoList.sort(Comparator.comparing(CourseDto::getName));
        return courseDtoList;
    }

    /**
     * Finds list of courses by teacher id.
     *
     * @param id - teacher id
     * @param limit
     * @param offset
     * @return {@link List<Course>} with sorting by name
     */
    public List<Course> findCourseTeacher(int id, int limit, int offset, String sortingColumn, String sortingMode){
        log.info("Getting courses for teacher by id={}", id);
        List<Course> courseTeacher = courseDao.findCourseTeacher(id, limit, offset,sortingColumn,sortingMode);
//        courseTeacher.sort(Comparator.comparing(Course::getName));
        return courseTeacher;

    }

    /**
     * Finds list of courses by topic id, course status.
     *
     * @param topicId - topic id
     * @param status - course status
     * @return {@link List<Course>} with sorting by name
     */
    public List<Course> findCoursesByTopicAndStatus(int topicId,String status,int limit, int offset,String sortingColumn, String sortingMode) {
        List<Course> coursesByTopicAndAvailable = courseDao.findCoursesByTopicAndAvailable(topicId, status ,limit, offset, sortingColumn,sortingMode);
//        coursesByTopicAndAvailable.sort(Comparator.comparing(Course::getName));
        return coursesByTopicAndAvailable;
    }

    /**
     * Finds list of courses by topic id.
     *
     * @param topicId - topic id
     * @return {@link List<Course>} with sorting by name
     */
    public List<Course> findCourseByTopic(int topicId) {
        List<Course> courseByTopic = courseDao.findCourseByTopic(topicId);
        courseByTopic.sort(Comparator.comparing(Course::getName));
        return courseByTopic;
    }

    public int getNumberOfRows(String sql, String status) {
        return courseDao.getNumberOfRows(sql,status);
    }

    public int getNumberOfRowsTeacherStatus(String sql,int teacherId,String status) {
        return courseDao.getNumberOfRowsTeacherStatus(sql,teacherId,status);
    }

    public int getNumberOfRowsTeacher(String sql,int teacherId) {
        return courseDao.getNumberOfRowsTeacher(sql,teacherId);
    }

    public List<Course> searchCoursesProgressTeacher(String text) {
        return courseDao.searchCoursesProgressTeacher(text);
    }

    public int getNumberOfRowsAdminCourseTopicStatus(String sql,int topicId, String status) {
        return courseDao.getNumberOfRowsAdminCourseTopicStatus(sql,topicId,status);
    }
}
