package brillio.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class StatusMailSender {
	
	@Autowired
private JavaMailSender mail;
	
	
	public void sendMail(String toEmail,String subject,String body) {
		
		
		SimpleMailMessage message=new SimpleMailMessage();
		
		message.setFrom("vikashmishra123174@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		
		mail.send(message);
	}
	
	
}
