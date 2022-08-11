<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization"/>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Create topic</title>
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

        .hm-gradient {
            background-image: linear-gradient(to top, #f3e7e9 0%, #e3eeff 99%, #e3eeff 100%);
        }

        .darken-grey-text {
            color: #2E2E2E;
        }

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

        .danger-text {
            color: #ff3547;
        }

        .success-text {
            color: #00C851;
        }

        .table-bordered.red-border, .table-bordered.red-border th, .table-bordered.red-border td {
            border: 1px solid #ff3547 !important;
        }

        .table.table-bordered th {
            text-align: center;
        }
    </style>
</head>
<body>

<navbar:NavAdmin/>
<main>
    <div class="container mt-4">
        <h3>
            <fmt:message key="topic.createTopic"/>
        </h3>
        <form action="/createTopic" name="name" method="post">
            <label> <fmt:message key="login.name"/>:
                <input type="text" name="name" value="<c:if test="${not empty restoredValues.name}">${restoredValues.name}</c:if>"/>
                <c:if test="${not empty messagesMap.name}">
                    <div style="color: red">${messagesMap.name}</div>
                </c:if>
            </label>
            <input type="submit" value="<fmt:message key="login.save"/>" name="Save"><br>
        </form>
    </div>
    <div class="container mt-4">
        <div class="card mb-4">
            <div class="card-body">
                <table class="table table-hover">
                    <thead class="mdb-color darken-3">
                    <tr class="text-white">
                        <th><fmt:message key="login.name"/></th>
                        <th><fmt:message key="course.byTopic"/></th>
                        <th><fmt:message key="login.delete"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="topic" items="${allTopic}">
                        <tr>
                            <td><c:out value="${topic.name}"/></td>
                            <td><input type="button" onclick="location.href='/courseByTopicAdmin?topic_id=${topic.id}'"
                                       value="<fmt:message key="course.byTopic"/>"/></td>
                            <td>
                                <form action="/deleteTopic?topic_id=${topic.id}" method="post">
                                    <i class="fas fa-trash"></i>
                                    <input type="submit" onclick="confirmDelete();" value="<fmt:message key="login.delete"/>" name="Delete"/>
                                </form>
                                <script>
                                    function confirmDelete() {
                                        if (confirm("Ви впевнені,що хочете видалити?")) {
                                            location.href = '/createTopic';
                                        } else {
                                            return false;
                                        }
                                    }
                                </script>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</main>

</body>
</html>
