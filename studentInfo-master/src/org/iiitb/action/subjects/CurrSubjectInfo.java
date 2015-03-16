package org.iiitb.action.subjects;

public class CurrSubjectInfo {
	 private String courseId;
	  private String subjectCode;
	  private String subjectName;
	  
	public CurrSubjectInfo(String courseId, String subjectCode,
			String subjectName) {
		super();
		this.courseId = courseId;
		this.subjectCode = subjectCode;
		this.subjectName = subjectName;
	}
	public CurrSubjectInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

}
