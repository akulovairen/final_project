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
        </dd>

        <dt class="col-sm-3"><fmt:message key="login.teacher"/></dt>
        <dd class="col-sm-9"><p>${course.teacher.surname} ${course.teacher.name}</p></dd>

        <dt class="col-sm-3"><fmt:message key="login.description"/></dt>
        <dd class="col-sm-9"><p>${course.description}</p></dd>
        <div class="panel-body">
        <dt class="col-sm-3">  </dt>
        <dd class="col-sm-9"><a class="link-color">Save Ukraine | Save People</a><br/></dd>
        </div>
    </div>
</div>

<div class="container">
    <hr>
    <div class="row">
        <div class="panel panel-primary filterable">
            <div class="panel-heading">
                <h3 class="panel-title"><fmt:message key="gradebook.students"/></h3>
                <div class="pull-right">
                    <button class="btn btn-default btn-xs btn-filter"><span class="glyphicon glyphicon-filter"></span> <fmt:message key="button.filter"/></button>
                </div>
            </div>
            <table class="table">
                <thead>
                <tr class="filters">
                    <th><input type="text" class="form-control" placeholder="<fmt:message key="login.surname"/>" disabled></th>
                    <th><input type="text" class="form-control" placeholder="<fmt:message key="login.nameUser"/>" disabled></th>
                    <th><input type="text" class="form-control" placeholder="<fmt:message key="login.test"/> 1" disabled></th>
                    <th><input type="text" class="form-control" placeholder="<fmt:message key="login.test"/> 2" disabled></th>
                    <th><input type="text" class="form-control" placeholder="<fmt:message key="login.test"/> 3" disabled></th>
                    <th><input type="text" class="form-control" placeholder="<fmt:message key="login.test"/> 4" disabled></th>
                    <th><input type="text" class="form-control" placeholder="<fmt:message key="login.totalScore"/>" disabled></th>
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
</body>
</html>
