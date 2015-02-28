package org.iiitb.action.announcements;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.iiitb.action.dao.InterestDAO;
import org.iiitb.action.dao.impl.InterestDAOImpl;
import org.iiitb.model.layout.Interest;
import org.iiitb.util.ConnectionPool;

import com.opensymphony.xwork2.ActionSupport;

public class AddAnnouncementAction extends ActionSupport
{
	private List<String> li;
	public String execute() throws SQLException
	{
		li=new ArrayList<String>();
		Connection cn=ConnectionPool.getConnection();
		InterestDAO interestDAO=new InterestDAOImpl();
		int k=0;
		for(Interest i:interestDAO.getAllInterests(cn))
			li.add(i.getId()+". "+i.getName());
		ConnectionPool.freeConnection(cn);
		return "success";
	}
	public List<String> getLi() {
		return li;
	}
	public void setLi(List<String> li) {
		this.li = li;
	}
	
}
