package com.todoapp.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.todoapp.dao.TodoDao;
import com.todoapp.model.Todo;

@WebServlet("/todo")
public class TodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TodoDao todoDao = new TodoDao();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		switch (action) {
		case "showTasks":
			showTasks(request, response);
			break;

		case "markComplete":
			markComplete(request, response);
			break;

		case "add":
			addTodo(request, response);
			break;
		case "update":
			updateTodo(request, response);
			break;
		case "delete":
			deleteTodo(request, response);
			break;

		}
	}

	private void showTasks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int projectId = Integer.parseInt(request.getParameter("projectId"));

		List<Todo> mytasks = todoDao.getTodosByProjectId(projectId);
		HttpSession session = request.getSession();
		session.setAttribute("ProjectId", projectId);
		if (mytasks.size() > 0) {
			session.setAttribute("MyTasks", mytasks);
			session.setAttribute("TasksFound", "Yes");
		} else {
			session.setAttribute("TasksFound", "NO");
		}

		request.getRequestDispatcher("home.jsp").forward(request, response);

	}

	private void markComplete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int todoId = Integer.parseInt(request.getParameter("taskId"));
		todoDao.updateTodoStatus(todoId);
		HttpSession session = request.getSession();
		int projectId = (Integer) session.getAttribute("ProjectId");
		List<Todo> mytasks = todoDao.getTodosByProjectId(projectId);

		if (mytasks.size() > 0) {
			session.setAttribute("MyTasks", mytasks);
			session.setAttribute("TasksFound", "Yes");
		} else {
			session.setAttribute("TasksFound", "NO");
		}

		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

	private void addTodo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int projectId = Integer.parseInt(request.getParameter("projectId"));
		String description = request.getParameter("description");
		Todo todo = new Todo();
		todo.setDescription(description);
		todo.setProjectId(projectId);
		todoDao.addTodo(todo);
		response.sendRedirect("project?id=" + projectId);
	}

	private void updateTodo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int todoId = Integer.parseInt(request.getParameter("id"));
		String description = request.getParameter("description");
		todoDao.updateTodoDescription(todoId, description);
		response.sendRedirect("project?id=" + request.getParameter("projectId"));
	}

	private void deleteTodo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int projectId = Integer.parseInt(request.getParameter("projectId"));
		int todoId = Integer.parseInt(request.getParameter("id"));
		todoDao.deleteTodoById(todoId);
		response.sendRedirect("project?id=" + projectId);
	}

}
