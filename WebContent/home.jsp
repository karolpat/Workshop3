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

	<h3>List of the newest solutions</h3>

	<table>

		<thead>
			<th>Created</th>
			<th>Updated</th>
			<th>Excercise id</th>
			<th>Author</th>
			<th>Details</th>
		</thead>
		<tbody>
			<c:forEach var="solution" items="${list }">
				<tr>
					<td>${solution.created}</td>
					<td>${solution.update}</td>
					<td align="center">${solution.excercise_id }</td>
					<td align="center">${solution.username }</td>
					<td><a href="/warsztat/details?id=${solution.id }">Show details</a></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
	<a href="/warsztat/homepage?all=yes">See all</a>
	

</body>
</html>