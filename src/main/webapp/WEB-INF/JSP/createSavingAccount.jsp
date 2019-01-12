<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="SavingAccountcreated" method="post">
		<br>Account Holder Name		<input
			name="accountHolderName" />
		<br>
			
			Enter Initial Balance<input name="accountBalance" />



		<br>Salaried<input type="radio" name="salary" value="yes" />salarised<input
			type="radio" name="salary" value="no" />
		 not salarised<input type="submit" value="submit">
	</form>

</body>
</html>