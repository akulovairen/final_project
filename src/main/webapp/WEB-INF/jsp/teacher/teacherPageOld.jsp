<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1">--%>

    <title>TeacherPage</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/bootstrap-table.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/bootstrap-table.min.css">


    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js">

<%--    <script>--%>
<%--        $(document).ready(function () {--%>
<%--            $('#dtBasicExample').DataTable({--%>
<%--                "searching": true // false to disable search (or any other option)--%>
<%--            });--%>
<%--            $('.dataTables_length').addClass('bs-select');--%>
<%--        });--%>

<%--    </script>--%>


<%--    <script type="text/javascript">--%>
<%--        function sort() {--%>
<%--            let sortingColumnSelect = document.getElementsByName("sortingColumn")[0];--%>
<%--            let sortingColumnCurrentIndex = sortingColumnSelect.selectedIndex;--%>
<%--            let sortingColumn = sortingColumnSelect.value;--%>
<%--            let sortingModeSelect = document.getElementsByName("sortingMode")[0];--%>
<%--            let sortingModeCurrentIndex = sortingModeSelect.selectedIndex;--%>
<%--            let sortingMode = sortingModeSelect.value;--%>

<%--            location.href = '/teacherPage?sortingColumn='+sortingColumn+"&sortingMode="+sortingMode;--%>

<%--            sortingColumnSelect.selectedIndex = sortingColumnCurrentIndex;--%>
<%--            sortingModeCurrentIndex.selectedIndex = sortingModeCurrentIndex;--%>
<%--        }--%>
<%--    </script>--%>
</head>
<body>
<h2>
    Мої курси
</h2>



<%--<main class="m-3">--%>

    <table id = "dtBasicExample"
            data-toggle="table"
           data-url="${courseProgress}"
           data-sort-name="sort-all"
           data-sort-order="desc"
           data-search="true"
           data-filter-control="true"
           data-click-to-select="true"
           class="table-responsive">
<%--           class="table table-striped table-bordered table-sm">--%>
        <thead>
        <tr>
            <th data-field="topic"
                data-sortable="true"
                data-filter-control="select">
                Тема
            </th>
            <th data-field="name"
                data-sortable="true"
                data-filter-control="input">
                Назва
            </th>
            <th data-field="dataStart"
                data-sortable="true"
                data-filter-control="select">
                Дата початку
            </th>
            <th data-field="duration"
                data-sortable="true"
                data-filter-control="select">
                Тривалість
            </th>
            <th data-field="description"
                data-sortable="false">
                Докладніше
            </th>
            <th data-field="sort-all"
                data-sortable="false">
                Журнал успішності
            </th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="course" items="${courseProgress}">
        <tr>
            <td class="bs-checkbox "><input data-index="i" name="btSelectItem" type="checkbox">
            <td><c:out value="${course.topic.name }"/></td>
            <td><c:out value="${course.name }"/></td>
            <td><c:out value="${course.dateStart }"/></td>
            <td><c:out value="${course.duration }"/></td>
                <%--            <td><c:out value="${course.duration }"/></td>--%>
            <td><a id="description" href="/courseDescriptionTeacher?course_id=${course.id}">
                <button type="button">Докладніше </button>
            </a></td>
            <td><a href="/teacherGradebookByCourse?course_id=${course.id}">
                <button type="button">Журнал успішності</button>
            </a></td>

        </tr>
        </c:forEach>
        </tbody>
    </table>

    <nav aria-label="Navigation for countries">
        <ul class="pagination">
            <c:if test="${currentPage != 1}">
                <li class="page-item"><a class="page-link"
                                         href="teacherPage?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
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
                                                 href="teacherPage?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage lt noOfPages}">
                <li class="page-item"><a class="page-link"
                                         href="teacherPage?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
                </li>
            </c:if>
        </ul>
    </nav>
<%--</main>--%>


<input type="button" value="Журнал успеваемости" onclick="location.href='/teacherGradebook'"/><br>

<input type="button" value="Архивные курсы" onclick="location.href='/teacherCompleted'"/><br>

<input type="button" value="Профиль" onclick="location.href='/profile'"/>

</body>
</html>
