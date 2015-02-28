package org.iiitb.action.friends;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.iiitb.action.dao.LayoutDAO;
import org.iiitb.action.dao.StudentDAO;
import org.iiitb.action.dao.impl.LayoutDAOImpl;
import org.iiitb.action.dao.impl.StudentDAOImpl;
import org.iiitb.model.StudentInfo;
import org.iiitb.model.User;
import org.iiitb.model.layout.AnnouncementsItem;
import org.iiitb.model.layout.NewsItem;
import org.iiitb.util.ConnectionPool;
import org.iiitb.util.Constants;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author prashanth
 * 
 */
public class MyFriendsAction extends ActionSupport implements SessionAware
{
	/**
	 * 
	 */
	private StudentInfo myProfile;
	private List<StudentInfo> students;
	private Map<String, Object> session;

  private List<NewsItem> allNews;
  private List<AnnouncementsItem> announcements;
  private LayoutDAO layoutDAO = new LayoutDAOImpl();
  private String lastLoggedOn;

	public StudentInfo getMyProfile()
	{
		return myProfile;
	}

	public void setMyProfile(StudentInfo myProfile)
	{
		this.myProfile = myProfile;
	}

	public List<StudentInfo> getStudents()
	{
		return students;
	}

	public void setStudents(List<StudentInfo> students)
	{
		this.students = students;
	}

	public List<NewsItem> getAllNews() {
    return allNews;
  }

  public void setAllNews(List<NewsItem> allNews) {
    this.allNews = allNews;
  }

  public List<AnnouncementsItem> getAnnouncements() {
    return announcements;
  }

  public void setAnnouncements(List<AnnouncementsItem> announcements) {
    this.announcements = announcements;
  }

  public String execute() throws SQLException
	{

		User user = (User) session.get("user");
		if (user != null)
		{

			StudentDAO studentDao = new StudentDAOImpl();

			StudentInfo studentInfo = studentDao.getStudentByUserId(user.getUserId());
			
      
			if (studentInfo != null)
			{

				setMyProfile(studentInfo);

				setStudents(studentDao.getFriends(user.getUserId()));
				
				Connection connection = ConnectionPool.getConnection();
	      allNews = layoutDAO.getAllNews(connection);
	      announcements = layoutDAO.getAnnouncements(connection,
	          Integer.parseInt(user.getUserId()));
			setLastLoggedOn((String) this.session.get(Constants.LAST_LOGGED_ON));
	      ConnectionPool.freeConnection(connection);
			}
			else
			{
				return ERROR;
			}
			
		}

		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> session)
	{
		this.session = session;

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
