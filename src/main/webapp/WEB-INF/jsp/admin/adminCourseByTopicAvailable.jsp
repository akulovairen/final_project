<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization"/>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Course By Topic Available</title>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

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
                <a class="dropdown-item" href="adminCourseByTopicAvailable?topic_id=${topic.id}"><c:out value="${topic.name}"/></a>
            </c:forEach>
        </div>
    </div>
</div><br/>

<div class="container">
    <h3><fmt:message key="login.availableCourses"/></h3>
    <table class="table table-bordered table-hover">
        <thead class="thead-ligh">
        <tr>
            <th><fmt:message key="login.topic"/>
                <c:choose>
                    <c:when test="${sortingMode eq 'DESC'}">
                        <a href="adminCourseByTopicAvailable?topic_id=${topic_id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}&sortingColumn=t.name&sortingMode=ASC"><i class="fa fa-sort-up"></i></a>
                    </c:when>
                    <c:otherwise>
                        <a href="adminCourseByTopicAvailable?topic_id=${topic_id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}&sortingColumn=t.name&sortingMode=DESC"><i class="fa fa-sort-down"></i></a>
                    </c:otherwise>
                </c:choose></th>
            <th><fmt:message key="login.name"/>
                <c:choose>
                    <c:when test="${sortingMode eq 'DESC'}">
                        <a href="adminCourseByTopicAvailable?topic_id=${topic_id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}&sortingColumn=c.name&sortingMode=ASC"><i class="fa fa-sort-up"></i></a>
                    </c:when>
                    <c:otherwise>
                        <a href="adminCourseByTopicAvailable?topic_id=${topic_id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}&sortingColumn=c.name&sortingMode=DESC"><i class="fa fa-sort-down"></i></a>
                    </c:otherwise>
                </c:choose></th>
            <th><fmt:message key="login.dataStart"/>
                <c:choose>
                    <c:when test="${sortingMode eq 'DESC'}">
                        <a href="adminCourseByTopicAvailable?topic_id=${topic_id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}&sortingColumn=c.date_start&sortingMode=ASC"><i class="fa fa-sort-up"></i></a>
                    </c:when>
                    <c:otherwise>
                        <a href="adminCourseByTopicAvailable?topic_id=${topic_id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}&sortingColumn=c.date_start&sortingMode=DESC"><i class="fa fa-sort-down"></i></a>
                    </c:otherwise>
                </c:choose></th>
            <th><fmt:message key="login.duration"/>
                <c:choose>
                    <c:when test="${sortingMode eq 'DESC'}">
                        <a href="adminCourseByTopicAvailable?topic_id=${topic_id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}&sortingColumn=c.duration&sortingMode=ASC"><i class="fa fa-sort-up"></i></a>
                    </c:when>
                    <c:otherwise>
                        <a href="adminCourseByTopicAvailable?topic_id=${topic_id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}&sortingColumn=c.duration&sortingMode=DESC"><i class="fa fa-sort-down"></i></a>
                    </c:otherwise>
                </c:choose></th>
            <th><fmt:message key="login.teacher"/>
                <c:choose>
                    <c:when test="${sortingMode eq 'DESC'}">
                        <a href="adminCourseByTopicAvailable?topic_id=${topic_id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}&sortingColumn=u.surname&sortingMode=ASC"><i class="fa fa-sort-up"></i></a>
                    </c:when>
                    <c:otherwise>
                        <a href="adminCourseByTopicAvailable?topic_id=${topic_id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}&sortingColumn=u.surname&sortingMode=DESC"><i class="fa fa-sort-down"></i></a>
                    </c:otherwise>
                </c:choose></th>
            <th><fmt:message key="course.status"/></th>
            <th><fmt:message key="login.edit"/></th>
        </tr>
        </thead>
        <tbody>
    <c:choose>
    <c:when test="${courseTopicAvailable == null || courseTopicAvailable.isEmpty()}"><br/>
        <h5 style="color: red"><fmt:message key="course.notFound"/></h5>
    </c:when>
    <c:otherwise>
        <c:forEach var="course" items="${courseTopicAvailable}">
            <tr>
                <td><c:out value="${course.topic.name }"/></td>
                <td><c:out value="${course.name }"/></td>
                <td><c:out value="${course.dateStart }"/></td>
                <td><c:out value="${course.duration }"/></td>
                <td><c:out value="${course.teacher.surname} ${course.teacher.name}"/></td>
                <td>
                    <form id="status1" action="/changeCourseStatus?status=progress&course_id=${course.id}" method="post">
                        <input type="submit" value="<fmt:message key="course.start"/>"/>
                    </form>
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
                                         href="adminCourseByTopicAvailable?topic_id=${topic_id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}&sortingColumn=${sortingColumn}&sortingMode=${sortingMode}"><fmt:message key="pagin.previous"/></a>
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
                                                 href="adminCourseByTopicAvailable?topic_id=${topic_id}&recordsPerPage=${recordsPerPage}&currentPage=${i}&sortingColumn=${sortingColumn}&sortingMode=${sortingMode}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage lt noOfPages}">
                <li class="page-item"><a class="page-link"
                                         href="adminCourseByTopicAvailable?topic_id=${topic_id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}&sortingColumn=${sortingColumn}&sortingMode=${sortingMode}"><fmt:message key="pagin.next"/></a>
                </li>
            </c:if>
        </ul>
    </nav>
    </c:otherwise>
    </c:choose>
