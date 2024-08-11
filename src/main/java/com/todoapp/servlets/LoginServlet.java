package com.todoapp.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.todoapp.dao.ProjectDao;
import com.todoapp.dao.UserDao;
import com.todoapp.model.Project;
import com.todoapp.model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDao userDao = new UserDao();
	private ProjectDao projectDao = new ProjectDao();

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("inside Login doPost()");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (userDao.validateUser(username, password)) {
			HttpSession session = request.getSession();

			User user = userDao.getUserByUsername(username);
			session.setAttribute("User", user);

			List<Project> myprojects = projectDao.getProjectsByUserId(user.getId());
			if (myprojects.size() > 0) {
				session.setAttribute("MyProjects", myprojects);
				session.setAttribute("ProjectFound", "Yes");
			} else {
				session.setAttribute("ProjectFound", "NO");
			}
			response.sendRedirect("home.jsp");
			
		} else {
			request.setAttribute("errorMessage", "Invalid username or password");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
