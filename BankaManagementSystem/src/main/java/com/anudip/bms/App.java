package com.anudip.bms;

import java.util.List;
import java.util.Scanner;

import org.hibernate.internal.build.AllowSysOut;

import com.anudip.bms.entity.Bank;
import com.anudip.bms.entity.Employee;
import com.anudip.bms.entity.Customer;
import com.anudip.bms.service.BankService;
import com.anudip.bms.service.EmployeeService;
import com.anudip.bms.service.CustomerService;
import com.anudip.bms.serviceImpl.BankServiceImpl;
import com.anudip.bms.serviceImpl.EmployeeServiceImpl;
import com.anudip.bms.serviceImpl.CustomerServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{

	public static void mainMenu() {
		while(true) {

			//User Menu list
			System.out.println("--------------------------------------------------------");
			System.out.println("                        MAIN MENU                       ");
			System.out.println("--------------------------------------------------------");
			System.out.println("Enter 1. -> Admin Menu\nEnter 2. -> Employee Menu\nEnter 3. -> User Menu\nEnter 4. -> Exit");
			System.out.println("--------------------------------------------------------");

			Scanner scanner = new Scanner(System.in);
			int a = scanner.nextInt();
			switch (a) 
			{
			
			case 1: crudAdmin();
			break;

			case 2: crudEmp();
			break;

			case 3: crudUser();
			break;

			case 4: System.exit(0);
			break;

			default: 
				System.out.println("Invalid choice!");
				break;

			}//end switch case
			scanner.close();
		}//end while loop
	}//end mainMenu()


	// instantiating polymorphic service objects
	static BankService serviceAdmin = new BankServiceImpl();
	static EmployeeService serviceEmp = new EmployeeServiceImpl();
	static CustomerService serviceUser = new CustomerServiceImpl();

	//crud operations for admin
	public static void crudAdmin() {

		while(true) {

			//Admin Menu list
			System.out.println("--------------------------------------------------------");
			System.out.println("                       ADMIN MENU                       ");
			System.out.println("--------------------------------------------------------");
			System.out.println("Enter 1. -> Create Admin A/c \n Enter 2. -> All Users Detail \nEnter 3. -> All Employees Detail \nEnter 4. -> Change Password \nEnter 5. -> Deactivate Account \nEnter 6. -> Delete User A/c \nEnter 7. -> Delete Employee A/c \nEnter 8. -> Go to Main-Menu \nEnter 9. -> Exit");
			System.out.println("--------------------------------------------------------");

			Scanner scanner = new Scanner(System.in);
			int b = scanner.nextInt();
			switch (b) 
			{
			case 1: 
				//creating admin object reference
				Bank admin = new Bank();
				//setting all details into admin using user-input
				System.out.println("Enter admin's first name...");
				admin.setFname(scanner.next());
				System.out.println("Enter admin's last name...");
				admin.setLname(scanner.next());
				System.out.println("Enter admin's username name...");
				admin.setUsername(scanner.next());
				System.out.println("Enter a password (max length 15)");
				admin.setPassword(scanner.next());
				//invoking create method to create account of admin
				serviceAdmin.createAdmin(admin);
				break;

			case 2:	//fetchAllUsers()
				try {
					System.out.println("Enter your username\nEnter your password");
					String fuUsername = scanner.next();
					String fuPassword = scanner.next();
					List<Customer> users = serviceAdmin.fetchAllUsers(fuUsername, fuPassword);
					//displaying the users list
					System.out.println("--------------------------------------------------------");
					for (Customer bankUser : users) {
						System.out.println("A/c number	: "+bankUser.getAccountNo()+"\nA/c holder	: "+bankUser.getAccountHolder()+"\nA/c Type	: "+bankUser.getAccountType()+"\nBranch		: "+bankUser.getBranch()+"\nContact		: "+bankUser.getContact()+"\nEmail		: "+bankUser.getEmail());
						System.out.println("--------------------------------------------------------");
					}
					System.out.println("--------------------------------------------------------");
					System.out.println("Done");

				}catch(Exception e) {
					e.printStackTrace();
				}
				break;	

			case 3:	//fetchAllEmps()
				try {
					System.out.println("Enter your username\nEnter your password");
					String feUserName = scanner.next();
					String feuPassword = scanner.next();
					List<Employee> emps = serviceAdmin.fetchAllEmployees(feUserName, feuPassword);
					//displaying the employees list
					System.out.println("--------------------------------------------------------");
					for (Employee bankEmp : emps) {
						System.out.println("First name	: "+bankEmp.getFname()+"\nLast name	: "+bankEmp.getLname()+"\nUsername	: "+bankEmp.getUsername());																												
						System.out.println("--------------------------------------------------------");
					}
					System.out.println("--------------------------------------------------------");
					System.out.println("Done");

				}catch(Exception e) {
					e.printStackTrace();
				}
				break;	

			case 4:	//changePassword()
				Bank cAdmin = new Bank();
				System.out.println("Enter your new password (max length 15)");
				cAdmin.setPassword(scanner.next());
				//passing user-input into changePassword()
				System.out.println("Enter your username/nEnter your old password");
				String cpuserName = scanner.next();
				String cpoldpass = scanner.next();
				@SuppressWarnings("unused") Bank chngPassAdmin = serviceAdmin.changePassword(cpuserName, cpoldpass, cAdmin);
				System.out.println("Password changed successfully");
				break;	

			case 5:	//deactivateAccount()
				//passing user-input for deleting admin account
				System.out.println("Enter your username\nEnter your password");
				String userdaUsername = scanner.next();
				String daPassword = scanner.next();
				serviceAdmin.deactivateAccount(userdaUsername, daPassword);
				break;	

			case 6:	//deleteUser()
				//passing user-input for deleting user account
				System.out.println("Enter admin username\nEnter admin password\nEnter user account no. ");
				String duUserName = scanner.next();
				String duPassword = scanner.next();
				int accN = scanner.nextInt();
				serviceAdmin.deleteUser(duUserName, duPassword, accN);
				break;	

			case 7:	//deleteEmployee()
				//passing user-input for deleting employee account
				System.out.println("Enter admin username\nEnter admin password\nEnter employee username to delete");
				String deUsername = scanner.next();
				String dePassword = scanner.next();
				String eUseename5 = scanner.next();
				serviceAdmin.deleteEmployee(deUsername, dePassword, eUseename5);
				break;	

			case 8:	//mainMenu()
				mainMenu();
				break;

			case 9: //exit()	
				System.exit(0);

			default:
				System.out.println("Invalid choice!");
			}// switch end
			scanner.close();
		}//while end
	}//crudAdmin end

	//crud operations for employee
	public static void crudEmp() {
		while(true) {

			//User Menu list
			System.out.println("--------------------------------------------------------");
			System.out.println("                       EMPLOYEE MENU                    ");
			System.out.println("--------------------------------------------------------");
			System.out.println("   Enter 1. -> Create Employee A/c  \n   Enter 2. -> All Users Detail     \n   Enter 3. -> Change Password      \n   Enter 4. -> Delete Account       \n   Enter 5. -> Go to Main-Menu      \n   Enter 6. -> Exit                 ");
			System.out.println("--------------------------------------------------------");
			Scanner scanner = new Scanner(System.in);
			int c = scanner.nextInt();
			switch (c) 
		    {
			case 1: 
				//creating employee object reference
				Employee emp = new Employee();
				//setting all details into employee using user-input
				System.out.println("Enter employee's first name");
				emp.setFname(scanner.next());
				System.out.println("Enter employee's last name");
				emp.setLname(scanner.next());
				System.out.println("Enter employee's username name");
				emp.setUsername(scanner.next());
				System.out.println("Enter a password (max length 15)");
				emp.setPassword(scanner.next());
				//invoking create method to create account of employee
				serviceEmp.createEmp(emp);
				break;

			case 2:	//fetchAllUsers()
				try {
					System.out.println("Enter Bank employee username\nEnter Bank employee password");
					String fuUsername1 =scanner.next();
					String fuPassword1 = scanner.next();
					List<Customer> users = serviceEmp.fetchAllUsers(fuUsername1, fuPassword1);
					//displaying the users list
					System.out.println("--------------------------------------------------------");
					for (Customer bankUser : users) {
						System.out.println("A/c number	: "+bankUser.getAccountNo()+"\nA/c holder	: "+bankUser.getAccountHolder()+"\nA/c Type	: "+bankUser.getAccountType()+"\nBranch		: "+bankUser.getBranch()+"\nContact		: "+bankUser.getContact()+"\nEmail		: "+bankUser.getEmail()										
								);
						System.out.println("--------------------------------------------------------");
					}
					System.out.println("--------------------------------------------------------");
					System.out.println("Done");
				}catch(Exception e) {
					e.printStackTrace();
				}

				break;

			case 3:	//changePassword()
				Employee cEmp = new Employee();
				System.out.println("Enter your new password (max length 15)");
				cEmp.setPassword(scanner.next());
				//passing user-input into changePassword()
				System.out.println("Enter Bank employee username \nEnter employee password");
				String cpsername2 = scanner.next();
				String cpPassword2 = scanner.next();
				@SuppressWarnings("unused") Employee chngPassEmp = serviceEmp.changePassword(cpsername2, cpPassword2, cEmp);
				System.out.println("Password changed successfully");
				break;

			case 4:	//deactivateAccount()
				//passing user-input for deleting account
				System.out.println("Enter Bank employee username\nEnter Bank employee password");
				String daUsername2 = scanner.next();
				String daPassword = scanner.next();
				serviceEmp.deactivateAccount(daUsername2, daPassword);
				break;	

			case 5:	//mainMenu()
				mainMenu();
				break;

			case 6: //exit()	
				System.exit(0);

			default:
				System.out.println("Invalid choice!");
			}// switch end
			scanner.close();
		}//while end
	}//crudEmp end

	//crud operations for user
	public static void crudUser() {

		while(true) {	//for looping

			//User Menu list
			System.out.println("--------------------------------------------------------");
			System.out.println("                        USER MENU                       ");
			System.out.println("--------------------------------------------------------");
			System.out.println("   Enter 1. -> Create User Account  \n   Enter 2. -> Check Balance        \n   Enter 3. -> Deposite             \n   Enter 4. -> Withdraw             \n   Enter 5. -> Change Pin           \n   Enter 6. -> Delete Account       \n   Enter 7. -> Go to Main-Menu      \n   Enter 8. -> Exit                 ");
			System.out.println("--------------------------------------------------------");

			Scanner scanner = new Scanner(System.in);
			int d = scanner.nextInt();
			switch (d) {

			case 1: //createAccount()
				//creating user object reference
				Customer newUser = new Customer();
				//setting all details into user using user-input
				System.out.println("Enter an account number (max 10 digits)");
				newUser.setAccountNo(scanner.nextInt());
				System.out.println("Enter account holder name");
				newUser.setAccountHolder(scanner.next());
				System.out.println("Enter account type (current/saving)");
				newUser.setAccountType(scanner.next());
				System.out.println("Enter your bank branch");
				newUser.setBranch(scanner.next());
				System.out.println("Enter your contact number (max 10 digits)");
				newUser.setContact(scanner.nextLong());
				System.out.println("Enter your email id");
				newUser.setEmail(scanner.next());
				System.out.println("Enter your secret pin (max 4 digits)");
				newUser.setPin(scanner.nextInt());
				System.out.println("Deposite any ammount to active the account (minimum amount 100.00)");
				newUser.setBalance(scanner.nextDouble());
				//invoking create method to create account of user
				serviceUser.createAccount(newUser);
				break;

			case 2:	//balanceInquiry()
				//passing user-input into balanceInquiry method 
				System.out.println("Enter your account number");
				System.out.println("Enter your 4 digit pin");
				int beAcn = scanner.nextInt();
				int bePin = scanner.nextInt();
				Customer fetchUser = serviceUser.balanceInquiry(beAcn,bePin);
				System.out.println(fetchUser.getBalance());
				break;

			case 3:	//deposite()
				//passing user-input into balanceInquiry method 
				System.out.println("Enter your account number \nEnter your 4 digit pin");
				int dAcn4 = scanner.nextInt();
				int Dpin4 = scanner.nextInt();
				Customer dUser = new Customer();
				Customer depositeUser = serviceUser.deposite(dAcn4, Dpin4, dUser);
				System.out.println("Enter your deposite amount");
				dUser.setBalance(scanner.nextDouble());
				System.out.println("Current ballance is"+depositeUser.getBalance());
				break;

			case 4:	//withdrawal()
				//passing user-input into withdrawal()
				System.out.println("Enter your account number \nEnter your 4 digit pin \nnter your withdrawal amount");
				int wAccountNo = scanner.nextInt();
				int wPin = scanner.nextInt();
				Customer wUser = new Customer();
				double wAmount = scanner.nextDouble();
				wUser.setBalance(wAmount);
				Customer withdrawUser = serviceUser.withdrawal(wAccountNo, wPin, wUser);
				System.out.println("Current ballance is"+withdrawUser.getBalance());
				break;

			case 5:	//changePin()
				//passing user-input into changePin()
				System.out.println("Enter your account number \nEnter your 4 digit pin \nnter your new pin (max size 4 digits)");
				int cAccountNo = scanner.nextInt();
				int cPin = scanner.nextInt();
				Customer cUser = new Customer();
				cUser.setPin(scanner.nextInt());
				Customer chngPinUser = serviceUser.changePin(cAccountNo, cPin, cUser);
				System.out.println("Pin changed successfully");
				break;

			case 6:	//deleteAccount()
				//passing user-input for deleting account
				System.out.println("Enter your account number \nEnter your 4 digit pin");
				int dltAccountNo = scanner.nextInt();
				int dltPin = scanner.nextInt();
				serviceUser.deleteAccount(dltAccountNo, dltPin);
				break;

			case 7:	//mainMenu()
				mainMenu();
				break;

			case 8: //exit()	
				System.exit(0);

			default:
				System.out.println("Invalid choice!");
			}// switch end
			scanner.close();
		}//while end
	}//crudUser end

	//============================================================================================================

	public static void main( String[] args )
	{
		mainMenu();
	}
}
