<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag pageEncoding="UTF-8" %>
<html><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization"/>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" >Help In Wartime</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="/teacherPage"><fmt:message key="login.myCourse"/></a></li>
            <li class="active"><a href="/teacherCompleted"><fmt:message key="login.completedCourse"/></a></li>
            <li class="active"><a href="/teacherAvailableCourse"><fmt:message key="login.availableCourses"/></a></li>

        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/profile"><span class="glyphicon glyphicon-user"></span> <fmt:message key="login.profile"/></a></li>
            <li><a onclick="return confirmDelete();" href="/logout"><span class="glyphicon glyphicon-log-in"></span> <fmt:message key="login.logout"/></a></li>

            <script>
                function confirmDelete(){
                    if(confirm("Ви впевнені,що хочете вийти?")){
                        location.href='/logout';
                    }else {
                        return false;
                    }
                }
            </script>
        </ul>
    </div>
</nav>