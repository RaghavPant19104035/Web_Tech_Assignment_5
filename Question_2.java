import java.time.YearMonth;

class Customer {

	String firstName;
	String lastName;
	boolean isMale;
	int age;
    String phoneNumber;
	String address;
	
	Customer(String firstName, String lastName, boolean isMale, int age, String phoneNumber, String Address) { // Customer constructor for initializing the object
		this.firstName = firstName;
		this.lastName = lastName;
		this.isMale = isMale;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
}

class Account extends Customer {
	private String CIF_No;  // CIF Number
	private String bankName;
	private double balance;
	private int yearOfOpening;
	
	Account(String firstName, String lastName, boolean isMale, int age,
			      String phoneNumber, String address, String CIF_No, String bankName) { // account constructor for initializing the account object
		super(firstName, lastName, isMale, age, phoneNumber, address); // calling super for initializing the parent constructor 
		this.CIF_No = CIF_No;
		this.bankName = bankName;
		this.yearOfOpening = YearMonth.now().getYear(); // year of opening the account
		
		if(bankName.equals("SBI")) {
			this.balance = SBI.minBalanceAllowed;
		}
		else if(bankName.equals("ICICI")) {
			this.balance = ICICI.minBalanceAllowed;
		}
		else if(bankName.equals("PNB")) {
			this.balance = PNB.minBalanceAllowed;
		}
		else {
			System.out.println("ERROR : Wrong bank name entered");
		}
		
		this.balanceTillDate();
	}
	
	public void deposit(double amount) {
		balance += amount;    // adding the amount to the balance while depositing
	}
	
	public void withdrawal(double amount, double minBalanceRequired, double maxWithdrawal) {
		if(amount > balance - minBalanceRequired || amount > maxWithdrawal) {
			System.out.println("ERROR : " + amount + " amount cannot be withdrawn");
		}
		else {
			balance -= amount;  // subtracting amount from the balance while withdrawing
		}
	}
	
	public void balanceTillDate() {
		
		double interestRate = 0; // declaring the interest rate
		if(bankName.equals("SBI")) {
			interestRate = SBI.interestRate;
		}
		else if(bankName.equals("ICICI")) {
			interestRate = ICICI.interestRate;
		}
		else if(bankName.equals("PNB")) {
			interestRate = PNB.interestRate;
		}
		
		this.balance += (interestRate * this.balance / 100) * (2023 - yearOfOpening); // getting balance according to simple interest
	}
	
	public void displayDetailAccount() { // details of the account
		System.out.println("------ ACCOUNT DETAILS -----------");
		System.out.println(this.bankName + " BANK");
		System.out.println("CIF number : " + this.CIF_No);
		System.out.println("Name : " + this.firstName + " " + this.lastName);
		System.out.println("Account balance : " + this.balance);
		System.out.println("-----------------------------------");
	}
	
}

class RBI {
	final static double minInterestRate = 0.04; // final static because they are constant are not dependent on the object
	final static double minBalanceAllowed = 3000;
	final static double maxWithdrawalLimit = 200000;
	
}

class SBI extends RBI {
	
	final static double interestRate = minInterestRate + 2; // SBI interest rate
	
	public void deposit(double amount, Account account) { 
		account.deposit(amount); // deposit function of account class is called for adding deposit to the balance
	}
	
	public void withdrawal(double amount, Account account) {
		account.withdrawal(amount, minBalanceAllowed, maxWithdrawalLimit); // withdrawal function of account class is called for withdrawing money from the balance
	}
	
}

class ICICI extends RBI {
    final static double interestRate = minInterestRate + 1.2; // ICICI interest rate
	
	public void deposit(double amount, Account account) {
		account.deposit(amount); // deposit function of account class is called for adding deposit to the balance
	}
	
	public void withdrawal(double amount, Account account) {
		account.withdrawal(amount, minBalanceAllowed, maxWithdrawalLimit); // withdrawal function of account class is called for withdrawing money from the balance
	}
}

class PNB extends RBI {
    final static double interestRate = minInterestRate + 1.7; // PNB interest rate
    
	public void deposit(double amount, Account account) {
		account.deposit(amount); // deposit function of account class is called for adding deposit to the balance
	}
	
	public void withdrawal(double amount, Account account) {
		account.withdrawal(amount, minBalanceAllowed, maxWithdrawalLimit); // withdrawal function of account class is called for withdrawing money from the balance
	}
	
}
public class Question_2 {
	
	public static void main(String[] args) {
		
		SBI sbi = new SBI();
		ICICI icici = new ICICI();
		PNB pnb = new PNB();
		
		// accounts are created by calling parameterized constructor
		Account account1 = new Account("Raghav", "Pant", true, 21, "9501905560", "Sector - 12, Chandigarh", "521234123452", "SBI");
		Account account2 = new Account("Michael", "Scott", true, 45, "9423432443", "Mumbai Bandra", "124562456987", "ICICI");
		Account account3 = new Account("Jessica", "Christian", false, 23, "9324128990", "Navi Mumbai", "435783236713", "PNB");
		
		account1.deposit(500000); // depositing 5 lakh in account 1
		sbi.withdrawal(50000, account1); // withdrawing 50 thousand in account 1
		account1.displayDetailAccount(); // displaying details of account 1
		
		System.out.println();
		icici.deposit(1000000, account2); // depositing 10 lakh in account 2
		account2.displayDetailAccount(); // displaying details of account 2
		
		System.out.println();
		pnb.deposit(50000, account3); // depositing 50 thousand in account 3
		account3.displayDetailAccount(); // displaying details of account 3
	}
}
