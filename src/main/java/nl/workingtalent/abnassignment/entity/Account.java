package nl.workingtalent.abnassignment.entity;

import java.time.LocalDate;
import java.util.Arrays;
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
	
	@OneToOne(mappedBy = "account")
	private Customer customer;
	
	// Constructors
	public Account() { super(); }

	public Account(long accountNumber, String accountType, long balance, String currency) {
		super();
		this.iban = calculateIban(accountNumber);
		this.accountType = accountType;
		this.balance = balance;
		this.currency = currency;
	}

	//Getters and setters
	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	/*
	 * Calculate and update the IBAN number based on an account number.
	 */
	public static String calculateIban(long accountNumber) {
		String countryCode = "NL";
		String bankCode = "XYZB";
		
		int checkDigits = calculateCheckDigits(countryCode, bankCode, accountNumber);
		
		return String.format("NL%02d %s %10d", checkDigits, bankCode, accountNumber);
	}
	
	public static int calculateCheckDigits(String countryCode, String bankCode, long accountNumber) {
		String string = String.format("%s%010d%s00", bankCode, accountNumber, countryCode);
		int remainder = 0;
		for (char c : string.toCharArray()) {
			if (Character.isLetter(c)) {
				remainder = (remainder*3 + (c - 'A' + 10)) % 97;
			}
			else {
				remainder = (remainder*10 + (c - '0')) % 97;
			}
		}
		return 98 - remainder;
	}
}
