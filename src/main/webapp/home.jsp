<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.todoapp.model.Project"%>
<%@ page import="com.todoapp.dao.ProjectDao"%>
<%@ page import="com.todoapp.model.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 20px;
	width: 100%;
}

h2, h3 {
	color: #333;
}

form {
	margin-bottom: 20px;
	background-color: #fff;
	padding: 15px;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

label {
	display: block;
	margin-bottom: 5px;
	font-weight: bold;
}

input[type="text"] {
	width: 50%;
	padding: 8px;
	margin-bottom: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

button {
	background-color: #4CAF50;
	color: white;
	border: none;
	padding: 10px 20px;
	text-align: center;
	font-size: 16px;
	cursor: pointer;
	border-radius: 4px;
}

button:hover {
	background-color: #45a049;
}

.project-item {
	padding: 10px;
	background-color: #fff;
	border: 1px solid #ddd;
	border-radius: 4px;
	margin-bottom: 10px;
	justify-content: space-around;
	align-items: center;
}

.project-item span {
	font-size: 18px;
}

.project-item form {
	margin: 0;
	display: inline;
}
</style>
</head>
<body>
	<h2 style="color: purple;">Projects</h2>

	<!-- Create Project Form -->
	<form action="project" method="post">
		<input type="hidden" name="action" value="create"> <label
			for="title" style="color: magenta;">New Project:</label> <input
			type="text" id="title" name="title" required>
		<button type="submit">Create</button>
	</form>
	<table>
		<tr>
			<td>
				<!-- List Projects -->
				<h3 style="color: orange;">My Projects:</h3> 
				
				<c:if  	test="${ ProjectFound eq 'NO'}">
					<h1>OOOOOOPPSS - No Projects</h1>
				</c:if> 
				
				<c:if test="${ ProjectFound eq 'Yes'}">
					<table class="project-item">
						<tr>
							<td>Project Id</td>
							<td>Title</td>
							<td>Created On</td>
							<td>Owner</td>
							<td></td>
						</tr>
						<c:if test=""></c:if>
						<c:forEach var="myproject" items="${MyProjects}">

							<tr>
								<td>${myproject.id}</td>
								<td>${myproject.title}</td>
								<td>${myproject.createdDate}</td>
								<td>${myproject.userId}</td>
								<td>
									<form action="todo" method="post">
										<input type="hidden" name="action" value="showTasks">
										<input type="hidden" name="projectId" value="${myproject.id}">
										<button type="submit">Show Tasks</button>
									</form>
								</td>

							</tr>
						</c:forEach>
					</table>
				</c:if>
			</td>
			<td></td>
		</tr>
	</table>
	<!-- List Tasks -->
	<h3 style="color: orange;">List of Todos -</h3>
	<c:if test="${ TasksFound eq 'NO'}">
		<h1>OOOOOOPPSS - No ToDos</h1>
	</c:if>

	<c:if test="${ TasksFound eq 'Yes'}">

		<table class="project-item">
			<tr>
				<td>Description</td>
				<td>Created On</td>
				<td>Updated On</td>
				<td>Project Id</td>
				<td>Status</td>
			</tr>

			<c:forEach var="mytask" items="${MyTasks}">

				<tr>
					<td>${mytask.description}</td>
					<td>${mytask.createdDate}</td>
					<td>${mytask.updatedDate}</td>
					<td>${mytask.projectId}</td>
					<td>${mytask.status}</td>

					<td>
						<form action="todo" method="post">
							<input type="hidden" name="action" value="editTask"> <input
								type="hidden" name="taskId" value="${mytask.id}">
							<button type="submit">Edit Task</button>
						</form>
					</td>

					<td>
						<form action="todo" method="post">
							<input type="hidden" name="action" value="markComplete">
							<input type="hidden" name="taskId" value="${mytask.id}">
							<button type="submit">Mark as Complete</button>
						</form>
					</td>

				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>
