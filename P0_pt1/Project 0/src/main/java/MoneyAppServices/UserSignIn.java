  
package MoneyAppServices;



import MoneyAppPojos.User;

public interface UserSignIn {
	
	//Create User if retrieve User is false
	public User createUser(String firstNameLastName, String username, String password, String email, String phoneNum);	
	
	//Sign in user if password is successful
	public boolean signIn(String username, String password);

}
