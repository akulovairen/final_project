<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.login"/>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Доступные курсы</title>
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

        /*----------------Containers--------------------*/

        /*#top-container-fluid-nav*/
        /*{*/
        /*    !*background-image: url("../images/.jpg");*!*/
        /*    background-color:white;*/
        /*    background-attachment:scroll;*/
        /*    background-repeat:no-repeat;*/
        /*    background-position:center;*/
        /*    background-size:cover;*/
        /*}*/
        /*https://media.dyvys.info/2020/04/zsu-2.jpg*/
        /*марка русский военный*/

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

        /**********************
               color-graph
       **********************/
        .colorgraph {
            width: 80%;
            text-align: center;
            margin: 0 auto;
        }

        /**********************
		navtabs
**********************/
        .tab-content {
            padding: 20px;
            max-height: 1000px;
        }

        /*.nav-tabs .nav-link.active, .nav-tabs .nav-item.show .nav-link*/
        /*{*/
        /*    border-color:#00BCD4 #fff #00BCD4;*/
        /*}*/
        .tab-content .card-block {
            padding: 5px;
        }

        .tab-content .list-group-item {
            border: 0px solid white;

        }

        .tab-content .list-group-item h5 {
            color: #057e8e;
        }

        .tab-content .list-group-item .mb-1 {
            font-size: 15px;
        }

        /**********************
		Left nav
**********************/
        span {
            font-size: 10px;
            float: right;
            background-color: #F4F7F9;
            border-radius: 10px;
            padding: 3px 8px;
            color: #84919B;
        }

        .lower-case {
            color: #84919B;
            font-size: 12px;
            text-align: center;
        }

        /**********************
                Left nav
        **********************/


        /**********************
                footer-container-fluid
        **********************/

        #footer-container-fluid li {
            list-style-type: none;
            color: #098c9c;
            font-size: 10px;
        }

        #footer-container-fluid ul .title {
            color: black;
            font-size: 15px;
        }

        #footer-container-fluid ul .address {
            color: #607d8bad;
        }

        /**********************
                footer-container-fluid
        **********************/
    </style>
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

</head>
<body>
<navbar:NavStudent/>
<div class="container-fluid" id="banner-container-fluid">
    <div class="container">
        <div class="jumbotron">
            <h1 class="leader">ПІДТРИМАЙ УКРАЇНУ ПІД ЧАС ВІЙНИ</h1>
            <p>SUPPORT | UKRAINE</p>
            <p class="lead">
                <a class="btn btn-success btn-lg" href="/coursesInProgress" role="button">Повернутися</a><br/>
                <%--                <a class="btn btn-lg" href="?lang=UA" role="button">UA</a>--%>
                <%--                <a class="btn btn-lg" href="?lang=EN" role="button">EN</a>--%>
                <%--                <a href="?lang=UA"><span class="glyphicon glyphicon-user"></span> UA</a>&ndash;%&gt;--%>
                <%--                    <a href="?lang=EN"><span class=""></span> EN</a>--%>
            </p>
        </div>

    </div>
</div>
<div class="container-fluid">
    <div class="row">
        <div id="admin-sidebar" class="col-md-2 p-x-0 p-y-3">
            <ul class="sidenav admin-sidenav list-unstyled">
                <c:forEach var="topic" items="${allTopic}">
                    <li><a href="/courseByTopic?topic_id=${topic.id}"><c:out value="${topic.name}"/></a></li>
                </c:forEach>
            </ul>
        </div>
        <div class="container">
            <%--                <h1>--%>
            <%--                    <fmt:message key="login.allAvailableCourses"/>--%>
            <%--                </h1>--%>
            <h4 style="padding-top:10px"></h4>
            <div class="row">
                <c:forEach var="course" items="${available}">
                    <div class="col-lg-4 col-sm-6">
                        <div class="pricingTable2">
                            <div class="pricingTable-header">
                                <h2 class="title">${course.name}</h2>
                                <div class="price-value">${course.topic.name}</div>
                            </div>
                            <ul class="pricing-content">
                                <li><i class="fa fa-check"></i> <fmt:message key="login.start"/> ${course.dateStart}
                                </li>
                                <li><i class="fa fa-check"></i> <fmt:message
                                        key="login.durationInWeeks"/>: ${course.duration}</li>
                                <li><i class="fa fa-check"></i> <fmt:message
                                        key="login.teacher"/>: ${course.teacher.surname} ${course.teacher.name}</li>

                            </ul>
                            <a href="/coursesDescription?course_id=${course.id}"
                               class="pricingTable-signup"><fmt:message key="login.more"/></a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<%--<div class="dropdown">--%>
<%--  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
<%--    Теми--%>
<%--  </button>--%>
<%--  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">--%>
<%--      <c:forEach var="topic" items="${allTopic}">--%>
<%--          <li><a href="/courseByTopic?topic_id=${topic.id}"><c:out value="${topic.name}"/></a></li>--%>
<%--      </c:forEach>--%>
<%--  </div>--%>
<%--</div>--%>

<%--    <c:forEach var="topic" items="${allTopic}">--%>
<%--        <a href="/courseByTopic?topic_id=${topic.id}"><c:out value="${topic.name}"/></a><br/>--%>
<%--    </c:forEach>--%>


<%--    <c:forEach items="${available}" var="entry">--%>
<%--        Topic : <c:out value="${entry.key}"/> <br/>--%>
<%--        <c:forEach items="${entry.value}" var="it">--%>
<%--            <a href="/coursesDescription?course_id=${it.id}"><c:out value="${it.name }"/></a><br/>--%>
<%--        </c:forEach>--%>
<%--    </c:forEach>--%>

<%--    <form action="/courseRegister" method="get">--%>
<%--        <input type="submit" value="Зареєстровані курси">--%>
<%--    </form>--%>

<%--    <form action="/coursesCompleted" method="get">--%>
<%--        <input type="submit" value="Закінченні курси">--%>
<%--    </form>--%>

<%--    <form action="/profile" method="get">--%>
<%--        <input type="submit" value="Профіль">--%>
<%--    </form>--%>

<%--    <form action="/gradebookStudent" method="get">--%>
<%--        <input type="submit" value="Журнал успішності">--%>
<%--    </form>--%>

<input type="button" value="На головну" onclick="location.href='/coursesInProgress'"/>

</body>
</html>
