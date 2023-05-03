<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>Skaičius</title>
		<jsp:include page="header.jsp"/>
	</head>
	<body class="container">
		<div class="table-responsive">
			<caption>Skaičiaus informacija</caption>
			<table class="table table-striped">
				<tr>
					<td><b>Id:</b></td>
					<td>${number.id}</td>
				</tr>
				<tr>
					<td><b>Pirmas skaičius:</b></td>
					<td>${number.sk1}</td>
				</tr>
				<tr>
					<td><b>Ženklas:</b></td>
					<td>${number.action}</td>
				</tr>
				<tr>
					<td><b>Antras skaičius:</b></td>
					<td>${number.sk2}</td>
				</tr>
				<tr>
					<td><b>Rezultatas:</b></td>
					<td>${number.result}</td>
				</tr>
			</table>
		</div>


		<a type="button" href="/numbers" class="d-inline btn btn-secondary">Atgal</a>
		<a href="/update?id=${number.id}"  class="btn btn-warning">Atnaujinti</a>
	</body>
</html>
