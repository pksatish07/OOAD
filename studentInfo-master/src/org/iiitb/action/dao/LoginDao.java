package org.iiitb.action.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public class LoginDao 
{
	// JDBC driver name and database URL
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		static final String DB_URL = "jdbc:mysql://localhost:3306/sisdb";

		// Database credentials
		static final String USER = "root";
		static final String PASS = "root";
	
	public boolean checkForPasswordReset(String userName, String emailId)
	{
		Connection con = null;
		Statement stmt = null;
		String sql;
		boolean valid = false;
		try
		{
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			con = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = con.createStatement();

			sql = "select * from sisdb.user where username= ? and email= ?;";

			PreparedStatement preparedStatement = null;

			preparedStatement = (PreparedStatement) con.prepareStatement(sql);

			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, emailId);

			// System.out.println(preparedStatement);

			ResultSet rs = null;

			rs = preparedStatement.executeQuery();

			while (rs.next())
			{
				// Retrieve by column name
				valid = true;;
			}

			// Clean-up environment

			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return valid;
	}
	
	public void resetPassword(String userName, String pass)
	{
		Connection con = null;
		String sql;
		try
		{
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			sql = "update sisdb.user set password= ? where username= ?;";
			
			PreparedStatement preparedStatement = null;

			preparedStatement = (PreparedStatement) con.prepareStatement(sql);

			preparedStatement.setString(1, pass);
			preparedStatement.setString(2, userName);

			// System.out.println(preparedStatement);

			preparedStatement.execute();

			// Clean-up environment
			preparedStatement.close();
			con.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

	}
}
