package com.example.Dodot.web;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Dodot.domain.Customer;
import com.example.Dodot.domain.CustomerRepository;
import com.example.Dodot.domain.StatusRepository;




@Controller
public class CustomerController {
	@Autowired
    private CustomerRepository repository;
	 @Autowired
	 private StatusRepository srepository;
	 @RequestMapping(value="/login")
	    public String login() {	
	        return "login";
	    }	
	 @RequestMapping("/home")
	 public String customersList(Model model, @RequestParam(required = false, defaultValue = "id") String sort) {
	     Sort.Order order = new Sort.Order(Sort.Direction.ASC, sort);
	     Sort sortObj = Sort.by(order);

	     List<Customer> customers = repository.findAll(sortObj);
	     double totalSales = customers.stream().mapToDouble(Customer::getInvoice).sum();
	     int totalEntries = customers.size();
	     
	     // Count the entries with status "Not possible"
	     long notPossibleCount = customers.stream().filter(customer -> "Not possible".equals(customer.getStatus().getPcondition())).count();

	     // Calculate the percentage
	     double notPossiblePercentage = ((double) notPossibleCount / totalEntries) * 100;
	     
	     model.addAttribute("customers", customers);
	     model.addAttribute("totalSales", totalSales); // Add total sales to the model
	     model.addAttribute("totalEntries", totalEntries);
	     model.addAttribute("notPossiblePercentage", notPossiblePercentage);
	     return "home";
	 }
	
	@RequestMapping(value = "/showInProgress", method = RequestMethod.GET)
	public String showCustomersInProgress(Model model) {
	    List<Customer> customersInProgress = repository.findByStatusPcondition("In progress");
	    model.addAttribute("customers", customersInProgress);
	    return "home"; 
	}
	@RequestMapping(value = "/showDone", method = RequestMethod.GET)
	public String showCustomersDone(Model model) {
	    List<Customer> customersDone = repository.findByStatusPcondition("Done");
	    model.addAttribute("customers", customersDone);
	    return "home"; 
	}
	@RequestMapping(value = "/showNotPossible", method = RequestMethod.GET)
	public String showCustomersNotPossible(Model model) {
	    List<Customer> customersNotPossible = repository.findByStatusPcondition("Not possible");
	    model.addAttribute("customers", customersNotPossible);
	    return "home"; 
	}

	
	@RequestMapping("/add")
    public String addCustomer(Model model) {
       model.addAttribute("customer", new Customer());
       model.addAttribute("statuses", srepository.findAll());
       return "addcustomer";
    }	
	
	
	
	   @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String save(Customer customer){
	        repository.save(customer);
	        return "redirect:home";
	    }  
	   
	   
	   @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	    public String editCustomer(@PathVariable("id") Long customerId, Model model) {
	    	model.addAttribute("customer", repository.findById(customerId));
	    	model.addAttribute("statuses", srepository.findAll());
	    	return "editcustomer";
	   
	    }
	   
	   
	 @PreAuthorize("hasAuthority('ADMIN')")
	 @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	    public String deleteCustomer(@PathVariable("id") Long customerId) {
	        repository.deleteById(customerId);
	        return "redirect:/home";
	    }
}
