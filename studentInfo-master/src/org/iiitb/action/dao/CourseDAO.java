package org.iiitb.action.dao;

import java.sql.Connection;
import java.util.List;

import org.iiitb.action.enrollment.EnrollmentSubjectsInfo;
import org.iiitb.action.subjects.CurrSubjectInfo;
import org.iiitb.action.subjects.SubjectInfo;

public interface CourseDAO {

	/**
	 * Gets all the courses
	 * 
	 * @param connection
	 * @param userId
	 * @return list of subjectInfo objects
	 */
	public List<SubjectInfo> getAllCourses(Connection connection, int userId);

	/**
	 * Get Enrolled Courses
	 * 
	 * @param connection
	 * @param userId
	 * @return list of subjectInfo objects
	 */
	public List<SubjectInfo> getEnrolledCourses(Connection connection,
			int userId);

	/**
	 * 
	 * @param studentID
	 * @return
	 */
	public List<String> getNames(int studentID);

	/**
	 * 
	 * @param studentID
	 * @param term
	 * @return
	 */
	public List<String> getNames(int studentID, int term);

	public boolean setCourse(Connection connection, String code,
			String courseName, String credits, String lastDate,
			String semester, String facultyName);

	public List<EnrollmentSubjectsInfo> getAlreadyEnrolledCourses(
			Connection connection, int userId);

	public List<EnrollmentSubjectsInfo> getNotEnrolledCourses(
			Connection connection, int userId);

	public boolean removeSubjects(Connection connection, String[] toDelete,
			int userId);

	public List<EnrollmentSubjectsInfo> getPreviousSubjects(
			Connection connection, int parseInt);
	
	public boolean addSubjects(Connection connection, String[] toAdd, int userId);
	
	public List<CurrSubjectInfo> getcurrEnrolledCourses(Connection connection,
			String student);
}
