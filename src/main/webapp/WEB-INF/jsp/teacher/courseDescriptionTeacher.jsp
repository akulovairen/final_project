<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization"/>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<head>
    <title>CourseDescription</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

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
    <script>
        $(document).ready(function(){
            $('.filterable .btn-filter').click(function(){
                var $panel = $(this).parents('.filterable'),
                    $filters = $panel.find('.filters input'),
                    $tbody = $panel.find('.table tbody');
                if ($filters.prop('disabled') == true) {
                    $filters.prop('disabled', false);
                    $filters.first().focus();
                } else {
                    $filters.val('').prop('disabled', true);
                    $tbody.find('.no-result').remove();
                    $tbody.find('tr').show();
                }
            });

            $('.filterable .filters input').keyup(function(e){
                /* Ignore tab key */
                var code = e.keyCode || e.which;
                if (code == '9') return;
                /* Useful DOM data and selectors */
                var $input = $(this),
                    inputContent = $input.val().toLowerCase(),
                    $panel = $input.parents('.filterable'),
                    column = $panel.find('.filters th').index($input.parents('th')),
                    $table = $panel.find('.table'),
                    $rows = $table.find('tbody tr');
                /* Dirtiest filter function ever ;) */
                var $filteredRows = $rows.filter(function(){
                    var value = $(this).find('td').eq(column).text().toLowerCase();
                    return value.indexOf(inputContent) === -1;
                });
                /* Clean previous no-result if exist */
                $table.find('tbody .no-result').remove();
                /* Show all rows, hide filtered ones (never do that outside of a demo ! xD) */
                $rows.show();
                $filteredRows.hide();
                /* Prepend no-result row if all rows are filtered */
                if ($filteredRows.length === $rows.length) {
                    $table.find('tbody').prepend($('<tr class="no-result text-center"><td colspan="'+ $table.find('.filters th').length +'">No result found</td></tr>'));
                }
            });
        });
    </script>

</head>
<body>
<navbar:NavTeacher/>

<div class="col-md-12 content">
    <div class="panel panel-default">
        <div class="panel-heading ">
            <h1 class="text-center"><em>${course.name}</em></h1>
        </div>
        <p></p>
        <dt class="col-sm-3"><fmt:message key="login.topic"/></dt>
        <dd class="col-sm-9">
            <p>${course.topic.name}</p></dd>

        <dt class="col-sm-3"><fmt:message key="login.dataStart"/></dt>
        <dd class="col-sm-9">
            <p>${course.dateStart}</p></dd>

        <dt class="col-sm-3"><fmt:message key="login.durationInWeeks"/> </dt>
        <dd class="col-sm-9">
            <p>${course.duration}</p>
            <%--        <p>Donec id elit non mi porta gravida at eget metus.</p>--%>
        </dd>

        <dt class="col-sm-3"><fmt:message key="login.teacher"/></dt>
        <dd class="col-sm-9"><p>${course.teacher.surname} ${course.teacher.name}</p></dd>

        <dt class="col-sm-3"><fmt:message key="login.description"/></dt>
        <dd class="col-sm-9"><p>${course.description}</p></dd>
        <div class="panel-body">
<%--            <p>${course.description}</p>--%>
        <dt class="col-sm-3">  </dt>
        <dd class="col-sm-9"><a class="link-color">Save Ukraine | Save People</a><br/></dd>
<%--            <a class="link-color">Save Ukraine | Save People</a><br/>--%>
        </div>
    </div>
</div>

<%--    <div class="container">--%>
<%--        <div class="row">--%>
<%--            <div class="col-md-8 mx-auto wow fadeInUp">--%>
<%--                <h3 class="text-center font-weight-bold">${course.name}</h3>--%>
<%--                <p class=" text-center">${course.topic.name}</p>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="row">--%>
<%--            <div class="col-sm-6 col-md-4 col-lg-6 mt-4">--%>
<%--                <div class="my-right-text wow fadeInUp">--%>

<%--                    <p class="text-justify font-italic"> <fmt:message key="login.dataStart"/>: ${course.dateStart}</p>--%>
<%--                    <p class="text-justify font-italic"> <fmt:message key="login.durationInWeeks"/>: ${course.duration}</p>--%>
<%--                    <p class="text-justify font-italic"> <fmt:message key="login.teacher"/>: ${course.teacher.surname} ${course.teacher.name}</p>--%>
<%--                    <p class="text-justify font-italic"> <fmt:message key="login.countStudent"/>: ${countStudent}</p>--%>
<%--                    <p class="text-justify font-italic"> <fmt:message key="login.description"/>: ${course.description}</p>--%>
<%--                    <a class="link-color">Save Ukraine | Save People</a><br/>--%>
<%--                </div>--%>
<%--            </div>--%>

<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<%--<div class="container">--%>
<%--<div class="row">--%>
<%--    <dt class="col-sm-3"><fmt:message key="login.dataStart"/></dt>--%>
<%--    <dd class="col-sm-9">${course.dateStart}</dd>--%>

<%--    <dt class="col-sm-3"><fmt:message key="login.durationInWeeks"/> </dt>--%>
<%--    <dd class="col-sm-9">--%>
<%--        <p>${course.duration}</p>--%>
<%--&lt;%&ndash;        <p>Donec id elit non mi porta gravida at eget metus.</p>&ndash;%&gt;--%>
<%--    </dd>--%>

<%--    <dt class="col-sm-3"><fmt:message key="login.teacher"/></dt>--%>
<%--    <dd class="col-sm-9">${course.teacher.surname} ${course.teacher.name}</dd>--%>

