<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Skaičiuoti</title>
            <jsp:include page="header.jsp"/>

    </head>
    <body class="container">
        <h4>${sk1} ${action} ${sk2} = ${result} </h4>
        <form>
         <input type="button" value="Go back!" onclick="history.back()">
        </form>
    </body>
</html>
