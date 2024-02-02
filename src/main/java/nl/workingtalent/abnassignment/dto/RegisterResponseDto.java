package nl.workingtalent.abnassignment.dto;

public class RegisterResponseDto {
	private boolean success;
	private String username;
	private String password;
	private String message;
	
	// Constructors
	public RegisterResponseDto(String username, String password) {
		super();
		this.success = true;
		this.username = username;
		this.password = password;
	}
	public RegisterResponseDto(String message) {
		super();
		this.success = false;
		this.message = message;
	}
	
	// Getters and setters
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
