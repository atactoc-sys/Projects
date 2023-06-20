package com.anudip.bms.service;

import java.util.List;

import com.anudip.bms.entity.Bank;
import com.anudip.bms.entity.Employee;
import com.anudip.bms.entity.Customer;
import com.anudip.bms.exception.GlobalException;

public interface BankService 
{
	// for creating new admin account
		void createAdmin(Bank admin);
		
		// for fetching all users account details
		List<Customer> fetchAllUsers(String username, String password) throws GlobalException;
		
		// for fetching all employees account details
		List<Employee> fetchAllEmployees(String username, String password) throws GlobalException;
		
		// for updating admin account details
		Bank changePassword(String username, String password, Bank admin);
		
		// for deleting existing admin account
		void deactivateAccount(String username, String password);
		
		// for deleting existing user account
		void deleteUser(String username, String password, int accountNo);
		
		// for deleting existing employee account
		void deleteEmployee(String adminUsername, String adminPassword, String empUsername);
}
