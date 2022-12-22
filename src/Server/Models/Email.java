package Server.Models;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	final String username = "tranduykhuongit@gmail.com";
	final String password = "dqkxbrhkgefagusu";
	String from;
	String to;

	public Email(String from, String to) {
		this.from = from;
		this.to = to;
	}

	public boolean sendEmail(String subject, String content) {
		try {
			Properties prop = new Properties();
			prop.put("mail.smtp.host", "smtp.gmail.com");
			prop.put("mail.smtp.port", "587");
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.starttls.enable", "true"); // TLS

			prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

//			prop.put("mail.smtp.starttls.required", "true");
//			prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

			Session session = Session.getInstance(prop, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(this.from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.to));

			message.setSubject(subject);
			message.setContent(content, "text/html");

			Transport.send(message);

			System.out.println("Done");
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
}
