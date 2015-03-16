package org.iiitb.action.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.iiitb.util.ConnectionPool;
import org.iiitb.util.Constants;

import com.mysql.jdbc.Statement;
import com.opensymphony.xwork2.ActionSupport;

public class AdduserAction extends ActionSupport implements SessionAware,
    ServletRequestAware

{

  private String username;
  private String name;
  private String emailId;
  private String password;
  private String roll_no;
  private String hostel_addr;
  private String student_id;
  private String dob;
  private int current_sem;
  public int getCurrent_sem() {
	return current_sem;
}

public void setCurrent_sem(int current_sem) {
	this.current_sem = current_sem;
}

long user_id = -1;
  String fileUploadContentType;
  private String fileUploadFileName;
  File fileUpload;
  private HttpServletRequest servletRequest;

  public String getDob() {
    return dob;
  }

  public void setDob(String dob) {
    this.dob = dob;
  }

  public String getFileUploadContentType() {
    return fileUploadContentType;
  }

  public void setFileUploadContentType(String fileUploadContentType) {
    this.fileUploadContentType = fileUploadContentType;
  }

  public String getFileUploadFileName() {
    return fileUploadFileName;
  }

  public void setFileUploadFileName(String fileUploadFileName) {
    this.fileUploadFileName = fileUploadFileName;
  }

  public File getFileUpload() {
    return fileUpload;
  }

  public void setFileUpload(File fileUpload) {
    this.fileUpload = fileUpload;
  }

  public String getStudent_id() {
    return student_id;
  }

  public void setStudent_id(String student_id) {
    this.student_id = student_id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmailId() {
    return emailId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRoll_no() {
    return roll_no;
  }

  public void setRoll_no(String roll_no) {
    this.roll_no = roll_no;
  }

  public String getHostel_addr() {
    return hostel_addr;
  }

  public void setHostel_addr(String hostel_addr) {
    this.hostel_addr = hostel_addr;
  }

  private Map<String, Object> session;

  public Map<String, Object> getSession() {
    return session;
  }

  public String execute() throws NamingException, SQLException,
      FileNotFoundException {

    String ret = ERROR;

    Connection conn = ConnectionPool.getConnection();
    ResultSet generatedKeys = null;
    PreparedStatement preStmt = null;
    try {
      String query = "insert into user(name,username,password,email,user_type) values(?,?,?,?,?)";

      preStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      preStmt.setString(1, name);
      preStmt.setString(2, username);
      preStmt.setString(3, password);
      preStmt.setString(4, emailId);
      preStmt.setString(5, "S");
     

      if (preStmt.executeUpdate() > 0)
        ret = SUCCESS;
      else
        ret = ERROR;

      generatedKeys = preStmt.getGeneratedKeys();
      if (generatedKeys.next()) {
        user_id = generatedKeys.getLong(1);
      } else {
        ret = ERROR;
      }

      query = "insert into student(student_id,roll_no,hostel_addr, photo, dob,current_sem) values(?,?,?,?,?,?)";

      preStmt = conn.prepareStatement(query);
      preStmt.setLong(1, user_id);
      preStmt.setString(2, roll_no);
      preStmt.setString(3, hostel_addr);

      String destpath = servletRequest.getSession().getServletContext()
          .getRealPath("/");
     // System.out.println("Server path:" + destpath);
      File destFile = new File(destpath, fileUploadFileName);

      try {
        FileUtils.copyFile(fileUpload, destFile);

      } catch (IOException e) {
        e.printStackTrace();
        return ERROR;
      }
      FileInputStream inputStream = new FileInputStream(destFile);
      preStmt.setBlob(4, inputStream);

      if (null == dob) {
        dob = "11/09/1989";
      }
      Date dobDate = null;
      DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
      try {
        dobDate = new Date(formatter.parse(dob).getTime());
      } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      System.out.println(dobDate);
      preStmt.setDate(5, dobDate);
      preStmt.setInt(6,current_sem);

      if (preStmt.executeUpdate() > 0)
        ret = SUCCESS;
      else
        ret = ERROR;

    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      ConnectionPool.freeConnection(conn);
    }
    return ret;

  }

  public void validate() {

    if (StringUtils.isEmpty(username)) {
      addFieldError(Constants.DB_USERNAME, Constants.USER_NAME_BLANK);
    }

    if (StringUtils.isEmpty(password)) {
      addFieldError(Constants.DB_PASSWORD, Constants.PASSWORD_BLANK);
    }

    if (StringUtils.isEmpty(name)) {
      addFieldError("name", "name cannot be blank");
    }

    if (StringUtils.isEmpty(emailId)) {
      addFieldError("emailId", "emailId cannot be blank");
    }

  }

  @Override
  public void setSession(Map<String, Object> session) {
    this.session = session;

  }

  @Override
  public void setServletRequest(HttpServletRequest req) {
    this.servletRequest = req;

  }
}