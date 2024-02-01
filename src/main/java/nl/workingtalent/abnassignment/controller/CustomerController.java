package nl.workingtalent.abnassignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import nl.workingtalent.abnassignment.entity.Customer;
import nl.workingtalent.abnassignment.entity.Login;
import nl.workingtalent.abnassignment.service.CustomerService;

@RestController
@CrossOrigin
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	@PostMapping("register")
	public RegisterResponseDto register(@RequestBody RegisterRequestDto registerRequestDto) {
		System.out.println("Test");
		Customer customer = customerService.addCustomer(toEntity(registerRequestDto));
		return new RegisterResponseDto(customer.getUsername(), customer.getPassword());
	}
	
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
	
	@GetMapping("logon")
	public LogonResponseDto logon(HttpServletRequest request) {
		Login login = (Login) request.getAttribute("XYZ_LOGIN");
		if (login == null) {
			return new LogonResponseDto(false, "Not logged in");
		}
		return new LogonResponseDto(true, "Logged in with username: " + login.getCustomer().getUsername());
	}
	
	@GetMapping("overview")
	public OverviewResponseDto getCustomerOverview(HttpServletRequest request) {
		Login login = (Login) request.getAttribute("XYZ_LOGIN");
		if (login == null) {
			return new OverviewResponseDto("Not logged in");
		}
		return new OverviewResponseDto("NL32 XYZB 1234 5678 90", "CHECKING", 0, "Euro");
	}
	
	private Customer toEntity(RegisterRequestDto registerDto) {
		Customer customer = new Customer(registerDto.getName(), registerDto.getAdress(), registerDto.getBirthdate(), 
				registerDto.getIdDocument(), registerDto.getUsername());
		return customer;
	}
}