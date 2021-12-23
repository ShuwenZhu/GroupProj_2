<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Register</title>

<link href="bootstrap/bootstrap.min.css" rel="stylesheet">

</head>

<body>
	<main class="d-flex">
		<form class="text-center mx-auto pt-5" style="width: 330px;"
			action="/register?token=${param.token}&redirect=${param.redirect}"
			method="post">
			<h1 class="h3 mb-3 fw-normal">Please Register</h1>

			<div class="form-floating">
				<input type="text" class="form-control" id="floatingUsername"
					name="username" placeholder="Username" value="${param.username}"
					required> <label for="floatingUsername">Username</label>
			</div>
			<div class="form-floating mt-1">
				<input type="password" class="form-control" id="floatingPassword"
					name="password" placeholder="Password" required> <label
					for="floatingPassword">Password</label>
			</div>
			<div class="form-floating mt-1">
				<input type="password" class="form-control"
					id="floatingPasswordConfirm" name="passwordconfirm"
					placeholder="Re-enter Password" required> <label
					for="floatingPasswordConfirm">Re-enter Password</label>
			</div>
			<div class="form-floating mt-1">
				<input type="email" class="form-control" id="floatingEmail"
					placeholder="Email" readonly value="${email}"> <label
					for="floatingEmail">Email</label>
			</div>

			<button class="w-100 mt-1 btn btn-lg btn-primary" type="submit">Register</button>

			<p class="text-danger pt-5">${message}</p>
		</form>
	</main>

	<script src="bootstrap/bootstrap.bundle.min.js"></script>

</body>
</html>
