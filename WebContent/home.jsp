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

	<h3>List of the newest five solutions</h3>

	<table>

		<thead>
			<th>Created</th>
			<th>Updated</th>
			<th>Description</th>
			<th>Excercise id</th>
			<th>User id</th>
			<th></th>
		</thead>
		<tbody>
			<c:forEach var="solution" items="${list }">
				<tr>
					<td>${solution.created}</td>
					<td>${solution.update}</td>
					<td>${solution.description}</td>
					<td>${solution.excercise_id }</td>
					<td>${solution.users_id }</td>
				</tr>
			</c:forEach>
		</tbody>

	</table>

</body>
</html>