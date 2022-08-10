<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag pageEncoding="UTF-8" %>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" >Help In Wartime</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="/teacherPage">Мої курси</a></li>
            <li class="active"><a href="/teacherCompleted">Архівні курси</a></li>
            <li class="active"><a href="/teacherAvailableCourse">Майбутні курси</a></li>

        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/profile"><span class="glyphicon glyphicon-user"></span> Профіль</a></li>
            <li><a onclick="return confirmDelete();" href="/logout"><span class="glyphicon glyphicon-log-in"></span> Вийти</a></li>

            <script>
                function confirmDelete(){
                    if(confirm("Ви впевнені,що хочете вийти?")){
                        location.href='/logout';
                    }else {
                        return false;
                    }
                }
            </script>
        </ul>
    </div>
</nav>