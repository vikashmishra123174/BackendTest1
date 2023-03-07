package brillio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import brillio.com.StatusMailSender;

@SpringBootApplication
@EnableScheduling
public class BackendApplication {
@Autowired
	StatusMailSender senderservice;
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
		@EventListener(ApplicationReadyEvent.class)
		   public void sendEmal() {
		     senderservice.sendMail("vikashmishra123174@gmail.com", "Website is UP", "Please do the needful website is UP");
		   }
	

}
