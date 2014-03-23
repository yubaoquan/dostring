package mypackage;


import java.io.File;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Mail {

	boolean sended = false;
	Properties props;
	Session session;
	MimeMessage msg;
	Transport transport;
	private InternetAddress[] receiverAddressArray = new InternetAddress[5]; 
	public void send(String s) {
		try {
			receiverAddressArray[0] = new InternetAddress("721731428@qq.com");
			props = new Properties();
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.transport.protocol", "smtp");
			session = Session.getDefaultInstance(props);
			//session.setDebug(true);
			
			msg = new MimeMessage(session);
			String filePath = "E:/中文.txt";
			String fileName = "中文.txt";
			File file = new File(filePath);
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText("mbp1 text");
			mbp1.attachFile(file);
			String encodedFileName = MimeUtility.encodeText(fileName);
			
            mbp1.setFileName(encodedFileName);
            
			Multipart mp = new MimeMultipart();
		    mp.addBodyPart(mbp1);
		    msg.setSubject("test send file");
			msg.setText(s);
			msg.setContent(mp);
			msg.setSentDate(new Date());
			
			/*xxxxxxxxxxxxxxxx为发送方邮箱用户名*/
			msg.setFrom(new InternetAddress("18080922587@163.com"));
			transport = session.getTransport();
			/*xxxxxxxxxxxxxxxx为发送方邮箱用户名、
			 *yyyyyyyyy为发送方邮箱密码*/
			try {
				//transport.connect("smtp.qq.com", 25, "1079139460@qq.com", "103110506");
				transport.connect("smtp.163.com", "18080922587@163.com", "wocao123");
			} catch (AuthenticationFailedException e) {
				System.out.println("login failed");
				System.exit(-1);
			}
			System.out.println("login succeed!");
			
			transport.sendMessage(msg, new Address[] { new InternetAddress(
					"721731428@qq.com") });
			transport.close();
			sended = true;
		} catch (Exception e) {
		}

	}
	public static void main(String[] args) {
		
		new Mail().send("fourth test?");
		
	}
}
