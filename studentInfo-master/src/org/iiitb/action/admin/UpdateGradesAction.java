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
public class UpdateGradesAction extends ActionSupport implements SessionAware
{
	/**
	 * 
	 * serial id
	 */
	private static final long serialVersionUID = -3927650660405287420L;

	private String studentDisplayChoice;
	private String courseDisplayChoice;
	private String gradeDisplayChoice;

	private Map<String, Object> session;
	private static final String USER = "user";

	public String execute() throws Exception
	{
		User loggedInUser = (User) this.session.get(USER);
		if (loggedInUser != null)
		{				
			new ResultDAOImpl().updateGrades(studentDisplayChoice, courseDisplayChoice, gradeDisplayChoice);
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
