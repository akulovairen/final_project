<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.login"/>
<div class="bg-nav bg-dark">
    <div class="container">
        <nav class="navbar navbar-expand-sm  navbar-dark">
            <!-- Brand -->
            <a class="navbar-brand" href="/coursesInProgress">Help In Wartime</a>
            <%--                <img src="bird.jpg" alt="Logo" style="width:40px;">--%>

            <!-- Toggler/collapsibe Button -->
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <!-- Navbar links -->
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="/coursesInProgress"><i class="fas fa-solid fa-home"></i> <fmt:message
                                key="login.toHomePage"/> <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/profile"><i class="fas fa-solid fa-user"></i> <fmt:message
                                key="login.profile"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" onclick="return confirmDelete();" href="/logout"><i
                                class="fas fa-solid fa-arrow-right"></i> <fmt:message key="login.logout"/></a>
                    </li>
                    <%--                    <li class="nav-item">--%>
                    <%--                        <a class="nav-link" href="?lang=EN"><img src="/images?filename=british-flag-2.png"/></a>--%>
                    <%--                    </li>--%>
                    <%--                    <li class="nav-item">--%>
                    <%--                        <a class="nav-link" href="?lang=UA"><img src="/images?filename=flag-3d-UA 2.png"/></a>--%>
                    <%--                    </li>--%>
                    <script>
                        function confirmDelete() {
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
</div>