</div>
<%--<div class="dropdown">--%>
<%--    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">--%>
<%--        ?????????? ???? ????????????????--%>
<%--        <span class="caret"></span>--%>
<%--    </button>--%>
<%--    <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">--%>
<%--        <li><a href="/adminCoursesAvailableList">???????????????? ??????????</a>--%>
<%--        <li><a href="/adminCoursesList">?????????? ?? ????????????????</a></li>--%>
<%--        <li><a href="/completedCourseAdmin">?????????????? ??????????</a></li>--%>
<%--    </ul>--%>
<%--</div>--%>

<%--<div class="dropdown">--%>
<%--    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">--%>
<%--        ????????--%>
<%--        <span class="caret"></span>--%>
<%--    </button>--%>
<%--    <ul class="dropdown-menu" aria-labelledby="dropdownMenu">--%>
<%--        <c:forEach var="topic" items="${allTopic}">--%>
<%--            <li><a href="/adminCourseByTopicAvailable?topic_id=${topic.id}"><c:out value="${topic.name}"/></a></li><br/>--%>
<%--        </c:forEach>--%>
<%--    </ul>--%>
<%--</div>--%>

<%--<div class="container">--%>
<%--    <h1>???????????????? ??????????</h1>--%>

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
<%--            <th data-field="topic" data-filter-control="input" data-sortable="true">????????</th>--%>
<%--            <th data-field="name" data-filter-control="input" data-sortable="true">??????????</th>--%>
<%--            <th data-field="dateStart" data-filter-control="input" data-sortable="true">???????? ??????????????</th>--%>
<%--            <th data-field="duration" data-filter-control="select" data-sortable="true">????????????????????</th>--%>
<%--            <th data-field="teacher" data-filter-control="select" data-sortable="true">????????????????</th>--%>
<%--            <th data-field="status" data-sortable="false">????????????</th>--%>
<%--            <th data-field="edit" data-sortable="false">????????????????????</th>--%>
<%--        </tr>--%>
<%--        </thead>--%>
<%--        <tbody>--%>
<%--            <c:forEach var="course" items="${courseTopicAvailable  }">--%>
<%--        <tr>--%>
<%--            <td><c:out value="${course.topic.name }"/></td>--%>
<%--            <td><c:out value="${course.name }"/></td>--%>
<%--            <td><c:out value="${course.dateStart }"/></td>--%>
<%--            <td><c:out value="${course.duration }"/></td>--%>
<%--            <td><c:out value="${course.teacher.surname} ${course.teacher.name}"/></td>--%>
<%--            <td>--%>
<%--                <form action="/changeCourseStatus?status=progress&course_id=${course.id}" method="post">--%>
<%--                    <input type="submit" value="??????????????????"/>--%>
<%--                </form>--%>
<%--            </td>--%>
<%--            <td><a--%>
<%--                    href="/editCourse?course_id=${course.id}">--%>
<%--                <button type="button">????????????????????</button>--%>
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

</body>
</html>

