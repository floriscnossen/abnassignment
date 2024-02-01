package nl.workingtalent.abnassignment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.workingtalent.abnassignment.entity.Customer;
import nl.workingtalent.abnassignment.entity.Login;
import nl.workingtalent.abnassignment.repository.CustomerRepository;
import nl.workingtalent.abnassignment.repository.LoginRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	LoginRepository loginRepository;
	
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public String login(String username, String password) {
		Optional<Customer> optionalCustomer = customerRepository.findByUsername(username);
    	if (optionalCustomer.isEmpty()) {
    		return null;
    	}
    	
    	Customer customer = optionalCustomer.get();
    	if (!customer.getPassword().equals(password)) {
    		return null;
    	}
    	
    	Login login = loginRepository.save(new Login(customer));
    	return login.getToken();
	}
}
