package org.iiitb.action.enrollment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.struts2.interceptor.SessionAware;
import org.iiitb.action.dao.LayoutDAO;
import org.iiitb.action.dao.impl.LayoutDAOImpl;
import org.iiitb.model.User;
import org.iiitb.model.layout.AnnouncementsItem;
import org.iiitb.model.layout.NewsItem;
import org.iiitb.util.ConnectionPool;
import org.iiitb.util.Constants;

public class Indexaction implements SessionAware {
	private int semester;
	private ArrayList<DataConectivity> al;
	private ArrayList<Integer> delete = new ArrayList<Integer>();
	public static StringBuffer s;
	String displayString;

	private List<NewsItem> allNews;
	public List<AnnouncementsItem> getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(List<AnnouncementsItem> announcements) {
		this.announcements = announcements;
	}

	public LayoutDAO getLayoutDAO() {
		return layoutDAO;
	}

	public void setLayoutDAO(LayoutDAO layoutDAO) {
		this.layoutDAO = layoutDAO;
	}

	public String getLastLoggedOn() {
		return lastLoggedOn;
	}

	public void setLastLoggedOn(String lastLoggedOn) {
		this.lastLoggedOn = lastLoggedOn;
	}

	private List<AnnouncementsItem> announcements;
	private LayoutDAO layoutDAO = new LayoutDAOImpl();
	private String lastLoggedOn = "";

	public String getDisplayString() {
		return displayString;
	}

	public void setDisplayString(String displayString) {
		this.displayString = displayString;
	}

	private ArrayList<Integer> selected;
	private static HashMap<Integer, String> hm = new HashMap<Integer, String>();
	public static HashMap<Integer, String> sub = new HashMap<Integer, String>();
	

	// private static ArrayList<Interger,String> DateComp=new
	// ArrayList<String>();
	public String Update() throws SQLException {
		User loggedInUser = (User) this.session.get("user");
		s = new StringBuffer();
		
		String NEWLINE = System.getProperty("line.separator");
		
		// for (Integer value : selected) {
		// System.out.println(value
		// + "############################################");
		// }
		for (Entry<Integer, String> entry : hm.entrySet()) {

			Integer key = entry.getKey();
			if (!selected.contains(key)) {
				delete.add(key);

			}
		}

		for (Integer a : delete) {
			hm.remove(a);
		}

		for (Entry<Integer, String> entry : hm.entrySet()) {
			Integer key = entry.getKey();
			String value = entry.getValue();
			// System.out.println(key + " " + value);
		}

		for (Entry<Integer, String> entry : hm.entrySet()) {
				String r = "Enrolled for " + sub.get(entry.getKey()) + ". <br/>";
				s.append(r);
		}
		User user = (User) session.get("user");

		DataConectivity.CourseUpdate(Integer.parseInt(user.getUserId()),
				selected);

		setDisplayString(s.toString());
		
		Connection connection = ConnectionPool.getConnection();
		allNews = layoutDAO.getAllNews(connection);
		announcements = layoutDAO.getAnnouncements(connection,
				Integer.parseInt(loggedInUser.getUserId()));
		setLastLoggedOn((String) this.session.get(Constants.LAST_LOGGED_ON));
		ConnectionPool.freeConnection(connection);

		return "success";
	}

	public String execute() {
		int semester = getSemester();
		al = new ArrayList<DataConectivity>();
		ArrayList<DataConectivity> ALDC = DataConectivity
				.CourseDisplay(semester);
		for (int i = 0; i < ALDC.size(); i++) {

			// System.out.println("ALDC" + ALDC.get(i).getCoursename());
			al.add(ALDC.get(i));
			//System.out.println(al.get(i).getCoursename());
			hm.put(al.get(i).getCourse_id(), al.get(i).getLastdate());
			sub.put(al.get(i).getCourse_id(), al.get(i).getCoursename());
			// System.out.println("hashmap#########################"+hm.get(key));

		}

		for (Entry<Integer, String> entry : hm.entrySet()) {
			Integer key = entry.getKey();
			String value = entry.getValue();
			//System.out.println(key + " " + value);
		}
		return "success";

	}

	public int getSemester() {
		return semester;
	}

	public ArrayList<Integer> getSelected() {
		return selected;
	}

	public void setSelected(ArrayList<Integer> selected) {
		this.selected = selected;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public ArrayList<DataConectivity> getAl() {
		return al;
	}

	public void setAl(ArrayList<DataConectivity> al) {
		this.al = al;
	}

	Map<String, Object> session;

	private Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public List<NewsItem> getAllNews() {
		return allNews;
	}

	public void setAllNews(List<NewsItem> allNews) {
		this.allNews = allNews;
	}

}
