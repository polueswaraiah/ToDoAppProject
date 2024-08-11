package com.todoapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.todoapp.model.Todo;
import com.todoapp.util.JDBCUtils;

public class TodoDao {
    public boolean addTodo(Todo todo) {
        String sql = "INSERT INTO todos (description, status, created_date,updated_date, project_id) VALUES (?, ?, ?,?,?)";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, todo.getDescription());
            statement.setString(2, todo.getStatus());
            statement.setDate(3, new java.sql.Date(todo.getCreatedDate().getTime()));
            statement.setDate(4, new java.sql.Date(todo.getUpdatedDate().getTime()));
            statement.setInt(5, todo.getProjectId());
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
                    todo.setCreatedDate(resultSet.getDate("created_date"));
                    todo.setUpdatedDate(resultSet.getDate("updated_date"));
                    todo.setProjectId(resultSet.getInt("project_id"));
                    todos.add(todo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
    }

    public boolean updateTodoStatus(int id) {
        String sql = "UPDATE todos SET status = ?, updated_date = ?  WHERE id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "Completed");
            statement.setDate(2, new java.sql.Date(new Date().getTime()));
            statement.setInt(3, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteTodoById(int id) {
        String sql = "DELETE FROM todos WHERE id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateTodoDescription(int todoId, String description) {
        String sql = "UPDATE todos SET description = ?, updated_date = CURRENT_TIMESTAMP WHERE id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, description);
            statement.setInt(2, todoId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}