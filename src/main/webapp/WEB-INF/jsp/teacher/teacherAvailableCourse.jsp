<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Teacher Available Courses</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>


    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/bootstrap-table.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/bootstrap-table.min.css">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<navbar:NavTeacher/>

<div class="container">
    <h1>Майбутні курси</h1>

    <div id="toolbar">
    </div>

    <table id="table"
           data-toggle="table"
           data-search="true"
           data-filter-control="true"
           data-show-export="true"
           data-click-to-select="true"
           data-toolbar="#toolbar"
           class="table-responsive">
        <thead>
        <tr>
            <th data-field="topic" data-filter-control="input" data-sortable="true">Тема</th>
            <th data-field="name" data-filter-control="input" data-sortable="true">Назва</th>
            <th data-field="dateStart" data-filter-control="input" data-sortable="true">Дата початку</th>
            <th data-field="duration" data-filter-control="select" data-sortable="true">Тривалість</th>
            <th data-field="countStudent" data-filter-control="select" data-sortable="true">Кількість студентів</th>
            <th data-field="description" data-sortable="false">Докладніше</th>
<%--            <th data-field="gradebook" data-sortable="false">Журнал успішності</th>--%>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="course" items="${courseAvailable}">
            <tr>
                <td><c:out value="${course.topic.name }"/></td>
                <td><c:out value="${course.name }"/></td>
                <td><c:out value="${course.dateStart }"/></td>
                <td><c:out value="${course.duration }"/></td>
                <td><c:out value="${course.countStudent }"/></td>
                <td><a id="description" href="/courseDescriptionTeacher?course_id=${course.id}">
                    <button type="button">Докладніше </button>
                </a></td>
<%--                <td><a id="gradebook" href="/teacherGradebookByCourse?course_id=${course.id}">--%>
<%--                    <button type="button">Журнал успішності</button>--%>
<%--                </a></td>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script>
    var $table = $('#table');
    $(function () {
        $('#toolbar').find('select').change(function () {
            $table.bootstrapTable('refreshOptions', {
                exportDataType: $(this).val()
            });
        });
    })

    var trBoldBlue = $("table");

    $(trBoldBlue).on("click", "tr", function (){
        $(this).toggleClass("bold-blue");
    });
</script>

</body>
</html>
