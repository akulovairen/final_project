<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization"/>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Registration Teacher</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
</head>
<body>

<navbar:NavAdmin/>

<div class="container">
    <br>
    <p class="text-center"><fmt:message key="login.slavaUkraine"/></p>
    <hr>
    <div class="card bg-light">
        <article class="card-body mx-auto" style="max-width: 400px;">
            <h4 class="card-title mt-3 text-center"><fmt:message key="login.creatAccount"/></h4>
            <form action="/registrationTeacher" method="post">
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="name" class="form-control" placeholder="<fmt:message key="login.enterName"/>"
                           type="text"
                           value="<c:if test="${not empty restoredValues.name}">${restoredValues.name}</c:if>"
                           required/>
                    <c:if test="${not empty messagesMap.name}">
                        <div style="color: red">${messagesMap.name}</div>
                    </c:if>
                </div>
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="surname" class="form-control" placeholder="<fmt:message key="login.enterSurname"/>"
                           value="<c:if test="${not empty restoredValues.surname}">${restoredValues.surname}</c:if>"
                           required/>
                    <c:if test="${not empty messagesMap.surname}">
                        <div style="color: red">${messagesMap.surname}</div>
                    </c:if>
                </div>
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
                    </div>
                    <input name="email" class="form-control" placeholder="<fmt:message key="login.enterEmail"/>"
                           type="email"
                           value="<c:if test="${not empty restoredValues.email}">${restoredValues.email}</c:if>"
                           required/>
                    <c:if test="${not empty messagesMap.email}">
                        <div style="color: red">${messagesMap.email}</div>
                    </c:if>
                </div>
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i title="<fmt:message key="login.enterBirthday"/>"
                                                           class="fa fa-user"></i> </span>
                    </div>
                    <input name="birthday" class="form-control" type="date"
                           value="<c:if test="${not empty restoredValues.birthday}">${restoredValues.birthday}</c:if>"
                           required/>
                    <c:if test="${not empty messagesMap.birthday}">
                        <div style="color: red">${messagesMap.birthday}</div>
                    </c:if>
                </div>
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                    </div>
                    <input class="form-control" name="password" placeholder="<fmt:message key="login.enterPassword"/>"
                           type="password">
                </div>
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                    </div>
                    <input class="form-control" name="password_repeat"
                           placeholder="<fmt:message key="login.repeatPassword"/>" type="password" required/>
                    <c:if test="${not empty messagesMap.password_repeat}">
                        <div style="color: red">${messagesMap.password_repeat}</div>
                    </c:if>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block"><fmt:message
                            key="login.registerTeacher"/></button>
                </div>
            </form>
        </article>
    </div>
</div>

</body>
</html>
