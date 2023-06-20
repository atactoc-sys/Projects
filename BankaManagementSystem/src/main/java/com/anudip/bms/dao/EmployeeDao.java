package com.anudip.bms.dao;

import java.util.List;

import com.anudip.bms.entity.Employee;
import com.anudip.bms.entity.Customer;

public interface EmployeeDao 
{
	// for creating new Employee account
		boolean createEmp(Employee employee);
		
		// for fetching admin account details
		List<Customer> fetchAllUsers(String username, String password);
		
		// for updating Employee account details
		Employee changePassword(String username, String password,Employee employee);
		
		// for deleting existing Employee account
		boolean deactivateAccount(String username, String password);
}
