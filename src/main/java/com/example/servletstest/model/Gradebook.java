package com.example.servletstest.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * DB model for gradebook table.
 */
public class Gradebook {
    int id;
    User student;
    @Max(value = 5, message = "{gradebook.test.invalid}")
    @Min(value = 0, message = "{gradebook.test.invalid}")
    int test1;
    @Max(value = 5, message = "{gradebook.test.invalid}")
    @Min(value = 0, message = "{gradebook.test.invalid}")
    int test2;
    @Max(value = 5, message = "{gradebook.test.invalid}")
    @Min(value = 0, message = "{gradebook.test.invalid}")
    int test3;
    @Max(value = 5, message = "{gradebook.test.invalid}")
    @Min(value = 0, message = "{gradebook.test.invalid}")
    int test4;
    double totalScore;
    Course course;

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public User getStudent() {
        return student;
    }

//    public void setStudent(User student) {
//        this.student = student;
//    }

    public int getTest1() {
        return test1;
    }

//    public void setTest1(int test1) {
//        this.test1 = test1;
//    }

    public int getTest2() {
        return test2;
    }

//    public void setTest2(int test2) {
//        this.test2 = test2;
//    }

    public int getTest3() {
        return test3;
    }

//    public void setTest3(int test3) {
//        this.test3 = test3;
//    }

    public int getTest4() {
        return test4;
    }

//    public void setTest4(int test4) {
//        this.test4 = test4;
//    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public static class GradebookBuilder{
        private Gradebook gradebookBuilder;

        public GradebookBuilder() {
            gradebookBuilder = new Gradebook();
        }

        public GradebookBuilder withId(int id){
            gradebookBuilder.id=id;
            return this;
        }

        public GradebookBuilder withStudent(User student){
            gradebookBuilder.student=student;
            return this;
        }

        public GradebookBuilder withTest1(int test1){
            gradebookBuilder.test1=test1;
            return this;
        }

        public GradebookBuilder withTest2(int test2){
            gradebookBuilder.test2=test2;
            return this;
        }

        public GradebookBuilder withTest3(int test3){
            gradebookBuilder.test3=test3;
            return this;
        }

        public GradebookBuilder withTest4(int test4){
            gradebookBuilder.test4=test4;
            return this;
        }

        public GradebookBuilder withTotalScore(double totalScore){
            gradebookBuilder.totalScore=totalScore;
            return this;
        }

        public GradebookBuilder withCourse(Course course){
            gradebookBuilder.course=course;
            return this;
        }

        public Gradebook build(){
            return gradebookBuilder;
        }
    }
}
