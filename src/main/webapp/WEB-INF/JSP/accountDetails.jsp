<%@ page isELIgnored="false" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action ="updateAccount">
	<table>
		<tr>
			<th>Account Number</th>
			<th><a href="sortByName">Holder Name</a></th>
			<th>Account Balance</th>
			<th>Salary</th>
			<th>Over Draft Limit</th>
			<th>Type Of Account</th>
		</tr>
		<jstl:if test="${account!=null}">
			<tr>
				<td><input type="number" name="accountNumber" value="${account.bankAccount.accountNumber}" readonly></td>
				<td><input type="text" name="accounthn" value="${account.bankAccount.accountHolderName}"></td>
				<td><input type="number" name="balance" value="${account.bankAccount.accountBalance}" readonly="readonly"></td>
				<td>
				<select name="salary">
				<option selected="selected">${account.salary==true?"Yes":"No"}</option>
					<option selected="selected">${account.salary==true?"No":"Yes"}</option>
					</select>
					</td>
				<td>${"N/A"}</td>
				<td><input type="text" name="accountType" value="Saving" readonly="readonly"></td>
			</tr>
			<input type="submit" value="Update">
		</jstl:if>
		<jstl:if test="${accounts!=null}">
			<jstl:forEach var="account" items="${accounts}">
				<tr>
					<td>${account.bankAccount.accountNumber}</td>
					<td>${account.bankAccount.accountHolderName }</td>
					<td>${account.bankAccount.accountBalance}</td>
					<td>${account.salary==true?"Yes":"No"}</td>
					<td>${"N/A"}</td>
					<td>${"Savings"}</td>
				</tr>
			</jstl:forEach>
		</jstl:if>
	</table>
</form>	
</body>
</html>