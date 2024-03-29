package accountManagement;

import java.io.Console;

import org.mindrot.jbcrypt.BCrypt;
import java.time.Instant;
import java.util.Scanner;
import java.util.Currency;
import java.util.Locale;


import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.*;

import accountManagement.fileOperation.FileOps;
//import java.security.MessageDigest;

public class CreateAccount {
	User user ;
	Instant createdTime;
	Instant lastModified;
	String filePath;
	Currency curr;

	public static String byCrypt(String password) {
		// Generate a salt for bcrypt
		String salt = BCrypt.gensalt();

		// Hash the password using the generated salt
		String hashedPassword = BCrypt.hashpw(password, salt);

		return hashedPassword;
	}
	
/* ----------------------------------------- Moving from SHA to Bycrypt Hashing ------------------------------------- */

//	 static String sha256(final String base) {
//	    try{
//	        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
//	        final byte[] hash = digest.digest(base.getBytes("UTF-8"));
//	        final StringBuilder hexString = new StringBuilder();
//	        for (int i = 0; i < hash.length; i++) {
//	            final String hex = Integer.toHexString(0xff & hash[i]);
//	            if(hex.length() == 1) 
//	              hexString.append('0');
//	            hexString.append(hex);
//	        }
//	        return hexString.toString();
//	    } catch(Exception ex){
//	       throw new RuntimeException(ex);
//	    }
//	}

	void createAccount() {
		Console console = System.console();
		Scanner sc = new Scanner(System.in);
		Bank bank = new Bank();
		User user = new User();
		System.out.println(
				"/***********************************************NEW ACCOUNT*****************************************/");
		System.out.println("/*/Enter the User Details/*/\n");
		System.out.print("Username:");
		user.setUsername(sc.nextLine());
		
		String pass = null;
		do {
			System.out.print(user.getPassword() == null ? "Password:" : "Retype Password:");
			pass = (console == null) ? byCrypt(sc.nextLine()) : byCrypt(new String(console.readPassword()));
			// set password
			user.setPassword(pass);
			// password Confirmation
			System.out.print("Confirm Password:");
			pass = (console == null) ? sc.nextLine() : new String(console.readPassword());
		}while(!BCrypt.checkpw(pass,user.getPassword()));
		pass = null;
		
		System.out.println("/*/Enter the Bank Details/*/\n");
		System.out.print("Bank name: ");
		bank.setName(sc.nextLine());
		System.out.print("IFSC code (can be left blank) : ");
		bank.setIfsc(sc.nextLine());
		
		System.out.print("Account Number : ");
		bank.setAccNO(sc.nextLine());
		System.out.println(
				"/*/Set Currency (press return if you need to set the system default or provide the IETF BCP 47 language code (eg. en-IN)) : /*/\n");
		String currency = sc.nextLine();
		Locale locale = null;
		try {
			locale = (currency.equals("")) ? Locale.getDefault() : Locale.forLanguageTag(currency);
			curr = Currency.getInstance(locale);
		} catch (Exception e) {
			System.out.println("Invalid Language code using System default");
			locale = Locale.getDefault();
			curr = Currency.getInstance(locale);
		}
		System.out.println("Enter the absolute file path you want to store your details (return if the details need to be stored in current directory):");
		this.filePath = sc.nextLine();
		createdTime =  Instant.now();
		lastModified = Instant.now();
		user.setBank(bank);
		this.user = user;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			
			objectMapper.registerModule(new JavaTimeModule());
			objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			String jsonString = objectMapper.writeValueAsString(this);
			FileOps fileOps = new FileOps();
			fileOps.setFileName(this.user.getUsername()+".json");
			fileOps.setFilePath(this.filePath);
			fileOps.createConnection();
			fileOps.writeToFile(jsonString);
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
		sc.close();
	}

	public static void main(String[] args) {
		CreateAccount cr = new CreateAccount();
		cr.createAccount();
	}

}
