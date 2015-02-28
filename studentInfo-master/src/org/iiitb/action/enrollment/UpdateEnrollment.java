package org.iiitb.action.enrollment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.iiitb.action.dao.CourseDAO;
import org.iiitb.action.dao.LayoutDAO;
import org.iiitb.action.dao.impl.CourseDAOImpl;
import org.iiitb.action.dao.impl.LayoutDAOImpl;
import org.iiitb.model.User;
import org.iiitb.model.layout.AnnouncementsItem;
import org.iiitb.model.layout.NewsItem;
import org.iiitb.util.ConnectionPool;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings({ "serial", "unused" })
public class UpdateEnrollment extends ActionSupport implements ServletRequestAware,ServletResponseAware,SessionAware{

	private static final String USER = "user";
	private Map<String, Object> session;
	private List<String> semester;
	private List<NewsItem> allNews;
	private List<AnnouncementsItem> announcements;
	private LayoutDAO layoutDAO = new LayoutDAOImpl();
	public String user_id;
	private HttpServletRequest request;


	public String execute(){

		User loggedInUser = (User) this.session.get(USER);
		if (null == loggedInUser) {
			//System.out.println("System error");
			return "error";
		}

		else{

			String user_id = loggedInUser.getUserId();

			Connection connection = ConnectionPool.getConnection();
			String[] toDelete = getServletRequest().getParameterValues("toDeleteCheckbox");
			String[] toAdd = getServletRequest().getParameterValues("toAddCheckbox");
			List<String> subjectsToDelete = null;
			boolean deleteResult =  true;
			boolean addResult =true;
			int userId;

			if(null != toDelete){
				try {

					deleteResult = new CourseDAOImpl().removeSubjects(connection,toDelete,Integer.parseInt(user_id));
					//System.out.println("Success from deleteSubject");

				}catch (Exception e) {
					System.out.println("System failure");
					e.printStackTrace();
					// TODO: handle exception
				}
			}

			if(null != toAdd){
				try{
					CourseDAO addSubject = new CourseDAOImpl();
					//System.out.println("toAdd");
					for(String s:toAdd){
						//System.out.println(" = " +s);
					}
					addResult =  addSubject.addSubjects(connection,toAdd,Integer.parseInt(user_id));
					//System.out.println("Success from addSubjects");
				} catch (Exception e) {

					System.out.println("System error");
					// TODO: handle exception
					e.printStackTrace();

				}

			}
			if(deleteResult ==  true & addResult == true)
				return "success";
			else
				return "error";
		}
	}

	@Override
	public void setServletRequest(HttpServletRequest request){
		this.request = request;
	}

	public HttpServletRequest getServletRequest() {
		return this.request;
	}
	public List<String> getSemester() {
		return semester;
	}

	public void setSemester(List<String> semester) {
		this.semester = semester;
	}

	private String lastLoggedOn;

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
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

	public Map<String, Object> getSession(){
		return this.session;
	}

}
