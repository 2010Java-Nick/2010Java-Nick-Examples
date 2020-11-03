package MoneyAppPojos;

public class User {
	
	private String username;
	
	private String password; //Maybe Hash?
	
	private String email;
	
	private String phoneNum;
	
	private String firstNameLastName;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Creation of user obj
	 * @param firstNameLastName
	 * @param username
	 * @param password
	 * @param email
	 * @param phoneNum
	 */
	public User( String firstNameLastName,String username, String password, String email, String phoneNum) {
		this();
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNum = phoneNum;
		this.firstNameLastName = firstNameLastName;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public String getFirstNameLastName() {
		return firstNameLastName;
	}

	public void setFirstNameLastName(String firstNameLastName) {
		this.firstNameLastName = firstNameLastName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstNameLastName == null) ? 0 : firstNameLastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phoneNum == null) ? 0 : phoneNum.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstNameLastName == null) {
			if (other.firstNameLastName != null)
				return false;
		} else if (!firstNameLastName.equals(other.firstNameLastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phoneNum == null) {
			if (other.phoneNum != null)
				return false;
		} else if (!phoneNum.equals(other.phoneNum))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", email=" + email + ", phoneNum=" + phoneNum
				+ ", firstNameLastName=" + firstNameLastName + "]";
	}
	
	
	
	
	

}
