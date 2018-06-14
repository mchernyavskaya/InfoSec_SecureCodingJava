package com.examresults;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;

import javax.servlet.ServletResponse;
import javax.servlet.ServletResponseWrapper;
import javax.servlet.http.HttpServletResponse;

public class Database {

    private Connection connection = null;
    private ResultSet resultSet = null;

    public boolean openConnection() {
        boolean flag = false;
        try {
            //Load MySQL Driver
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/secure_users?serverTimezone=UTC", "root", "toor");
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean closeConnection() {
        boolean flag = false;
        if (connection != null) {
            try {
                connection.close();
                flag = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }


    public boolean checkLogin(String username, String password) throws IOException {

        boolean flag = false;
        try {
            flag = checkHashedPasswordForTable(username, password, "users");
        } catch (SQLException e) {
            e.printStackTrace();
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            ServletResponse response = null;
            response.setContentType("text/plain");
            response.getOutputStream().print(stringWriter.toString());
        }
        return flag;
    }

    public boolean checkAdminLogin(String username, String password) {
        boolean flag = false;
        try {
            flag = checkHashedPasswordForTable(username, password, "admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    private boolean checkHashedPasswordForTable(String username, String password, String table) throws SQLException {
        String query = "SELECT * FROM " + table + " where username=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        resultSet = preparedStatement.executeQuery();
        resultSet.last();
        String passwordHash = resultSet.getString("password");
        return  (passwordHash != null && BCrypt.checkpw(password, passwordHash));
    }
}
