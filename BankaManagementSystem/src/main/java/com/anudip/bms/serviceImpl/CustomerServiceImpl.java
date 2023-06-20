package com.anudip.bms.serviceImpl;

import com.anudip.bms.dao.CustomerDao;
import com.anudip.bms.daoImpl.CustomerDaoImpl;
import com.anudip.bms.entity.Customer;
import com.anudip.bms.service.CustomerService;

public class CustomerServiceImpl implements CustomerService
{

	//instantiating polymorphic dao object
		CustomerDao daoUser = new CustomerDaoImpl();

		@Override
		public void createAccount(Customer user) {
			// TODO Auto-generated method stub
			daoUser.createAccount(user);
			if ((daoUser.createAccount(user)) == true) {
				System.out.println("Account successfully created");
			} else {
				System.out.println("Oups! some thing went wrong check credentials properly");
			}
		}

		@Override
		public Customer balanceInquiry(int accountNo, int pin) {
			// TODO Auto-generated method stub
			Customer returnUser = daoUser.balanceInquiry(accountNo, pin);
			return returnUser;
		}

		@Override
		public Customer deposite(int accountNo, int pin, Customer user) {
			// TODO Auto-generated method stub
			Customer returnUser = daoUser.deposite(accountNo, pin, user);
			return returnUser;
		}

		@Override
		public Customer withdrawal(int accountNo, int pin, Customer user) {
			// TODO Auto-generated method stub
			Customer returnUser = daoUser.withdrawal(accountNo, pin, user);
			return returnUser;
		}

		@Override
		public Customer changePin(int accountNo, int pin, Customer user) {
			// TODO Auto-generated method stub
			Customer returnUser = daoUser.changePin(accountNo, pin, user);
			return returnUser;
		}

		@Override
		public void deleteAccount(int accountNo, int pin) {
			// TODO Auto-generated method stub
			daoUser.deleteAccount(accountNo, pin);
			if ((daoUser.deleteAccount(accountNo, pin)) == true) {
				System.out.println("Account has been successfully deleted");
			} else {

			}
			
		}

}
