package com.todoapp.servlets;

import java.io.IOException;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.todoapp.dao.ProjectDao;
import com.todoapp.model.Project;
import com.todoapp.model.User;

@WebServlet("/project")
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectDao projectDao = new ProjectDao();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");

		if (user == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		
		switch (action) {
		case "create":
			createProject(request, response, user);
			break;

		default:
			response.sendRedirect("home.jsp");
		}
	}

	private void createProject(HttpServletRequest request, HttpServletResponse response, User user)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		
		Project project = new Project();
		project.setTitle(title);
		project.setUserId(user.getId());
		project.setCreatedDate(new Date());
		
		projectDao.addProject(project);
        HttpSession session = request.getSession();
        
	    List<Project> myprojects =    projectDao.getProjectsByUserId(user.getId());
        session.setAttribute("MyProjects", myprojects);
    	if (myprojects.size() > 0) {
			session.setAttribute("MyProjects", myprojects);
			session.setAttribute("ProjectFound", "Yes");
		} else {
			session.setAttribute("ProjectFound", "NO");
		}
		response.sendRedirect("home.jsp");
	}
/*
	private void updateProject(HttpServletRequest request, HttpServletResponse response, User user)
			throws ServletException, IOException {
		int projectId = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		Project project = projectDao.getProjectById(projectId);
		project.setTitle(title);
		projectDao.updateProject(project);
		response.sendRedirect("home.jsp");
	}

	private void deleteProject(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int projectId = Integer.parseInt(request.getParameter("id"));
		projectDao.deleteProject(projectId);
		response.sendRedirect("home.jsp");
	}

	private void listProjects(HttpServletRequest request, HttpServletResponse response, User user)
			throws ServletException, IOException {
		List<Project> projects = projectDao.getProjectsByUserId(user.getId());
		request.setAttribute("projects", projects);
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int projectId = Integer.parseInt(request.getParameter("id"));
		Project existingProject = projectDao.getProjectById(projectId);
		request.setAttribute("project", existingProject);
		request.getRequestDispatcher("edit_project.jsp").forward(request, response);
	}
	*/
}
