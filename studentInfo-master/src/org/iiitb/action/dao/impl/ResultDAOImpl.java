/**
 * 
 */
package org.iiitb.action.dao.impl;

import java.util.LinkedList;
import java.util.List;

import org.iiitb.action.dao.ResultDAO;
import org.iiitb.action.grades.GradeInfo;
import org.iiitb.util.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author kempa
 * 
 */
public class ResultDAOImpl implements ResultDAO
{
	private List<GradeInfo> resultList = new LinkedList<GradeInfo>();
	
	private List<String> gradeList = new LinkedList<String>();
	
	/*private static final String GET_GRADES_QUERY1 = "select course.code, grade.name, "
		+ "grade.status "
			+ "from course, result, grade, semester "
			+ "where course.course_id = result.course_id "
			+ "and result.grade_id = grade.grade_id "
			+ "and course.semester_id = semester.semester_id "
			+ "and result.student_id = ? "
			+ "and semester.term = ? "
			+ " and course.name = ?";*/
	
	
	private static final String GET_GRADES_QUERY1 = "select name , status from grade where grade_id = (select grade_id from result where course_id = (select course_id from course where name =? and semester_id=?) and student_id=?)";
	
	private static final String GET_COURSE_CODE = "select code from course where name =? and semester_id=?";
	
	
	
	
	
/*	private static final String GET_GRADES_QUERY2 = "select "
			+ "course.name, course.code, grade.name, grade.status "
			+ "from "
			+ "course, result, grade, semester "
			+ "where "
			+ "course.course_id = result.course_id "
			+ "and "
			+ "result.grade_id = grade.grade_id "
			+ "and "
			+ "course.semester_id = semester.semester_id "
			+ "and "
			+ "result.student_id = ?";*/
	
	private static final String GET_GRADES_QUERY2 = "select g.name , g.status, c.code , c.name from course c , result r , grade g where c.course_id = r.course_id and student_id =? and r.grade_id = g.grade_id";
	
	
/*	
	private static final String GET_GRADES_QUERY3 = "select "
			+ "course.name, course.code, grade.name, grade.status "
			+ "from "
			+ "course, result, grade, semester "
			+ "where "
			+ "course.course_id = result.course_id "
			+ "and "
			+ "result.grade_id = grade.grade_id "
			+ "and "
			+ "course.semester_id = semester.semester_id "
			+ "and "
			+ "result.student_id = ? "
			+ "and semester.term = ? ";*/
	
	
	
	private static final String GET_GRADES_QUERY3 ="select g.name,g.status,c.code,c.name from course c , result r , grade g  where c.course_id = r.course_id and r.student_id = ? and c.semester_id =? and r.grade_id=g.grade_id";
	
	private static final String GET_GRADES_QUERY4 = "select "
			+ "distinct name "
			+ "from grade";
	
	private static final String UPDATE_GRADES = "update result  " + 
			"set  " + 
			"    grade_id = ? " + 
			"where " + 
			"    student_id = ? and course_id = ?";

  public boolean updateGrades(String studentName,String courseName,String gradeName)
	{
		Connection con = ConnectionPool.getConnection();
		PreparedStatement ps = null;
		boolean result=false;
		try
		{
		  /** Dirty fix **/
		  final String GET_GRADE_ID = "select grade_id from grade where name = ?";
		  final String GET_STUDENT_ID = "select student_id from student, user where user.name = ? and student_id = user_id";
		  final String GET_COURSE_ID = "select course_id from course where name = ?";    

		  ps = con.prepareStatement(GET_GRADE_ID);
		  ps.setString(1, gradeName);
		  ResultSet rs = ps.executeQuery();
		  rs.next();
		  int gradeId = rs.getInt("grade_id");
		  
		  ps = con.prepareStatement(GET_STUDENT_ID);
		  ps.setString(1, studentName);
      rs = ps.executeQuery();
      rs.next();
      int studentId = rs.getInt("student_id");
      
      ps = con.prepareStatement(GET_COURSE_ID);
      ps.setString(1, courseName);
      rs = ps.executeQuery();
      rs.next();
      int courseId = rs.getInt("course_id");
      /** End of dirty fix **/
      
			ps = con.prepareStatement(UPDATE_GRADES);
			ps.setInt(1, gradeId);
			ps.setInt(2, studentId);
			ps.setInt(3, courseId);
			if(ps.executeUpdate()==1)
			{
				result=true;
			}
				
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.freeConnection(con);
		}
		return result;
		
	}
	public List<GradeInfo> getGrades(int studentID, int term, String subjectName)
	{
		Connection con = ConnectionPool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs;
		String subjectCode = null;
		try
		{
			
			ps = con.prepareStatement( GET_COURSE_CODE);
			ps.setString(1, subjectName);
			ps.setInt(2, term);
			rs = ps.executeQuery();
			while (rs.next())
			{
				subjectCode = rs.getString(1);
				
			}
			//System.out.println(subjectName);
			//System.out.println(subjectCode);
			
			ps = con.prepareStatement(GET_GRADES_QUERY1);
			
			ps.setString(1, subjectName);
			ps.setInt(2, term);
			ps.setInt(3, studentID);
			
			
			rs = ps.executeQuery();
			while (rs.next())
			{
				
				String grade = rs.getString(1);
				String result = rs.getString(2);
				resultList.add(new GradeInfo(subjectName, subjectCode, grade,
						result));
				//System.out.println(subjectName+subjectCode+grade+result);
			}
			
			
			//System.out.println("Final result is "+resultList);
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (ps != null)
				try
				{
					ps.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			ConnectionPool.freeConnection(con);
		}

		return resultList;
	}

	public List<GradeInfo> getGrades(int studentID)
	{
		Connection con = ConnectionPool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs;
		try
		{
			ps = con.prepareStatement(GET_GRADES_QUERY2);
			ps.setInt(1, studentID);
			rs = ps.executeQuery();
			while (rs.next())
			{
				String subjectName = rs.getString(4);
				String subjectCode = rs.getString(3);
				String grade = rs.getString(1);
				String result = rs.getString(2);
				resultList.add(new GradeInfo(subjectName, subjectCode, grade,
						result));
			}			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (ps != null)
				try
				{
					ps.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			ConnectionPool.freeConnection(con);
		}

		
		return resultList;
	}
	
	public List<GradeInfo> getGrades(int studentID, int term)
	{
		Connection con = ConnectionPool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs;
		try
		{
			ps = con.prepareStatement(GET_GRADES_QUERY3);
			ps.setInt(1, studentID);
			ps.setInt(2, term);
			rs = ps.executeQuery();
			while (rs.next())
			{
				String subjectName = rs.getString(4);
				String subjectCode = rs.getString(3);
				String grade = rs.getString(1);
				String result = rs.getString(2);
				resultList.add(new GradeInfo(subjectName, subjectCode, grade,
						result));
			}			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (ps != null)
				try
				{
					ps.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			ConnectionPool.freeConnection(con);
		}

		return resultList;
	}
	
	public List<String> getGrades() {
		Connection con = ConnectionPool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs;
		try {
			ps = con.prepareStatement(GET_GRADES_QUERY4);
			rs = ps.executeQuery();
			while (rs.next()) {
				String gradeName = rs.getString(1);
				gradeList.add(gradeName);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (ps != null)
				try {
					ps.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			ConnectionPool.freeConnection(con);
		}

		return gradeList;
	}
}
