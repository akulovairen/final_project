package com.example.servletstest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Gradebook data object for JSON conversion.
 */
public class GradebookDto {
    @JsonProperty("course_id")
    private Integer courseId;
    private List<Grades> data = new ArrayList<>();

    public GradebookDto() {
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public List<Grades> getData() {
        return data;
    }

    public void setData(List<Grades> data) {
        this.data = data;
    }
}
