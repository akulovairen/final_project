package com.example.servletstest.filters;

import com.example.servletstest.model.RoleEnum;

import java.util.*;

/**
 * Security configuration class that allows roles to open URLs.
 */
public class SecurityConfig {
    private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();

    static {
        init();
    }

    private static void init() {

        // Configuration for role "admin"
        List<String> urlPatternsAdmin = new ArrayList<String>();

        urlPatternsAdmin.add("/adminPage");
        urlPatternsAdmin.add("/blockUnblockStudent");
        urlPatternsAdmin.add("/changeCourseStatus");
        urlPatternsAdmin.add("/completedCourseAdmin");
        urlPatternsAdmin.add("/adminCoursesForTeacher");
        urlPatternsAdmin.add("/adminCoursesForTeacher");
        urlPatternsAdmin.add("/profile");
        urlPatternsAdmin.add("/logout");
        urlPatternsAdmin.add("/editProfile");
        urlPatternsAdmin.add("/createCourse");
        urlPatternsAdmin.add("/deleteCourse");
        urlPatternsAdmin.add("/adminCoursesAvailableList");
        urlPatternsAdmin.add("/progressCourseAdmin");
        urlPatternsAdmin.add("/progressCourseAdmin");
        urlPatternsAdmin.add("/editCourse");
        urlPatternsAdmin.add("/adminCoursesList");
        urlPatternsAdmin.add("/registrationTeacher");
        urlPatternsAdmin.add("/adminTeacherList");
        urlPatternsAdmin.add("/createTopic");
        urlPatternsAdmin.add("/deleteTopic");
        urlPatternsAdmin.add("/courseByTopicAdmin");
        urlPatternsAdmin.add("/teacherSearch");
        urlPatternsAdmin.add("/adminCourseByTopicProgress");
        urlPatternsAdmin.add("/adminCourseByTopicAvailable");
        urlPatternsAdmin.add("/adminCourseByTopicFinished");
        urlPatternsAdmin.add("/completedCourseAdmin");
        urlPatternsAdmin.add("/images");


        mapConfig.put(RoleEnum.ADMIN.getValue(), urlPatternsAdmin);

        // Configuration for role "student"
        List<String> urlPatternsStudent = new ArrayList<String>();

        urlPatternsStudent.add("/coursesCompleted");
        urlPatternsStudent.add("/gradebookStudent");
        urlPatternsStudent.add("/coursesInProgress");
        urlPatternsStudent.add("/coursesAvailable");
        urlPatternsStudent.add("/coursesDescription");
        urlPatternsStudent.add("/profile");
        urlPatternsStudent.add("/logout");
        urlPatternsStudent.add("/editProfile");
        urlPatternsStudent.add("/courseByTopic");
        urlPatternsStudent.add("/courseRegister");
        urlPatternsStudent.add("/images");

        mapConfig.put(RoleEnum.STUDENT.getValue(), urlPatternsStudent);

        // Configuration for role "teacher"
        List<String> urlPatternsTeacher = new ArrayList<String>();

        urlPatternsTeacher.add("/teacherPage");
        urlPatternsTeacher.add("/teacherGradebook");
        urlPatternsTeacher.add("/teacherCompleted");
        urlPatternsTeacher.add("/courseDescriptionTeacher");
        urlPatternsTeacher.add("/profile");
        urlPatternsTeacher.add("/logout");
        urlPatternsTeacher.add("/editProfile");
        urlPatternsTeacher.add("/teacherGradebookByCourse");
        urlPatternsTeacher.add("/searchCoursesTeacher");
        urlPatternsTeacher.add("/teacherAvailableCourse");
        urlPatternsTeacher.add("/images");

        mapConfig.put(RoleEnum.TEACHER.getValue(), urlPatternsTeacher);

    }

    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }

}
