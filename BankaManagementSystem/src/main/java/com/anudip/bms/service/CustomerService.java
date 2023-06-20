package com.anudip.bms.service;

import com.anudip.bms.entity.Customer;

public interface CustomerService 
{
	// for creating new user account
		void createAccount(Customer user);
		
		// for fetching user account details
		Customer balanceInquiry(int accountNo, int pin);
		
		// for updating user account details
		Customer deposite(int accountNo , int pin, Customer user);
		
		// for updating user account details
		Customer withdrawal(int accountNo, int pin, Customer user);

		// for updating user account details
		Customer changePin(int accountNo, int pin, Customer user);

		// for deleting existing user account
		void deleteAccount(int accountNo, int pin);
}
