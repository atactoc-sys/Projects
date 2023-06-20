package com.anudip.bms.serviceImpl;

import java.util.List;
import java.util.Optional;

import com.anudip.bms.dao.EmployeeDao;
import com.anudip.bms.daoImpl.EmployeeDaoImpl;
import com.anudip.bms.entity.Employee;
import com.anudip.bms.entity.Customer;
import com.anudip.bms.exception.GlobalException;
import com.anudip.bms.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService
{

	//instantiating polymorphic dao object
			EmployeeDao daoEmp = new EmployeeDaoImpl();
			
			@Override
			public void createEmp(Employee emp) {
				// TODO Auto-generated method stub
				daoEmp.createEmp(emp);
				if (daoEmp.createEmp(emp) == true) {
					System.out.println("Employee created successfully");
				} else {
					System.out.println("Oups! some thing went wrong check credentials properly");
				}
				
			}

			@Override
			public List<Customer> fetchAllUsers(String username, String password) throws GlobalException {
				// TODO Auto-generated method stub
				List<Customer> users = daoEmp.fetchAllUsers(username, password);
				//null validation
				return Optional.of(users).orElseThrow(() -> new GlobalException("customer does not exists"));
			}

			@Override
			public Employee changePassword(String username, String password, Employee emp) {
				// TODO Auto-generated method stub
				Employee returnEmp = daoEmp.changePassword(username, password, emp);
				System.out.println("Password successfully changed");
				return returnEmp;
			}

			@Override
			public void deactivateAccount(String username, String password) {
				// TODO Auto-generated method stub
				daoEmp.deactivateAccount(username, password);
				if ((daoEmp.deactivateAccount(username, password) )== true) {
					System.out.println("Employee deleted successfully");
				} else {
					System.out.println("Something went wrong");
				}
			}

}
