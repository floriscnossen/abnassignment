package nl.workingtalent.abnassignment.dto;

public class OverviewResponseDto {
	private boolean success;
	private String message;
	private String iban;
	private String accountType;
	private long balance;
	private String currency;
	
	// Constructors
	public OverviewResponseDto(String message) {
		super();
		this.success = false;
		this.message = message;
	}

	public OverviewResponseDto(String iban, String accountType, long balance, String currency) {
		super();
		this.success = true;
		this.iban = iban;
		this.accountType = accountType;
		this.balance = balance;
		this.currency = currency;
	}
	
	// Getters and setters
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

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
}
