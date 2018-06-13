package com.examresults;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletResponse;
import javax.servlet.ServletResponseWrapper;
import javax.servlet.http.HttpServletResponse;

public class Database {

	private Connection connection = null;	    
    private ResultSet resultSet = null;
    private Statement statement = null;
   
    
	public boolean openConnection() {
		
		boolean flag = false;
		
		try {
			
			//Load MySQL Driver
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?serverTimezone=UTC","root","toor");
			flag = true;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }
		return flag;
		
	}
	
 
	public boolean checkLogin(String username, String password) throws IOException {

		boolean flag = false;
		
		try {
			statement = connection.createStatement();
			String query = "SELECT * FROM users where username='"+username+"' AND password='"+password+"'";
		    resultSet = statement.executeQuery(query);
		    
		    if(resultSet.next()) {
		    	flag = true;
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			statement = connection.createStatement();
			String query = "SELECT * FROM admin where username='"+username+"' AND password='"+password+"'";
		    resultSet = statement.executeQuery(query);
		    
		    if(resultSet.next()) {
		    	flag = true;
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	      
		return flag;		
	}
		
}
