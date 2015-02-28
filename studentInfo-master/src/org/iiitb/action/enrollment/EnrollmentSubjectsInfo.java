package org.iiitb.action.enrollment;

public class EnrollmentSubjectsInfo {

	private String subjectCode;
	private String subjectName;
	private String credits;
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
	public String getCredits() {
		return credits;
	}
	public void setCredits(String credits) {
		this.credits = credits;
	}
	
	public void setEnorllmentSubjectsInfo(String subCode,String subName,String credits){
		this.subjectCode = subCode;
		this.subjectName = subName;
		this.credits = credits;
	}
}
