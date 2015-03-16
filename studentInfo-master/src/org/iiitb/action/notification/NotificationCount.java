package org.iiitb.action.notification;

import org.iiitb.action.dao.NotificationDAO;
import org.iiitb.action.dao.impl.NotificationDAOImpl;

public class NotificationCount {

	public int notificationCount(int studentID) {

		NotificationDAO count = new NotificationDAOImpl();
		//System.out.println("studentID" + studentID);
		return (count.notificationCount(studentID));
	}

}
