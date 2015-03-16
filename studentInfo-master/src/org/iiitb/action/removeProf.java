package org.iiitb.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class removeProf {
	
	private String removeprof;
	
	public String getRemoveprof() {
		return removeprof;
	}

	public void setRemoveprof(String removeprof) {
		this.removeprof = removeprof;
	}

	public String execute() throws ClassNotFoundException, SQLException{
		
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sisdb","root","root");
		
		String query2 = "DELETE FROM user where name = ?";
		
		PreparedStatement prepareStatement2 = (PreparedStatement) conn.prepareStatement(query2);
		prepareStatement2.setString(1,getRemoveprof());
		prepareStatement2.executeUpdate();
		
		
		return "success";
	}
}
