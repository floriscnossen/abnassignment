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
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 100)
	private String name;
	
	@Column(nullable = true, length = 100)
	private String adress;
	
	@Column(length = 3)
	private String countryCode;

	@Column(nullable = false)
	private LocalDate birthdate;
	
	@Column(nullable = true)
	private String idDocument;
	
	@Column(nullable = false, length = 100, unique = true)
	private String username;
	
	@Column(nullable = false, length = 100)
	private String password;

	@OneToMany(mappedBy = "customer")
	private List<Login> logins;
	
	@OneToOne
	@JoinColumn
	private Account account;
	
	// Constructors
	public Customer() { super(); }

	public Customer(String name, String adress, String countryCode, LocalDate birthdate, String idDocument, 
			String username, Account account) {
		super();
		this.name = name;
		this.adress = adress;
		this.countryCode = countryCode;
		this.birthdate = birthdate;
		this.idDocument = idDocument;
		this.username = username;
		this.password = RandomStringUtils.randomAlphanumeric(14);
		this.account = account;
	}
	
	// Getters and setters

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public String getIdDocument() {
		return idDocument;
	}

	public void setIdDocument(String idDocument) {
		this.idDocument = idDocument;
	}

	public List<Login> getLogins() {
		return logins;
	}

	public void setLogins(List<Login> logins) {
		this.logins = logins;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
