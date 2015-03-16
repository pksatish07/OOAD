package org.iiitb.action.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.iiitb.action.dao.NotificationDAO;
import org.iiitb.model.StudentInfo;
import org.iiitb.util.ConnectionPool;
import org.iiitb.util.Constants;

public class NotificationDAOImpl implements NotificationDAO {

	public static final String NOTIFICATION_QUERY_STRING = "select count(*) from notification where student_id = ?";

	private static final String ADD_NOTIFICATION = "insert into notification (friend_id,student_id)  select s1.student_id,s2.student_id from student s1,student s2 where s1.student_id =? and s2.roll_no =?";

	private static final String CHECK_NOTIFICATION_STATUS = "select * from notification where (friend_id =? AND student_id = (select student_id from student where roll_no=?))";
	private static final String GET_FREINDS_QRY = "select roll_no,photo from student where student_id IN (select friend_id from notification where student_id = ?)";

	private static final String CHECK_NOTIFICATION = "select * from notification where (student_id =? AND friend_id = (select student_id from student where roll_no=?))";

	private static final String GET_USER_ID_FROM_ROLL_NO = "select student_id from student where roll_no =?";

	private static final String INSERT_TO_FRIEND = "insert into friend (friend_id,student_id) values (?,?)";

	private static final String DELETE_FROM_NOTIFICATION = "delete from notification where student_id=? and friend_id = ?";

	@Override
	public int notificationCount(int studentID) {
		// TODO Auto-generated method stub
		Connection con = ConnectionPool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(NOTIFICATION_QUERY_STRING);
			ps.setInt(1, studentID);
			rs = ps.executeQuery();
			while (rs.next()) {
				//System.out.println("Test " + Integer.parseInt(rs.getString(1)));
				return (Integer.parseInt(rs.getString(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			ConnectionPool.freeConnection(con);
		}
		return 0;
	}

	@Override
	public void addNotification(int studentID, String friendRollNo) {
		// TODO Auto-generated method stub
		Connection con = ConnectionPool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flag = false;
		try {

			ps = con.prepareStatement(CHECK_NOTIFICATION_STATUS);
			ps.setInt(1, studentID);
			ps.setString(2, friendRollNo);
			rs = ps.executeQuery();
			while (rs.next()) {
				flag = true;
			}

			// Added to prevent refresh from adding duplicate entries

			if (!flag) {
				ps = con.prepareStatement(ADD_NOTIFICATION);
				ps.setInt(1, studentID);
				ps.setString(2, friendRollNo);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			ConnectionPool.freeConnection(con);
		}

	}

	@Override
	public List<StudentInfo> getFriends(String userId) {
		// rollno is logged in user_id

		List<StudentInfo> friends = null;
		StudentInfo studentVo = null;
		Connection conn = ConnectionPool.getConnection();

		try {
			PreparedStatement stmt = conn.prepareStatement(GET_FREINDS_QRY);
			stmt.setString(1, userId);
			// stmt.setString(2, rollno);

			ResultSet rs = stmt.executeQuery();
			friends = new ArrayList<StudentInfo>();
			if (rs.next()) {

			} else {
				return friends;
			}
			do {

				studentVo = new StudentInfo();
				// studentVo.setDob(rs.getString("dob"));
				// studentVo.setName(rs.getString("name"));
				// InputStream binaryStream = rs.getBinaryStream("photo");
				// studentDao.setPhoto(binaryStream);
				studentVo.setRollNo(rs.getString("roll_no"));

				friends.add(studentVo);
			} while (rs.next());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionPool.freeConnection(conn);
		}
		if (friends.size() == 0)
			return null;
		else
			return friends;

	}

	@Override
	public String isNotified(String userId, String friendRollNo) {
		Connection con = ConnectionPool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = con.prepareStatement(CHECK_NOTIFICATION);
			ps.setString(1, userId);
			ps.setString(2, friendRollNo);
			rs = ps.executeQuery();
			while (rs.next()) {
				return (Constants.NOTIFICATION);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			ConnectionPool.freeConnection(con);
		}
		return Constants.NON_NOTIFICATION;
	}

	@Override
	public String acceptFriend(String userId, String friendRollNo) {
		Connection con = ConnectionPool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			// System.out.println("friendRollNo "+friendRollNo);

			String friendUserId = "";
			ps = con.prepareStatement(GET_USER_ID_FROM_ROLL_NO);
			// ps.setString(1, userId);
			ps.setString(1, friendRollNo);
			rs = ps.executeQuery();
			while (rs.next()) {
				friendUserId = rs.getString(1);
			}

			// System.out.println("friendUserId "+friendUserId);

			// Insert to friend table for both student
			ps = con.prepareStatement(INSERT_TO_FRIEND);
			ps.setString(1, userId);
			ps.setString(2, friendUserId);
			ps.executeUpdate();

			ps = con.prepareStatement(INSERT_TO_FRIEND);
			ps.setString(1, friendUserId);
			ps.setString(2, userId);
			ps.executeUpdate();

			// Delete from notification table
			ps = con.prepareStatement(DELETE_FROM_NOTIFICATION);
			ps.setString(1, userId);
			ps.setString(2, friendUserId);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			ConnectionPool.freeConnection(con);
		}
		return Constants.NON_NOTIFICATION;
	}

}