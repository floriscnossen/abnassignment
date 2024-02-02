package nl.workingtalent.abnassignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.abnassignment.dto.CountryRequestDto;
import nl.workingtalent.abnassignment.dto.CountryResponseDto;
import nl.workingtalent.abnassignment.entity.Country;
import nl.workingtalent.abnassignment.service.CountryService;

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
