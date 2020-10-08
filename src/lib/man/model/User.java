package lib.man.model;

import java.io.Serializable;

public class User implements Serializable{
	
	private static final long serialVersionUID = -1964357865647441722L;
	public static final String STORAGE_TYPE = "USER";
	
	
	private String userName;
	private String password;
	private Role role;
	
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	public User(String userName, String password, Role role) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}	

}
