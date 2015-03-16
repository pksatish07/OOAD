package org.iiitb.action.image;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.iiitb.model.User;
import org.iiitb.util.ConnectionPool;

import com.opensymphony.xwork2.ActionSupport;

public class AdminImageAction extends ActionSupport implements SessionAware,ServletRequestAware{

	private Map<String, Object> session;
	private String userId;
	private InputStream photo;
	String fileUploadContentType;
	private String fileUploadFileName;
	File fileUpload;
	private HttpServletRequest servletRequest;
	
	@Override
	  public void setServletRequest(HttpServletRequest req) {
	    this.servletRequest = req;

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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public InputStream getPhoto() {
		return photo;
	}
	public void setPhoto(InputStream photo) {
		this.photo = photo;
	}

	public InputStream getAdminImage(String userId){

		Connection conn = ConnectionPool.getConnection();
		String query="select image from adminimage where userid=?";


		try {

			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, userId);

			ResultSet rs = stmt.executeQuery();

			if (rs.next())
			{

				InputStream binaryStream = rs.getBinaryStream("image");
				setPhoto(binaryStream);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return photo;
	}

	public String updateimage(){

		
		User user = (User) session.get("user");
		userId = user.getUserId();
		Connection conn = ConnectionPool.getConnection();
		String query = "update adminimage set image=? where userid=?";
		PreparedStatement stmt=null;
		try {
		
			stmt = conn.prepareStatement(query);

			String destpath = servletRequest.getSession().getServletContext().getRealPath("/");
			//System.out.println("Server path:" + destpath);
			File destFile = new File(destpath, fileUploadFileName);
			FileInputStream inputStream = null;
			try {
				FileUtils.copyFile(fileUpload, destFile);
				inputStream = new FileInputStream(destFile);


			} catch (IOException e) {
				e.printStackTrace();
				return ERROR;
			}
			
			stmt.setBlob(1, inputStream);
			
			stmt.setInt(2,Integer.parseInt(userId));

			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionPool.freeConnection(conn);
		}

		return SUCCESS;

	}


	public String execute(){
		Connection conn = ConnectionPool.getConnection();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("image/jpeg");

		InputStream in = this.getAdminImage(userId);

		OutputStream out;


		try {
			out = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int len;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ConnectionPool.freeConnection(conn);
		return NONE;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

}
