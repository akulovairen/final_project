<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization"/>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Teacher Gradebook By Course</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
            integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
            crossorigin="anonymous"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/bootstrap-table.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/bootstrap-table.min.css">

    <script type="text/javascript">
        function addToTable2(student_id) {
            let foundTrElement = document.getElementById("st" + student_id);
            if (foundTrElement != null) {
                changeButton(foundTrElement, student_id, true);

                let toBeUpdatedTable = document.getElementById("to_be_updated_tbody");
                toBeUpdatedTable.appendChild(foundTrElement);
            }
        }

        function removeFromTable2(student_id) {
            let foundTrElement = document.getElementById("st" + student_id);
            if (foundTrElement != null) {
                let toBeUpdatedTable = document.getElementById("to_be_updated_tbody");
                let studentsListTable = document.getElementById("students_list_tbody");

                changeButton(foundTrElement, student_id, false);

                toBeUpdatedTable.removeChild(foundTrElement);
                studentsListTable.appendChild(foundTrElement)
            }
        }

        function changeButton(foundTrElement, student_id, removeBtn) {
            let addBtn = foundTrElement.children[5].children['addBtn'];
            let btnParentTd = addBtn.parentNode;
            btnParentTd.removeChild(addBtn);
            if (removeBtn) {
                addBtn.value = "Отменить";
                let removeFunction = "removeFromTable2(" + student_id + ")";
                addBtn.setAttribute("onclick", removeFunction);
            } else {
                addBtn.value = "Обновить";
                let removeFunction = "addToTable2(" + student_id + ")";
                addBtn.setAttribute("onclick", removeFunction);
            }
            btnParentTd.appendChild(addBtn);
        }

        function submitGradebook() {

            if (confirm("Ви підтвержуєте оновлення журналу?")) {
                let tbody = document.getElementById("to_be_updated_tbody");
                let gradebook = [];

                let trs = tbody.children;
                for (let i = 0; i < trs.length; i++) {
                    let tr = trs[i];

                    let studentId = tr.id.substring(2);
                    let grades = {
                        "test1": tr.children[1].children[0].value,
                        "test2": tr.children[2].children[0].value,
                        "test3": tr.children[3].children[0].value,
                        "test4": tr.children[4].children[0].value,
                    };

                    let gradebookId = tr.children[5].children[0].value;
                    let object = {
                        "student_id": studentId,
                        "gradebook_id": gradebookId,
                        "grades": grades
                    };

                    gradebook.push(object);
                }
                let courseId = new URLSearchParams(window.location.search).get("course_id");

                let requestBody = {
                    "course_id": courseId,
                    "data": gradebook
                };
                let xhr = new XMLHttpRequest();
                xhr.open("POST", '/teacherGradebookByCourse', true);
                xhr.setRequestHeader("Content-type", "application/json");
                xhr.onreadystatechange = function () {
                    if (xhr.readyState == XMLHttpRequest.DONE) {
                        console.log('status is = ' + xhr.status);
                        if (xhr.status == 200 || xhr.status == 201) {
                            location.href = '/teacherGradebookByCourse?course_id=' + courseId;
                        } else {
                            document.getElementById("errMsg").innerHTML = "<span style='color: red'>Error. Value should be between 0 and 5</span>";
                        }
                    }
                }
                xhr.send(JSON.stringify(requestBody));
            } else {
                return false;
            }
        }
    </script>
</head>
<body>

<navbar:NavTeacher/>
<div class="container">
    <div style="text-align: center;">
        <caption class=".caption-top"><fmt:message key="gradebook.byCourse"/> ${course.name}</caption>
        <br/>
        <input type="text" id="myInput" onkeyup="myFunction()" placeholder="<fmt:message key="gradebook.search"/>">
    </div>

    <table id="students_list" class="table table-striped caption-top">
        <thead class="table-light">
        <tr>
            <th><fmt:message key="login.student"/> </th>
            <th><fmt:message key="login.test"/> 1</th>
            <th><fmt:message key="login.test"/> 2</th>
            <th><fmt:message key="login.test"/> 3</th>
            <th><fmt:message key="login.test"/> 4</th>
            <th><fmt:message key="gradebook.action"/> </th>
        </tr>
        </thead>
        <tbody id="students_list_tbody">

        <c:forEach var="course" items="${courseGradebook}">
            <tr id="st${course.student.id}">
                <td><c:out value="${course.student.surname} ${course.student.name}"/></td>
                <td><input type="number" min=0 max=5 value="${course.test1}"
                />
                    <c:if test="${not empty messagesMap.test1}">
                        <div style="color: red">${messagesMap.test1}</div>
                    </c:if></td>
                <td><input type="number" min=0 max=5 value="${course.test2}"
                />
                    <c:if test="${not empty messagesMap.test2}">
                        <div style="color: red">${messagesMap.test2}</div>
                    </c:if></td>
                <td><input type="number" min=0 max=5 value="${course.test3}"
                />
                    <c:if test="${not empty messagesMap.test3}">
                        <div style="color: red">${messagesMap.test3}</div>
                    </c:if></td>
                <td><input type="number" min=0 max=5 value="${course.test4}"
                />
                    <c:if test="${not empty messagesMap.test4}">
                        <div style="color: red">${messagesMap.test4}</div>
                    </c:if></td>
                <td>
                    <input id="gb" type="hidden" value="${course.id}">
                    <input type="button" name="addBtn" value="<fmt:message key="gradebook.update"/>" onclick="addToTable2(${course.student.id})">
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <caption><fmt:message key="gradebook.students.updateGrades"/></caption>
    <table id="to_be_updated" class="table table-striped caption-top">
        <thead>
        <tr>
            <th><fmt:message key="login.student"/></th>
            <th><fmt:message key="login.test"/> 1</th>
            <th><fmt:message key="login.test"/> 2</th>
            <th><fmt:message key="login.test"/> 3</th>
            <th><fmt:message key="login.test"/> 4</th>
            <th><fmt:message key="gradebook.action"/></th>
        </tr>
        </thead>
        <tbody id="to_be_updated_tbody">
        </tbody>
    </table>
    <button type="button" onclick="submitGradebook()"><fmt:message key="gradebook.updateAll"/></button>
    <div id="errMsg"></div>
</div>

<script>
    function myFunction() {
        var input, filter, table, tr, td, i, txtValue;
        input = document.getElementById("myInput");
        filter = input.value.toUpperCase();
        table = document.getElementById("students_list");
        tr = table.getElementsByTagName("tr");
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[0];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
</script>

</body>
</html>
