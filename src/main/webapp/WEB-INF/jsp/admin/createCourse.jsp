<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization"/>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Create course</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">

    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/all.js"></script>
    <link href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" rel="stylesheet">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css"
          integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">
    <script>
        function search() {
            let textValue = document.getElementsByName("teacher_id")[0].value;

            let oReq = new XMLHttpRequest();

            oReq.open("GET", "/teacherSearch?text=" + textValue, true);
            document.getElementById("teacherList").innerHTML = "";

            oReq.onload = function (e) {
                let myResponse = oReq.response;
                let teacherArray = JSON.parse(myResponse);
                let teacherList = document.getElementById("teacherList");

                teacherArray.forEach(teacher => {
                    let option = document.createElement('option');
                    option.value = teacher.id;
                    option.innerHTML = teacher.surname + " " + teacher.name;
                    teacherList.appendChild(option);
                });
            }
            oReq.send();
        }
    </script>
</head>
<body>
<navbar:NavAdmin/>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header"><fmt:message key="login.createCourse"/></div>
                <div class="card-body">

                    <form class="form-horizontal" method="post" action="/createCourse">

                        <c:choose>
                        <c:when test="${not empty restoredValues.topicList}">
                            <c:set var="topicVar" scope="session" value="${restoredValues.topicList}"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="topicVar" scope="session" value="${course.topic.id}"/>
                        </c:otherwise>
                        </c:choose>

                        <div class="form-group">
                            <label for="topic" class="cols-sm-2 control-label"><i class="fas fa-arrow-up"
                                                                                  aria-hidden="true"></i> <fmt:message
                                    key="login.topic"/></label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"></span>
                                    <%--                                    <input type="topicList" class="form-control" name="topic" id="topic" placeholder="Enter your Name" />--%>
                                    <select name="topicList" id="topic">
                                        <c:forEach var="topic" items="${allTopic}">
                                            <option value="${topic.id}"
                                                        <c:if test="${topicVar == topic.id}">
                                                            selected = "selected"
                                                        </c:if>
                                            ><c:out value="${topic.name}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="name" class="cols-sm-2 control-label"><i class="fa fa-audio-description"
                                                                                 aria-hidden="true"></i> <fmt:message
                                    key="login.name"/></label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"></span>
                                    <input type="text" class="form-control" id="name" name="name"
                                           placeholder="<fmt:message key="course.enterName"/>"
                                           value="<c:if test="${not empty restoredValues.name}">${restoredValues.name}</c:if>"
                                           required/>

                                    <c:if test="${not empty messagesMap.name}">
                                        <div style="color: red">${messagesMap.name}</div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="dataStart" class="cols-sm-2 control-label"><i class="fa fa-calendar"
                                                                                      aria-hidden="true"></i>
                                <fmt:message key="login.dataStart"/> </label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"></span>
                                    <input type="date" class="form-control" name="date_start" id="dataStart"
                                           value="<c:if test="${not empty restoredValues.date_start}">${restoredValues.date_start}</c:if>"
                                           required/>
                                    <c:if test="${not empty messagesMap.dateStart}">
                                        <div style="color: red">${messagesMap.dateStart}</div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="duration" class="cols-sm-2 control-label"><i
                                    class="fa fa-arrow-alt-circle-right" aria-hidden="true"></i> <fmt:message
                                    key="login.durationInWeeks"/></label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"></span>
                                    <input type="number" class="form-control" name="duration" id="duration"
                                           value="<c:if test="${not empty restoredValues.duration}">${restoredValues.duration}</c:if>"
                                           required/>
                                    <c:if test="${not empty messagesMap.duration}">
                                        <div style="color: red">${messagesMap.duration}</div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                </div>
                <c:choose>
                    <c:when test="${not empty restoredValues.description}">
                        <c:set var="descriptionVar" scope="session" value="${restoredValues.description}"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="descriptionVar" scope="session" value="${course.description}"/>
                    </c:otherwise>
                </c:choose>
                <div class="form-group">
                    <label for="description" class="cols-sm-2 control-label"><i class="fa fa-list-alt"
                                                                                aria-hidden="true"></i> <fmt:message
                            key="login.description"/></label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <textarea class="form-control" type="textarea" name="description" id="description"
                                      placeholder="<fmt:message key="course.enterDescription"/>">${descriptionVar}</textarea>
                            <%--                                value="<c:if test="${not empty restoredValues.description}">${restoredValues.description}</c:if>" required/></textarea>--%>
                            <c:if test="${not empty messagesMap.description}">
                                <div style="color: red">${messagesMap.description}</div>
                            </c:if>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="teacher" class="cols-sm-2 control-label"><i class="fa fa-user" aria-hidden="true"></i>
                        <fmt:message key="login.teacher"/></label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"></span>
                            <input type="text" class="form-control" name="teacher_id" id="teacher" list="teacherList"
                                   oninput="search()"
                                    <c:choose>
                                        <c:when test="${not empty restoredValues.teacher_id}">
                                            value="${restoredValues.teacher_id}"
                                        </c:when>
                                        <c:otherwise>
                                            value="${course.teacher.id}"
                                        </c:otherwise>
                                    </c:choose>
                                   required/>
                            <datalist id="teacherList">
                                <c:forEach var="user" items="${allTeacher}">
                                    <option value="${user.id}"><c:out value="${user.surname} ${user.name}"/></option>
                                </c:forEach>
                            </datalist>
                        </div>
                    </div>
                </div>
                <div class="form-group ">
                    <button type="submit" class="btn btn-primary btn-lg btn-block login-button"><fmt:message
                            key="login.createCourse"/>
                    </button>
                </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    function search() {
        let textValue = document.getElementsByName("teacher_id")[0].value;

        let oReq = new XMLHttpRequest();

        oReq.open("GET", "/teacherSearch?text=" + textValue, true);
        document.getElementById("teacherList").innerHTML = "";

        oReq.onload = function (e) {
            let myResponse = oReq.response;
            let teacherArray = JSON.parse(myResponse);
            let teacherList = document.getElementById("teacherList");

            teacherArray.forEach(teacher => {
                let option = document.createElement('option');
                option.value = teacher.id;
                option.innerHTML = teacher.surname + " " + teacher.name;
                teacherList.appendChild(option);
            });
        }
        oReq.send();
    }
</script>
</body>
</html>
