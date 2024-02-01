package nl.workingtalent.abnassignment.entity;

import org.apache.commons.lang3.RandomStringUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Login {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 100, unique = true)
	private String token;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Customer customer;
	
	// Constructors
	public Login() {
		super();
	}

	public Login(Customer customer) {
		super();
		this.customer = customer;
		this.token = RandomStringUtils.randomAlphanumeric(100);
	}
	
	//Getters and setters
	
	public Customer getCustomer() {
		return customer;
	}
	
	public String getToken() {
		return token;
	}
}
