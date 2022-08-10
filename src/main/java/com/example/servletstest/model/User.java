package com.example.servletstest.model;

import javax.validation.constraints.*;
import java.sql.Date;
import java.time.LocalDate;

/**
 * DB model for user table.
 */
public class User {
    private int id;
//    @NotNull(message = "Ім'я повинно бути введено")
    @Size(min = 3, max = 25, message = "{register.name.invalid}")
    private String name;
//    @NotNull(message = "Прізвище повинно бути введено")
    @Size(min = 3, max = 40, message = "{register.surname.invalid}")
    private String surname;
    @NotNull()
//    @Pattern(regexp = "[a-zA-Z0-9-_.]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}", message = "Вказано невалідний email")
    @Email(message = "{register.email.invalid}")
    private String email;
    private String password;
    private String role;
    @Past(message = "{register.birthday.invalid}")
    private LocalDate birthday;
    private Boolean locked;

    public User() {

    }

    public Date getBirthday() {
        return Date.valueOf(birthday);
    }

//    public void setBirthday(LocalDate birthday) {
//        this.birthday = birthday;
//    }

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

//    public void setEmail(String email) {
//        this.email = email;
//    }

    public String getPassword() {
        return password;
    }

//    public void setPassword(String password) {
//        this.password = password;
//    }

    public String getRole() {
        return role;
    }

//    public void setRole(String role) {
//        this.role = role;
//    }

    public Boolean getLocked() {
        return locked;
    }

//    public void setLocked(Boolean locked) {
//        this.locked = locked;
//    }

    public User(int id, String name, String surname, LocalDate birthday, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static class UserBuilder{
        private User userBuilder;

        public UserBuilder(){
            userBuilder=new User();
        }

        public UserBuilder withId(int id){
            userBuilder.id=id;
            return this;
        }

        public UserBuilder withName(String name){
            userBuilder.name=name;
            return this;
        }

        public UserBuilder withSurname(String surname){
            userBuilder.surname=surname;
            return this;
        }

        public UserBuilder withBirthday(LocalDate birthday){
            userBuilder.birthday=birthday;
            return this;
        }

        public UserBuilder withEmail(String email){
            userBuilder.email=email;
            return this;
        }

        public UserBuilder withPassword(String password){
            userBuilder.password=password;
            return this;
        }

        public UserBuilder withRole(String role){
            userBuilder.role=role;
            return this;
        }

        public UserBuilder withLocked(Boolean locked){
            userBuilder.locked=locked;
            return this;
        }

        public User build(){
            return userBuilder;
        }
    }
}
