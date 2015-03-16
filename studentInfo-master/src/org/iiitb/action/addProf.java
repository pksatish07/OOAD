package org.iiitb.action;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.NamingException;

public class addProf {
	
	private String username;
	private String name;
	private String emailId;
	private String password;
	private String qualification;
	private String cabinno;
	private String specialization;
	
public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getCabinno() {
		return cabinno;
	}

	public void setCabinno(String cabinno) {
		this.cabinno = cabinno;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

public String execute() throws NamingException, SQLException,
FileNotFoundException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sisdb","root","root");
		
		String query2 = "INSERT INTO user (name,username,password,email,user_type) values (?,?,?,?,?)";
		
		PreparedStatement prepareStatement2 = (PreparedStatement) conn.prepareStatement(query2);
		prepareStatement2.setString(1,getName());
		prepareStatement2.setString(2,getUsername());
		prepareStatement2.setString(3,getPassword());
		prepareStatement2.setString(4,getEmailId());
		prepareStatement2.setString(5,"F");
		prepareStatement2.executeUpdate();
		
	

	return "success";
}

}
