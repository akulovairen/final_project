package com.example.servletstest.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * DB model for course table.
 */
@Table(name = "course")
public class Course implements java.io.Serializable {
    @Id
    private int id;
    @NotEmpty(message = "{course.name.invalid}")
//    @Min(value = 3,message = "Курс повинен мати більше ніж 3 символа")
//    @Max(value = 5, message = "Should be <= 5 symbols")
    private String name;
    @ManyToOne
    @JoinColumn(name = "topic_id",nullable = false)
    private Topic topic;
    @NotNull
    @Column(name = "date_start")
    @Future(message = "{course.date.invalid}")
    private LocalDate dateStart;
//    @NotNull(message = "Поле не може бути порожнім")
    @Min(value = 1, message = "{course.duration.invalid}")
    private int duration;
    private String description;
    private User teacher;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public static class CourseBuilder{
        private Course courseBuilder;

         public CourseBuilder(){
             courseBuilder=new Course();
         }

         public CourseBuilder withId(int id){
             courseBuilder.id=id;
             return this;
         }

         public CourseBuilder withName(String name){
             courseBuilder.name=name;
             return this;
         }

         public CourseBuilder withTopic(Topic topic){
             courseBuilder.topic=topic;
             return this;
         }

         public CourseBuilder withDateStart(LocalDate dateStart){
             courseBuilder.dateStart=dateStart;
             return this;
         }

         public CourseBuilder withDuration(int duration){
             courseBuilder.duration=duration;
             return this;
         }

         public CourseBuilder withDescription(String description){
             courseBuilder.description=description;
             return this;
         }

         public CourseBuilder withTeacher(User teacher){
             courseBuilder.teacher=teacher;
             return this;
         }

         public CourseBuilder withStatus(String status){
             courseBuilder.status=status;
             return this;
         }

         public Course build(){
             return courseBuilder;
         }
    }
}
