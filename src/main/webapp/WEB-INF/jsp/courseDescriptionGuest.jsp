<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.login"/>
<html>
<head>
    <title>Course description guest</title>

    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    <link rel="icon" href="https://maxcdn.icons8.com/Share/icon/color/Gaming/pokecoin1600.png" />
    <!---- Icon link local ----->

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
    <!-------    font awesome online plug --------------->

    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

    <style>
        @import 'https://fonts.googleapis.com/css?family=Raleway:300,400';


        body {
            font-family: 'Raleway:300', sans-serif
        }

        .admin-sidenav {
            width: auto;
            height: auto;
            margin-left: 0px;
        }
        .admin-sidenav a {
            text-decoration: none;
        }
        .admin-sidenav li {
            text-align: justify;
            padding: .5rem;
            padding-left: 1rem;
            -webkit-transition: all .2s linear;
            transition: all .2s linear;
            background-color: #000;
            border: 1px solid #333;
        }
        .admin-sidenav li a {
            color: #fff;
        }

        .admin-sidenav li a:active {
            border-color: #02d3f5;
        }

        .admin-sidenav li:hover {
            border-radius: 0 .5rem .5rem 0;
            border-color: #02d3f5;
            -webkit-transform: translate(30px, 0px);
            transform: translate(30px, 0px);
            background: -webkit-linear-gradient(left, #006a7b, #002340);
            background: linear-gradient(to right, #006a7b, #002340);
        }

        .admin-sidenav li:active {
            border-color: #02d3f5;
        }
    </style>
    <style>
        .how-section1{
            margin-top:-15%;
            padding: 10%;
        }
        .how-section1 h4{
            color: #ffa500;
            font-weight: bold;
            font-size: 30px;
        }
        .how-section1 .subheading{
            color: #3931af;
            font-size: 20px;
        }
        .how-section1 .row
        {
            margin-top: 10%;
        }
        .how-img
        {
            text-align: center;
        }
        .how-img img{
            width: 40%;
        }
    </style>
</head>
<body>

<div class="bg-nav bg-dark">
    <div class="container">
        <nav class="navbar navbar-expand-sm  navbar-dark">
            <!-- Brand -->
            <a class="navbar-brand" href="coursesListGuest">Help In Wartime</a>
<%--                <img src="bird.jpg" alt="Logo" style="width:40px;">--%>

            <!-- Toggler/collapsibe Button -->
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <!-- Navbar links -->
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="/coursesListGuest"><i class="fa fa-home"></i> <fmt:message key="login.allCourse"/> <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/login"><i class="fa fa-arrow-right"></i> <fmt:message key="login.login"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link " href="/registration"><i class="fa fa-user"></i> <fmt:message key="login.register"/></a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</div>
<%--    <div class="jumbotron">--%>
        <div class="row">
            <div id="admin-sidebar" class="col-md-2 p-x-0 p-y-3">
                <ul class="sidenav admin-sidenav list-unstyled">
                    <c:forEach var="topic" items="${allTopic}">
                        <li><a href="/courseByTopicGuest?topic_id=${topic.id}"><c:out value="${topic.name}"/></a></li>
                    </c:forEach>

                </ul>
            </div>
        </div>
<%--<div class="container">--%>
<div class="how-section1">
<div class="row">
    <div class="col-md-6">
        <h4>${course.name}</h4>
        <h4 class="subheading"><em><fmt:message key="login.dataStart"/></em>: <c:out value="${course.dateStart}"/><br>
            <em><fmt:message key="login.durationInWeeks"/></em>: <c:out value="${course.duration}"/><br>
            <em><fmt:message key="login.teacher"/></em>: <c:out value="${course.teacher.surname} ${course.teacher.name}"/><br></h4>
        <p class="text-muted">
            ${course.description} <br></p>
    </div>
    <div class="col-md-6 how-img">
        <img src="/images?filename=Разом ми сила.jpg" />
    </div>
</div>
    <p><a class="btn btn-primary" href="/registration">Зареєструватися</a></p>
</div>
</div>
<%--<div class="container-fluid">--%>
<%--    <div class="jumbotron">--%>
<%--        <div class="row">--%>
<%--            <div id="admin-sidebar" class="col-md-2 p-x-0 p-y-3">--%>
<%--                <ul class="sidenav admin-sidenav list-unstyled">--%>
<%--                    <c:forEach var="topic" items="${allTopic}">--%>
<%--                        <li><a href="/courseByTopicGuest?topic_id=${topic.id}"><c:out value="${topic.name}"/></a></li>--%>
<%--                    </c:forEach>--%>

<%--                </ul>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="container">--%>
<%--            <div class="row">--%>
<%--                <div class="col-md-8 mx-auto wow fadeInUp">--%>
<%--                    <h3 class="text-center font-weight-bold">${course.name}</h3>--%>
<%--                    <p class=" text-center">${course.topic.name}</p>--%>
<%--                    &lt;%&ndash;                <p class=" text-center">${course.dateStart}</p>&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                <p class=" text-center">${course.duration}</p>&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                <p class=" text-center"></p>&ndash;%&gt;--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="row">--%>
<%--                &lt;%&ndash;            <div class="col-sm-6 col-md-4 col-lg-6 mt-4 wow bounceInUp" data-wow-duration="1.4s">&ndash;%&gt;--%>
<%--                &lt;%&ndash;                <div class="big-img-3">&ndash;%&gt;--%>
<%--                &lt;%&ndash;&lt;%&ndash;                    <img src="img/flag.png">&ndash;%&gt;&ndash;%&gt;--%>
<%--                &lt;%&ndash;                    <img src="/images?filename=flag.png"/>&ndash;%&gt;--%>
<%--                &lt;%&ndash;                </div>&ndash;%&gt;--%>
<%--                &lt;%&ndash;            </div>&ndash;%&gt;--%>
<%--                <div class="col-sm-6 col-md-4 col-lg-6 mt-4">--%>
<%--                    <div class="my-right-text wow fadeInUp">--%>

<%--                        <p class="text-justify font-italic"> <fmt:message key="login.dataStart"/>: ${course.dateStart}</p>--%>
<%--                        <p class="text-justify font-italic"> <fmt:message key="login.durationInWeeks"/>: ${course.duration}</p>--%>
<%--                        <p class="text-justify font-italic"> <fmt:message key="login.teacher"/>: ${course.teacher.surname} ${course.teacher.name}</p>--%>
<%--                        <p class="text-justify font-italic">${course.description}</p>--%>
<%--                        <a class="link-color">Save Ukraine | Save People</a><br/>--%>
<%--                        <button type="button" class="btn btn-success"><fmt:message key="login.register"/></button>--%>
<%--                        &lt;%&ndash;                    <p class="text-muted">Lorem Ipsum Dolor Sit Amet</p>&ndash;%&gt;--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--<section class="about-us py-5 " id="about-us">--%>
<%--    <div class="container mt-5">--%>
<%--        <div class="row">--%>
<%--            <div class="col-md-6">--%>
<%--                <h1 class='text-success'>${course.name}</h1>--%>
<%--                <h2>${course.topic.name}</h2>--%>
<%--                <hr>--%>
<%--                <p class="text-justify font-italic"> <fmt:message key="login.dataStart"/>: ${course.dateStart}</p>--%>
<%--                <p class="text-justify font-italic"> <fmt:message key="login.durationInWeeks"/>: ${course.duration}</p>--%>
<%--                <p class="text-justify font-italic"> <fmt:message key="login.teacher"/>: ${course.teacher.surname} ${course.teacher.name}</p>--%>
<%--                <p>${course.description}</p>--%>
<%--&lt;%&ndash;                <p>Consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore etae magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>&ndash;%&gt;--%>
<%--&lt;%&ndash;                <p>Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>&ndash;%&gt;--%>
<%--                <button type="button" class="btn btn-success">Let's Know More</button>--%>

<%--            </div>--%>
<%--&lt;%&ndash;            <div class="col-md-6">&ndash;%&gt;--%>
<%--&lt;%&ndash;                <img src=" https://img.freepik.com/darmowe-wektory/stojak-z-ukrainskim-plakatem-z-kolorowymi-rekami-flagi_1017-37652.jpg?w=2000" class="img-fluid"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;            </div>&ndash;%&gt;--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</section>--%>
<%--        <div id="admin-main-control" class="col-md-10 p-x-3 p-y-1">--%>
<%--            <div class="description">--%>
<%--                <h1>     ${course.name}--%>
<%--                    <p> Дата начала:</p><p> ${course.dateStart} <br></p>--%>
<%--                       <p>Длительность:</p> <c:out value="${course.duration}"/><br>--%>
<%--                        Описание: <c:out value="${course.description}"/><br>--%>
<%--                       <p> Преподователь: <c:out value="${course.teacher.surname} ${course.teacher.name}"/><br></p>--%>
<%--                    <button href="/registration" class="btn btn-outline-secondary btn-lg">Зареєструватися</button>   </h1>--%>
<%--            </div>--%>
<%--            <div class="container">--%>
<%--                <div class="row">--%>
<%--                    <div class="col-sm-3 col-md-3">--%>
<%--                        <div class="well">--%>
<%--                            <h2 class="text-muted"> ${course.name} </h2>--%>
<%--                            <h1>${course.topic.name}</h1>>--%>
<%--                            <ul>--%>
<%--                                <li>${course.dateStart}</li>--%>
<%--                                <li>${course.duration} тижнів</li>--%>
<%--                                <li>Викладач ${course.teacher.surname} ${course.teacher.name}</li>--%>
<%--&lt;%&ndash;                                <li>E-mail support</li>&ndash;%&gt;--%>
<%--                            </ul>--%>
<%--                            <p>${course.description}</p>                         <hr>--%>
<%--&lt;%&ndash;                            <h3>$15.99 / month</h3>&ndash;%&gt;--%>
<%--                            <hr>--%>
<%--                            <p><a class="btn btn-default btn-lg" href="/registration"><i class="icon-ok"></i> Зареєструватися</a></p>--%>
<%--                        </div>--%>
<%--                    </div>--%>

<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<%--<section class="testimonials text-center ">--%>
<%--    <div class="container">--%>
<%--        <div class="row">--%>
<%--            <div class="col-md-8 mx-auto wow fadeInUp">--%>
<%--                <h3 class="text-center font-weight-bold">${course.name}</h3>--%>
<%--                <p class=" text-center">${course.topic.name}</p>--%>
<%--&lt;%&ndash;                <p class=" text-center">${course.dateStart}</p>&ndash;%&gt;--%>
<%--&lt;%&ndash;                <p class=" text-center">${course.duration}</p>&ndash;%&gt;--%>
<%--&lt;%&ndash;                <p class=" text-center"></p>&ndash;%&gt;--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="row">--%>
<%--&lt;%&ndash;            <div class="col-sm-6 col-md-4 col-lg-6 mt-4 wow bounceInUp" data-wow-duration="1.4s">&ndash;%&gt;--%>
<%--&lt;%&ndash;                <div class="big-img-3">&ndash;%&gt;--%>
<%--&lt;%&ndash;&lt;%&ndash;                    <img src="img/flag.png">&ndash;%&gt;&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <img src="/images?filename=flag.png"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;                </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;            </div>&ndash;%&gt;--%>
<%--            <div class="col-sm-6 col-md-4 col-lg-6 mt-4">--%>
<%--                <div class="my-right-text wow fadeInUp">--%>

<%--                    <p class="text-justify font-italic"> <fmt:message key="login.dataStart"/>: ${course.dateStart}</p>--%>
<%--                    <p class="text-justify font-italic"> <fmt:message key="login.durationInWeeks"/>: ${course.duration}</p>--%>
<%--                    <p class="text-justify font-italic"> <fmt:message key="login.teacher"/>: ${course.teacher.surname} ${course.teacher.name}</p>--%>
<%--                    <p class="text-justify font-italic">${course.description}</p>--%>
<%--                    <a href="#" class="link-color">Save Ukraine | Save People</a><br/>--%>
<%--                    <button type="button" class="btn btn-success"><fmt:message key="login.register"/></button>--%>
<%--&lt;%&ndash;                    <p class="text-muted">Lorem Ipsum Dolor Sit Amet</p>&ndash;%&gt;--%>
<%--                </div>--%>
<%--            </div>--%>

<%--        </div>--%>
<%--    </div>--%>
<%--</section>--%>
</body>
</html>
