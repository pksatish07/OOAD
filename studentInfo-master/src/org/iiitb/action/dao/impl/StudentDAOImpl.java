package org.iiitb.action.dao.impl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.iiitb.action.dao.StudentDAO;
import org.iiitb.model.StudentInfo;
import org.iiitb.util.ConnectionPool;
import org.iiitb.util.Constants;

public class StudentDAOImpl implements StudentDAO
{

	//private static final String GET_FREINDS_QRY = "select u.name,s1.roll_no, s1.student_id, s1.dob,s1.photo from user u, student s1,student s, friends f where s.student_id=f.student_id1 and s.student_id=? and s.student_id !=s1.student_id and s1.student_id=f.student_id2 and u.user_id=s1.student_id union select u.name,s1.roll_no, s1.student_id, s1.dob,s1.photo from user u, student s,student s1, friends f where s.student_id=f.student_id2 and s.student_id=? and s.student_id !=s1.student_id and s1.student_id=f.student_id1 and u.user_id=s1.student_id";

	private static final String GET_FREINDS_QRY = "select roll_no,photo from student where student_id IN (select friend_id from friend where student_id = ?)"; 
	
	
	
	private static final String GET_STUDENT_QRY = "select * from student s, user u  where s.student_id= u.user_id and s.student_id=?";
	
	private static final String GET_STUDENT_ROLL_QRY = "select * from student s, user u  where s.student_id= u.user_id and s.roll_no=?";

	//private static final String ARE_THEY_FRIENDS_QRY = "select s1.roll_no, s2.roll_no from student s1, student s2, friends f where s1.student_id=f.student_id1 and s2.student_id=f.student_id2 and s1.student_id=? and s2.roll_no=? union select s1.roll_no, s2.roll_no from student s1, student s2, friends f where s1.student_id=f.student_id1 and s2.student_id=f.student_id2 and s2.student_id=? and s1.roll_no=?";

	
	private static final String ARE_THEY_FRIENDS_QRY ="select * from friend where (student_id =? AND friend_id = (select student_id from student where roll_no=?))";
	private static final String CHECK_NOTIFICATION_STATUS = "select * from notification where (friend_id =? AND student_id = (select student_id from student where roll_no=?))";
	//private static final String ARE_THEY_FRIENDS_QRY ="select * from notification where (student_id =? AND )";
	
	
	//private static final String ADD_FRIENDS_QRY = "insert into friends(student_id1, student_id2)     select s1.student_id, s2.student_id from student s1, student s2 where s1.student_id=? and s2.roll_no=?";

	
	
	// friendNo : Roll_no of the person whom we searched
	// user_id of the student logged in
	
	private static final String ADD_FRIENDS_QRY = "insert into friend(friend_id,student_id)    select s1.student_id,s2.student_id from student s1,student s2 where s1.roll_no = ? AND s2.student_id=?";
	

	
	private static final String GET_STUDENT_NAMES = "select name from user where user_type=?";
	@Override
	public List<String> getStudents()
	{
		List<String> names = null;
		Connection conn = ConnectionPool.getConnection();

		try
		{
			PreparedStatement stmt = conn.prepareStatement(GET_STUDENT_NAMES);
			stmt.setString(1, "S");
			ResultSet rs = stmt.executeQuery();

			if (rs.next())
			{
				names = new ArrayList<String>();
			}
			do
			{
				names.add(rs.getString("name"));
			}
			while (rs.next());

		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.freeConnection(conn);
		}

		return names;
	}
	public StudentInfo getStudentByUserId(String userId)
	{

		StudentInfo user = null;

		Connection conn = ConnectionPool.getConnection();

		try
		{
			PreparedStatement stmt = conn.prepareStatement(GET_STUDENT_QRY);
			stmt.setString(1, userId);

			ResultSet rs = stmt.executeQuery();

			if (rs.next())
			{
				user = new StudentInfo();
				user.setDob(rs.getString("dob"));
				user.setName(rs.getString("name"));
				InputStream binaryStream = rs.getBinaryStream("photo");
				user.setPhoto(binaryStream);
				user.setRollNo(rs.getString("roll_no"));

			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.freeConnection(conn);
		}
		return user;

	}

	@Override
	public StudentInfo getStudentByRollNo(String rollNo)
	{

		StudentInfo user = null;

		Connection conn = ConnectionPool.getConnection();

		try
		{
			PreparedStatement stmt = conn.prepareStatement(GET_STUDENT_ROLL_QRY);
			stmt.setString(1, rollNo);

			ResultSet rs = stmt.executeQuery();

			if (rs.next())
			{
				user = new StudentInfo();
				user.setDob(rs.getString("dob"));
				user.setName(rs.getString("name"));
				InputStream binaryStream = rs.getBinaryStream("photo");
        user.setPhoto(binaryStream);
				user.setRollNo(rs.getString("roll_no"));
				user.setStudentId(rs.getInt("user_id"));
				

			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.freeConnection(conn);
		}
		return user;

	}

	@Override
	public List<StudentInfo> getFriends(String rollno)
	{
			//rollno is logged in user_id
		
		List<StudentInfo> friends = null;
		StudentInfo studentVo = null;
		Connection conn = ConnectionPool.getConnection();

		try
		{
			PreparedStatement stmt = conn.prepareStatement(GET_FREINDS_QRY);
			stmt.setString(1, rollno);
			//stmt.setString(2, rollno);

			ResultSet rs = stmt.executeQuery();
			friends = new ArrayList<StudentInfo>();
			if (rs.next())
			{
				
			}
			else
			{
				return friends;
			}
			do
			{
				
				studentVo = new StudentInfo();
				//studentVo.setDob(rs.getString("dob"));
				//studentVo.setName(rs.getString("name"));
				//InputStream binaryStream = rs.getBinaryStream("photo");
				//studentDao.setPhoto(binaryStream);
				studentVo.setRollNo(rs.getString("roll_no"));

				friends.add(studentVo);
			}
			while (rs.next());

		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.freeConnection(conn);
		}
		if(friends.size() == 0)
			return null;
		else
		return friends;

	}

	@Override
	public String findRelationShip(String rollNo, String friendNo)
	{

		Connection conn = ConnectionPool.getConnection();
		String result = Constants.NOT_A_FRIEND;
		try
		{
			PreparedStatement stmt = conn.prepareStatement(ARE_THEY_FRIENDS_QRY);
			stmt.setString(1, rollNo);
			stmt.setString(2, friendNo);
		

			ResultSet rs = stmt.executeQuery();

			if (rs.next())
			{
				result = Constants.FRIEND;
			}
			
			//Added for notification functionality
			if(!result .equalsIgnoreCase(Constants.FRIEND))
			{
			stmt =  conn.prepareStatement(CHECK_NOTIFICATION_STATUS);
			stmt.setString(1, rollNo);
			stmt.setString(2, friendNo);
		

			 rs = stmt.executeQuery();

			if (rs.next())
			{
				result = Constants.FRIEND_NOTIFICATION;
			}
			}
			

		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.freeConnection(conn);
		}

		return result;

	}

	@Override
	public boolean addFriend(String userId, String friendNo)
	{

		Connection conn = ConnectionPool.getConnection();
		boolean result = false;
		try
		{
			PreparedStatement stmt = conn.prepareStatement(ADD_FRIENDS_QRY);
			stmt.setString(1, friendNo);
			stmt.setString(2, userId);

			if (stmt.executeUpdate() == 1)
			{
				result = true;
			}

		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.freeConnection(conn);
		}

		return result;

	}

}
