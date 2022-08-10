package com.example.servletstest.model;

import javax.persistence.Column;

/**
 * DB model for courseUser table.
 */
public class CourseUser {
    private int id;
    @Column(name = "course_id")
    private Course course;
    @Column(name = "user_id")
    private User user;
    private String courseStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public static class CourseUserBuilder{
        private CourseUser courseUserBuilder;

        public CourseUserBuilder() {
            courseUserBuilder = new CourseUser();
        }

        public CourseUserBuilder withId(int id){
            courseUserBuilder.id=id;
            return this;
        }

        public CourseUserBuilder withCourse(Course course){
            courseUserBuilder.course=course;
            return this;
        }

        public CourseUserBuilder withUser(User user){
            courseUserBuilder.user=user;
            return this;
        }

        public CourseUserBuilder withCourseStatus(String courseStatus){
            courseUserBuilder.courseStatus=courseStatus;
            return this;
        }

        public CourseUser build(){
            return courseUserBuilder;
        }
    }
}
