package org.iiitb.action;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.iiitb.action.dao.LoginDao;
import org.iiitb.service.PasswordGenerator;

import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordAction extends ActionSupport {
	LoginDao loginDao = new LoginDao();
	private String username;
	private String emailId;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String execute() {
		// we add javax.mail.jar for sending mail
		System.out.println("here..");
		String host = "smtp.gmail.com";
		String port = "587";
		boolean valid = loginDao.checkForPasswordReset(getUsername(),
				getEmailId());
		if (valid) {
			PasswordGenerator pg = new PasswordGenerator();
			String newPassword = pg.generate(6); // generate a new random
													// password
			loginDao.resetPassword(getUsername(), newPassword); // set new
																// password in
																// database

			Properties prop = new Properties();
			prop.put("mail.smtp.host", host);
			prop.put("mail.smtp.port", port);
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.starttls.enable", "true");

			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(
							"pksatish07@gmail.com", "pksatish03");
				}
			};
			Session session = Session.getInstance(prop, auth);

			String msgBody = "Your new password is " + newPassword;
			try {
				MimeMessage msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress("abhipassion.sharma@gmail.com"));
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
						getEmailId()));
				msg.setSubject("Your new password..");
				msg.setText(msgBody);
				Transport.send(msg);
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}

			return SUCCESS;
		} else {
			addActionError("Invalid User name or email Id");
			return ERROR;
		}
	}
}
