<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization"/>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Мої курси</title>

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
        .our-plans {
            width: 100%;
        }

        .our-plans .price-plan {
            width: 100%;
            padding: 20px;
            background: #fff;
            border: 1px solid #eaeaea;
            margin-bottom: 20px;
        }

        .our-plans .price-plan:last-child {
            margin-bottom: 0;
        }

        .our-plans .price-plan .plan-details,
        .our-plans .price-plan .plan-features,
        .our-plans .price-plan .accept-plan {
            float: left;
            padding: 0 10px;
        }

        .our-plans .price-plan .plan-details {
            width: 25%;
        }

        .our-plans .price-plan .plan-features {
            width: 55%;
        }

        .our-plans .price-plan .accept-plan {
            width: 20%;
        }

        .our-plans .price-plan .plan-details h2 {
            display: block;
            font-size: 24px;
            line-height: 30px;
            font-weight: 700;
            padding: 20px 0;
            color: #252a37;
            margin-bottom: 0;
        }

        .our-plans .price-plan .plan-details h3 {
            font-size: 34px;
            padding: 0 0;
            position: relative;
            color: #252a37;
            margin: 0 0 25px;
        }

        .our-plans .price-plan .plan-details h3 sub {
            font-size: 20px;
            left: -0.6em;
            position: relative;
        }

        .our-plans .price-plan .plan-details h3 sup {
            top: -0.9em;
            font-size: 50%;
            left: -0.01em;
            font-weight: 700;
        }

        /* plan features */
        .our-plans .price-plan .plan-features ul {
            list-style: none;
        }

        .our-plans .price-plan .plan-features ul li {
            position: relative;
            padding-left: 30px;
            margin-bottom: 4px;
        }

        .our-plans .price-plan .plan-features ul li:before {
            content: "\f00c";
            font-family: FontAwesome;
            font-style: normal;
            font-weight: normal;
            text-decoration: inherit;
            /*--adjust as necessary--*/
            color: #2154cf;
            font-size: 18px;
            padding-right: 0.5em;
            position: absolute;
            top: 0px;
            left: 0;
        }

        /* plan details ends here */
        .our-plans .price-plan .accept-plan {
            text-align: center;
        }

        .our-plans .price-plan .accept-plan a {
            text-align: center;
            padding: 5px 20px;
            background-color: #2154cf;
            color: #fff;
            border-radius: 27px;
            margin-top: 20%;
            display: inline-block;
        }

        .our-plans .price-plan .accept-plan a:hover {
            background: #272d33;
        }

    </style>
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
        body {
            /*background-image: url("../images/.png");*/
            background-color: white;
            background-attachment: scroll;
            background-repeat: no-repeat;
            background-position: bottom;
            background-size: cover;
        }

        #banner-container-fluid {
            background-image: url("https://kyiv.comments.ua/img/publications/cjOOyF0Vb65hjbQ2dpQh2inchfiNaeb4.jpeg");
            /*background-color:;*/
            background-attachment: scroll;
            background-repeat: no-repeat;
            background-position: center;
            background-size: cover;
        }

        #footer-container-fluid {
            /*background-image: url("../images/");*/
            background-color: white;
            background-attachment: scroll;
            background-repeat: no-repeat;
            background-position: center;
            background-size: cover;
            margin: 60px 60px;

        }

        a:hover {
            text-decoration: none;
            cursor: hand;
        }

        #banner-container-fluid .jumbotron {
            background-color: #9e9e9e54;
            color: white;
            max-height: 500px;
            height: 430px;
        }

        #banner-container-fluid .jumbotron .lead {
            text-align: center;
        }

        #banner-container-fluid .jumbotron .leader {
            text-align: center;
        }

        #banner-container-fluid .jumbotron p {
            text-align: center;
        }

    </style>

</head>
<body>
<navbar:NavStudent/>
<div class="container-fluid" id="banner-container-fluid">
    <div class="container">
        <div class="jumbotron">
            <h1 class="leader"><fmt:message key="slogan.supportUkraine"/></h1>
            <p>SUPPORT | UKRAINE</p>
            <p class="lead">
                <a class="btn btn-success btn-lg" href="/coursesAvailable" role="button"><fmt:message key="course.newCourse"/></a><br/>
            </p>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div id="admin-sidebar" class="col-md-2 p-x-0 p-y-3">
        <ul class="sidenav admin-sidenav list-unstyled">
            <li><a href="/coursesAvailable"><fmt:message key="login.availableCourse"/></a></li>
            <li><a href="/courseRegister"><fmt:message key="login.registerCourse"/></a></li>
            <li><a href="/coursesCompleted"><fmt:message key="login.completedCourse"/> </a></li>
            <li><a href="/gradebookStudent"><fmt:message key="login.gradebook"/> </a></li>
        </ul>
    </div>
    <section class="our-plans">
        <div class="container">
            <div class="row text-center">
                <div class="col-md-12">
                    <h2 class="section-title"><fmt:message key="login.myCourse"/></h2>
                </div>
            </div>

            <div class="row mrt-40">
                <c:choose>
                    <c:when test="${progress == null || progress.isEmpty()}"><br/>
                        <h5 style="color: red"><fmt:message key="course.notFound"/></h5>
                    </c:when>
                    <c:otherwise>

                        <c:forEach var="course" items="${progress}">
                            <div class="price-plan">
                                <div class="plan-details">
                                    <ul>
                                        <i class="fas fa-calendar-times"></i> <fmt:message
                                            key="login.dataStart"/>: ${course.dateStart}<br/>
                                        <i class="fas fa-arrow-up"></i> <fmt:message
                                            key="login.durationInWeeks"/>: ${course.duration}<br/>
                                        <i class="fas fa-user"></i> <fmt:message
                                            key="login.teacher"/>: ${course.teacher.surname} ${course.teacher.name}
                                    </ul>
                                </div>
                                <div class="plan-features">
                                    <em><h3 class="section-title ">${course.name}</h3></em>
                                    <p>${course.description}</p>
                                </div>
                                <div class="accept-plan">
                                    <a target="_self" href="/coursesDescription?course_id=${course.id}"><fmt:message
                                            key="login.more"/></a>
                                </div>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </section>
</div>
</div>
</body>
</html>