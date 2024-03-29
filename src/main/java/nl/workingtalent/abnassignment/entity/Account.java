package nl.workingtalent.abnassignment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Account {
	@Id
	private String iban;
	
	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	@Column(nullable = true, length = 100)
	private long balance;
	
	@Column(nullable = true, length = 100)
	private String currency;
	
	@OneToOne(mappedBy = "account")
	private Customer customer;
	
	// Constructors
	public Account() { super(); }

	public Account(long accountNumber, AccountType accountType, long balance, String currency) {
		super();
		this.iban = calculateIban(accountNumber);
		this.accountType = accountType;
		this.balance = balance;
		this.currency = currency;
	}

	// Getters and setters
	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
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
	 * The dutch IBAN has the following format: NLcc bbbb #### #### ## where cc are the check digits, 
	 * bbbb is the bank code and #### #### ## is the account number.
	 */
	public static String calculateIban(long accountNumber) {
		String countryCode = "NL";
		String bankCode = "XYZB";
		
		int checkDigits = calculateCheckDigits(countryCode, bankCode, accountNumber);
		
		return String.format("NL%02d %s %10d", checkDigits, bankCode, accountNumber);
	}
	/*
	 * Calculate the check digits in the IBAN based on a country code, a bank code and an account number.
	 * We first put country code and two zeros at the end of the IBAN and convert all letters into numbers 
	 * via the mapping A -> 10, B -> 11, ..., Z -> 35. Then we calculate the remainder of this number modulo 97.
	 * We use modular arithmetic to speed up this process.
	 * Lastly we get the check digits by subtracting this remainder from 98.
	 */
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
