<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization"/>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Completed Course</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

    <!-- popper.js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>

    <!-- ajax -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- bootstrap js -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>


    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">

    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/all.js"></script>
    <link href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" rel="stylesheet">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css"
          integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">

    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/all.js"></script>
    <link href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" rel="stylesheet">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css"
          integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">

</head>
<body>
<navbar:NavAdmin/>

<div class="container">
    <div class="btn-group dropright">
        <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <fmt:message key="course.byStatus"/>
        </button>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="/adminCoursesAvailableList"><fmt:message key="login.availableCourses"/></a>
            <a class="dropdown-item" href="/adminCoursesList"><fmt:message key="login.courseInProgress"/></a>
            <a class="dropdown-item" href="/completedCourseAdmin"><fmt:message key="login.completedCourse"/></a>
        </div>
    </div>

    <div class="btn-group dropright">
        <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <fmt:message key="course.byTopic"/>
        </button>
        <div class="dropdown-menu">
            <c:forEach var="topic" items="${allTopic}">
                <a class="dropdown-item" href="adminCourseByTopicFinished?topic_id=${topic.id}"><c:out value="${topic.name}"/></a>
            </c:forEach>
        </div>
    </div>
</div><br/>
<div class="container">
    <h3><fmt:message key="login.completedCourse"/></h3>
    <table class="table table-bordered table-hover">
        <thead thead-ligh>
        <tr>
            <th><fmt:message key="login.topic"/>
                <c:choose>
                    <c:when test="${sortingMode eq 'DESC'}">
                        <a href="adminCoursesList?sortingColumn=t.name&sortingMode=ASC"><i class="fa fa-sort-up"></i></a>
                    </c:when>
                    <c:otherwise>
                        <a href="adminCoursesList?sortingColumn=t.name&sortingMode=DESC"><i class="fa fa-sort-down"></i></a>
                    </c:otherwise>
                </c:choose>
            </th>
            <th><fmt:message key="login.name"/>
                <c:choose>
                    <c:when test="${sortingMode eq 'DESC'}">
                        <a href="adminCoursesList?sortingColumn=c.name&sortingMode=ASC"><i class="fa fa-sort-up"></i></a>
                    </c:when>
                    <c:otherwise>
                        <a href="adminCoursesList?sortingColumn=c.name&sortingMode=DESC"><i class="fa fa-sort-down"></i></a>
                    </c:otherwise>
                </c:choose></th>
            <th><fmt:message key="login.dataStart"/>
                <c:choose>
                    <c:when test="${sortingMode eq 'DESC'}">
                        <a href="adminCoursesList?sortingColumn=c.date_start&sortingMode=ASC"><i class="fa fa-sort-up"></i></a>
                    </c:when>
                    <c:otherwise>
                        <a href="adminCoursesList?sortingColumn=c.date_start&sortingMode=DESC"><i class="fa fa-sort-down"></i></a>
                    </c:otherwise>
                </c:choose></th>
            <th><fmt:message key="login.durationInWeeks"/>
                <c:choose>
                    <c:when test="${sortingMode eq 'DESC'}">
                        <a href="adminCoursesList?sortingColumn=c.duration&sortingMode=ASC"><i class="fa fa-sort-up"></i></a>
                    </c:when>
                    <c:otherwise>
                        <a href="adminCoursesList?sortingColumn=c.duration&sortingMode=DESC"><i class="fa fa-sort-down"></i></a>
                    </c:otherwise>
                </c:choose></th>
            <th><fmt:message key="login.teacher"/>
                <c:choose>
                    <c:when test="${sortingMode eq 'DESC'}">
                        <a href="adminCoursesList?sortingColumn=u.surname&sortingMode=ASC"><i class="fa fa-sort-up"></i></a>
                    </c:when>
                    <c:otherwise>
                        <a href="adminCoursesList?sortingColumn=u.surname&sortingMode=DESC"><i class="fa fa-sort-down"></i></a>
                    </c:otherwise>
                </c:choose></th>
            <th><fmt:message key="course.status"/></th>
            <th><fmt:message key="login.edit"/></th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${finished == null || finished.isEmpty()}"><br/>
                <h5 style="color: red"><fmt:message key="course.notFound"/></h5>
        </c:when>
        <c:otherwise><br/>
        <c:forEach var="course" items="${finished}">
            <tr>
                <td><c:out value="${course.topic.name }"/></td>
                <td><c:out value="${course.name }"/></td>
                <td><c:out value="${course.dateStart }"/></td>
                <td><c:out value="${course.duration }"/></td>
                <td><c:out value="${course.teacher.surname} ${course.teacher.name}"/></td>
                <td>
                    <fmt:message key="course.completed"/>
                </td>
                <td><a id="edit1"
                       href="/editCourse?course_id=${course.id}">
                    <button type="button"><fmt:message key="login.edit"/></button>
                </a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
