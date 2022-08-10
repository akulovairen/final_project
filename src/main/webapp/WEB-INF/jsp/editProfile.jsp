<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.login"/>
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
<navbar:NavTeacher/>
</c:when>
<c:otherwise>
<navbar:NavStudent/>
</c:otherwise>
</c:choose>
<div class="container">
    <div class="card bg-light">
        <article class="card-body mx-auto" style="max-width: 400px;">
            <h4 class="card-title mt-3 text-center"><fmt:message key="login.editProfile"/> </h4>
            <%--            <p class="text-center">Get started with your free account</p>--%>
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
                </div> <!-- form-group// -->
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
                </div> <!-- form-group// -->
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
                </div> <!-- form-group// -->
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block"> Редагувати</button>
                </div> <!-- form-group// -->
                <p class="text-center"> Не хочу змінювати <a href="/profile">Повернутися</a> </p>
            </form>
        </article>
    </div> <!-- card.// -->
</div>

<%--    <form method="post" action="<c:url value='/editProfile'/>">--%>

<%--        <p>--%>
<%--            <label>Email: </label><input type="text" name="email"--%>
<%--                <c:choose>--%>
<%--                    <c:when test="${restoredValues.email != null}">--%>
<%--                        value="${restoredValues.email}"--%>
<%--                    </c:when>--%>
<%--                    <c:otherwise>--%>
<%--                        value="${user.email}"--%>
<%--                    </c:otherwise>--%>
<%--                </c:choose>--%>
<%--        />--%>
<%--        <c:if test="${not empty messagesMap.email}"><div style="color: red">${messagesMap.email}</div></c:if>--%>

<%--        </p>--%>

<%--        <p>--%>
<%--            <label>Имя: </label><input type="text" name="name"--%>
<%--                <c:choose>--%>
<%--                    <c:when test="${restoredValues.name != null}">--%>
<%--                        value="${restoredValues.name}"--%>
<%--                    </c:when>--%>
<%--                    <c:otherwise>--%>
<%--                        value="${user.name}"--%>
<%--                    </c:otherwise>--%>
<%--                </c:choose>--%>
<%--        />--%>
<%--        <c:if test="${not empty messagesMap.name}"><div style="color: red">${messagesMap.name}</div></c:if>--%>

<%--        </p>--%>
<%--        <p>--%>
<%--            <label>Фамилия: </label><input type="text" name="surname"--%>
<%--                <c:choose>--%>
<%--            <c:when test="${restoredValues.surname != null}">--%>
<%--            value="${restoredValues.surname}"--%>
<%--            </c:when>--%>
<%--            <c:otherwise>--%>
<%--            value="${user.surname}"--%>
<%--            </c:otherwise>--%>
<%--            </c:choose>--%>
<%--            />--%>
<%--        <c:if test="${not empty messagesMap.surname}"><div style="color: red">${messagesMap.surname}</div></c:if>--%>
<%--        </p>--%>

<%--        <p>--%>
<%--            <label>Дата рождения: </label><input type="date" name="birthday"--%>
<%--                <c:choose>--%>
<%--                    <c:when test="${not empty restoredValues.birthday}">--%>
<%--                        value="${restoredValues.birthday}"--%>
<%--                    </c:when>--%>
<%--                    <c:otherwise>--%>
<%--                        value="${user.birthday}"--%>
<%--                    </c:otherwise>--%>
<%--                </c:choose>--%>
<%--        />--%>
<%--        <c:if test="${not empty messagesMap.birthday}"><div style="color: red">${messagesMap.birthday}</div></c:if>--%>
<%--        </p>--%>

<%--        <input type="submit" value="Сохранить" name="Save"/><br>--%>

<%--    </form>--%>

<%--    <input type="button" value="Назад" onclick="location.href='/profile'"/>--%>

</body>
</html>
