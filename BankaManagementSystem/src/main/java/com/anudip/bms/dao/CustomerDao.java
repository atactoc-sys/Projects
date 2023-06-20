package com.anudip.bms.dao;

import com.anudip.bms.entity.Customer;

public interface CustomerDao 
{
	    // to create new user account
		boolean createAccount(Customer costomer);
		
		// to fetch user account details
		Customer balanceInquiry(int accountNo, int pin);
		
		// to update user account details
		boolean Customer deposite(int accountNo , int pin, Customer customer);
		
		// to update user account details
		boolean Customer withdrawal(int accountNo, int pin, Customer customer);

		// to update user account details
		boolean Customer changePin(int accountNo, int pin, Customer customer);

		// to delete existing user account
		boolean deleteAccount(int accountNo, int pin);
}
