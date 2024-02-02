package nl.workingtalent.abnassignment.entity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Country {
	@Id
	private String countryCode;
	
	private String name;
	
	private boolean registerAllowed;
	
	// Constructors
	public Country() { super(); }

	public Country(String countryCode, String name, boolean registerAllowed) {
		super();
		this.countryCode = countryCode;
		this.name = name;
		this.registerAllowed = registerAllowed;
	}

	// Getters and setters
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isRegisterAllowed() {
		return registerAllowed;
	}

	public void setRegisterAllowed(boolean registerAllowed) {
		this.registerAllowed = registerAllowed;
	}
}
