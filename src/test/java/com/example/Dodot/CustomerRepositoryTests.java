package com.example.Dodot;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Dodot.domain.AppUser;
import com.example.Dodot.domain.AppUserRepository;
import com.example.Dodot.domain.Customer;
import com.example.Dodot.domain.CustomerRepository;
import com.example.Dodot.domain.Status;
import com.example.Dodot.domain.StatusRepository;

import org.junit.jupiter.api.Test;
//@DataJpaTest

//@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DodotApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTests {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private StatusRepository srepository;
    
    @Autowired
    private AppUserRepository urepository;
    
    @Test
    public void findByTitleShouldReturnBook() {
        List<Customer> customers = repository.findByLastName("Linus");
        
        assertThat(customers).hasSize(1);
        assertThat(customers.get(0).getFirstName()).isEqualTo("Benjamin");
    }
    
    @Test
    public void createNewCustomer() {
    	Status status = new Status("Warranty");
    	srepository.save(status);
    	Customer customer = new Customer("Constantin", "Sarb","Vantaa", "constantin.sarb@yahoo.com", "0441225477", "Laptop Lenovo", 0, status);
    	repository.save(customer);
    	assertThat(customer.getId()).isNotNull();
    }    
    @Test
    public void deleteNewBook() {
		List<Customer> customers = repository.findByLastName("Robinson");
		Customer customer = customers.get(0);
		repository.delete(customer);
		List<Customer> newCustomers= repository.findByLastName("Robinson");
		assertThat(newCustomers).hasSize(0);
     }
 /*   @Test
    public void createNewUser() {
    	AppUser user = new AppUser("costin","$2a$04$ei8ezXyK8LuXOfWOBi39CeKj3mVuOMtM6PiIaxbcvuoSSYfq05U3S", "costin");
    	urepository.save(user);
    	assertThat(user.getId()).isNotNull();
    }  
    */
    @Test
    public void findByNameShouldReturnUser() {
        AppUser user = urepository.findByUsername("admin");
        
        assertThat(user).isNotNull();
        assertThat(user.getPasswordHash()).isEqualTo("$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C");
    }
}
