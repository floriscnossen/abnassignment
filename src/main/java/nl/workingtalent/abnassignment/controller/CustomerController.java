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
import nl.workingtalent.abnassignment.dto.LogonResponseDto;
import nl.workingtalent.abnassignment.dto.OverviewResponseDto;
import nl.workingtalent.abnassignment.dto.RegisterRequestDto;
import nl.workingtalent.abnassignment.dto.RegisterResponseDto;
import nl.workingtalent.abnassignment.entity.Account;
import nl.workingtalent.abnassignment.entity.AccountType;
import nl.workingtalent.abnassignment.entity.Customer;
import nl.workingtalent.abnassignment.entity.Login;
import nl.workingtalent.abnassignment.service.CustomerService;

@RestController
@CrossOrigin
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	/*
	 * Register a new customer in the system. The customer must be in a country from the allowed list
	 * and must be at least 18 years old.
	 */
	@PostMapping("register")
	public RegisterResponseDto register(@RequestBody RegisterRequestDto registerRequestDto) {
		Customer customer = toEntity(registerRequestDto);
		if (!customerService.countryAllowed(customer.getCountryCode())) {
			return new RegisterResponseDto("You are not allowed to register in your country");
		}
		
		if (Period.between(customer.getBirthdate(), LocalDate.now()).getYears() < 18) {
			return new RegisterResponseDto("You must be at least 18 years old to register");
		}
		try {
			customer = customerService.addCustomer(toEntity(registerRequestDto));
			return new RegisterResponseDto(customer.getUsername(), customer.getPassword());
		}
		catch (DataIntegrityViolationException e) {
			return new RegisterResponseDto("Username '" + customer.getUsername() + "' already exists");
		}
	}
	
	/*
	 * Customer logs in with their username and password and receive a bearer token.
	 */
	@PostMapping("token")
	public TokenResponseDto getToken(@RequestBody TokenRequestDto requestDto) {
		String token = customerService.login(requestDto.getUsername(), requestDto.getPassword());
		if (token == null) {
			return new TokenResponseDto(false, "Wrong username or password");
		}
		else {
			return new TokenResponseDto(true, token);
		}
	}

	/*
	 * Customer logs in with their bearer token and receive whether they are logged in.
	 */
	@GetMapping("logon")
	public LogonResponseDto logon(HttpServletRequest request) {
		Login login = (Login) request.getAttribute("XYZ_LOGIN");
		if (login == null) {
			return new LogonResponseDto(false, "Not logged in");
		}
		return new LogonResponseDto(true, "Logged in with username: " + login.getCustomer().getUsername());
	}
	
	/*
	 * Customer receives an overview of their bank account. This includes their iban, account type, balance and currrency.
	 */
	@GetMapping("overview")
	public OverviewResponseDto getCustomerOverview(HttpServletRequest request) {
		Login login = (Login) request.getAttribute("XYZ_LOGIN");
		if (login == null) {
			return new OverviewResponseDto("Not logged in");
		}
		Account account = login.getCustomer().getAccount();
		return new OverviewResponseDto(account.getIban(), account.getAccountType().toString(), account.getBalance(), 
				account.getCurrency());
	}
	
	/*
	 * Creates a new customer and account entity based on the register request body.
	 */
	private Customer toEntity(RegisterRequestDto registerDto) {
		long accountNumber = new RandomDataGenerator().nextLong(0, 9999999999L);
		Account account = new Account(accountNumber, AccountType.CHECKING, 0, "Euro");
		Customer customer = new Customer(registerDto.getName(), registerDto.getAdress(), registerDto.getCountryCode(),
				registerDto.getBirthdate(), registerDto.getIdDocument(), registerDto.getUsername(), account);
		return customer;
	}
}
