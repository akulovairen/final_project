<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <title>Available courses Guest</title>

<%--        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">--%>
<%--        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>--%>
<%--        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>--%>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    <link rel="icon" href="https://maxcdn.icons8.com/Share/icon/color/Gaming/pokecoin1600.png" />
    <!---- Icon link local ----->

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
    <!-------    font awesome online plug --------------->

    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">


    <style rel="css">
        .demo2 {
            background: #f2f2f2;
            padding: 30px 0
        }

        .pricingTable2 {
            padding-bottom: 30px;
            margin: 0 15px;
            background: #fff;
            text-align: center;
            border-radius: 15px;
            overflow: hidden
        }

        .pricingTable2:hover {
            box-shadow: 0 0 10px rgba(195, 67, 67, .3) inset, 0 0 20px -5px rgba(0, 0, 0, .8)
        }

        .pricingTable2 .pricingTable-header {
            padding: 20px 15px 45px;
            background: #0922c6;
            -webkit-clip-path: polygon(50% 100%, 100% 60%, 100% 0, 0 0, 0 60%);
            clip-path: polygon(50% 100%, 100% 60%, 100% 0, 0 0, 0 60%);
            position: relative
        }

        .pricingTable2 .pricingTable-header:before {
            content: "";
            width: 400px;
            height: 400px;
            border-radius: 50%;
            position: absolute;
            right: -50%;
            top: -130%;
            background: repeating-radial-gradient(rgba(255, 255, 255, .05), rgba(255, 255, 255, .2) 20%);
            transition: all .5s ease 0s
        }

        .pricingTable2:hover .pricingTable-header:before {
            right: 50%
        }

        .pricingTable2 .title {
            font-size: 30px;
            color: #fff;
            margin: 0
        }

        .pricingTable2 .price-value {
            display: block;
            font-size: 20px;
            color: #000;
            margin: 0 0 20px;
            transition: all .3s ease 0s
        }

        .pricingTable2 .pricing-content {
            padding: 30px 25px 0;
            margin: 0;
            list-style: none
        }

        .pricingTable2 .pricing-content li {
            font-size: 18px;
            color: #909090;
            line-height: 40px;
            letter-spacing: 1px;
            text-transform: capitalize;
            border-bottom: 2px solid rgba(0, 0, 0, .15);
            margin-bottom: 10px;
            position: relative
        }

        .pricingTable2 .pricing-content li:last-child {
            border-bottom: none
        }

        .pricingTable2 .pricing-content li i {
            color: #ffc11a
        }

        .pricingTable2 .pricingTable-signup {
            display: block;
            padding: 18px 0;
            margin: 0 25px;
            border-radius: 10px;
            background: #ffc11a;
            font-size: 20px;
            color: #fff;
            letter-spacing: 1px;
            text-transform: uppercase;
            overflow: hidden;
            position: relative;
            transition: all .3s ease 0s
        }

        .pricingTable2 .pricingTable-signup:hover {
            letter-spacing: 2px;
            box-shadow: 0 0 10px rgba(0, 0, 0, .7), 0 0 0 7px rgba(255, 255, 255, .5) inset
        }

        .pricingTable2 .pricingTable-signup:before {
            content: "";
            width: 230px;
            height: 230px;
            border-radius: 50%;
            background: repeating-radial-gradient(rgba(255, 255, 255, .05), rgba(255, 255, 255, .2) 20%);
            position: absolute;
            top: -180%;
            right: -40%;
            transition: all .8s ease 0s
        }

        .pricingTable2 .pricingTable-signup:hover:before {
            right: 40%
        }

        .pricingTable2.blue .pricingTable-header, .pricingTable2.blue .pricingTable-signup {
            background: #ffff00
        }

        .pricingTable2.blue .pricing-content li i {
            color: #15b8f3
        }

        @media only screen and (max-width: 990px) {
            .pricingTable2 {
                margin-bottom: 30px
            }
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
            background-color:white;
            background-attachment:scroll;
            background-repeat:no-repeat;
            background-position:bottom;
            background-size:cover ;
        }

        /*----------------Containers--------------------*/

        #top-container-fluid-nav
        {
            /*background-image: url("../images/.jpg");*/
            background-color:white;
            background-attachment:scroll;
            background-repeat:no-repeat;
            background-position:center;
            background-size:cover;
        }
        /*https://media.dyvys.info/2020/04/zsu-2.jpg*/
        /*марка русский военный*/

        #banner-container-fluid {
            background-image: url("https://kyiv.comments.ua/img/publications/cjOOyF0Vb65hjbQ2dpQh2inchfiNaeb4.jpeg");
            background-attachment: scroll;
            background-repeat: no-repeat;
            background-position: center;
            background-size: cover;
        }
        #footer-container-fluid
        {
            /*background-image: url("../images/");*/
            background-color:white;
            background-attachment:scroll;
            background-repeat:no-repeat;
            background-position:center;
            background-size:cover;
            margin:60px 60px;

        }

        a:hover
        {
            text-decoration:none;
            cursor:hand;
        }

        #banner-container-fluid  .jumbotron
        {
            background-color:#9e9e9e54;
            color:white;
            max-height:500px;
            height:430px;
        }
        #banner-container-fluid  .jumbotron .lead
        {
            text-align:center;
        }
        #banner-container-fluid  .jumbotron .leader
        {
            text-align:center;
        }
        #banner-container-fluid  .jumbotron p
        {
            text-align:center;
        }

        /**********************
               color-graph
       **********************/
        .colorgraph
        {
            width:80%;
            text-align:center;
            margin: 0 auto;
        }

        /**********************
		navtabs
**********************/
        .tab-content {padding:20px;max-height:1000px;}
        .nav-tabs .nav-link.active, .nav-tabs .nav-item.show .nav-link
        {
            border-color:#00BCD4 #fff #00BCD4;
        }
        .tab-content .card-block
        {
            padding:5px;
        }
        .tab-content .list-group-item
        {
            border:0px solid white;

        }
        .tab-content .list-group-item h5
        {
            color:#057e8e;
        }
        .tab-content .list-group-item .mb-1  {
            font-size:15px;
        }
        /**********************
		Left nav
**********************/
        span
        {
            font-size:10px;
            float:right;
            background-color:#F4F7F9;
            border-radius:10px;
            padding:3px 8px;
            color:#84919B;
        }
        .lower-case
        {
            color:#84919B;
            font-size:12px;
            text-align:center;
        }

        /**********************
                Left nav
        **********************/




        /**********************
                footer-container-fluid
        **********************/

        #footer-container-fluid  li
        {
            list-style-type:none;
            color:#098c9c;
            font-size:10px;
        }

        #footer-container-fluid ul .title
        {
            color:black;
            font-size:15px;
        }

        #footer-container-fluid ul .address
        {
            color:#607d8bad;
        }
        /**********************
                footer-container-fluid
        **********************/
    </style>
