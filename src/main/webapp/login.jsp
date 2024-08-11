<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

.login-container {
	background-color: white;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	width: 300px;
	text-align: center;
}

h2 {
	color: #ff6347;
	margin-bottom: 20px;
}

label {
	color: #ff8c00;
	font-weight: bold;
	display: block;
	margin-bottom: 8px;
}

input[type="text"], input[type="password"] {
	width: 100%;
	padding: 8px;
	margin-bottom: 15px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

button {
	background-color: #ff6347;
	color: white;
	padding: 10px 15px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	width: 100%;
}

button:hover {
	background-color: #ff4500;
}

.error-message {
	color: red;
	margin-top: 10px;
}

a {
	color: #1e90ff;
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}
</style>
</head>
<body>

	<div class="login-container">
		<h2>Login</h2>
		<p class="error-message">${errorMessage}</p>

		<form action="login" method="post">
			<label for="username">Username:</label> <input type="text"
				id="username" name="username" required><br>
			<br> <label for="password">Password:</label> <input
				type="password" id="password" name="password" required><br>
			<br>
			<button type="submit">Login</button>
		</form>
		<p>
			Don't have an account? <a href="register.jsp">Signup</a>
		</p>
	</div>

</body>
</html>
