<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization"/>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>EditProfile</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">

    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/all.js"></script>
    <link href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" rel="stylesheet">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css"
          integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">
    <!------ Include the above in your HEAD tag ---------->

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
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
<div class="container">
    <div class="card bg-light">
        <article class="card-body mx-auto" style="max-width: 400px;">
            <h4 class="card-title mt-3 text-center"><fmt:message key="login.editProfile"/> </h4>
            <form  action="/editProfile" method="post">
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="name" class="form-control" type="text" name="name"
                    <c:choose>
                        <c:when test="${restoredValues.name != null}">
                            value="${restoredValues.name}"
                        </c:when>
                        <c:otherwise>
                            value="${user.name}"
                        </c:otherwise>
                    </c:choose>
                    />
                    <c:if test="${not empty messagesMap.name}"><div>${messagesMap.name}</div></c:if>
                </div> <!-- form-group// -->
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="surname" class="form-control"
                           name="surname"
                            <c:choose>
                                <c:when test="${restoredValues.surname != null}">
                                    value="${restoredValues.surname}"
                                </c:when>
                                <c:otherwise>
                                    value="${user.surname}"
                                </c:otherwise>
                            </c:choose>
                    />
                    <c:if test="${not empty messagesMap.surname}"><div>${messagesMap.surname}</div></c:if>
                </div>
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
                    </div>
                    <input name="email" class="form-control"
                           type="email" name="email"
                            <c:choose>
                                <c:when test="${restoredValues.email != null}">
                                    value="${restoredValues.email}"
                                </c:when>
                                <c:otherwise>
                                    value="${user.email}"
                                </c:otherwise>
                            </c:choose>
                    />
                    <c:if test="${not empty messagesMap.email}"><div>${messagesMap.email}</div></c:if>
                </div>
                <div class="form-group input-group">
                    <div class="input-group-prepend" >
                        <span class="input-group-text"> <i title="<fmt:message key="login.enterBirthday"/>" class="fa fa-user"></i> </span>
                    </div>
                    <input name="birthday" class="form-control" type="date" name="birthday"
                            <c:choose>
                                <c:when test="${not empty restoredValues.birthday}">
                                    value="${restoredValues.birthday}"
                                </c:when>
                                <c:otherwise>
                                    value="${user.birthday}"
                                </c:otherwise>
                            </c:choose>
                    />
                    <c:if test="${not empty messagesMap.birthday}"><div>${messagesMap.birthday}</div></c:if>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block"> <fmt:message key="login.edit"/></button>
                </div>
                <p class="text-center"> <fmt:message key="course.notEdit"/> <a href="/profile"><fmt:message key="login.back"/></a> </p>
            </form>
        </article>
    </div>
</div>

</body>
</html>
