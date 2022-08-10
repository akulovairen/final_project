<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization"/>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Admin Page</title>

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
        .bg-primary {
            background-color: #000 !important;
        }

        .bg-circle {
            display: inline-block;
            width: 60px;
            height: 60px;
            padding: 14px 4px;
            color: #ececec;
            text-align: center;
            border-radius: 50%;
        }

        .bg-circle-outline {
            width: 50px;
            height: 50px;
            /*color:smoke;*/
            padding: 8px 2px;
            border: 2px solid;
            /*border-color: smoke;*/
            border-radius: 50%;
        }


        .bg-circle a, a:hover, .media a:focus {
            text-decoration: none !important;
            outline: none;
            color: #ececec;
        }

        .bg-circle-outline a, a:hover, .media a:focus {
            text-decoration: none !important;
            outline: none;
            color: #ececec;
        }
    </style>

</head>
<body>
<navbar:NavAdmin/>

<footer class="mt-5">
    <div class="container-fluid bg-faded mt-5">
        <div class="container">
            <div class="row py-3">
                <!-- footer column 1 start -->
                <div class="col-md-4">
                    <!-- row start -->
                    <div class="row py-2">
                        <div class="col-sm-3 hidden-md-down">
                            <a class="bg-circle bg-info" href="/createCourse">
                                <i class="fas fa-2x fa-fw fa-magic" aria-hidden="true "></i>
                            </a>
                        </div>
                        <div class="col-sm-9">
                            <h4><fmt:message key="login.createCourse"/></h4>
                            <p><fmt:message key="login.whyNot?"/></p>
                        </div>
                    </div>
                    <div class="row py-2">
                        <div class="col-sm-3 hidden-md-down">
                            <a class="bg-circle bg-info" href="/adminCoursesList">
                                <i class="fa fa-2x fa-fw fa-solid fa-list" aria-hidden="true "></i>
                            </a>
                        </div>
                        <div class="col-sm-9">
                            <h4><fmt:message key="login.listCourses"/></h4>
                            <p><fmt:message key="login.theyHere"/></p>
                        </div>
                    </div>
                    <!-- row end -->
                </div>
                <!-- footer column 1 end -->
                <!-- footer column 2 start -->
                <div class="col-md-4">
                    <div class="row py-2">
                        <div class="col-sm-3 hidden-md-down">
                            <a class="bg-circle bg-info" href="/registrationTeacher">
                                <i class="fa fa-2x fa-fw fa-solid fa-user-check" aria-hidden="true "></i>
                            </a>
                        </div>
                        <div class="col-sm-9">
                            <h4><fmt:message key="login.registerTeacher"/></h4>
                            <p><fmt:message key="login.comeWithUs"/></p>
                        </div>
                    </div>
                    <div class="row py-2">
                        <div class="col-sm-3 hidden-md-down">
                            <a class="bg-circle bg-info" href="/adminTeacherList">
                                <i class="fa fa-2x fa-fw fa-solid fa-users" aria-hidden="true"></i>
                            </a>
                        </div>
                        <div class="col-sm-9">
                            <h4><fmt:message key="login.listTeacher"/></h4>
                            <p><fmt:message key="login.searchTeacherHere"/></p>
                        </div>
                    </div>
                </div>
                <!-- row end -->
                <!-- row start -->
                <div class="col-md-4">
                    <!-- row starting  -->
                    <!-- row ended -->
                    <!-- row starting  -->
                    <div class="row py-2">
                        <div class="col-sm-3 hidden-md-down">
                            <a class="bg-circle bg-info" href="/createTopic">
                                <i class="fa fa-2x fa-fw fa-solid fa-magic" aria-hidden="true "></i>
                            </a>
                        </div>
                        <div class="col-sm-9">
                            <h4><fmt:message key="login.createTopic"/></h4>
                            <p><fmt:message key="login.seeListTopic"/></p>
                        </div>
                    </div>
                    <div class="row py-2">
                        <div class="col-sm-3 hidden-md-down">
                            <a class="bg-circle bg-info" href="/blockUnblockStudent">
                                <i class="fa fa-2x fa-fw fa-solid fa-user-lock" aria-hidden="true "></i>
                            </a>
                        </div>
                        <div class="col-sm-9">
                            <h4><fmt:message key="login.blockUnblockStudent"/></h4>
                            <p><fmt:message key="login.doItHere"/></p>
                        </div>
                    </div>
                    <!-- row ended -->
                </div>
            </div>
        </div>
    </div>
</footer>
</body>
</html>
