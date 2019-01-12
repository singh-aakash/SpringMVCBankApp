<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="amountdeposit">
		<p>
			Account Number:<input type="number" name="accountNumber" required placeholder="Enter AccountNumber">
		</p>
		<p>
			Amount:<input type="number" name="amount" required min="0"
				required placeholder="Enter amount">
		</p>
		<input type="submit" name="submit" value="submit">
<%-- 		<div>
			<jsp:include page="homeLink.html"></jsp:include>
		</div> --%>
	</form>
</body>
</html>