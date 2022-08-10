<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Error page</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <style>
        body {
            background: #dedede;
        }
        .page-wrap {
            min-height: 100vh;
        }
    </style>
</head>
<body>
<c:choose>
    <c:when test="${role eq 'admin'}">
        <navbar:NavAdmin/>
    </c:when>
    <c:when test="${role eq 'teacher'}">
        <navbar:NavTeacher/>
    </c:when>
    <c:otherwise>
        <navbar:NavStudent/>
    </c:otherwise>
</c:choose>

<div class="page-wrap d-flex flex-row align-items-center">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-12 text-center">
                <span class="display-1 d-block">Oops...</span>
<%--                <div class="mb-4 lead">${errorStatus}.</div>--%>
                <div class="mb-4 lead">${errorMessage}.</div>
                <c:choose>
                    <c:when test="${role eq 'admin'}">
                        <a href="/adminPage" class="btn btn-link">Back to Home</a>
                    </c:when>
                    <c:when test="${role eq 'teacher'}">
                        <a href="/teacherPage" class="btn btn-link">Back to Home</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/coursesInProgress" class="btn btn-link">Back to Home</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>

<%--<div align-items="center" justify-content="center">--%>
<%--    <h1>Oops... You've got an error</h1>--%>
<%--    <p><b>Http status:</b> <c:out value="${errorStatus}"/></p>--%>
<%--    <p><b>Error message:</b> <c:out value="${errorMessage}"/></p>--%>
<%--</div>--%>



<%--    <p><b>Error message:</b> <c:out value="${errorMessage}"/></p>--%>
<%--    <p><b>Request URI:</b> <c:out value="${requestUri}"/></p>--%>
<%--    <p><b>Stack trace:</b> <c:out value="${stackTrace}"/></p>--%>
</body>
</html>
