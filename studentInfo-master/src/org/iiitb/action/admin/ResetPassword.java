package org.iiitb.action.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.iiitb.model.User;
import org.iiitb.util.ConnectionPool;

import com.opensymphony.xwork2.ActionSupport;

public class ResetPassword extends ActionSupport implements SessionAware,ServletRequestAware {

	private Map<String, Object> session;
	private HttpServletRequest servletRequest;
	private String oldpwd;
	private String newpwd;
	private String reenterpwd;
	private String userId;



	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOldpwd() {
		return oldpwd;
	}

	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	public String getReenterpwd() {
		return reenterpwd;
	}

	public void setReenterpwd(String reenterpwd) {
		this.reenterpwd = reenterpwd;
	}

	public String execute(){

		

		if(isValidpwd()){
			Connection conn = ConnectionPool.getConnection();
			String query = "update user set password=? where user_id=?";
			PreparedStatement stmt=null;

			try {
				User user = (User) session.get("user");
				userId = user.getUserId();
				stmt = conn.prepareStatement(query);
				stmt.setString(1, newpwd);
				stmt.setInt(2,Integer.parseInt(userId));
				stmt.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				ConnectionPool.freeConnection(conn);
			}

		}
		return SUCCESS;

	}
	
	public boolean isValidpwd(){
		
		User user = (User) session.get("user");
		if(user.getPassword().equals(oldpwd)){
			return true;
		}else
			return false;
			
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		// TODO Auto-generated method stub
		this.servletRequest = req;

	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;

	}

}
