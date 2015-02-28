package org.iiitb.action.subjects;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.iiitb.action.dao.CourseDAO;
import org.iiitb.action.dao.LayoutDAO;
import org.iiitb.action.dao.impl.CourseDAOImpl;
import org.iiitb.action.dao.impl.LayoutDAOImpl;
import org.iiitb.model.User;
import org.iiitb.model.layout.AnnouncementsItem;
import org.iiitb.model.layout.NewsItem;
import org.iiitb.util.ConnectionPool;
import org.iiitb.util.Constants;

import com.opensymphony.xwork2.ActionSupport;

public class SubjectsAction extends ActionSupport implements SessionAware {

  /**
   * serial id
   */
  private static final long serialVersionUID = 3193702317572110684L;

  private static final String SHOW_ALL_COURSES = "Show All Courses";
  private static final String SHOW_ENROLLED_COURSES = "Show Enrolled Courses";

  private static final String USER = "user";
  private List<SubjectInfo> subjectInfoList;
  private List<String> subjectDisplayList;
  private String subjectDisplayChoice;
  private List<NewsItem> allNews;
  private List<AnnouncementsItem> announcements;
  private LayoutDAO layoutDAO = new LayoutDAOImpl();
  private CourseDAO courseDAO = new CourseDAOImpl();
  private Map<String, Object> session;
	
  private String lastLoggedOn;

  {
    subjectDisplayList = new ArrayList<String>();
    subjectDisplayList.add(SHOW_ALL_COURSES);
    subjectDisplayList.add(SHOW_ENROLLED_COURSES);
  }

public List<String> getSubjectDisplayList() {
    return subjectDisplayList;
  }

  public void setSubjectDisplayList(List<String> subjectDisplayList) {
    this.subjectDisplayList = subjectDisplayList;
  }

  public String getSubjectDisplayChoice() {
    return subjectDisplayChoice;
  }

  public void setSubjectDisplayChoice(String subjectDisplayChoice) {
    this.subjectDisplayChoice = subjectDisplayChoice;
  }

  public List<SubjectInfo> getSubjectInfoList() {
    return subjectInfoList;
  }

  public void setSubjectInfoList(List<SubjectInfo> subjectInfoList) {
    this.subjectInfoList = subjectInfoList;
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

  public String execute() throws SQLException {
    Connection connection = ConnectionPool.getConnection();
    User loggedInUser = (User) this.session.get(USER);
    if (null != loggedInUser) {    	
      if (null == subjectDisplayChoice
          || subjectDisplayChoice.equals(SHOW_ALL_COURSES)) {
        subjectInfoList = courseDAO.getAllCourses(connection,
            Integer.parseInt(loggedInUser.getUserId()));
      } else {
        subjectInfoList = courseDAO.getEnrolledCourses(connection,
            Integer.parseInt(loggedInUser.getUserId()));
      }

      allNews = layoutDAO.getAllNews(connection);
      announcements = layoutDAO.getAnnouncements(connection,
          Integer.parseInt(loggedInUser.getUserId()));
	  setLastLoggedOn((String) this.session.get(Constants.LAST_LOGGED_ON));      
      ConnectionPool.freeConnection(connection);
      return SUCCESS;
    } else {
      return LOGIN;
    }
  }

  @Override
  public void setSession(Map<String, Object> session) {
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
