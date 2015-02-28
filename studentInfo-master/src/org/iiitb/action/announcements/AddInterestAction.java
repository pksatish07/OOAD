package org.iiitb.action.announcements;

import java.sql.Connection;
import java.sql.SQLException;

import org.iiitb.action.dao.InterestDAO;
import org.iiitb.action.dao.impl.InterestDAOImpl;
import org.iiitb.model.layout.Interest;
import org.iiitb.util.ConnectionPool;

import com.opensymphony.xwork2.ActionSupport;

public class AddInterestAction extends ActionSupport
{
	private String name;
	private String details;
	
	public String execute() throws SQLException
	{
		if(!name.trim().equals("") && !details.trim().equals(""))
		{
			Connection cn=ConnectionPool.getConnection();
			InterestDAO interestDAO=new InterestDAOImpl();
			interestDAO.addInterest(cn, new Interest(name, details));
			ConnectionPool.freeConnection(cn);
			return SUCCESS;
		}
		
		if(name.trim().equals(""))
			addFieldError("name", "name cannot be empty");
			
		if(details.trim().equals(""))
			addFieldError("details", "details cannot be empty");
		
		return INPUT;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}
