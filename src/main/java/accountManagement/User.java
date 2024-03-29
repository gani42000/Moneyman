package accountManagement;

class User {
	private String username;
	private Bank bank;
	private String password; 
	
	//Getters 
	
	public String getUsername() {
		return this.username;
	}
	
	public Bank getBank() {
		return this.bank;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	//Setters
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
