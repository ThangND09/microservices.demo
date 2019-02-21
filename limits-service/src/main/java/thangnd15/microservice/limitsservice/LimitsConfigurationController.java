package thangnd15.microservice.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {
	
	@Autowired
	private Configuration configuration;

	@GetMapping("/limits")
	public Configuration retrieveLimitsFromConfigurations() {
		return new Configuration(configuration.getMinimum(), configuration.getMaximum());
	}
	
}
