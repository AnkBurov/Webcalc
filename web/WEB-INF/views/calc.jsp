<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spring MVC based expression calculator</title>
    <style>
        p {
            text-indent: 20px; /* Отступ первой строки в пикселах */
        }
    </style>
    <meta charset="utf-8">
</head>
<body>
<h1>Spring MVC based expression calculator</h1>
<p>Корректно (учитывая приоритет операций) вычисляет результат выражений типа -5 + 2 * 5 / -2.5 ^ -2</p>
<ul>
    <li>Знак числа можно обозначать как +5 или -5</li>
    <li>Выдает результат в формате -5 + 2 * 5 / -2.5 ^ -2 = 57.5</li>
    <li>Обрабатывает всевозможные сценарии ошибочного ввода: лишние пробелы в начале, в середине и в конце выражения,
        неизвестные символы, пропущенные знаки операций и пропущенные символы, запятые вместо точек.</li>
    <li>Вычисляет выражение согласно приоритету знаков арифметических операций</li>
    <li>На текущий момент скобки не поддерживаются.</li>
</ul>
<form method="Post">
    <input type="text" name="expression"/>
    <input type='submit'/>
</form>
${calcExpression}
</body>
</html>
