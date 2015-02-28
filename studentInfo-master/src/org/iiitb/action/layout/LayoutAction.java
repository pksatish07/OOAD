package org.iiitb.action.layout;

import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.interceptor.SessionAware;
import org.iiitb.util.ConnectionPool;
import org.iiitb.util.Constants;
import org.iiitb.action.dao.LayoutDAO;
import org.iiitb.action.dao.impl.LayoutDAOImpl;
import org.iiitb.model.User;
import org.iiitb.model.layout.*;

public class LayoutAction extends ActionSupport implements SessionAware
{
	private Map<String, Object> session;
	private User user=null;
	private List<NewsItem> allNews;
	private List<AnnouncementsItem> announcements;
	private String lastLoggedOn;
	
	private LayoutDAO layoutDAO=new LayoutDAOImpl();
	
	public String execute() throws SQLException
	{
		if(session!=null)
			user = (User)session.get("user");
		if(user != null)
		{
			Connection cn=ConnectionPool.getConnection();
			allNews=layoutDAO.getAllNews(cn);
			announcements=layoutDAO.getAnnouncements(cn, Integer.parseInt(user.getUserId()));
			setLastLoggedOn((String) this.session.get(Constants.LAST_LOGGED_ON));
			ConnectionPool.freeConnection(cn);	
		}
		return "success";
	}

	public List<NewsItem> getAllNews() {
		return allNews;
	}

	public List<AnnouncementsItem> getAnnouncements() {
		return announcements;
	}
	
	public User getUser()
	{
		return this.user;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session=arg0;
	}

	public String getLastLoggedOn()
	{
		return lastLoggedOn;
	}

	public void setLastLoggedOn(String lastLoggedOn)
	{
		this.lastLoggedOn = lastLoggedOn;
	}
}