<%--    <dt class="col-sm-3 text-truncate"><fmt:message key="login.description"/></dt>--%>
<%--    <dd class="col-sm-9">${course.description}</dd>--%>

<%--&lt;%&ndash;    <dt class="col-sm-3">Nesting</dt>&ndash;%&gt;--%>
<%--&lt;%&ndash;    <dd class="col-sm-9">&ndash;%&gt;--%>
<%--&lt;%&ndash;        <dl class="row">&ndash;%&gt;--%>
<%--&lt;%&ndash;            <dt class="col-sm-4">Nested definition list</dt>&ndash;%&gt;--%>
<%--&lt;%&ndash;            <dd class="col-sm-8">Aenean posuere, tortor sed cursus feugiat, nunc augue blandit nunc.</dd>&ndash;%&gt;--%>
<%--&lt;%&ndash;        </dl>&ndash;%&gt;--%>
<%--&lt;%&ndash;    </dd>&ndash;%&gt;--%>
<%--</div>--%>
<%--</div>--%>
<%--<h2>--%>
<%--    Course--%>
<%--</h2>--%>
<%--<main>--%>
<%--    <article>--%>
<%--        <p>Название: <c:out value="${course.name}"/></p>--%>
<%--            Дата начала: <c:out value="${course.dateStart}"/><br>--%>
<%--            Длительность: <c:out value="${course.duration}"/><br>--%>
<%--            Описание: <c:out value="${course.description}"/><br>--%>
<%--            Преподователь: <c:out value="${course.teacher.surname} ${course.teacher.name}"/><br>--%>
<%--            Кількіть студентів звреєстрованих на курс: ${countStudent}<br></p>--%>
<%--        <section>--%>
<%--            <p>здесь могут быть похожие записи, слайдер, баннеры, миниатюры и т.д...</p>--%>
<%--        </section>--%>
<%--    </article>--%>
<%--</main>--%>

<div class="container">
    <hr>
    <div class="row">
        <div class="panel panel-primary filterable">
            <div class="panel-heading">
                <h3 class="panel-title">Журнал успішності студентів</h3>
                <div class="pull-right">
                    <button class="btn btn-default btn-xs btn-filter"><span class="glyphicon glyphicon-filter"></span> Filter</button>
                </div>
            </div>
            <table class="table">
                <thead>
                <tr class="filters">
                    <th><input type="text" class="form-control" placeholder="First Name" disabled></th>
                    <th><input type="text" class="form-control" placeholder="Last Name" disabled></th>
                    <th><input type="text" class="form-control" placeholder="Test1" disabled></th>
                    <th><input type="text" class="form-control" placeholder="Test2" disabled></th>
                    <th><input type="text" class="form-control" placeholder="Test3" disabled></th>
                    <th><input type="text" class="form-control" placeholder="Test4" disabled></th>
                    <th><input type="text" class="form-control" placeholder="TotalScore" disabled></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${courseGradebook}" var="entry">
                    <tr>
                        <td>${entry.student.surname}</td>
                        <td>${entry.student.name}</td>
                        <td>${entry.test1}</td>
                        <td>${entry.test2}</td>
                        <td>${entry.test3}</td>
                        <td>${entry.test4}</td>
                        <td>${entry.totalScore}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%--        <h2>--%>
<%--            Журнал по курсу--%>
<%--        </h2>--%>
<%--<table class="table table-bordered">--%>
<%--    <thead>--%>
<%--    <tr>--%>
<%--        <th>Тема</th>--%>
<%--        <th>Тест 1</th>--%>
<%--        <th>Тест 2</th>--%>
<%--        <th>Тест 3</th>--%>
<%--        <th>Тест 4</th>--%>
<%--        <th>Загальний бал</th>--%>
<%--    </tr>--%>
<%--    </thead>--%>
<%--    <tbody>--%>
<%--    <c:forEach items="${courseGradebook}" var="entry">--%>
<%--        <tr>--%>
<%--            <td><c:out value="${entry.student.name} ${entry.student.surname}"/></td>--%>
<%--            <td>${entry.test1}</td>--%>
<%--            <td>${entry.test2}</td>--%>
<%--            <td>${entry.test3}</td>--%>
<%--            <td>${entry.test4}</td>--%>
<%--            <td>${entry.totalScore}</td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--    </tbody>--%>
<%--</table>--%>
<%--        <table>--%>
<%--            <thead>--%>
<%--            <tr>--%>
<%--                <th>student</th>--%>
<%--                <th>test1</th>--%>
<%--                <th>test2</th>--%>
<%--                <th>test3</th>--%>
<%--                <th>test4</th>--%>
<%--                <th>totalScore</th>--%>
<%--            </tr>--%>
<%--            </thead>--%>
<%--            <tbody>--%>
<%--&lt;%&ndash;            FOREACH HERE&ndash;%&gt;--%>
<%--            <c:forEach items="${courseGradebook}" var="entry">--%>
<%--            <tr>--%>
<%--&lt;%&ndash;                TODO: join users table in SQL query and print entry.student.name here&ndash;%&gt;--%>
<%--                <td><c:out value="${entry.student.name} ${entry.student.surname}"/></td>--%>
<%--                <td>${entry.test1}</td>--%>
<%--                <td>${entry.test2}</td>--%>
<%--                <td>${entry.test3}</td>--%>
<%--                <td>${entry.test4}</td>--%>
<%--                <td>${entry.totalScore}</td>--%>
<%--            </tr>--%>
<%--            </c:forEach>--%>
<%--            </tbody>--%>
<%--        </table>--%>



<input type="button" value="Назад" onclick="window.history.back()"/>

</body>
</html>
