package lt.mackelo.backend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		//SpringApplication.run(BackendApplication.class, args);
		SpringApplication application = new SpringApplication(BackendApplication.class);
		application.setAdditionalProfiles("ssl");
		application.run(args);
	}
}
