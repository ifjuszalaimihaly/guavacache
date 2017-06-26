package guavaproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import guavaproject.service.GuavaService;

@SpringBootApplication
public class Application {
	
	
	public static void main(String[] args) {
		//run the application
		SpringApplication.run(Application.class, args);		 
	}

}
