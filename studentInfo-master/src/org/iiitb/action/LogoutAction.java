package org.iiitb.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements SessionAware
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7751903212229027485L;
	private SessionMap<String, Object> session;

	@Override
	public String execute() throws NumberFormatException, SQLException
	{
		//System.out.println("logout called");
		//System.out.println(session.get("userName") + "before removing");
		session.remove("user");
		session.remove("userName");
		//System.out.println(session.get("userName") + "after removing");
		session.clear();
		session.invalidate();
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> sessionAttributes)
	{
		this.session = (SessionMap<String, Object>)sessionAttributes;
	}
	
	public SessionMap<String, Object> getSession()
	{
		return session;
	}

}
