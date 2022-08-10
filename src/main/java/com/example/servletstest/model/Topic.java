package com.example.servletstest.model;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * DB model for topic table.
 */
@Table(name = "topic")
public class Topic {
    @Id
    private int id;
    @Min(message = "{topic.name.invalid}", value = 3)
    private String name;

    @OneToMany(mappedBy = "course")
    private List<Course> courses;

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public static class TopicBuilder{
        private Topic topicBuilder;

        public TopicBuilder(){
            topicBuilder=new Topic();
        }

        public TopicBuilder withId(int id){
            topicBuilder.id=id;
            return this;
        }

        public TopicBuilder withName(String name){
            topicBuilder.name=name;
            return this;
        }

        public Topic build(){
            return topicBuilder;
        }
    }
}