</head>
<body>
<%--<div class="demo2">--%>

<%--                <nav class="navbar navbar-inverse">--%>
<%--    <div class="container-fluid">--%>
<%--        <div class="navbar-header">--%>
<%--            <a class="navbar-brand" >HelpInWartime</a>--%>
<%--        </div>--%>
<%--        <ul class="nav navbar-nav">--%>
<%--            <li class="active"><a href="/coursesListGuest"> <fmt:message key="login.availableCourse"/> </a></li>--%>
<%--        </ul>--%>
<%--        <ul class="nav navbar-nav navbar-right">--%>
<%--            <li><a href="/registration"><span class="glyphicon glyphicon-user"></span> <fmt:message key="login.register"/> </a></li>--%>
<%--            <li><a href="/login" ><span class="glyphicon glyphicon-log-in"></span> <fmt:message key="login.login"/> </a></li>--%>

<%--        </ul>--%>
<%--    </div>--%>
<%--</nav>--%>

<nav class=" my-nav navbar navbar-light navbar-expand-lg ">
    <div class="container">
        <a class="navbar-brand" href="/coursesListGuest">HelpInWartime</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/coursesListGuest"><fmt:message key="login.allCourse"/></a>
                </li>
<%--                <li>--%>
<%--                    <a href="?lang=UA"><span class="glyphicon glyphicon-user"></span> UA</a>--%>
<%--                    <a href="?lang=EN"><span class=""></span> EN</a>--%>
<%--                </li>--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link" href="#">Features</a>--%>
<%--                </li>--%>
                <li class="nav-item">
                    <a class="nav-link" href="/login"><fmt:message key="login.login"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="/registration"><fmt:message key="login.register"/></a>
                </li>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link" href="?lang=UA">UA</a>--%>
<%--                </li>--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-item" href="?lang=EN">EN</a>--%>
<%--                </li>--%>
            </ul>
        </div>
    </div>
</nav>

