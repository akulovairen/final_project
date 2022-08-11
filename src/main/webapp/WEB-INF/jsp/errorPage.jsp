<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="localization" />
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
        <div class="bg-nav bg-dark">
            <div class="container">
                <nav class="navbar navbar-expand-sm  navbar-dark">
                    <!-- Brand -->
                    <a class="navbar-brand" href="/teacherPage">Help In Wartime</a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <!-- Navbar links -->
                    <div class="collapse navbar-collapse" id="navbarNav">
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item active">
                                <a class="nav-link" href="/teacherPage"><i class="fas fa-solid fa-home"></i> <fmt:message key="login.toHomePage"/> <span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/profile"><i class="fas fa-solid fa-user"></i> <fmt:message key="login.profile"/></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" onclick="return confirmLogout();" href="/logout"><i class="fas fa-solid fa-arrow-right"></i> <fmt:message key="login.logout"/></a>
                                <script>
                                    function confirmLogout() {
                                        if (confirm("Ви впевнені,що хочете вийти?")) {
                                            location.href = '/logout';
                                        } else {
                                            return false;
                                        }
                                    }
                                </script>
                        </ul>
                    </div>
                </nav>
            </div>
        </div><br/>
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

<%--    <p><b>Error message:</b> <c:out value="${errorMessage}"/></p>--%>
<%--    <p><b>Request URI:</b> <c:out value="${requestUri}"/></p>--%>
<%--    <p><b>Stack trace:</b> <c:out value="${stackTrace}"/></p>--%>
</body>
</html>
