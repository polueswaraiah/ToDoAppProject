package com.todoapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.todoapp.model.Project;
import com.todoapp.model.Todo;
import com.todoapp.util.JDBCUtils;

public class ProjectDao {
	
    public boolean addProject(Project project) {
        String sql = "INSERT INTO projects (title, user_id,created_date) VALUES (?, ?,?)";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, project.getTitle());
            statement.setInt(2, project.getUserId());
            statement.setDate(3, new java.sql.Date(project.getCreatedDate().getTime()));

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Project> getProjectsByUserId(int userId) {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM projects WHERE user_id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Project project = new Project();
                    project.setId(resultSet.getInt("id"));
                    project.setTitle(resultSet.getString("title"));
                    project.setCreatedDate(resultSet.getDate("created_date"));
                    project.setUserId(resultSet.getInt("user_id"));
                    projects.add(project);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    public Project getProjectById(int projectId) {
        String sql = "SELECT * FROM projects WHERE id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, projectId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Project project = new Project();
                    project.setId(resultSet.getInt("id"));
                    project.setTitle(resultSet.getString("title"));
                    project.setCreatedDate(resultSet.getDate("created_date"));
                    project.setUserId(resultSet.getInt("user_id"));
                    return project;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateProject(Project project) {
        String sql = "UPDATE projects SET title = ? WHERE id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, project.getTitle());
            statement.setInt(2, project.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteProject(int id) {
        String sql = "DELETE FROM projects WHERE id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Todo> getTodosByProjectId(int projectId) {
        List<Todo> todos = new ArrayList<>();
        String sql = "SELECT * FROM todos WHERE project_id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, projectId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Todo todo = new Todo();
                    todo.setId(resultSet.getInt("id"));
                    todo.setDescription(resultSet.getString("description"));
                    todo.setStatus(resultSet.getString("status"));
                    todo.setCreatedDate(resultSet.getTimestamp("created_date"));
                    todo.setUpdatedDate(resultSet.getTimestamp("updated_date"));
                    todo.setProjectId(resultSet.getInt("project_id"));
                    todos.add(todo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
}}