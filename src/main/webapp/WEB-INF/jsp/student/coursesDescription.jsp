<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization"/>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Course Description</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">

    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/all.js"></script>
    <link href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" rel="stylesheet">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css"
          integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">
    <style>
        .how-section1 {
            margin-top: -15%;
            padding: 10%;
        }

        .how-section1 h4 {
            color: #ffa500;
            font-weight: bold;
            font-size: 30px;
        }

        .how-section1 .subheading {
            color: #3931af;
            font-size: 20px;
        }

        .how-section1 .row {
            margin-top: 10%;
        }

        .how-img {
            text-align: center;
        }

        .how-img img {
            width: 40%;
        }
    </style>
</head>
<body>

<navbar:NavStudent/>

<div class="how-section1">
    <div class="row">
        <div class="col-md-6">
            <h4>${course.name}</h4>
            <h4 class="subheading"><em><fmt:message key="login.dataStart"/></em>: <c:out
                    value="${course.dateStart}"/><br>
                <em><fmt:message key="login.durationInWeeks"/></em>: <c:out value="${course.duration}"/><br>
                <em><fmt:message key="login.teacher"/></em>: <c:out
                        value="${course.teacher.surname} ${course.teacher.name}"/><br></h4>
            <p class="text-muted">
                ${course.description} <br></p>
        </div>
        <div class="col-md-6 how-img">
            <img src="/images?filename=Разом ми сила.jpg"/>
        </div>
    </div>
    <c:choose>
    <c:when test="${gradeByUserAndCourse == null}">
        <form method="post" action="/coursesDescription">
            <input type="hidden" name="courseId" id="courseId" value="${course.id}"/>
            <input class="btn btn-primary" type="submit" value="<fmt:message key="login.register"/>">
        </form>
    </c:when>
    <c:otherwise>
</div>
<div class="container">
    <table class="table table-bordered">
        <h2>
            <fmt:message key="gradebook.my"/>
        </h2>
        <thead>
        <tr>
            <th><fmt:message key="login.name"/></th>
            <th><fmt:message key="login.test"/> 1</th>
            <th><fmt:message key="login.test"/> 2</th>
            <th><fmt:message key="login.test"/> 3</th>
            <th><fmt:message key="login.test"/> 4</th>
            <th><fmt:message key="login.totalScore"/></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${gradeByUserAndCourse.course.name}</td>
            <td>${gradeByUserAndCourse.test1}</td>
            <td>${gradeByUserAndCourse.test2}</td>
            <td>${gradeByUserAndCourse.test3}</td>
            <td>${gradeByUserAndCourse.test4}</td>
            <td>${gradeByUserAndCourse.totalScore}</td>
        </tr>
        </tbody>
    </table>
    </c:otherwise>
    </c:choose>
</div>

</body>
</html>
