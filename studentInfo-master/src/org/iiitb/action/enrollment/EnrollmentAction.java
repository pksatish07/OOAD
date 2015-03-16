package org.iiitb.action.enrollment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.iiitb.action.dao.LayoutDAO;
import org.iiitb.action.dao.impl.CourseDAOImpl;
import org.iiitb.action.dao.impl.LayoutDAOImpl;
import org.iiitb.action.dao.impl.SemesterDAOImpl;
import org.iiitb.model.User;
import org.iiitb.model.layout.AnnouncementsItem;
import org.iiitb.model.layout.NewsItem;
import org.iiitb.util.ConnectionPool;
import org.iiitb.util.Constants;

import com.opensymphony.xwork2.ActionSupport;

public class EnrollmentAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 3193702317572110684L;
	private static final String USER = "user";
	private Map<String, Object> session;
	private List<String> semester;
	private List<NewsItem> allNews;
	private List<AnnouncementsItem> announcements;
	private LayoutDAO layoutDAO = new LayoutDAOImpl();
	private List<EnrollmentSubjectsInfo> subjectsToDelete;
	private List<EnrollmentSubjectsInfo> subjectsToAdd;
	private List<EnrollmentSubjectsInfo> previousSubjects;
	private int currentSem;


	public int getCurrentSem() {
		return currentSem;
	}

	public void setCurrentSem(int currentSem) {
		this.currentSem = currentSem;
	}

	public List<String> getSemester() {
		return semester;
	}

	public void setSemester(List<String> semester) {
		this.semester = semester;
	}

	private String lastLoggedOn;

	public String execute() throws SQLException {

		User loggedInUser = (User) this.session.get(USER);
		if (null != loggedInUser) {

			String user_id = loggedInUser.getUserId();
			
			Connection connection = ConnectionPool.getConnection();
			this.setSubjectsToDelete(new CourseDAOImpl().getAlreadyEnrolledCourses(connection, Integer.parseInt(user_id)));
			this.setSubjectsToAdd(new CourseDAOImpl().getNotEnrolledCourses(connection, Integer.parseInt(user_id)));
			this.setCurrentSem(new SemesterDAOImpl().getCurrentSem(connection, Integer.parseInt(user_id)));
			if(this.getCurrentSem() >1){
				this.setPreviousSubjects(new CourseDAOImpl().getPreviousSubjects(connection,Integer.parseInt(user_id)));
			}
			else{
				this.setPreviousSubjects(null);
			}
			
			setLastLoggedOn((String) this.session.get(Constants.LAST_LOGGED_ON));
			allNews = layoutDAO.getAllNews(connection);
			announcements = layoutDAO.getAnnouncements(connection,
					Integer.parseInt(loggedInUser.getUserId()));

			// subjectsToDelete = enrollmentDAO.getEnrolledSubjects(loggedInUser.getUserId());

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

	public String getLastLoggedOn() {
		return lastLoggedOn;
	}

	public void setLastLoggedOn(String lastLoggedOn) {
		this.lastLoggedOn = lastLoggedOn;
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

	public List<EnrollmentSubjectsInfo> getSubjectsToDelete() {
		return subjectsToDelete;
	}

	public void setSubjectsToDelete(List<EnrollmentSubjectsInfo> subjectsToDelete) {
		this.subjectsToDelete = subjectsToDelete;
	}

	public List<EnrollmentSubjectsInfo> getSubjectsToAdd() {
		return subjectsToAdd;
	}

	public void setSubjectsToAdd(List<EnrollmentSubjectsInfo> subjectsToAdd) {
		this.subjectsToAdd = subjectsToAdd;
	}

	public List<EnrollmentSubjectsInfo> getPreviousSubjects() {
		return previousSubjects;
	}

	public void setPreviousSubjects(List<EnrollmentSubjectsInfo> previousSubjects) {
		this.previousSubjects = previousSubjects;
	}

}
