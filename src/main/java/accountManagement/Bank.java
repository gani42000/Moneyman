package accountManagement;

class Bank {
	private String name;
	private String ifsc;
	private String accNo;
	
	
	//Getters
	public String getName() {
		return this.name;
	}
	
	public String getIfsc() {
		return this.ifsc;
	}
	
	public String getAccNo() {
		return this.accNo;
	}
	
	//Setters 
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	
	public void setAccNO(String accNo) {
		this.accNo = accNo;
	}
}
