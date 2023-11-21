package com.example.Dodot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.Dodot.domain.AppUser;
import com.example.Dodot.domain.AppUserRepository;
import com.example.Dodot.domain.Customer;
import com.example.Dodot.domain.CustomerRepository;
import com.example.Dodot.domain.Status;
import com.example.Dodot.domain.StatusRepository;

@SpringBootApplication
public class DodotApplication {
	private static final Logger log = LoggerFactory.getLogger(DodotApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DodotApplication.class, args);
	}
@Bean
public CommandLineRunner demo(CustomerRepository repository, StatusRepository srepository, AppUserRepository urepository) {
	return (args) -> {
		log.info("save a couple of customers");
		srepository.save(new Status("In progress"));
		srepository.save(new Status("Done"));
		srepository.save(new Status("Not possible"));
		
		repository.save(new Customer("John", "Malkovich", "Vantaa", "Jmal@yahoo.com", "0418029540","broken display", 195.50, srepository.findByPcondition("In progress").get(0)));
		repository.save(new Customer("Benjamin", "Linus", "Espoo", "BLin@yahoo.com", "0456465464","OS reinstall", 80.00, srepository.findByPcondition("Done").get(0)));
		repository.save(new Customer("Ann", "Robinson", "Helsinki", "annr@yahoo.com", "0456464477","keyboard replacement", 145.50,srepository.findByPcondition("Not possible").get(0)));
		repository.save(new Customer("Frank", "Stewart", "Kerava", "Sfrankn@yahoo.com", "0456465464","maintenance", 98.00, srepository.findByPcondition("Done").get(0)));
		AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
		AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
		//urepository.save(user1);
		//urepository.save(user2);
		
		log.info("fetch all customers");
		for (Customer customer : repository.findAll()) {
			log.info(customer.toString()); 
		}
	};
	}
}