<nav aria-label="Navigation for countries">
    <ul class="pagination">
        <c:if test="${currentPage != 1}">
            <li class="page-item"><a class="page-link"
                                     href="completedCourseAdmin?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}&sortingColumn=${sortingColumn}&sortingMode=${sortingMode}"><fmt:message key="pagin.previous"/></a>
            </li>
        </c:if>

        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <li class="page-item active"><a class="page-link">
                            ${i} <span class="sr-only">(current)</span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link"
                                             href="completedCourseAdmin?recordsPerPage=${recordsPerPage}&currentPage=${i}&sortingColumn=${sortingColumn}&sortingMode=${sortingMode}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <li class="page-item"><a class="page-link"
                                     href="completedCourseAdmin?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}&sortingColumn=${sortingColumn}&sortingMode=${sortingMode}"><fmt:message key="pagin.next"/></a>
            </li>
        </c:if>
    </ul>
</nav>
    </c:otherwise>
    </c:choose>
</div>
<%--<table class="table table-hover">--%>
<%--    <thead>--%>
<%--    <tr>--%>
<%--        <th scope="col">Тема</th>--%>
<%--        <th scope="col">Назва</th>--%>
<%--        <th scope="col">Дата початку</th>--%>
<%--        <th scope="col">Тривалість</th>--%>
<%--        <th scope="col">Викладач</th>--%>
<%--&lt;%&ndash;        <th scope="col">Статус</th>&ndash;%&gt;--%>
<%--        <th scope="col">Редагувати</th>--%>
<%--    </tr>--%>
<%--    </thead>--%>
<%--    <tbody>--%>
<%--    <tr>--%>
<%--        <c:forEach var="course" items="${finished}">--%>
<%--    <tr>--%>

<%--        <td><c:out value="${course.topic.name }"/></td>--%>
<%--        <td><c:out value="${course.name }"/></td>--%>
<%--        <td><c:out value="${course.dateStart }"/></td>--%>
<%--        <td><c:out value="${course.duration }"/></td>--%>
<%--        <td><c:out value="${course.teacher.surname} ${course.teacher.name}"/></td>--%>
<%--&lt;%&ndash;        <td>&ndash;%&gt;--%>
<%--&lt;%&ndash;            <form action="/changeCourseStatus?status=progress&course_id=${course.id}" method="post">&ndash;%&gt;--%>
<%--&lt;%&ndash;                <input type="submit" value="Запустить"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;            </form>&ndash;%&gt;--%>
<%--&lt;%&ndash;        </td>&ndash;%&gt;--%>
<%--        <td><a--%>
<%--                href="/editCourse?course_id=${course.id}">--%>
<%--            <button type="button">Редагувати</button>--%>
<%--        </a></td>--%>

<%--    </tr>--%>
<%--    </c:forEach>--%>
<%--    </tbody>--%>
<%--</table>--%>
<%--<div class="container">--%>
<%--    <h1>Архівні курси</h1>--%>

<%--    <div id="toolbar">--%>
<%--    </div>--%>

<%--    <table id="table"--%>
<%--           data-toggle="table"--%>
<%--           data-search="true"--%>
<%--           data-filter-control="true"--%>
<%--           data-show-export="true"--%>
<%--           data-click-to-select="true"--%>
<%--           data-toolbar="#toolbar"--%>
<%--           class="table-responsive">--%>
<%--        <thead>--%>
<%--        <tr>--%>
<%--            <th data-field="topic" data-filter-control="input" data-sortable="true">Тема</th>--%>
<%--            <th data-field="name" data-filter-control="input" data-sortable="true">Назва</th>--%>
<%--            <th data-field="dateStart" data-filter-control="input" data-sortable="true">Дата початку</th>--%>
<%--            <th data-field="duration" data-filter-control="select" data-sortable="true">Тривалість</th>--%>
<%--            <th data-field="teacher" data-filter-control="select" data-sortable="true">Викладач</th>--%>
<%--            <th data-field="edit" data-sortable="false">Редагувати</th>--%>
<%--        </tr>--%>
<%--        </thead>--%>
<%--        <tbody>--%>
<%--            <c:forEach var="course" items="${finished}">--%>
<%--        <tr>--%>

<%--            <td><c:out value="${course.topic.name }"/></td>--%>
<%--            <td><c:out value="${course.name }"/></td>--%>
<%--            <td><c:out value="${course.dateStart }"/></td>--%>
<%--            <td><c:out value="${course.duration }"/></td>--%>
<%--            <td><c:out value="${course.teacher.surname} ${course.teacher.name}"/></td>--%>

<%--            <td><a href="/editCourse?course_id=${course.id}">--%>
<%--                <button type="button">Редагувати</button>--%>
<%--            </a></td>--%>

<%--        </tr>--%>
<%--        </c:forEach>--%>
<%--        </tbody>--%>
<%--    </table>--%>
<%--</div>--%>

<%--<script>--%>
<%--    var $table = $('#table');--%>
<%--    $(function () {--%>
<%--        $('#toolbar').find('select').change(function () {--%>
<%--            $table.bootstrapTable('refreshOptions', {--%>
<%--                exportDataType: $(this).val()--%>
<%--            });--%>
<%--        });--%>
<%--    })--%>

<%--    var trBoldBlue = $("table");--%>

<%--    $(trBoldBlue).on("click", "tr", function (){--%>
<%--        $(this).toggleClass("bold-blue");--%>
<%--    });--%>
<%--</script>--%>

<%--<input type="button" value="На главную" onclick="location.href='/adminPage'"/>--%>

</body>
</html>
