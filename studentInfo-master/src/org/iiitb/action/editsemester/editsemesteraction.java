package org.iiitb.action.editsemester;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.iiitb.action.dao.CourseDAO;
import org.iiitb.action.dao.LayoutDAO;
import org.iiitb.action.dao.ResultDAO;
import org.iiitb.action.dao.impl.CourseDAOImpl;
import org.iiitb.action.dao.impl.LayoutDAOImpl;
import org.iiitb.action.dao.impl.ResultDAOImpl;
import org.iiitb.action.dao.impl.SemesterDAOImpl;
import org.iiitb.action.dao.impl.StudentDAOImpl;
import org.iiitb.action.dao.SemesterDAO;
import org.iiitb.action.subjects.SubjectInfo;
import org.iiitb.model.StudentInfo;
import org.iiitb.model.User;
import org.iiitb.model.layout.AnnouncementsItem;
import org.iiitb.model.layout.NewsItem;
import org.iiitb.util.ConnectionPool;
import org.iiitb.util.Constants;

import com.opensymphony.xwork2.ActionSupport;

public class editsemesteraction extends ActionSupport implements SessionAware
{
	/**
	 * 
	 * serial id
	 */
	//private static final long serialVersionUID = -3927650660405287420L;

	private List<String> studentList;
	private List<String> semList;
	private String studentDisplayChoice;
	

	private Map<String, Object> session;
	private static final String USER = "user";

	private List<NewsItem> allNews;
	private List<AnnouncementsItem> announcements;
	private LayoutDAO layoutDAO = new LayoutDAOImpl();
	private String lastLoggedOn = "";

	public editsemesteraction()
	{
		studentList = new LinkedList<String>();
		semList = new LinkedList<String>();
		//studentList.add(DEFAULT_TERM);

	}

	public String execute() throws Exception
	{
		User loggedInUser = (User) this.session.get(USER);
		if (loggedInUser != null)
		{	
			Connection connection = ConnectionPool.getConnection();
			studentList.addAll(new StudentDAOImpl().getStudents());
			if (null == studentDisplayChoice) {
			  studentDisplayChoice = studentList.get(0);
			}

			
			allNews = layoutDAO.getAllNews(connection);
			announcements = layoutDAO.getAnnouncements(connection,
					Integer.parseInt(loggedInUser.getUserId()));
			setLastLoggedOn((String) this.session.get(Constants.LAST_LOGGED_ON));
			ConnectionPool.freeConnection(connection);
			return SUCCESS;
		}
		else
			return LOGIN;
	}


	public String getStudentDisplayChoice() {
    return studentDisplayChoice;
  }

  public void setStudentDisplayChoice(String studentDisplayChoice) {
    this.studentDisplayChoice = studentDisplayChoice;
  }


	public List<NewsItem> getAllNews()
	{
		return allNews;
	}

	public void setAllNews(List<NewsItem> allNews)
	{
		this.allNews = allNews;
	}

	public List<AnnouncementsItem> getAnnouncements()
	{
		return announcements;
	}

	public void setAnnouncements(List<AnnouncementsItem> announcements)
	{
		this.announcements = announcements;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
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

	public List<String> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<String> studentList) {
		this.studentList = studentList;
	}

	public List<String> getSemList() {
		return semList;
	}

	public void setSemList(List<String> semList) {
		this.semList = semList;
	}

	
}
