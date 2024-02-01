package nl.workingtalent.abnassignment.entity;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Account {
	@Id
	private String iban;
	
	@Column(nullable = true, length = 100)
	private String accountType;

	@Column(nullable = true, length = 100)
	private long balance;
	
	@Column(nullable = true, length = 100)
	private String currency;
	
	@OneToOne
	@JoinColumn
	private Customer customer;
	
	
	
	// Constructors
	public Account() {}
	
	//Getters and setters
}
