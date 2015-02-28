package org.iiitb.action.admin;

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

/**
 * 
 * @author Abhijith Madhav (MT2013002)
 * 
 */
public class AssignGradesAction extends ActionSupport implements SessionAware
{
	/**
	 * 
	 * serial id
	 */
	private static final long serialVersionUID = -3927650660405287420L;

	private List<String> studentList;
	private List<SubjectInfo> courseList;
	private List<String> gradeList;
	private String studentDisplayChoice;
	private String courseDisplayChoice;
	private String gradeDisplayChoice;

	private Map<String, Object> session;
	private static final String USER = "user";

	private List<NewsItem> allNews;
	private List<AnnouncementsItem> announcements;
	private LayoutDAO layoutDAO = new LayoutDAOImpl();
	private String lastLoggedOn = "";

	public AssignGradesAction()
	{
		studentList = new LinkedList<String>();
		//studentList.add(DEFAULT_TERM);

		courseList = new LinkedList<SubjectInfo>();
		//courseList.add(DEFAULT_COURSE);

		//setResultList(new LinkedList<GradeInfo>());
		gradeList = new LinkedList<String>();
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

			courseList.addAll(new CourseDAOImpl().getEnrolledCourses(connection, studentDisplayChoice));
			gradeList.addAll(new ResultDAOImpl().getGrades());
			
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

  public List<SubjectInfo> getCourseList()
	{
		return courseList;
	}

	public void setCourseList(List<SubjectInfo> courseList)
	{
		this.courseList = courseList;
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

	public List<String> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<String> gradeList) {
		this.gradeList = gradeList;
	}

	public String getCourseDisplayChoice() {
		return courseDisplayChoice;
	}

	public void setCourseDisplayChoice(String courseDisplayChoice) {
		this.courseDisplayChoice = courseDisplayChoice;
	}

	public String getGradeDisplayChoice() {
		return gradeDisplayChoice;
	}

	public void setGradeDisplayChoice(String gradeDisplayChoice) {
		this.gradeDisplayChoice = gradeDisplayChoice;
	}
}
