package nl.workingtalent.abnassignment.entity;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 100)
	private String name;
	
	@Column(nullable = true, length = 100)
	private String adress;
	
	private LocalDate birthdate;
	
	//TODO
	@Column(nullable = true, length = 100)
	private String idDocument;
	
	@Column(nullable = false, length = 100, unique = true)
	private String username;
	
	@Column(nullable = false, length = 100)
	private String password;

	@OneToMany(mappedBy = "customer")
	private List<Login> logins;
	
	@OneToOne(mappedBy = "customer")
	private Account account;
	
	// Constructors
	public Customer() {}

	public Customer(String name, String adress, LocalDate birthdate, String idDocument, String username) {
		super();
		this.name = name;
		this.adress = adress;
		this.birthdate = birthdate;
		this.idDocument = idDocument;
		this.username = username;
		this.password = RandomStringUtils.randomAlphanumeric(10);
	}
	
	//Getters and setters

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
