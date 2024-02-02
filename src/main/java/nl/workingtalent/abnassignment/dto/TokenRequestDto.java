package nl.workingtalent.abnassignment.dto;

public class TokenRequestDto {
	private String username;
	private String password;
	
	// Constructors
	public TokenRequestDto() {
		super();
	}
	
	public TokenRequestDto(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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
}
