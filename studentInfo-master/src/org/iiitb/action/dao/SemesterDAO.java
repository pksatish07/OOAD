package org.iiitb.action.dao;

import java.sql.Connection;
import java.util.List;

/**
 * @author kempa
 * 
 */
public interface SemesterDAO
{
	public List<String> getTerms(int studentID);
	
	public List<String> getSemester(Connection connection, int studentID, String year);
	
}
