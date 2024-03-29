package money;
import java.util.Date;
enum TransactionType {
	INCOOME,EXPESE
};
class Transaction {
	String name;
	Date date;
	float amount;
	TransactionType transactionType;
	
	
	/*
	 * Transaction Grouping is not added yet
	 * Need to be added in getters and setters  
	 * 
	 */
	
	//Getters 
	public String getName() {
		return this.name;
	}
	public Date getDate() {
		return this.date;
	}
	public float getAmount() {
		return this.amount;
	}
	public TransactionType getTransactionType() {
		return this.transactionType;
	}
	
	//Setters
	public void setName(String name) {
		this.name = name;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setAmount(float amount) {
		this.amount = amount; 
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType =  transactionType;
	}
	//Constructor 
	
	Transaction(String name,Date date,float amount,TransactionType transactionType){
		setName(name);
		setDate(date);
		setAmount(amount);
		setTransactionType(transactionType);
	}
	
}
