<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Skaičiuotuvas</title>
    </head>
    <body>
        <h2>Internetinis skaičiuotuvas. Galimos operacijos: sudėti, atimti, dauginti, dalinti</h2>
        <form method="post" action="calculate">
            Pirmas skaičius: <input type="number" name="sk1"><p>
            Antras skaičius: <input type="number" name="sk2"><p>
            Operacijos ženklas:
            <select name="action">
                <option selected="selected" value="+">Sudėtis</option>
                <option value="-">Atimtis</option>
                <option value="*">Daugyba</option>
                <option value="/">Dalyba</option>
            </select><p>
            <input type="submit" value="skaičiuoti">
        </form>
    </body>
</html>
