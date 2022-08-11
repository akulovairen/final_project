<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization"/>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Course for teacher</title>

    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">

    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/all.js"></script>
    <link href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" rel="stylesheet">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css"
          integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">

    <style>
        .filterable {
            margin-top: 15px;
        }

        .filterable .panel-heading .pull-right {
            margin-top: -20px;
        }

        .filterable .filters input[disabled] {
            background-color: transparent;
            border: none;
            cursor: auto;
            box-shadow: none;
            padding: 0;
            height: auto;
        }

        .filterable .filters input[disabled]::-webkit-input-placeholder {
            color: #333;
        }

        .filterable .filters input[disabled]::-moz-placeholder {
            color: #333;
        }

        .filterable .filters input[disabled]:-ms-input-placeholder {
            color: #333;
        }
    </style>

</head>
<body>

<navbar:NavAdmin/>

<div class="container">
    <table class="table table-bordered table-hover">
<%--    <table class="table">--%>
        <thead class="thead-ligh">
        <tr>
            <th><fmt:message key="login.topic"/>
                <c:choose>
                    <c:when test="${sortingMode eq 'DESC'}">
                        <a href="adminCoursesForTeacher?user_id=${user_id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}&sortingColumn=t.name&sortingMode=ASC"><i class="fa fa-sort-up"></i></a>
                    </c:when>
                    <c:otherwise>
                        <a href="adminCoursesForTeacher?user_id=${user_id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}&sortingColumn=t.name&sortingMode=DESC"><i class="fa fa-sort-down"></i></a>
                    </c:otherwise>
                </c:choose></th>
            <th><fmt:message key="login.name"/>
                <c:choose>
                    <c:when test="${sortingMode eq 'DESC'}">
                        <a href="adminCoursesForTeacher?user_id=${user_id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}&sortingColumn=c.name&sortingMode=ASC"><i class="fa fa-sort-up"></i></a>
                    </c:when>
                    <c:otherwise>
                        <a href="adminCoursesForTeacher?user_id=${user_id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}&sortingColumn=c.name&sortingMode=DESC"><i class="fa fa-sort-down"></i></a>
                    </c:otherwise>
                </c:choose></th>
            <th><fmt:message key="login.dataStart"/>
                <c:choose>
                    <c:when test="${sortingMode eq 'DESC'}">
                        <a href="adminCoursesForTeacher?user_id=${user_id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}&sortingColumn=c.date_start&sortingMode=ASC"><i class="fa fa-sort-up"></i></a>
                    </c:when>
                    <c:otherwise>
                        <a href="adminCoursesForTeacher?user_id=${user_id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}&sortingColumn=c.date_start&sortingMode=DESC"><i class="fa fa-sort-down"></i></a>
                    </c:otherwise>
                </c:choose></th>
            <th><fmt:message key="login.durationInWeeks"/>
                <c:choose>
                    <c:when test="${sortingMode eq 'DESC'}">
                        <a href="adminCoursesForTeacher?user_id=${user_id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}&sortingColumn=c.duration&sortingMode=ASC"><i class="fa fa-sort-up"></i></a>
                    </c:when>
                    <c:otherwise>
                        <a href="adminCoursesForTeacher?user_id=${user_id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}&sortingColumn=c.duration&sortingMode=DESC"><i class="fa fa-sort-down"></i></a>
                    </c:otherwise>
                </c:choose></th>
            <th><fmt:message key="course.status"/>
                <c:choose>
                    <c:when test="${sortingMode eq 'DESC'}">
                        <a href="adminCoursesForTeacher?user_id=${user_id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}&sortingColumn=c.status&sortingMode=ASC"><i class="fa fa-sort-up"></i></a>
                    </c:when>
                    <c:otherwise>
                        <a href="adminCoursesForTeacher?user_id=${user_id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}&sortingColumn=c.status&sortingMode=DESC"><i class="fa fa-sort-down"></i></a>
                    </c:otherwise>
                </c:choose></th>
            <th><fmt:message key="login.edit"/></th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
        <c:when test="${courseTeacher == null || courseTeacher.isEmpty()}">
        <h5 style="color: red"><fmt:message key="course.notFound"/></h5>
        </c:when>
        <c:otherwise>
        <c:forEach var="course" items="${courseTeacher}">
            <tr>
                <td><c:out value="${course.topic.name}"/></td>
                <td><c:out value="${course.name}"/></td>
                <td><c:out value="${course.dateStart}"/></td>
                <td><c:out value="${course.duration}"/></td>
                <td><c:out value="${course.status}"/></td>
                <td><a href="/editCourse?course_id=${course.id}">
                    <button type="button"><fmt:message key="login.edit"/></button>
                </a></td>
            </tr>
        </c:forEach>
        </c:otherwise>
        </c:choose>
        </tbody>
    </table>

    <nav aria-label="Navigation for countries">
        <ul class="pagination">
            <c:if test="${currentPage != 1}">
                <li class="page-item"><a class="page-link"
                                         href="adminCoursesForTeacher?user_id=${user_id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}&sortingColumn=${sortingColumn}&sortingMode=${sortingMode}"><fmt:message key="pagin.previous"/></a>
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
                                                 href="adminCoursesForTeacher?user_id=${user_id}&recordsPerPage=${recordsPerPage}&currentPage=${i}&sortingColumn=${sortingColumn}&sortingMode=${sortingMode}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage lt noOfPages}">
                <li class="page-item"><a class="page-link"
                                         href="adminCoursesForTeacher?user_id=${user_id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}&sortingColumn=${sortingColumn}&sortingMode=${sortingMode}"><fmt:message key="pagin.next"/></a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>

</body>
</html>
