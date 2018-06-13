package com.examresultsv2;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	private Connection connection = null;	    
    private ResultSet resultSet = null;
    private PreparedStatement preparedstatement = null;
   
    
	public boolean openConnection() {
		
		boolean flag = false;
		
		try {
			
			//Load MySQL Driver
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/secure_users?serverTimezone=UTC","root","toor");
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
	
	public boolean checkLogin(String username, String password) {

		boolean flag = false;
		
		try {
			String passwordhash = null;
			String query = "SELECT password FROM users where username=?";
			preparedstatement = connection.prepareStatement(query);
			preparedstatement.setString(1,username);
			resultSet = preparedstatement.executeQuery();
		    resultSet.last(); 
			passwordhash = resultSet.getString("password");		    
		    if(passwordhash != null) {
		     
		    	if(BCrypt.checkpw(password, passwordhash)) {
		    		
		    		flag = true;
		    	}
		    	else {
		    		flag = false;
		    	}
		    }
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	      
		return flag;		
	}
	
	public boolean checkAdminLogin(String username, String password) {

		boolean flag = false;
		
		try {
			String passwordhash = null;
			String query = "SELECT password FROM admin where username=?";
			preparedstatement = connection.prepareStatement(query);
			preparedstatement.setString(1,username);			 
			resultSet = preparedstatement.executeQuery();
			resultSet.last(); 
				passwordhash = resultSet.getString("password");		    
			    if(passwordhash != null) {
			     
			    	if(BCrypt.checkpw(password, passwordhash)) {
			    		
			    		flag = true;
			    	}
			    	else {
			    		flag = false;
			    	}
			    }
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	      
		return flag;		
	}
		
}
