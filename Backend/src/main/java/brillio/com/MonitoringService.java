package brillio.com;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


@Service
public class MonitoringService {
	@Autowired
	private MonitoringRepository repo;
	@Autowired
	private StatusMailSender s;
	MonitoringEntity e;
	
	String registerChecks(MonitoringEntity e) {
		repo.save(e);
		return "Checks Registered Successfully";
	}
	int count=0;
	long start=0;
	long timeElapsed=0;
	@Scheduled(fixedDelay = 5000)
public List<MonitoringEntity> getData() throws IOException{
	List<MonitoringEntity> k=repo.findAll();
	
	
	
	for(MonitoringEntity i: k) {
	if(i.getIsActive()==true) {
		if(MonitoringService.isAccessable(i.getUrl())==200) {
			count++;
			if(count==1) {
				s.sendMail("vikashmishra123174@gmail.com", "Website is UP", "Please do the needful website is UP");
			long finish = System.currentTimeMillis();
			System.out.println(System.currentTimeMillis()/1000); 
			 timeElapsed = (finish - start)/1000;
			System.out.println("====================timeElapsed==================" +timeElapsed);}
			i.setStatus("UP");
            i.setFrequency(timeElapsed);
			
		}else  {
			i.setStatus("DOWN");
			 start = System.currentTimeMillis();
			 i.setFrequency(start);
			 count=0;
		}	
	} else {
		
	
		i.setUrl("");
		i.setWebsiteName("");
	}
	
	}
	
	
	return repo.findAll();
}
public static int isAccessable(String url) throws IOException {
	int code=0;
	try {
	URL url1 = new URL(url);
	HttpURLConnection huc = (HttpURLConnection) url1.openConnection();

	huc.setRequestMethod ("GET");   
	huc.connect() ; 
	 code = huc.getResponseCode();}
	
	
	catch (java.net.UnknownHostException e) {
	      System.out.println("DOWNTIME");  
	}
	catch (Exception e) {
	      System.out.println("DOWNTIME");
	}
	System.out.println(code); 
	return code;
}
public MonitoringEntity getById(long id){
	return repo.findById(id).get();
	
}

}
