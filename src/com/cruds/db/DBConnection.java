package com.cruds.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection 
{
		static
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
			}
			catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}
		public static Connection getConnection()
		{
			Connection conn = null;
			try
			{
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bms", "root", "user");
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			return conn;
		}

	}



