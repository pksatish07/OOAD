/**
 * 
 */
package org.iiitb.action.dao;

import java.util.List;

import org.iiitb.action.grades.GradeInfo;

/**
 * @author kempa
 * 
 */
public interface ResultDAO
{
	public List<GradeInfo> getGrades(int studentID, int term, String subjectName);

	public List<GradeInfo> getGrades(int studentID);

	public List<GradeInfo> getGrades(int studentID, int term);
	
	public boolean updateGrades(String studentName,String courseName,String gradeName);
	
	public List<String> getGrades();
}
