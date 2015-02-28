package org.iiitb.action.syllabus;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.SessionAware;
import org.iiitb.action.dao.CourseDAO;
import org.iiitb.action.dao.LayoutDAO;
import org.iiitb.action.dao.SyllabusDAO;
import org.iiitb.action.dao.impl.CourseDAOImpl;
import org.iiitb.action.dao.impl.LayoutDAOImpl;
import org.iiitb.action.dao.impl.SyllabusDAOImpl;
import org.iiitb.action.subjects.SubjectInfo;
import org.iiitb.model.User;
import org.iiitb.model.layout.AnnouncementsItem;
import org.iiitb.model.layout.NewsItem;
import org.iiitb.util.ConnectionPool;
import org.iiitb.util.Constants;

import com.opensymphony.xwork2.ActionSupport;

public class SyllabusAction extends ActionSupport implements SessionAware{
		
	private String syllabusInfoList;
	private SyllabusDAO syllabusDAO = new SyllabusDAOImpl();
	private String subjectCode;
	private String subjectName;
	private String currentSyllabus;
	private String topic;
		
	private List<NewsItem> allNews;
	private List<AnnouncementsItem> announcements;
	
	private List<SubjectInfo> subjectInfoList;
	private List<String> subjectCodeList;
	


	private LayoutDAO layoutDAO = new LayoutDAOImpl();
	private Map<String, Object> session;
  
	private String lastLoggedOn;
  
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
	  
	    public String getSubjectName() {
	    return subjectName;
	  }
	    
	public String getSubjectCode() {
		return subjectCode;
	}
	
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
  
	// subjectCode to be send as a request parameter from Subjects page.
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	public List<String> getSubjectCodeList() {
		return subjectCodeList;
	}

	public void setSubjectCodeList(List<String> subjectCodeList) {
		this.subjectCodeList = subjectCodeList;
	}
	
	public String execute() throws SQLException {
		User user = (User) session.get("user");
		Connection connection = ConnectionPool.getConnection();
		syllabusInfoList = syllabusDAO.getSyllabus(connection, subjectCode);
		allNews = layoutDAO.getAllNews(connection);
		announcements = layoutDAO.getAnnouncements(connection,
        Integer.parseInt(user.getUserId()));
		setLastLoggedOn((String) this.session.get(Constants.LAST_LOGGED_ON));
		ConnectionPool.freeConnection(connection);
	    return SUCCESS;
	  }
	
	public String selectSyllabus() {
		CourseDAO courseDAO = new CourseDAOImpl();
		subjectCodeList = new ArrayList<String>();
		Connection connection = ConnectionPool.getConnection();
		subjectInfoList = courseDAO.getAllCourses(connection, 1);
		for (SubjectInfo subject : subjectInfoList) {
			subjectCodeList.add( subject.getSubjectName() );
		}

		ConnectionPool.freeConnection(connection);

		return SUCCESS;
	}
	
	public String addSyllabus() {
		Connection connection = ConnectionPool.getConnection();
		if(syllabusDAO.setSyllabus(connection, subjectName, topic))
		{
		ConnectionPool.freeConnection(connection);
		return SUCCESS;
		}
		else{
			return "error";
		}
	}
	
	public String currentSyllabus() {
		
		CourseDAO courseDAO = new CourseDAOImpl();
		subjectCodeList = new ArrayList<String>();
		Connection connection = ConnectionPool.getConnection();
		subjectInfoList = courseDAO.getAllCourses(connection, 1);
		for (SubjectInfo subject : subjectInfoList) {
			subjectCodeList.add( subject.getSubjectName() );
		}
		setCurrentSyllabus(syllabusDAO.currentSyllabus(connection,subjectName));
		ConnectionPool.freeConnection(connection);
		return "success";
	}
	
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	

  public String getSyllabusInfoList() {
		return syllabusInfoList;
	}

	public void setSyllabusInfoList(String syllabusInfoList) {
		this.syllabusInfoList = syllabusInfoList;
	}

@Override
  public void setSession(Map<String, Object> session) {
    this.session = session;
    
  }

public String getCurrentSyllabus() {
	return currentSyllabus;
}

public void setCurrentSyllabus(String currentSyllabus) {
	this.currentSyllabus = currentSyllabus;
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
