package com.anudip.bms.serviceImpl;

import java.util.List;
import java.util.Optional;

import com.anudip.bms.dao.BankDao;
import com.anudip.bms.daoImpl.BankDaoImpl;
import com.anudip.bms.entity.Bank;
import com.anudip.bms.entity.Employee;
import com.anudip.bms.entity.Customer;
import com.anudip.bms.exception.GlobalException;
import com.anudip.bms.service.BankService;

import jakarta.validation.constraints.Null;

public class BankServiceImpl implements BankService
{

	//instantiating polymorphic dao object
			BankDao daoAdmin = new BankDaoImpl();
			
			@Override
			public void createAdmin(Bank admin) {
				daoAdmin.createAdmin(admin);
				if (daoAdmin.createAdmin(admin) == true) 
				{
					System.out.println("Successfully Bank created");
				}
				else {
					System.out.println("Oups! some thing went wrong check credentials properly");
				}
			}

			@Override
			public List<Customer> fetchAllUsers(String username, String password) throws GlobalException{
				// TODO Auto-generated method stub
				List<Customer> users = daoAdmin.fetchAllUsers(username, password);
				//null validation
				return Optional.of(users).orElseThrow(() -> new GlobalException("User does not exists"));
			}

			@Override
			public List<Employee> fetchAllEmployees(String username, String password) throws GlobalException{
				// TODO Auto-generated method stub
				List<Employee> emps = daoAdmin.fetchAllEmployees(username, password);
				//null validation
				return Optional.of(emps).orElseThrow(() -> new GlobalException("Employee does not exists"));
			}

			@Override
			public Bank changePassword(String username, String password, Bank admin) {
				// TODO Auto-generated method stub
				Bank returnAdmin = daoAdmin.changePassword(username, password, admin);
				System.out.println("Password successfully changed");
				return returnAdmin;
			}

			@Override
			public void deactivateAccount(String username, String password) {
				// TODO Auto-generated method stub
				daoAdmin.deactivateAccount(username, password);
				if ((daoAdmin.deactivateAccount(username, password)) == true) 
				{
					System.out.println("Bank deleted successfully");
				}
				else {
					System.out.println("Something went wrong");
				}
			}

			@Override
			public void deleteUser(String username, String password, int accountNo) {
				// TODO Auto-generated method stub
				daoAdmin.deleteUser(username, password, accountNo);
				if ((daoAdmin.deleteUser(username, password, accountNo))== true) {
					System.out.println("Customer deleted successfully");
				}
				else {
					System.out.println("Something went wrong");
				}
			}

			@Override
			public void deleteEmployee(String adminUsername, String adminPassword, String empUsername) {
				// TODO Auto-generated method stub
				daoAdmin.deleteEmployee(adminUsername, adminPassword, empUsername);
				if ((daoAdmin.deleteEmployee(adminUsername, adminPassword, empUsername)) == true) {
					System.out.println("employee deleted successfully");
				}
				else {
					System.out.println("Something went wrong");
				}
			}
	
}
