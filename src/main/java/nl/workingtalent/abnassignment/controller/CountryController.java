package nl.workingtalent.abnassignment.controller;

import java.time.LocalDate;
import java.time.Period;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import nl.workingtalent.abnassignment.dto.TokenRequestDto;
import nl.workingtalent.abnassignment.dto.TokenResponseDto;
import nl.workingtalent.abnassignment.dto.CountryRequestDto;
import nl.workingtalent.abnassignment.dto.CountryResponseDto;
import nl.workingtalent.abnassignment.dto.LogonResponseDto;
import nl.workingtalent.abnassignment.dto.OverviewResponseDto;
import nl.workingtalent.abnassignment.dto.RegisterRequestDto;
import nl.workingtalent.abnassignment.dto.RegisterResponseDto;
import nl.workingtalent.abnassignment.entity.Account;
import nl.workingtalent.abnassignment.entity.AccountType;
import nl.workingtalent.abnassignment.entity.Country;
import nl.workingtalent.abnassignment.entity.Customer;
import nl.workingtalent.abnassignment.entity.Login;
import nl.workingtalent.abnassignment.service.CountryService;
import nl.workingtalent.abnassignment.service.CustomerService;

@RestController
@CrossOrigin
public class CountryController {
	@Autowired
	CountryService countryService;
	/*
	 * Adds a country to the list of allowed countries for registration. 
	 * Only an administrator should be allowed to call this request.
	 */
	@PostMapping("country/allow")
	public CountryResponseDto allowCountry(@RequestBody CountryRequestDto countryRequestDto) {
		Country country = new Country(countryRequestDto.getCountryCode(), countryRequestDto.getName(), true);
		
		countryService.addOrUpdateCountry(country);
		return new CountryResponseDto();
	}

	/*
	 * Removes a country from the list of allowed countries for registration. 
	 * Only an administrator should be allowed to call this request.
	 */
	@PostMapping("country/disallow")
	public CountryResponseDto disallowCountry(@RequestBody CountryRequestDto countryRequestDto) {
		Country country = new Country(countryRequestDto.getCountryCode(), countryRequestDto.getName(), false);
		
		countryService.addOrUpdateCountry(country);
		return new CountryResponseDto();
	}
}
