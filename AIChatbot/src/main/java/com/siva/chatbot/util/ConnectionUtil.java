package com.siva.chatbot.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	//creating final variables for the database connectoion properties
		private static final String  userName="root";
		private static final String  password="Siva@7567";
		private static final String  url="jdbc:mysql://localhost:3306/chatbot_db";
		
		public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(url, userName, password);
	    }

}
