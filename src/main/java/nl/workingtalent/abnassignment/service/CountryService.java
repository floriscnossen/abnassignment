package nl.workingtalent.abnassignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import nl.workingtalent.abnassignment.entity.Country;
import nl.workingtalent.abnassignment.repository.CountryRepository;

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
