package nl.workingtalent.abnassignment.dto;

public class LogonResponseDto {
	private boolean success;
	private String message;
	
	// Constructors
	public LogonResponseDto(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
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
