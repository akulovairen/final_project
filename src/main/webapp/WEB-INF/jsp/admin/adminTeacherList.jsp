<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization"/>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>List Teacher</title>
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
        @import url(https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css);
        @import url(https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.4.3/css/mdb.min.css);

        /*.hm-gradient {*/
        /*    background-image: linear-gradient(to top, #f3e7e9 0%, #e3eeff 99%, #e3eeff 100%);*/
        /*}*/
        /*.darken-grey-text {*/
        /*    color: #2E2E2E;*/
        /*}*/
        .input-group.md-form.form-sm.form-2 input {
            border: 1px solid #bdbdbd;
            border-top-left-radius: 0.25rem;
            border-bottom-left-radius: 0.25rem;
        }
        .input-group.md-form.form-sm.form-2 input.purple-border {
            border: 1px solid #9e9e9e;
        }
        .input-group.md-form.form-sm.form-2 input[type=text]:focus:not([readonly]).purple-border {
            border: 1px solid #ba68c8;
            box-shadow: none;
        }
        .form-2 .input-group-addon {
            border: 1px solid #ba68c8;
        }
        /*.danger-text {*/
        /*    color: #ff3547;*/
        /*}*/
        /*.success-text {*/
        /*    color: #00C851;*/
        /*}*/
        /*.table-bordered.red-border, .table-bordered.red-border th, .table-bordered.red-border td {*/
        /*    border: 1px solid #ff3547!important;*/
        /*}*/
        .table.table-bordered th {
            text-align: center;
        }
    </style>
</head>
<body>
<navbar:NavAdmin/>

<main>
    <div class="container mt-4">
        <h1>
            <fmt:message key="login.listTeacher"/>
        </h1>
    </div>
    <div class="container mt-4">
        <div class="card mb-4">
            <div class="card-body">
                <!--Table-->
                <table class="table table-hover">
                    <!--Table head-->
                    <thead class="mdb-color darken-3">
                    <tr class="text-white">
                        <th><fmt:message key="login.nameUser"/> <fmt:message key="login.surname"/> </th>
                        <th><fmt:message key="login.birthday"/></th>
                        <th><fmt:message key="login.teacherCourses"/> </th>
                        <th><fmt:message key="login.delete"/></th>
                    </tr>
                    </thead>
                    <!--Table head-->
                    <!--Table body-->
                    <tbody>

                    <c:forEach var="teacher" items="${allTeacher}">
                        <tr>
                            <td><c:out value="${teacher.surname} ${teacher.name}"/></td>
                            <td><c:out value="${teacher.birthday}"/></td>
                            <td>
                                <input type="button" onclick="location.href='/adminCoursesForTeacher?user_id=${teacher.id}'"
                                       value="<fmt:message key="login.teacherCourses"/>"/>
                            </td>
                            <td>
                                <form action="/adminTeacherList?user_id=${teacher.id}" method="post">
                                    <input type="submit" value="<fmt:message key="login.delete"/>" name="Delete"/><br>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <!--Table body-->
                </table>
                <!--Table-->
            </div>
        </div>
    </div>
</main>

<%--<table class="table table-hover">--%>
<%--    <thead>--%>
<%--    <tr>--%>
<%--        <th>Фамилия Имя</th>--%>
<%--        <th>Дата рождения</th>--%>
<%--    </tr>--%>
<%--    </thead>--%>
<%--    <tbody>--%>

<%--    <c:forEach var="teacher" items="${allTeacher}">--%>
<%--        <tr>--%>
<%--            <td><c:out value="${teacher.surname} ${teacher.name}"/></td>--%>
<%--            <td><c:out value="${teacher.birthday}"/></td>--%>

<%--            <td>--%>
<%--                <input type="button" onclick="location.href='/adminCoursesForTeacher?user_id=${teacher.id}'"--%>
<%--                       value="Курсы преподавателя"/>--%>

<%--            </td>--%>

<%--            <td>--%>
<%--                <form action="/adminTeacherList?user_id=${teacher.id}" method="post">--%>
<%--                    <input type="submit" value="Удалить" name="Delete"/><br>--%>
<%--                </form>--%>
<%--            </td>--%>

<%--        </tr>--%>

<%--    </c:forEach>--%>

<%--    </tbody>--%>
<%--</table>--%>
<%--</div>--%>

<nav aria-label="Navigation for countries">
    <ul class="pagination">
        <c:if test="${currentPage != 1}">
            <li class="page-item"><a class="page-link"
                                     href="adminTeacherList?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}"><fmt:message key="pagin.previous"/></a>
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
                                             href="adminTeacherList?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <li class="page-item"><a class="page-link"
                                     href="adminTeacherList?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}"><fmt:message key="pagin.next"/></a>
            </li>
        </c:if>
    </ul>
</nav>

</body>
</html>
