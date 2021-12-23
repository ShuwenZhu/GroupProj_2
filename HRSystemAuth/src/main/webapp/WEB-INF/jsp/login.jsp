<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Login</title>

<link href="bootstrap/bootstrap.min.css" rel="stylesheet">

</head>

<body>
	<main class="d-flex">
		<c:if test="${!empty user}">
			<div class="p-5 mt-5 mx-auto bg-light rounded">
				<h1>Login as</h1>
				<table class="table">
					<tbody>
						<tr>
							<td>Username:</td>
							<td>${user.username}</td>
						</tr>
						<tr>
							<td>Email:</td>
							<td>${user.email}</td>
						</tr>
					</tbody>
				</table>
				<a href="/logout">logout</a>
			</div>
		</c:if>
		<c:if test="${empty user}">
			<form class="text-center mx-auto pt-5 mt-5" style="width: 330px;"
				action="/login?redirect=${param.redirect}" method="post">
				<h1 class="h3 mb-3 fw-normal">Please Login</h1>

				<div class="form-floating">
					<input type="text" class="form-control" id="floatingUsername"
						name="username" placeholder="Username" value="${param.username}"
						required> <label for="floatingUsername">Username
						or Email</label>
				</div>
				<div class="form-floating mt-1">
					<input type="password" class="form-control" id="floatingPassword"
						name="password" placeholder="Password" required> <label
						for="floatingPassword">Password</label>
				</div>

				<button class="w-100 mt-1 btn btn-lg btn-primary" type="submit">Login</button>

				<p class="text-danger pt-5">${message}</p>
			</form>
		</c:if>
	</main>

	<script src="bootstrap/bootstrap.bundle.min.js"></script>

</body>
</html>
