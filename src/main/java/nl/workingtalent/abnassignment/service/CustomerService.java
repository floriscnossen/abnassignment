package nl.workingtalent.abnassignment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.workingtalent.abnassignment.entity.Country;
import nl.workingtalent.abnassignment.entity.Customer;
import nl.workingtalent.abnassignment.entity.Login;
import nl.workingtalent.abnassignment.repository.AccountRepository;
import nl.workingtalent.abnassignment.repository.CountryRepository;
import nl.workingtalent.abnassignment.repository.CustomerRepository;
import nl.workingtalent.abnassignment.repository.LoginRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	LoginRepository loginRepository;
	
	@Autowired
	CountryRepository countryRepository;
	
	/*
	 * Adds a new customer to the database.
	 */
	public Customer addCustomer(Customer customer) {
		accountRepository.save(customer.getAccount());
		return customerRepository.save(customer);
	}
	
	/*
	 * Logs in a customer and returns a bearer token. Returns null if login credentials are incorrect.
	 */
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
	
	/*
	 * Returns whether a country is allowed for registration.
	 */
	public boolean countryAllowed(String countryCode) {
		Optional<Country> optionalCountry = countryRepository.findById(countryCode);
		return optionalCountry.isPresent() && optionalCountry.get().isRegisterAllowed();
	}
}
