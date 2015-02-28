package org.iiitb.action.news;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.NamingException;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.SessionAware;
import org.iiitb.util.ConnectionPool;

import com.opensymphony.xwork2.ActionSupport;

public class RemovenewsAction extends ActionSupport implements SessionAware

{

	private String name;


	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Map<String, Object> session;

	public Map<String, Object> getSession() {
		return session;
	}
	
	

	public String execute() throws NamingException, SQLException {
		
		String ret = ERROR;
		Connection conn = ConnectionPool.getConnection();

		PreparedStatement preStmt = null;
		try {
			String query = "delete from news where name=?";

			preStmt = conn.prepareStatement(query);
			preStmt.setString(1, name);
			
			if (preStmt.executeUpdate() > 0)
			{
				addActionMessage("Successfully removed the news from database");
				ret = SUCCESS;
			}
			else
			{
				addActionError("Error occurred while deleting");
				ret = ERROR;
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionPool.freeConnection(conn);
		}
		return ret;

	}

	public void validate() {

		if (StringUtils.isEmpty(name)) {
			addFieldError("name", "name cannot be blank");
		}

	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;

	}
}