package org.iiitb.action.subjects;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.SessionAware;
import org.iiitb.action.dao.CourseDAO;
import org.iiitb.action.dao.SemesterDAO;
import org.iiitb.action.dao.SyllabusDAO;
import org.iiitb.action.dao.impl.CourseDAOImpl;
import org.iiitb.action.dao.impl.SemesterDAOImpl;
import org.iiitb.action.dao.impl.SyllabusDAOImpl;
import org.iiitb.util.ConnectionPool;
import org.iiitb.util.Constants;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings({ "serial", "unused" })
public class ManageSubjectsAction extends ActionSupport implements SessionAware {
	
	private Map<String, Object> session;
	
	private String code;
	private String name;
	private String semester;
	private String credit;
	private String faculty;
	private String lastDate;
		
	private List<String> facultyList;
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public String execute(){
		
		
		CourseDAO courseDAO = new CourseDAOImpl();
		Connection connection = ConnectionPool.getConnection();
		
		courseDAO.setCourse(connection, code, name, credit, lastDate, semester, faculty);
		ConnectionPool.freeConnection(connection);
		return "input";
	}
			
	
	public String initSubjects() {
				
		SyllabusDAO syllabusDAO = new SyllabusDAOImpl();
		facultyList = new ArrayList<String>();
		Connection connection = ConnectionPool.getConnection();		
		facultyList = syllabusDAO.getFaculty(connection);
		ConnectionPool.freeConnection(connection);
		return SUCCESS;
	}
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	public List<String> getFacultyList() {
		return facultyList;
	}

	public void setFacultyList(List<String> facultyList) {
		this.facultyList = facultyList;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}
}
