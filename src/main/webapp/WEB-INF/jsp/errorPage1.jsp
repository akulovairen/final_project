<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error page</title>
</head>
<body>
    <h1>Oops... You've got an error</h1>

    <p><b>Http status:</b> <c:out value="${javax.servlet.error.status_code}"/></p>
    <p><b>Error message:</b> <c:out value="${javax.servlet.error.message}"/></p>
    <p><b>Request URI:</b> <c:out value="${javax.servlet.error.request_uri}"/></p>
</body>
</html>
