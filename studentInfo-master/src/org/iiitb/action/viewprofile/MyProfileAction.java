package org.iiitb.action.viewprofile;

import java.sql.Connection;
import java.util.Map;
import java.util.List;

import org.apache.struts2.interceptor.SessionAware;
import org.iiitb.action.dao.LayoutDAO;
import org.iiitb.action.dao.MyProfileDAO;
import org.iiitb.action.dao.impl.LayoutDAOImpl;
import org.iiitb.model.User;
import org.iiitb.model.layout.AnnouncementsItem;
import org.iiitb.model.layout.NewsItem;
import org.iiitb.util.ConnectionPool;
import org.iiitb.util.Constants;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MyProfileAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;
	private String name;
	private String password;
	private String rollno;
	private List<String> interests;
	private List<String> defaultInterests;
	private int semester;

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRollno() {
		return rollno;
	}

	public void setRollno(String rollno) {
		this.rollno = rollno;
	}

	public List<String> getInterests() {
		return interests;
	}

	public void setInterests(List<String> interests) {
		this.interests = interests;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	private List<NewsItem> allNews;
	private List<AnnouncementsItem> announcements;
	private LayoutDAO layoutDAO = new LayoutDAOImpl();
	private String lastLoggedOn = "";

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

	public String execute() throws Exception {
		User user = (User) session.get("user");
		MyProfileDAO prof = new MyProfileDAO(user.getUserId());
		if (user != null) {
			//System.out.println("HETETE");
			name = user.getName();
			password = prof.getPassword();
			defaultInterests = prof.getDefaultInterests();
			// System.out.println(password);
			// name=prof.getPhoto();
			rollno = prof.getRollno();
			interests = prof.getInterests();
			semester = prof.getSemester();
			Connection connection = ConnectionPool.getConnection();
			allNews = layoutDAO.getAllNews(connection);
			announcements = layoutDAO.getAnnouncements(connection,
					Integer.parseInt(user.getUserId()));
			setLastLoggedOn((String) this.session.get(Constants.LAST_LOGGED_ON));
			ConnectionPool.freeConnection(connection);
			return SUCCESS;
		}

		else {
			return LOGIN;
		}

	}

	public List<String> getDefaultInterests() {
		return defaultInterests;
	}

	public void setDefaultInterests(List<String> defaultInterests) {
		this.defaultInterests = defaultInterests;
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

}
