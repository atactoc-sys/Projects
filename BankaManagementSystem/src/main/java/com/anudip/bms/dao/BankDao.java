package com.anudip.bms.dao;

import java.util.List;

import com.anudip.bms.entity.Bank;
import com.anudip.bms.entity.Employee;
import com.anudip.bms.entity.Customer;

public interface BankDao 
{
	// to create new admin account
	boolean createAdmin(Bank bamk);

	// to fetch all users account details
	List<Customer> fetchAllUsers(String username, String password);

	// to fetch all employees account details
	List<Employee> fetchAllEmployees(String username, String password);

	// to update admin account details
	Bank changePassword(String username, String password, Bank bank);

	// to delete existing admin account
	boolean deactivateAccount(String username, String password);

	// to delete existing user account
	boolean deleteUser(String username, String password, int accountNo);

	// to delete existing employee account
	boolean deleteEmployee(String adminUsername, String adminPassword, String empUsername);
}