<%--<div class="container-fluid" id="top-container-fluid-nav">--%>
<%--    <div class="container" id="top-con">--%>
<%--        <nav class="navbar navbar-toggleable-md navbar-light bg-faded">--%>
<%--            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">--%>
<%--                <span class="navbar-toggler-icon"></span>--%>
<%--            </button>--%>
<%--            <a class="navbar-brand" href="/coursesListGuest">HelpInWartime</a>--%>
<%--            <a class="nav-item" href="/login">Увійти</a>--%>
<%--            <a class="nav-item" href="/registration">Зареєструватися</a>--%>

<%--            <div class="collapse navbar-collapse" id="navbarTogglerDemo02">--%>
<%--                <ul class="navbar-nav mr-auto mt-2 mt-md-0">--%>
<%--                    <li class="nav-item active">--%>
<%--                        <a class="nav-link" href="/coursesListGuest">Home <span class="sr-only">(current)</span></a>--%>
<%--                    </li>--%>
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-link" href="#">Link</a>--%>
<%--                    </li>--%>
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-link disabled" href="#">Disabled</a>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </div>--%>
<%--        </nav>--%>
<%--    </div>--%>
<%--</div>--%>

<div class="container-fluid" id="banner-container-fluid">
    <div class="container">
        <div class="jumbotron">
            <h1 class="leader">ПІДТРИМАЙ УКРАЇНУ ПІД ЧАС ВІЙНИ</h1>
            <p>SUPPORT | UKRAINE</p>
            <p class="lead">
                <a class="btn btn-success btn-lg" href="#" role="button"> <i class="fa fa-dollar fa-1x"></i>ПРИЄДНАТИСЯ</a><br/>
                <a class="btn btn-lg" href="?lang=UA" role="button">UA</a>
                <a class="btn btn-lg" href="?lang=EN" role="button">EN</a>
<%--                <a href="?lang=UA"><span class="glyphicon glyphicon-user"></span> UA</a>&ndash;%&gt;--%>
                <%--                    <a href="?lang=EN"><span class=""></span> EN</a>--%>
            </p>
        </div>

    </div>
</div>

<%--    <div class="jumbotron">--%>
<%--        <div class="container">--%>
<%--            <h1 class="display-3">Психология с Яндекс Весна</h1>--%>
<%--            <p>--%>
<%--                Служба Яндекс.Рефераты (ранее Яндекс.Весна) позволяет генерировать случайный текст на заданную тематику.--%>
<%--                Импользуется для наполнения сайта контентом, что позволяет лучше оценить как он будет выглядеть в--%>
<%--                будущем.--%>
<%--            </p>--%>
<%--            <p>--%>
<%--                <a class="btn btn-primary btn-lg" href="https://yandex.ru/referats/" role="button">Яндекс.Рефераты</a>--%>
<%--            </p>--%>
<%--        </div>--%>
<%--    </div>--%>
    <div class="container-fluid">
        <div class="row">
            <div id="admin-sidebar" class="col-md-2 p-x-0 p-y-3">
                <ul class="sidenav admin-sidenav list-unstyled">
                    <c:forEach var="topic" items="${allTopic}">
                        <li><a href="/courseByTopicGuest?topic_id=${topic.id}"><c:out value="${topic.name}"/></a></li>
                    </c:forEach>
                </ul>
            </div>

            <div class="container">
<%--                <h1>--%>
<%--                    <fmt:message key="login.allAvailableCourses"/>--%>
<%--                </h1>--%>
                <h4 style="padding-top:10px"></h4>
                <div class="row">
                    <c:forEach var="course" items="${forGuest}">
                        <div class="col-lg-4 col-sm-6">
                            <div class="pricingTable2">
                                <div class="pricingTable-header">
                                    <h2 class="title">${course.name}</h2>
                                    <div class="price-value">${course.topic.name}</div>
                                </div>
                                <ul class="pricing-content">
                                    <li><i class="fa fa-check"></i> <fmt:message key="login.start"/> ${course.dateStart}
                                    </li>
                                    <li><i class="fa fa-check"></i> <fmt:message key="login.durationInWeeks"/>: ${course.duration}</li>
                                    <li><i class="fa fa-check"></i> <fmt:message key="login.teacher"/>: ${course.teacher.surname} ${course.teacher.name}</li>

                                </ul>
                                <a href="/courseDescriptionGuest?course_id=${course.id}"
                                   class="pricingTable-signup"><fmt:message key="login.more"/></a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</body>
</html>