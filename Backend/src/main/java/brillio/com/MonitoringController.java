package brillio.com;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitoringController {
@Autowired
	private MonitoringService service;
	
@PostMapping("/register")
	public String newCheckRegister(@RequestBody MonitoringEntity e) {
		
		return service.registerChecks(e);
}
		@GetMapping("/get")
		public List<MonitoringEntity> getLists() throws IOException{
		
		return service.getData();
		
	}
		@GetMapping("/byid/{id}")
	public 	MonitoringEntity getById(@PathVariable long id ){
		
			return service.getById(id); 
			
		}
}
