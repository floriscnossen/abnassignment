package nl.workingtalent.abnassignment.dto;

public class TokenResponseDto {
	private boolean success;
	private String token;
	private String message;
	
	// Constructors
	public TokenResponseDto(boolean success, String value) {
		super();
		this.success = success;
		if (success) {
			this.token = value;
		}
		else {
			this.message = value;
		}
	}
	
	// Getters and setters
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
