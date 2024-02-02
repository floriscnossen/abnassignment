package nl.workingtalent.abnassignment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import nl.workingtalent.abnassignment.entity.Country;
import nl.workingtalent.abnassignment.entity.Customer;
import nl.workingtalent.abnassignment.entity.Login;
import nl.workingtalent.abnassignment.repository.AccountRepository;
import nl.workingtalent.abnassignment.repository.CountryRepository;
import nl.workingtalent.abnassignment.repository.CustomerRepository;
import nl.workingtalent.abnassignment.repository.LoginRepository;

@Service
public class CountryService {
	@Autowired
	CountryRepository countryRepository;
	
	public Country addOrUpdateCountry(Country country) {
		return countryRepository.save(country);
	}
	
	/*
	 * Insert the Netherlands and Belgium in the list of allowed countries for registration.
	 */
	@PostConstruct
	public void allowNeterlandsAndBelgium() {
		countryRepository.save(new Country("NLD", "The Netherlands", true));
		countryRepository.save(new Country("BEL", "Belgium", true));
	}
}
