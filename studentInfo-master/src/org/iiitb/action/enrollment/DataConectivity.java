package org.iiitb.action.enrollment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.iiitb.util.ConnectionPool;

public class DataConectivity {
  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  static final String DB_URL = "jdbc:mysql://localhost/sisdb";// write db name

  // Database credentials
  static final String USER = "root";
  static final String PASS = "kishore";

  private int semesterid;
  private String coursename;

  private int studentid;
  private int courseid;
  private int student_id;

  private int credits;
  private int course_id;
  private String code, name, lastdate;
  int faculty_id, semester_id;

  public int getStudent_id() {
    return student_id;
  }

  public void setStudent_id(int student_id) {
    this.student_id = student_id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public int getCourse_id() {
    return course_id;
  }

  public void setCourse_id(int course_id) {
    this.course_id = course_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastdate() {
    return lastdate;
  }

  public void setLastdate(String lastdate) {
    this.lastdate = lastdate;
  }

  public int getFaculty_id() {
    return faculty_id;
  }

  public void setFaculty_id(int faculty_id) {
    this.faculty_id = faculty_id;
  }

  public int getSemester_id() {
    return semester_id;
  }

  public void setSemester_id(int semester_id) {
    this.semester_id = semester_id;
  }

  public int getStudentid() {
    return studentid;
  }

  public void setStudentid(int studentid) {
    this.studentid = studentid;
  }

  public int getCourseid() {
    return courseid;
  }

  public void setCourseid(int courseid) {
    this.courseid = courseid;
  }

  public int getSemesterid() {
    return semesterid;
  }

  public void setSemesterid(int semesterid) {
    this.semesterid = semesterid;
  }

  public String getCoursename() {
    return coursename;
  }

  public void setCoursename(String coursename) {
    this.coursename = coursename;
  }

  public int getCredits() {
    return credits;
  }

  public void setCredits(int credits) {
    this.credits = credits;
  }

  public static ArrayList<DataConectivity> CourseDisplay(int semester) {

    ArrayList<DataConectivity> ALDC = new ArrayList<DataConectivity>();
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 2: Register JDBC driver

      conn = ConnectionPool.getConnection();

      // STEP 4: Execute a query

      // System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql;
      sql = "SELECT course_id,code,name,credits,lastdate,faculty_id,semester_id FROM course WHERE semester_id="
          + semester + " and lastdate > CURDATE()";// write your query
      ResultSet rs = stmt.executeQuery(sql);

      // STEP 5: Extract data from result set
      while (rs.next()) {
        // Retrieve by column name
        DataConectivity dc = new DataConectivity();
        dc.course_id = rs.getInt("course_id");
        dc.coursename = rs.getString("name");
        dc.credits = rs.getInt("credits");
        dc.code = rs.getString("code");
        dc.lastdate = rs.getString("lastdate");
        dc.faculty_id = rs.getInt("faculty_id");
        dc.semester_id = rs.getInt("semester_id");

        // Display values
        // System.out.print("courseid " + dc.course_id + "       ");
        // System.out.print("code" + dc.code + "       ");
        // System.out.print("name " + dc.coursename + "     ");
        // System.out.print("credits" + dc.credits + "       ");
        // System.out.print("lastdate " + dc.lastdate + "      ");
        // System.out.print("facultyid" + dc.faculty_id + "         ");
        // System.out.print("semesterid " + dc.semester_id + "         ");

        ALDC.add(dc);

      }
      // STEP 6: Clean-up environment
      rs.close();
      stmt.close();
      ConnectionPool.freeConnection(conn);
    } catch (SQLException se) {
      // Handle errors for JDBC
      se.printStackTrace();
    } catch (Exception e) {
      // Handle errors for Class.forName
      e.printStackTrace();
    } finally {
      // finally block used to close resources
      try {
        if (stmt != null)
          stmt.close();
      } catch (SQLException se2) {
      }// nothing we can do
      try {
        if (conn != null)
          conn.close();
      } catch (SQLException se) {
        se.printStackTrace();
      }// end finally try
    }// end try
     // System.out.println("Goodbye!");

    return ALDC;
  }// end main

  public static void CourseUpdate(int studentid, ArrayList<Integer> Selectedlist) {

    ArrayList<Integer> sel = new ArrayList<Integer>();
    sel = Selectedlist;
    Connection conn = null;
    Statement stmt = null;
    try {

      conn = ConnectionPool.getConnection();

      // STEP 4: Execute a query
      // System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql, sql1;
      int i1 = 0, i2 = 0, i3;

      for (Integer i : sel) {
        // //System.out.println(i);

        sql1 = "select student_id,course_id,grade_id from result where course_id="
            + i + " " + "and" + " " + "student_id=" + studentid;
        ResultSet rs = stmt.executeQuery(sql1);
        while (rs.next()) {
          i1 = rs.getInt("student_id");
          i2 = rs.getInt("course_id");
          i3 = rs.getInt("grade_id");
          // //System.out.println("studentid"+i1+"courseid"+i2+"555555555555555555555555555555555555555");

        }

        if (i1 == studentid && i2 == i) {
          //String d = "Course " + Indexaction.sub.get(i2)
          //    + " is already registered." + "<br/>";
          //Indexaction.s.append("");
          // System.out.println("this course of courseid" + i2
          // + "is already registered plz dont add again");
          continue;

        }
        sql = " INSERT INTO result (student_id,course_id) VALUES ("
            + studentid + "," + i + ")";
        //String t = "updated course as " + studentid + i;
        //Indexaction.s.append(t);
        // System.out.println("updated course as " + studentid + i);
        int s = stmt.executeUpdate(sql);
        if (s == 1) {
          // System.out.println("done");
        }
      }
      // STEP 6: Clean-up environment

      stmt.close();
      ConnectionPool.freeConnection(conn);
    } catch (SQLException se) {
      // Handle errors for JDBC
      se.printStackTrace();
    } catch (Exception e) {
      // Handle errors for Class.forName
      e.printStackTrace();
    } finally {
      // finally block used to close resources
      try {
        if (stmt != null)
          stmt.close();
      } catch (SQLException se2) {
      }// nothing we can do
      try {
        if (conn != null)
          conn.close();
      } catch (SQLException se) {
        se.printStackTrace();
      }// end finally try
    }// end try
     // System.out.println("Goodbye!");

  }// end main

}
