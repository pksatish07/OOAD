package org.iiitb.action.announcements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.NamingException;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.SessionAware;
import org.iiitb.util.ConnectionPool;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class RemoveannAction extends ActionSupport implements SessionAware

{

	private String name;


	
	
	private Map<String, Object> session;

	public Map<String, Object> getSession() {
		return session;
	}

	public String execute() throws NamingException, SQLException {
		
	
		String ret = ERROR;
		Connection conn = ConnectionPool.getConnection();

		PreparedStatement preStmt = null;
		try {
			PreparedStatement p=conn.prepareStatement("delete from announcement_interest where announcement_id in (select announcement_id from announcement where name=?);");
			p.setString(1, name);
			p.executeUpdate();
			
			String query = "delete from announcement where name=?";

			preStmt = conn.prepareStatement(query);
			preStmt.setString(1, name);
			
			if (preStmt.executeUpdate() > 0)
			{
				addActionMessage("Successfully removed the announcement from database");
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}