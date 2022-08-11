<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization"/>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Gradebook Student</title>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">

    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/all.js"></script>
    <link href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" rel="stylesheet">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css"
          integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">
</head>
<body>

<navbar:NavStudent/>

<div class="container">
    <h2>
        <fmt:message key="gradebook.my"/>
    </h2>
<table class="table table-bordered table-hover">
    <thead class="thead-ligh">
    <tr>
        <th><fmt:message key="login.topic"/>
            <c:choose>
                <c:when test="${sortingMode eq 'DESC'}">
                    <a href="gradebookStudent?sortingColumn=t.name&sortingMode=ASC"><i class="fa fa-sort-up"></i></a>
                </c:when>
                <c:otherwise>
                    <a href="gradebookStudent?sortingColumn=t.name&sortingMode=DESC"><i class="fa fa-sort-down"></i></a>
                </c:otherwise>
            </c:choose></th>
        <th><fmt:message key="login.name"/>
            <c:choose>
                <c:when test="${sortingMode eq 'DESC'}">
                    <a href="gradebookStudent?sortingColumn=c.name&sortingMode=ASC"><i class="fa fa-sort-up"></i></a>
                </c:when>
                <c:otherwise>
                    <a href="gradebookStudent?sortingColumn=c.name&sortingMode=DESC"><i class="fa fa-sort-down"></i></a>
                </c:otherwise>
            </c:choose></th>
        <th><fmt:message key="login.test"/> 1
            <c:choose>
                <c:when test="${sortingMode eq 'DESC'}">
                    <a href="gradebookStudent?sortingColumn=g.test1&sortingMode=ASC"><i class="fa fa-sort-up"></i></a>
                </c:when>
                <c:otherwise>
                    <a href="gradebookStudent?sortingColumn=g.test1&sortingMode=DESC"><i class="fa fa-sort-down"></i></a>
                </c:otherwise>
            </c:choose></th>
        <th><fmt:message key="login.test"/> 2
            <c:choose>
            <c:when test="${sortingMode eq 'DESC'}">
                <a href="gradebookStudent?sortingColumn=g.test2&sortingMode=ASC"><i class="fa fa-sort-up"></i></a>
            </c:when>
            <c:otherwise>
                <a href="gradebookStudent?sortingColumn=g.test2&sortingMode=DESC"><i class="fa fa-sort-down"></i></a>
            </c:otherwise>
            </c:choose></th>
        <th><fmt:message key="login.test"/> 3
            <c:choose>
                <c:when test="${sortingMode eq 'DESC'}">
                    <a href="gradebookStudent?sortingColumn=g.test3&sortingMode=ASC"><i class="fa fa-sort-up"></i></a>
                </c:when>
                <c:otherwise>
                    <a href="gradebookStudent?sortingColumn=g.test3&sortingMode=DESC"><i class="fa fa-sort-down"></i></a>
                </c:otherwise>
            </c:choose></th>
        <th><fmt:message key="login.test"/> 4
            <c:choose>
            <c:when test="${sortingMode eq 'DESC'}">
                <a href="gradebookStudent?sortingColumn=g.test4&sortingMode=ASC"><i class="fa fa-sort-up"></i></a>
            </c:when>
            <c:otherwise>
                <a href="gradebookStudent?sortingColumn=g.test4&sortingMode=DESC"><i class="fa fa-sort-down"></i></a>
            </c:otherwise>
        </c:choose></th>
        <th><fmt:message key="login.totalScore"/>
            <c:choose>
                <c:when test="${sortingMode eq 'DESC'}">
                    <a href="gradebookStudent?sortingColumn=g.totalScore&sortingMode=ASC"><i class="fa fa-sort-up"></i></a>
                </c:when>
                <c:otherwise>
                    <a href="gradebookStudent?sortingColumn=g.totalScore&sortingMode=DESC"><i class="fa fa-sort-down"></i></a>
                </c:otherwise>
            </c:choose></th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <c:forEach var="gradebook" items="${allByStudent}">
    <tr>
        <td><c:out value="${gradebook.course.topic.name }"/></td>
        <td><c:out value="${gradebook.course.name }"/></td>
        <td><c:out value="${gradebook.test1 }"/></td>
        <td><c:out value="${gradebook.test2 }"/></td>
        <td><c:out value="${gradebook.test3 }"/></td>
        <td><c:out value="${gradebook.test4 }"/></td>
        <td><c:out value="${gradebook.totalScore }"/></td>

    </c:forEach>
    </tbody>
</table>
</div>
</body>
</html>
