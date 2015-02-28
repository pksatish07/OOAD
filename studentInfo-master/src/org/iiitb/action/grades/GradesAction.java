package org.iiitb.action.grades;

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
import org.iiitb.action.dao.SemesterDAO;
import org.iiitb.model.User;
import org.iiitb.model.layout.AnnouncementsItem;
import org.iiitb.model.layout.NewsItem;
import org.iiitb.util.ConnectionPool;
import org.iiitb.util.Constants;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author Abhijith Madhav (MT2013002)
 * 
 */
public class GradesAction extends ActionSupport implements SessionAware
{
	/**
	 * serial id
	 */
	private static final long serialVersionUID = -3927650660405287420L;

	private final static String DEFAULT_TERM = "All";
	private final static String DEFAULT_COURSE = "All";

	private static String prevTermDisplayChoice = DEFAULT_TERM;
	
	private String termDisplayChoice = DEFAULT_TERM;
	private List<String> termList;

	private String courseDisplayChoice = DEFAULT_COURSE;
	private List<String> courseList;
	
	private List<GradeInfo> resultList;

	private Map<String, Object> session;
	private static final String USER = "user";

	private List<NewsItem> allNews;
	private List<AnnouncementsItem> announcements;
	private LayoutDAO layoutDAO = new LayoutDAOImpl();
	private String lastLoggedOn = "";

	public GradesAction()
	{
		termList = new LinkedList<String>();
		termList.add(DEFAULT_TERM);

		courseList = new LinkedList<String>();
		courseList.add(DEFAULT_COURSE);

		setResultList(new LinkedList<GradeInfo>());
	}

	public String execute() throws Exception
	{
		User loggedInUser = (User) this.session.get(USER);
		if (loggedInUser != null)
		{	
			termList.addAll(new SemesterDAOImpl().getTerms(Integer
					.parseInt(loggedInUser.getUserId())));

			
			//System.out.println("Test "+loggedInUser.getUserId()+"Test "+termList);
			
			if (!termDisplayChoice.equals(DEFAULT_TERM))
				courseList.addAll(new CourseDAOImpl().getNames(
						Integer.parseInt(loggedInUser.getUserId()),
						Integer.parseInt(termDisplayChoice)));

			
			
			
			
			
			if (!termDisplayChoice.equals(DEFAULT_TERM)
					&& !courseDisplayChoice.equals(DEFAULT_COURSE))
				resultList.addAll(new ResultDAOImpl().getGrades(
						Integer.parseInt(loggedInUser.getUserId()),
						Integer.parseInt(termDisplayChoice),
						courseDisplayChoice));
			
			
			
			
			
			
			else if (termDisplayChoice.equals(DEFAULT_TERM))
				resultList.addAll(new ResultDAOImpl().getGrades(Integer
						.parseInt(loggedInUser.getUserId())));
			
			
			
			
			
			
			
			else if (!termDisplayChoice.equals(DEFAULT_TERM)
					&& courseDisplayChoice.equals(DEFAULT_COURSE))
				resultList.addAll(new ResultDAOImpl().getGrades(
						Integer.parseInt(loggedInUser.getUserId()),
						Integer.parseInt(termDisplayChoice)));
			
			
			
			
			
			
			
	
			Connection connection = ConnectionPool.getConnection();
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

	public List<String> getTermList()
	{
		return termList;
	}

	public void setTermList(List<String> termList)
	{
		this.termList = termList;
	}

	public String getTermDisplayChoice()
	{
		return termDisplayChoice;
	}

	public void setTermDisplayChoice(String termDisplayChoice)
	{
		this.termDisplayChoice = termDisplayChoice;
	}

	public String getCourseDisplayChoice()
	{
		return courseDisplayChoice;
	}

	public void setCourseDisplayChoice(String courseDisplayChoice)
	{
		this.courseDisplayChoice = courseDisplayChoice;
	}

	public List<String> getCourseList()
	{
		return courseList;
	}

	public void setCourseList(List<String> courseList)
	{
		this.courseList = courseList;
	}

	public List<GradeInfo> getResultList()
	{
		return resultList;
	}

	public void setResultList(List<GradeInfo> resultList)
	{
		this.resultList = resultList;
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
}
