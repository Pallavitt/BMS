package com.cruds.db;

import java.sql.Connection;

import com.cruds.db.DBConnection;

public class DBConnTest
{
	public static void main(String[] args) 
	{
        Connection conn = DBConnection.getConnection();
		if(conn != null)
		{
			System.out.println("Conn sucess");
		}

	}

}
