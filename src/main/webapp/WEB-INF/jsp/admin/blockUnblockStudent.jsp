<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization"/>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Block/Unblock student</title>

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

<div class="container">
    <h1 class="text-center">
        <fmt:message key="login.blockUnblockStudent"/>
    </h1>
    <div class="row">
        <div class="panel panel-primary filterable">
            <div class="panel-heading">
<%--                <h3 class="panel-title">Students</h3>--%>
                <div class="pull-right">
                    <button class="btn btn-default btn-xs btn-dark btn-filter"><i class="fas fa-filter"></i><fmt:message key="button.filter"/> </button>
                </div>
            </div>
            <table class="table table-hover">
                <thead>
                <tr class="filters">
                    <th><input type="text" class="form-control" placeholder="<fmt:message key="login.nameUser"/> <fmt:message key="login.surname"/>" disabled></th>
                    <th><input type="text" class="form-control" placeholder="<fmt:message key="login.birthday"/>" disabled></th>
                    <th><input type="text" class="form-control" placeholder="<fmt:message key="login.email"/>" disabled></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="user" items="${allUsers}">
                    <tr>
                        <td><c:out value="${user.surname} ${user.name}"/></td>
                        <td><c:out value="${user.birthday}"/></td>
                        <td><c:out value="${user.email}"/></td>

                        <c:choose>
                            <c:when test="${user.locked}">

                                <td>
                                    <form action="/blockUnblockStudent?user_id=${user.id}&lock=false" method="post">
                                        <input type="submit" value="<fmt:message key="login.unblock"/>" name="Unblock"
                                               onclick='alert("Студент ${user.surname} ${user.name} разблокований")'/><br>
                                    </form>
                                </td>

                            </c:when>
                            <c:otherwise>
                                <td>
                                    <form action="/blockUnblockStudent?user_id=${user.id}&lock=true" method="post">
                                        <input type="submit" value="<fmt:message key="login.block"/>" name="Block"
                                               onclick='alert("Студент ${user.surname} ${user.name} заблокований")'/><br>
                                    </form>
                                </td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

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

<script>
    function unlockUser(name) {
        alert('Студент ' + name + ' розблокований');
    }

    function lockUser(name) {
        alert('Студент ' + name + ' заблокований');
    }
</script>


<nav aria-label="Navigation for countries">
    <ul class="pagination">
        <c:if test="${currentPage != 1}">
            <li class="page-item"><a class="page-link"
                                     href="blockUnblockStudent?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}"><fmt:message key="pagin.previous"/></a>
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
                                             href="blockUnblockStudent?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <li class="page-item"><a class="page-link"
                                     href="blockUnblockStudent?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}"><fmt:message key="pagin.next"/></a>
            </li>
        </c:if>
    </ul>
</nav>
</div>

</body>
</html>
