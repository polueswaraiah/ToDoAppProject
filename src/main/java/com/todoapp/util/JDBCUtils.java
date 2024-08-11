package com.todoapp.util;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCUtils {

	private static final String URL = "jdbc:mysql://localhost:3306/todoappdb";
	private static final String USER = "root";
	private static final String PASSWORD = "Eswaraiah";
	private static final Logger LOGGER = Logger.getLogger(JDBCUtils.class.getName());

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			LOGGER.log(Level.SEVERE, "MySQL JDBC Driver not found.", e);
		}
	}

	public static Connection getConnection() throws SQLException {
		try {
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Connection failed.", e);
			throw e;
		}
	}
}
