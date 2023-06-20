package com.anudip.bms.service;

import java.util.List;

import com.anudip.bms.entity.Employee;
import com.anudip.bms.entity.Customer;
import com.anudip.bms.exception.GlobalException;

public interface EmployeeService 
{
void createEmp(Employee emp);
	
	// for fetching admin account details
	List<Customer> fetchAllUsers(String username, String password) throws GlobalException;
	
	// for updating Employee account details
	Employee changePassword(String username, String password, Employee emp);
	
	// for deleting existing Employee account
	void deactivateAccount(String username, String password);
}
