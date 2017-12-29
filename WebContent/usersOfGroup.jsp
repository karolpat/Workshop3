<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="/header.jsp"></jsp:include>

<h3>List of users of group no. ${param.id }</h3>

	<table>
		<thead>
			<th>ID</th>
			<th>Username</th>
			<th>Email</th>
			<th>Link to user</th>
		</thead>
		<tbody>

			<c:forEach var="aUser" items="${list }">

				<tr>
					<td>${aUser.id }</td>
					<td>${aUser.username }</td>
					<td>${aUser.email }</td>
					<td><a href="/warsztat/usersPage?id=${aUser.id }">Go to this user</a></td>
				</tr>
			</c:forEach>

		</tbody>

	</table>



</body>
</html>