package org.iiitb.action.image;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.iiitb.action.dao.StudentDAO;
import org.iiitb.action.dao.impl.StudentDAOImpl;
import org.iiitb.model.StudentInfo;
import org.iiitb.util.ConnectionPool;

import com.opensymphony.xwork2.ActionSupport;

public class ImageAction extends ActionSupport implements SessionAware {

  private StudentDAO studentDao = new StudentDAOImpl();
  private String userId;
  private String rollNo;

  private Map<String, Object> session;

  public String execute() throws SQLException, IOException {
    Connection connection = ConnectionPool.getConnection();
    StudentInfo student = null;
    if (null != rollNo && !rollNo.isEmpty()) {
      student = studentDao.getStudentByRollNo(rollNo);
    } else {
      student = studentDao.getStudentByUserId(userId);
    }
    HttpServletResponse response = ServletActionContext.getResponse();
    response.setContentType("image/jpeg");
    InputStream in = student.getPhoto();
    OutputStream out = response.getOutputStream();
    byte[] buffer = new byte[1024];
    int len;
    while ((len = in.read(buffer)) != -1) {
      out.write(buffer, 0, len);
    }
    ConnectionPool.freeConnection(connection);
    return NONE;
  }

  public String getRollNo() {
    return rollNo;
  }

  public void setRollNo(String rollNo) {
    this.rollNo = rollNo;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  @Override
  public void setSession(Map<String, Object> session) {
    this.session = session;

  }
}
