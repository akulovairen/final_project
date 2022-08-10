package com.example.servletstest.dto;

import com.example.servletstest.model.Course;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.TreeMap;

public class Grades {
    @JsonProperty("student_id")
    private Integer studentId;
    @JsonProperty("gradebook_id")
    private Integer gradebookId;
    private Map<String, Integer> grades = new TreeMap<>();

    public Grades() {
    }

    public Integer getStudentId() {
        return studentId;
    }

//    public void setStudentId(Integer studentId) {
//        this.studentId = studentId;
//    }

    public Integer getGradebookId() {
        return gradebookId;
    }

//    public void setGradebookId(Integer gradebookId) {
//        this.gradebookId = gradebookId;
//    }

    public Map<String, Integer> getGrades() {
        return grades;
    }

//    public void setGrades(Map<String, Integer> grades) {
//        this.grades = grades;
//    }

    public static class GradesBuilder {
        private Grades gradesBuilder;

        public GradesBuilder() {
            gradesBuilder = new Grades();
        }

        public GradesBuilder withStudentId(Integer studentId) {
            gradesBuilder.studentId = studentId;
            return this;
        }

        public GradesBuilder withGradebookId(Integer gradebookId){
            gradesBuilder.gradebookId=gradebookId;
            return this;
        }

        public GradesBuilder withGrades(Map<String,Integer> grades){
            gradesBuilder.grades=grades;
            return this;
        }

        public Grades build(){
            return gradesBuilder;
        }
    }
}
