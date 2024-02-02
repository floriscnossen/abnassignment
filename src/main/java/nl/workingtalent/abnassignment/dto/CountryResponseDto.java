package nl.workingtalent.abnassignment.dto;

public class CountryResponseDto {
	private boolean success;
	private String message;
	
	// Constructors
	public CountryResponseDto() {
		super();
		success = true;
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
}
