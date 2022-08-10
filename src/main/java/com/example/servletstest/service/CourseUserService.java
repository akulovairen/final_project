package com.example.servletstest.service;

import com.example.servletstest.dao.CourseDao;
import com.example.servletstest.dao.CourseUserDao;
import com.example.servletstest.exception.CustomValidationException;
import com.example.servletstest.model.Course;
import com.example.servletstest.model.CourseUser;
import com.example.servletstest.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Business logic for courseUser functionality
 */
public class CourseUserService {
    CourseUserDao courseUserDao;
    private Logger log = LogManager.getLogger(this);

    /**
     * Create courseUser service.
     *
     * @param courseUserDao - {@link CourseUserDao}
     */
    public CourseUserService(CourseUserDao courseUserDao) {
        this.courseUserDao=courseUserDao;
    }

//    public CourseUser updateCourseUserByStatus(int courseId, int userId, String courseStatus) {
//        log.info("Updating courseUser with status={} for parameters: course_id={} and user_id={}", courseStatus, courseId, userId);
//        CourseUser courseUser = new CourseUser();
//
//        Course course = new Course();
//        course.setId(courseId);
//        courseUser.setCourse(course);
//
//        User user = new User();
//        user.setId(userId);
//        courseUser.setUser(user);
//
//        courseUser.setCourseStatus(courseStatus);
//        return courseUserDao.updateCourseUserByStatus(courseUser);
//
//    }

    /**
     * Creates courseUser in DB. Data validation.
     *
     * @param courseId - course id
     * @param userId - user id
     * @param courseStatus - course status
     * @return courseUser id
     * @throws CustomValidationException - if validation fails
     */
    public int createCourseUser(int courseId, int userId, String courseStatus) {
        log.info("Creating courseUser for parameters: course_id={}, user_id={},courseStatus={}", courseId, userId, courseStatus);
//        CourseUser courseUser = new CourseUser();
//        Course course = new Course();
//        course.setId(courseId);

        Course course = new Course.CourseBuilder().withId(courseId).build();

//        User user = new User();
//        user.setId(userId);

        User user = new User.UserBuilder().withId(userId).build();

        CourseUser courseUser = new CourseUser.CourseUserBuilder().withCourse(course).withUser(user).withCourseStatus(courseStatus).build();

//        courseUser.setCourse(course);
//
//        courseUser.setUser(user);
//        courseUser.setCourseStatus(courseStatus);

        return courseUserDao.createCourseUser(courseUser);
    }

    public int countStudent(int courseId){
       return courseUserDao.countStudent(courseId);
    }
}
