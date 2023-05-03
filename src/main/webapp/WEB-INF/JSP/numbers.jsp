<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>



<html>
<head>
    <title>Skaičiai</title>
    <jsp:include page="header.jsp"/>
</head>
<body class="container">
    <div class="table-responsive">
        <table class="table table-striped">

            <tr>
                <th>Pirmas skaičius</th>
                <th>Ženklas</th>
                <th>Antras skaičius</th>
                <th>Rezultatas</th>
                <th>Veiksmas</th>
            </tr>

            <!-- iteruoja per visą skaičių sarašą -->
            <c:forEach var="element" items="${numbers}">

                <!-- konstruoja įrašo atnaujinimo adresą su skaičiaus id -->
                <c:url var="atnaujinti" value="/update">
                    <c:param name="id" value="${element.id}"/>
                </c:url>

                <!-- konstruoja įrašo trynimo adresą su skaičiaus id -->
                <c:url var="trinti" value="/delete">
                    <c:param name="id" value="${element.id}"/>
                </c:url>

                <!-- konstruoja įrašo peržiūros adresą su skaičiaus id -->
                <c:url var="rodyti" value="/show">
                    <c:param name="id" value="${element.id}"/>
                </c:url>

                <tr>
                    <td>${element.sk1}</td>
                    <td>${element.action}</td>
                    <td>${element.sk2}</td>
                    <td>${element.result}</td>
                    <td>
                        <!-- atvaizduoti atnaujinimo adresą --> <a href="${atnaujinti}">Atnaujinti</a>
                        | <a href="${trinti}"
                             onclick="if (!(confirm('Ar tikrai norite ištrinti šį įrašą?'))) return false">Trinti</a>
                        | <!-- atvaizduoti rodymo adresą --> <a href="${rodyti}">Rodyti</a>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </div>
</body>
</html>
