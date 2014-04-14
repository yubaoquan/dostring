package mypackage.testJavaMail;


import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class Mail2 {

	boolean sended = false;
	Properties props;
	Session session;
	MimeMessage msg;
	Transport transport;
	public void send(String s) {
		try {
			File file = new File("E:/test.png");
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.attachFile(file);
			
			Multipart mp = new MimeMultipart();
		    mp.addBodyPart(mbp1);
		    
			props = new Properties();
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.transport.protocol", "smtp");
			session = Session.getDefaultInstance(props);
			// session.setDebug(true);
			
			msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("1079139460@qq.com"));
			InternetAddress[] address = {new InternetAddress("721731428@qq.com")};
		    msg.setRecipients(Message.RecipientType.TO, address);
		    msg.setSubject(s);
		    
			msg.setContent(mp);
			msg.setSubject("test send file");
			msg.setText(s);
		//	msg.setDataHandler(dh);
			//msg.set
			/*xxxxxxxxxxxxxxxx为发送方邮箱用户名*/
			msg.setFrom(new InternetAddress());
			transport = session.getTransport();
			/*xxxxxxxxxxxxxxxx为发送方邮箱用户名、
			 *yyyyyyyyy为发送方邮箱密码*/
			transport.connect("smtp.qq.com", 25, "1079139460", "103110506");
			transport.sendMessage(msg, new Address[] { new InternetAddress(
					"721731428@qq.com") });
			transport.close();
			sended = true;
		} catch (Exception e) {
		}

	}
	public static void main(String[] args) {
		
		new Mail2().send("2");
		/*String letters = "ABC";
		char[] numbers = { '1', '2', '3' };
		System.out.println(letters + " easy as " + String.valueOf(numbers));
		System.out.println(numbers);
		int a = (int) (15.9);
		System.out.println(a);*/

		/*float foo = -1;

		float foo1 = 1.0;

		float foo2 = 42e1;

		float foo3 = 2.02f;
		float foo4 = 3.03d;
		float foo5 = 0x0123;*/
	}
}
