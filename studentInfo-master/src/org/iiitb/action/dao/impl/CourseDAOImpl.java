/**
 * 
 */
package org.iiitb.action.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.text.ParseException;

import org.iiitb.action.dao.CourseDAO;
import org.iiitb.action.enrollment.EnrollmentSubjectsInfo;
import org.iiitb.action.subjects.CurrSubjectInfo;
import org.iiitb.action.subjects.SubjectInfo;
import org.iiitb.util.ConnectionPool;

import java.sql.Statement;

public class CourseDAOImpl implements CourseDAO {

	private static final String ALL_COURSES_QUERY = "SELECT  "
			+ "    SUBJECTS_AVAILABLE.course_id as course_id, "
			+ "    subject_code, "
			+ "    subject_name, "
			+ "    faculty_name, "
			+ "    semester_term, "
			+ "    student_id, "
			+ "    grade_name "
			+ "FROM "
			+ "    (SELECT  "
			+ "        course.course_id as course_id, "
			+ "            course.code as subject_code, "
			+ "            course.name as subject_name, "
			+ "            teacher.name as faculty_name, "
			+ "            semester.term as semester_term "
			+ "    FROM "
			+ "        course course "
			+ "    LEFT OUTER JOIN semester semester ON semester.semester_id = course.semester_id "
			+ "    LEFT OUTER JOIN faculty faculty ON course.faculty_id = faculty.faculty_id "
			+ "    LEFT OUTER JOIN user teacher ON faculty.faculty_id = teacher.user_id) SUBJECTS_AVAILABLE "
			+ "        LEFT OUTER JOIN "
			+ "    (SELECT  "
			+ "        course.course_id, "
			+ "            student.user_id as student_id, "
			+ "            student.name as student_name, "
			+ "            grade.name as grade_name "
			+ "    FROM "
			+ "        course course "
			+ "    LEFT OUTER JOIN faculty faculty ON course.faculty_id = faculty.faculty_id "
			+ "    LEFT OUTER JOIN user teacher ON faculty.faculty_id = teacher.user_id "
			+ "    LEFT OUTER JOIN result result ON result.course_id = course.course_id "
			+ "    LEFT OUTER JOIN user student ON student.user_id = result.student_id "
			+ "    LEFT OUTER JOIN grade grade ON grade.grade_id = result.grade_id "
			+ "    LEFT OUTER JOIN semester semester ON semester.semester_id = course.semester_id "
			+ "    WHERE "
			+ "        student.user_id = ?) STUDENT_SELECTION ON STUDENT_SELECTION.course_id = SUBJECTS_AVAILABLE.course_id "
			+ "ORDER BY subject_code;";

	private static final String ENROLLED_COURSE_QUERY = "SELECT  "
			+ "    course.course_id as course_id, "
			+ "    student.user_id as student_id, "
			+ "    student.name as student_name, "
			+ "    grade.name as grade_name, "
			+ "    course.code as subject_code, "
			+ "    course.name as subject_name, "
			+ "    teacher.name as faculty_name, "
			+ "    semester.term as semester_term "
			+ "FROM "
			+ "    course course "
			+ "        LEFT OUTER JOIN "
			+ "    faculty faculty ON course.faculty_id = faculty.faculty_id "
			+ "        LEFT OUTER JOIN "
			+ "    user teacher ON faculty.faculty_id = teacher.user_id "
			+ "        LEFT OUTER JOIN "
			+ "    result result ON result.course_id = course.course_id "
			+ "        LEFT OUTER JOIN "
			+ "    user student ON student.user_id = result.student_id "
			+ "        LEFT OUTER JOIN "
			+ "    grade grade ON grade.grade_id = result.grade_id "
			+ "        LEFT OUTER JOIN "
			+ "    semester semester ON semester.semester_id = course.semester_id "
			+ "WHERE " + "    student.user_id = ?";

