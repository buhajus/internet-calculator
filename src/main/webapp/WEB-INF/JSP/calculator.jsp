<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Skaičiuotuvas</title>
        <style>
            .error{color:red}
        </style>
    </head>
    <body>

        <h2>Internetinis skaičiuotuvas. Galimos operacijos: sudėti, atimti, dauginti, dalinti</h2>
        <h3>Skaičiuotuvas jautrus neigiamiems skaičiams</h3>

        <form:form method="post" action="calculate" modelAttribute="number">

            Pirmas skaičius: <form:input type="number" path="sk1"/>
            <form:errors path="sk1" cssClass="error"/><p>

            Antras skaičius: <form:input type="number" path="sk2"/>
             <form:errors path="sk2" cssClass="error"/> <p>

            Operacijos ženklas:
            <select name="action">
                <option selected="selected" value="+">Sudėtis</option>
                <option value="-">Atimtis</option>
                <option value="*">Daugyba</option>
                <option value="/">Dalyba</option>
            </select>
            <input type="submit" value="skaičiuoti">
        </form:form>
    </body>
</html>
