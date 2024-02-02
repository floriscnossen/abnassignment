package nl.workingtalent.abnassignment.dto;

import java.time.LocalDate;

public class CountryRequestDto {
	private String countryCode;
	private String name;
	private boolean allowed;

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
	public boolean isAllowed() {
		return allowed;
	}
	public void setAllowed(boolean allowed) {
		this.allowed = allowed;
	}
}