	private static final String ENROLLED_COURSE_QUERY_BY_NAME = "SELECT  "
			+ "    course.course_id as course_id, "
			+ "    student.user_id as student_id, "
			+ "    student.name as student_name, "
			+ "    grade.name as grade_name, "
			+ "    course.code as subject_code, "
			+ "    course.name as subject_name, "
			+ "    teacher.name as faculty_name, "
			+ "    semester.term as semester_term "
			+ "FROM "
			+ "    course course "
			+ "        LEFT OUTER JOIN "
			+ "    faculty faculty ON course.faculty_id = faculty.faculty_id "
			+ "        LEFT OUTER JOIN "
			+ "    user teacher ON faculty.faculty_id = teacher.user_id "
			+ "        LEFT OUTER JOIN "
			+ "    result result ON result.course_id = course.course_id "
			+ "        LEFT OUTER JOIN "
			+ "    user student ON student.user_id = result.student_id "
			+ "        LEFT OUTER JOIN "
			+ "    grade grade ON grade.grade_id = result.grade_id "
			+ "        LEFT OUTER JOIN "
			+ "    semester semester ON semester.semester_id = course.semester_id "
			+ "WHERE " + "    student.name = ?";

	private static final String GET_NAMES_QUERY = "select distinct course.name"
			+ " from result, course "
			+ "where result.course_id = course.course_id "
			+ "and result.student_id=?";

	private static final String GET_PREVIOUSLY_ENROLLED_COURSES = "SELECT distinct code,name,credits"
			+ " from sisdb.result r,sisdb.course c"
			+ " WHERE r.course_id = c.course_id"
			+ " AND (c.semester_id < (select current_sem from sisdb.student where student_id = ?))"
			+ " AND r.student_id = ? ";
	
	private static final String GET_ENROLLED_COURSES = "SELECT code,name,credits"
			+ " from sisdb.result r,sisdb.course c"
			+ " WHERE r.course_id = c.course_id"
			+ " AND (c.semester_id = (select current_sem from sisdb.student where student_id = ?))"
			+ " AND r.student_id = ? ";

	private static final String GET_NOT_ENROLLED_COURSES = "SELECT code,name,credits from sisdb.course WHERE code NOT IN"
			+ " (SELECT code from sisdb.result r,sisdb.course c WHERE r.course_id = c.course_id"
			+ " AND (r.student_id = ?)"
			+ " AND (c.semester_id = (select current_sem from sisdb.student where student_id = ?)))"
			+ " AND (semester_id = (select current_sem from sisdb.student where student_id = ?))"
			+ " AND (lastdate > DATE(NOW()))";

	private static final String GET_CURR_ENROLLED_COURSES_QUERY_BY_NAME  = "SELECT c.course_id,c.code,c.name"
			+ " from sisdb.result r,sisdb.course c"
			+ " WHERE r.course_id = c.course_id"
			+ " AND (c.semester_id = (select current_sem from sisdb.student,sisdb.user where student_id =user_id and user.name=?))"
			+ " AND (r.student_id = (select user_id from sisdb.student,sisdb.user where student_id =user_id and user.name=?));";
	
	private static final String GET_NAMES_QUERY_TERM = "select c.name from course c ,result r where c.course_id = r.course_id and c.semester_id =? and r.student_id=?";

	private static final String GET_FACULTY_ID = "SELECT " + "user_id "
			+ "FROM user " + "WHERE name = ? " + "AND user_type = 'F'";

	private static final String SET_COURSE = "INSERT INTO course"
			+ "(course_id, code, name, credits, lastdate, faculty_id, semester_id) "
			+ "VALUES " + "(course_id, ?, ?, ?, ?, ?, ?)";

