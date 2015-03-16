package org.iiitb.action;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.NamingException;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.iiitb.action.dao.LayoutDAO;
import org.iiitb.action.dao.impl.LayoutDAOImpl;
import org.iiitb.model.User;
import org.iiitb.util.ConnectionPool;
import org.iiitb.util.Constants;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware
{

	private String username;

	private LayoutDAO layoutDAO = new LayoutDAOImpl();

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	private String password;

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	private SessionMap<String, Object> session;

	public String login() throws NamingException, SQLException
	{
		clearFieldErrors();

		User user = (User) session.get("user");
		if (user != null)
		{
			return SUCCESS;
		}
		else
		{
			User newUser = new User(username, password);
			if (isValidUser(newUser))
			{
				session.put("user", newUser);
				session.put("userName", username);
				if (newUser.getUserType().equalsIgnoreCase("A"))
				{
				
					return "admin";
				}

				Connection connection = ConnectionPool.getConnection();
				session.put(
						Constants.LAST_LOGGED_ON,
						layoutDAO.getLastLoggedOn(connection,
								Integer.parseInt(newUser.getUserId())));
				layoutDAO.setLastLoggedOn(connection,
						Integer.parseInt(newUser.getUserId()));
				ConnectionPool.freeConnection(connection);
				//System.out.println("In login action "+session.get("UserName"));
				return SUCCESS;
			}
			else
			{
				addActionError("Invalid userid or password");
				return INPUT;
			}

		}

	}

	public void validate()
	{

		if (StringUtils.isEmpty(username))
		{
			addFieldError(Constants.DB_USERNAME, Constants.USER_NAME_BLANK);
		}

		if (StringUtils.isEmpty(password))
		{
			addFieldError(Constants.DB_PASSWORD, Constants.PASSWORD_BLANK);
		}
	}

	private boolean isValidUser(User user)
	{

		Connection conn = ConnectionPool.getConnection();

		PreparedStatement preStmt;
		try
		{
			preStmt = conn.prepareStatement(Constants.GET_USER);

			preStmt.setString(1, user.getUsername());
			ResultSet result = preStmt.executeQuery();

			if (result.first())
			{
				if (!user.getPassword().equals(
						result.getString(Constants.DB_PASSWORD)))
				{
					addFieldError(Constants.DB_PASSWORD,
							Constants.INVALID_PASSWORD_ERROR);
					return false;
				}
				else
				{

					user.setUserId(result.getString("user_id"));
					user.setEmailId(result.getString("email"));
					user.setName(result.getString("name"));
					user.setUserType(result.getString("user_type"));

					if (user.getUserType().equalsIgnoreCase("A"))
						return true;
					else
					{
						preStmt = conn.prepareStatement(Constants.GET_PHOTO_QRY);
						preStmt.setString(1, user.getUsername());
						result = preStmt.executeQuery();
						if(result.next()){
							InputStream binaryStream = result.getBinaryStream("photo");
							user.setPhoto(binaryStream);
						}
					}

				}

			}
			else
			{
				addFieldError(Constants.DB_USERNAME,
						Constants.INVALID_USER_ERROR);
				return false;
			}

		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			ConnectionPool.freeConnection(conn);
		}
		return true;
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
