<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">

<%--    <script type="text/javascript" src="bootstrap.min.js"></script>--%>
    <script type="text/javascript">
        function addToTable2(student_id) {
            let foundTrElement = document.getElementById("st"+student_id);
            if (foundTrElement != null) {
                changeButton(foundTrElement, student_id, true);

                let toBeUpdatedTable = document.getElementById("to_be_updated_tbody");
                toBeUpdatedTable.appendChild(foundTrElement);
            }
        }

        function removeFromTable2(student_id) {
            let foundTrElement = document.getElementById("st"+student_id);
            if (foundTrElement != null) {
                let toBeUpdatedTable = document.getElementById("to_be_updated_tbody");
                let studentsListTable = document.getElementById("students_list_tbody");

                changeButton(foundTrElement, student_id, false);

                toBeUpdatedTable.removeChild(foundTrElement);
                studentsListTable.appendChild(foundTrElement)
            }
        }

        function changeButton(foundTrElement, student_id, removeBtn) {
            let elementsByTagName = foundTrElement.getElementsByTagName("input");
            if (elementsByTagName != null && elementsByTagName.length > 3) {
                let btn = elementsByTagName[4];
                let btnParentTd = btn.parentNode;
                btnParentTd.removeChild(btn);
                if (removeBtn) {
                    btn.value = "Отменить";
                    let removeFunction = "removeFromTable2(" + student_id + ")";
                    btn.setAttribute("onclick", removeFunction);
                } else {
                    btn.value = "Обновить";
                    let removeFunction = "addToTable2(" + student_id + ")";
                    btn.setAttribute("onclick", removeFunction);
                }
                btnParentTd.appendChild(btn);
            }
        }
    </script>

    <meta charset="UTF-8">
    <title>Gradebook</title>
</head>
<body>
<div>
    <table id = "students_list" class="table table-striped caption-top">
        <caption class=".caption-top">Журнал успеваемости по курсу</caption>
        <thead class="table-light">
        <tr>
            <th>Студент</th>
            <th>Тест 1</th>
            <th>Тест 2</th>
            <th>Тест 3</th>
            <th>Тест 4</th>
            <th>Действие</th>
        </tr>
        </thead>
        <tbody id = "students_list_tbody">
        <tr id = "st1">

            <td>Хуй Булыжников</td>
            <td><input type="number" value="5" min = 0 max = 5/></td>
            <td><input type="number" value="3" min = 0 max = 5/></td>
            <td><input type="number" value="4" min = 0 max = 5/></td>
            <td><input type="number" value="2" min = 0 max = 5/></td>
            <td><input type="button" name="addBtn" value="Обновить" onclick="addToTable2(1)"></td>
        </tr>
        <tr id = "st2">
            <td>Пися Камушкин</td>
            <td><input type="number" value="2" min = 0 max = 5/></td>
            <td><input type="number" value="3" min = 0 max = 5/></td>
            <td><input type="number" value="1" min = 0 max = 5/></td>
            <td><input type="number" value="0" min = 0 max = 5/></td>
            <td><input type="button" name="addBtn" value="Обновить" onclick="addToTable2(2)"></td>
        </tr>
        <tr id = "st3">
            <td>Валентин Игнатов</td>
            <td><input type="number" value="0" min = 0 max = 5/></td>
            <td><input type="number" value="0" min = 0 max = 5/></td>
            <td><input type="number" value="0" min = 0 max = 5/></td>
            <td><input type="number" value="0" min = 0 max = 5/></td>
            <td><input type="button" name="addBtn" value="Обновить" onclick="addToTable2(3)"></td>
        </tr>
        <tr id = "st4">
            <td>Лапша Изподковша</td>
            <td><input type="number" value="0" min = 0 max = 5/></td>
            <td><input type="number" value="0" min = 0 max = 5/></td>
            <td><input type="number" value="0" min = 0 max = 5/></td>
            <td><input type="number" value="0" min = 0 max = 5/></td>
            <td><input type="button" name="addBtn" value="Обновить" onclick="addToTable2(4)"></td>
        </tr>
        <tr id = "st5">
            <td>Синельников Хуец</td>
            <td><input type="number" value="5" min = 0 max = 5/></td>
            <td><input type="number" value="5" min = 0 max = 5/></td>
            <td><input type="number" value="3" min = 0 max = 5/></td>
            <td><input type="number" value="5" min = 0 max = 5/></td>
            <td><input type="button" name="addBtn" value="Обновить" onclick="addToTable2(5)"></td>
        </tr>
        </tbody>
    </table>
</div>
<div>
    <table id = "to_be_updated" class="table table-striped caption-top">
        <caption>Студенты, оценки которых будут обновлены</caption>
        <thead>
        <tr>
            <th>Студент</th>
            <th>Тест 1</th>
            <th>Тест 2</th>
            <th>Тест 3</th>
            <th>Тест 4</th>
            <th>Действие</th>
        </tr>
        </thead>
        <tbody id = "to_be_updated_tbody">
        </tbody>
    </table>
    <input type="button" value="Обновить журнал">
</div>
</body>
</html>
