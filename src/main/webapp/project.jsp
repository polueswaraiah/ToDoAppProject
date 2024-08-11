<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.todoapp.model.Project" %>
<%@ page import="com.todoapp.dao.ProjectDao" %>
<%@ page import="com.todoapp.model.User" %>
<%@ page import="com.todoapp.model.Todo" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }

        h2 {
            color: teal;
            margin-bottom: 20px;
        }

        h3 {
            color: indigo;
            margin-top: 30px;
        }

        form {
            margin-bottom: 20px;
            padding: 10px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 0 8px rgba(0, 0, 0, 0.1);
        }

        label {
            color: crimson;
            font-weight: bold;
            margin-right: 10px;
        }

        input[type="text"] {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-right: 10px;
            width: 250px;
        }

        button {
            background-color: #007bff;
            color: white;
            padding: 8px 12px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

        .project-item {
            margin-bottom: 10px;
            padding: 10px;
            background-color: white;
            border-radius: 4px;
            box-shadow: 0 0 6px rgba(0, 0, 0, 0.1);
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .project-item a {
            color: #007bff;
            text-decoration: none;
        }

        .project-item a:hover {
            text-decoration: underline;
        }

        .delete-button {
            background-color: red;
            color: white;
            padding: 5px 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .delete-button:hover {
            background-color: darkred;
        }
        
        .todo-item {
            margin-bottom: 10px;
            padding: 10px;
            background-color: #e9ecef;
            border-radius: 4px;
            box-shadow: 0 0 4px rgba(0, 0, 0, 0.1);
        }

        .todo-item form {
            display: inline;
        }
        
        .todo-item button {
            background-color: #28a745;
            color: white;
        }

        .todo-item button.delete {
            background-color: red;
        }

        .todo-item button.delete:hover {
            background-color: darkred;
        }
    </style>
</head>
<body>
    <h2>Projects</h2>
    <form action="project" method="post">
        <input type="hidden" name="action" value="create">
        <label for="title">New Project:</label>
        <input type="text" id="title" name="title" required>
        <button type="submit">Create</button>
    </form>

    <h3>Your Projects:</h3>
    <%
        User user = (User) session.getAttribute("user");
        ProjectDao projectDao = new ProjectDao();
        List<Project> projects = projectDao.getProjectsByUserId(user.getId());
        for (Project project : projects) {
    %>
        <div class="project-item">
            <a href="project?id=<%= project.getId() %>"><%= project.getTitle() %></a>
            <form action="project" method="post" style="display:inline;">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="id" value="<%= project.getId() %>">
                <button type="submit" class="delete-button">Delete</button>
            </form>
        </div>
        <h4>Manage Todos for <%= project.getTitle() %>:</h4>
        <form action="todo" method="post">
            <input type="hidden" name="action" value="add">
            <input type="hidden" name="projectId" value="<%= project.getId() %>">
            <label for="description_<%= project.getId() %>">New Todo:</label>
            <input type="text" id="description_<%= project.getId() %>" name="description" required>
            <button type="submit">Add Todo</button>
        </form>
        <% 
            // Fetch todos for the current project
            List<Todo> todos = projectDao.getTodosByProjectId(project.getId());
            for (Todo todo : todos) {
        %>
            <div class="todo-item">
                <p><%= todo.getDescription() %></p>
                <form action="todo" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="projectId" value="<%= project.getId() %>">
                    <input type="hidden" name="id" value="<%= todo.getId() %>">
                    <input type="text" name="description" value="<%= todo.getDescription() %>" required>
                    <button type="submit">Update</button>
                </form>
                
                <form action="todo" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="projectId" value="<%= project.getId() %>">
                    <input type="hidden" name="id" value="<%= todo.getId() %>">
                    <button type="submit" class="delete-button delete">Delete</button>
                </form>
            </div>
        <% } %>
    <% } %>
</body>
</html>
