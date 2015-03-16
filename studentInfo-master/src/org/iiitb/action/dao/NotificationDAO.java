package org.iiitb.action.dao;

import java.util.List;

import org.iiitb.model.StudentInfo;

public interface NotificationDAO {

	public int notificationCount(int studentID);
	public void addNotification(int studentID,String friendRollNo);
	public List<StudentInfo> getFriends(String userId);
	public String isNotified(String userId,String friendRollNo);
	public String acceptFriend(String userId,String friendRollNo);
}