	private void createSubjectInfoListFromResultSet(ResultSet rs,
			List<SubjectInfo> subjectInfoList) throws SQLException {
		while (rs.next()) {
			String courseId = rs.getString("course_id");
			String subjectCode = rs.getString("subject_code");
			String subjectName = rs.getString("subject_name");
			String facultyName = rs.getString("faculty_name");
			int semester = rs.getInt("semester_term");
			String enrolledVal = rs.getString("student_id");
			String enrolled = (null == enrolledVal) ? "N" : "Y";
			String gradeVal = rs.getString("grade_name");
			String grade = (null == gradeVal) ? "NA" : gradeVal;
			SubjectInfo subjectInfo = new SubjectInfo(courseId, subjectCode,
					subjectName, facultyName, semester, enrolled, grade);
			subjectInfoList.add(subjectInfo);
		}
	}

	
	@Override
	public List<SubjectInfo> getAllCourses(Connection connection, int userId) {
		List<SubjectInfo> subjectInfoList = null;
		PreparedStatement ps = null;
		try {
			subjectInfoList = new ArrayList<SubjectInfo>();
			ps = connection.prepareStatement(ALL_COURSES_QUERY);
			int index = 1;
			ps.setInt(index, userId);
			ResultSet rs = ps.executeQuery();
			createSubjectInfoListFromResultSet(rs, subjectInfoList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return subjectInfoList;
	}

	@Override
	public List<SubjectInfo> getEnrolledCourses(Connection connection,
			int userId) {
		List<SubjectInfo> subjectInfoList = null;
		PreparedStatement ps = null;
		try {
			subjectInfoList = new ArrayList<SubjectInfo>();
			ps = connection.prepareStatement(ENROLLED_COURSE_QUERY);
			int index = 1;
			ps.setInt(index, userId);
			ResultSet rs = ps.executeQuery();
			createSubjectInfoListFromResultSet(rs, subjectInfoList);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return subjectInfoList;
	}

	public List<SubjectInfo> getEnrolledCourses(Connection connection,
			String student) {
		List<SubjectInfo> subjectInfoList = null;
		PreparedStatement ps = null;
		try {
			subjectInfoList = new ArrayList<SubjectInfo>();
			ps = connection.prepareStatement(ENROLLED_COURSE_QUERY_BY_NAME);
			int index = 1;
			ps.setString(index, student);
			ResultSet rs = ps.executeQuery();
			createSubjectInfoListFromResultSet(rs, subjectInfoList);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return subjectInfoList;
	}

	public List<String> getNames(int studentID) {

		//System.out.println("Entered");
		List<String> courseList = new LinkedList<String>();
		Connection con = ConnectionPool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs;
		try {
			ps = con.prepareStatement(GET_NAMES_QUERY);
			rs = ps.executeQuery();
			ps.setInt(1, studentID);
			while (rs.next())
				courseList.add(rs.getString(1));
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

		return courseList;
	}

	public List<String> getNames(int studentID, int term) {

		List<String> courseList = new LinkedList<String>();
		Connection con = ConnectionPool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs;
		try {
			ps = con.prepareStatement(GET_NAMES_QUERY_TERM);
			ps.setInt(1, term);
			ps.setInt(2, studentID);
			rs = ps.executeQuery();
			while (rs.next())
				courseList.add(rs.getString(1));

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

		return courseList;
	}

	public boolean setCourse(Connection connection, String code,
			String courseName, String credits, String lastDate,
			String semester, String facultyName) {

		int index, is;
		String faculty_id = null,lastdate = null,faculty = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Date lastDateForEnrollment = null;
		faculty = facultyName.replaceAll(",","");
		
		semester = semester.replaceAll("," ,"");
		semester = semester.replaceAll(" " ,"");
		try {
			ps = connection.prepareStatement(GET_FACULTY_ID);

			index = 1;
			ps.setString(index,faculty);
			rs = ps.executeQuery();

			while (rs.next()) {
				faculty_id = rs.getString("user_id");
				//System.out.println("faculty_id::" + faculty_id);
			}
			// Insert Course Details
			ps = connection.prepareStatement(SET_COURSE);

			index = 1;
			ps.setString(index, code);
			index = 2;
			ps.setString(index, courseName);
			index = 3;
			ps.setInt(index, Integer.parseInt(credits));

			index = 4;
			if (lastDate == null){
				lastdate = "31/12/2015";
				
			}
			else{
				lastdate = lastDate;
			}
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {
				
				lastDateForEnrollment = new Date(formatter.parse(lastdate)
						.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ps.setDate(index,lastDateForEnrollment);

			index = 5;
			ps.setInt(index, Integer.parseInt(faculty_id));
			index = 6;
			ps.setInt(index, Integer.parseInt(semester));
			is = ps.executeUpdate();

			if (is != 1)
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	
	@Override
	public List<EnrollmentSubjectsInfo> getAlreadyEnrolledCourses(
			Connection connection, int userId) {
		List<EnrollmentSubjectsInfo> enrollmentInfoList = null;
		PreparedStatement ps = null;
		try {
			enrollmentInfoList = new ArrayList<EnrollmentSubjectsInfo>();
			ps = connection.prepareStatement(GET_ENROLLED_COURSES);
			ps.setInt(1, userId);
			ps.setInt(2, userId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				EnrollmentSubjectsInfo newEnrollmentInfo = new EnrollmentSubjectsInfo();
				newEnrollmentInfo.setEnorllmentSubjectsInfo(
						rs.getString("code"), rs.getString("name"),
						rs.getString("credits"));
				enrollmentInfoList.add(newEnrollmentInfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return enrollmentInfoList;
	}

	
	@Override
	public List<EnrollmentSubjectsInfo> getNotEnrolledCourses(
			Connection connection, int userId) {

		List<EnrollmentSubjectsInfo> enrollmentInfoList = null;
		PreparedStatement ps = null;
		try {
			enrollmentInfoList = new ArrayList<EnrollmentSubjectsInfo>();
			ps = connection.prepareStatement(GET_NOT_ENROLLED_COURSES);
			ps.setInt(1, userId);
			ps.setInt(2, userId);
			ps.setInt(3, userId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				EnrollmentSubjectsInfo newEnrollmentInfo = new EnrollmentSubjectsInfo();
				//System.out.println("Entered");
				newEnrollmentInfo.setEnorllmentSubjectsInfo(
						rs.getString("code"), rs.getString("name"),
						rs.getString("credits"));
				enrollmentInfoList.add(newEnrollmentInfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return enrollmentInfoList;

	}

	@Override
	public boolean removeSubjects(Connection connection, String[] toDelete,
			int userId) {

		Statement statement = null;
		for (String subCode : toDelete) {
			try {
				String sqlQuery = "delete from sisdb.result "
						+ " where "
						+ " course_id IN (select course_id from sisdb.course where code = '"+ subCode + "') AND student_id = '" + userId+"';";
				statement = (Statement) connection.createStatement();
				int rs = statement.executeUpdate(sqlQuery);

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addSubjects(Connection connection, String[] toAdd,
			int user_id) {

		for (String subjectCode : toAdd) {
			int courseID = 0;
			Statement statement = null;
			ResultSet courseIdResultSet;
			String sqlQuery1 = "select course_id from sisdb.course where code = '"
					+ subjectCode + "' ";

			try {
				statement = (Statement) connection.createStatement();
				courseIdResultSet = statement.executeQuery(sqlQuery1);
				if (null != courseIdResultSet) {
					//System.out.println("no errors in addSubjects");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("SystemError in addSubjects");
				return false;
			}
			try {
				while (courseIdResultSet.next()) {
					courseID = courseIdResultSet.getInt("course_id");
				}
				PreparedStatement preparedStatement = null;
				String sqlQuery2 = "INSERT INTO sisdb.result(student_id,course_id) values(?,?);";
				preparedStatement = connection.prepareStatement(sqlQuery2);
				preparedStatement.setInt(1, user_id);
				preparedStatement.setInt(2, courseID);

				int noOfRows = preparedStatement.executeUpdate();
				//System.out.println("rows affected = " + noOfRows);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		return true;
	}


	public List<EnrollmentSubjectsInfo> getPreviousSubjects(
			Connection connection, int userId) {
		
		List<EnrollmentSubjectsInfo> previousSubjects = null;
		PreparedStatement ps = null;
		try {
			previousSubjects = new ArrayList<EnrollmentSubjectsInfo>();
			ps = connection.prepareStatement(GET_PREVIOUSLY_ENROLLED_COURSES);
			ps.setInt(1, userId);
			ps.setInt(2, userId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				EnrollmentSubjectsInfo newEnrollmentInfo = new EnrollmentSubjectsInfo();
				newEnrollmentInfo.setEnorllmentSubjectsInfo(
						rs.getString("code"), rs.getString("name"),
						rs.getString("credits"));
				previousSubjects.add(newEnrollmentInfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return previousSubjects;
		
	}
	
	private void createCurrSubjectInfoListFromResultSet(ResultSet rs,
			List<CurrSubjectInfo> subjectInfoList) throws SQLException {
		while (rs.next()) {
			String courseId = rs.getString("course_id");
			String subjectCode = rs.getString("code");
			String subjectName = rs.getString("name");
			CurrSubjectInfo subjectInfo = new CurrSubjectInfo(courseId, subjectCode,
					subjectName);
			subjectInfoList.add(subjectInfo);
		}
	}
	
	public List<CurrSubjectInfo> getcurrEnrolledCourses(Connection connection,
			String student) {
		
		List<CurrSubjectInfo> subjectInfoList = null;
		PreparedStatement ps = null;
		try {
			subjectInfoList = new ArrayList<CurrSubjectInfo>();
			ps = connection.prepareStatement(GET_CURR_ENROLLED_COURSES_QUERY_BY_NAME);
			System.out.println(student);
			
			ps.setString(1, student);
			ps.setString(2, student);
			
			ResultSet rs = ps.executeQuery();
			createCurrSubjectInfoListFromResultSet(rs, subjectInfoList);
			System.out.println("In get cuur course");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return subjectInfoList;
	}
}