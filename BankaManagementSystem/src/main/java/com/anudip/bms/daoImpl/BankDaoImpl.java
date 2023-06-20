package com.anudip.bms.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.anudip.bms.config.HibernateUtil;
import com.anudip.bms.dao.BankDao;
import com.anudip.bms.entity.Bank;
import com.anudip.bms.entity.Employee;
import com.anudip.bms.entity.Customer;
import com.anudip.bms.exception.GlobalException;

public class BankDaoImpl implements BankDao
{

	//creating session globally so each and every method can have access of this
	Session session = HibernateUtil.getSession();

	//fetching all datas from DB and storing into list reference
	private List<Customer> users = session.createQuery("FROM BankUser", Customer.class).getResultList();
	private List<Employee> emps = session.createQuery("FROM BankEmployee", Employee.class).getResultList();
	private List<Bank> admins = session.createQuery("FROM BankAdmin", Bank.class).getResultList();

	//==========================================================================================================================

	//method for creating admin account
	@Override
	public boolean createAdmin(Bank bank)
	{
		try
		{	//activating session

			
				//adding bank to DB
				session.beginTransaction();
				session.save(bank);
				//saving to DB
				session.getTransaction().commit();
				return bank;
			
		}catch(HibernateException he) 
		{	//handeling hibernate exception
			he.printStackTrace();
		}catch(Exception e) 
		{			//handeling all kind of exception
			e.printStackTrace();
		}//end catch
		return false;
	}

	//==========================================================================================================================

	//method for retrieving all users details
	@Override
	public List<Customer> fetchAllUsers(String username, String password) 
	{
		try
		{	//activating session
			//fetching admin data to 
			Bank fetchAdmin;
			fetchAdmin = (Bank)session.get(Bank.class, username);

			//validations
			//for username
			if( !(admins.stream().anyMatch(x-> {
				return x.getUsername().equals(username);
			}))  )
			{
				//it will throw GlobalException
				throw new GlobalException("Invalid Username");
			}
			//for password
			else if( !(fetchAdmin.getPassword().equals(password)) ) 
			{
				//it will throw GlobalException
				throw new GlobalException("Invalid Password");
			}
			//null validation
			else if(users==null) {
				//it will throw GlobalException
				throw new GlobalException("No result found.");
			}
			//if all condition satisfies
			else {
				//it will return list of all bank users
				return users;
			}

		}catch(HibernateException he) 
		{	//handeling hibernate exception
			he.printStackTrace();
		}catch(Exception e) {			//handeling all kind of exception
			e.printStackTrace();
		}//end catch	
		return null;
	}

	//==========================================================================================================================

	//method for retrieving all employee details
	@Override
	public List<Employee> fetchAllEmployees(String username, String password) {
		try{	//activating session
			//fetching admin data to 
			Bank fetchAdmin = (Bank)session.get(Bank.class, username);

			//validations
			//for username
			if( !(admins.stream().anyMatch(x->x.getUsername().equals(username)))  ) {
				//it will throw GlobalException
				throw new GlobalException("Invalid username");
			}
			//for password
			else if( !(fetchAdmin.getPassword().equals(password)) ) {
				//it will throw GlobalException
				throw new GlobalException("Invalid password");
			}
			//null validation
			else if(emps==null) {
				//it will throw GlobalException
				throw new GlobalException("No result found.");
			}
			//if all condition satisfies
			else {
				//it will return list of all bank users
				return emps;
			}

		}catch(HibernateException he) {	//handeling hibernate exception
			he.printStackTrace();
		}catch(Exception e) {			//handeling all kind of exception
			e.printStackTrace();
		}//end catch	
		return null;
	}

	//==========================================================================================================================

	//method for updating admin details	
	@Override
	public Bank changePassword(String username, String password, Bank bank) {
		try{	//activating session
			//fetching admin data
			Bank fetchAdmin;
			fetchAdmin = (Bank)session.get(Bank.class, username);

			//validations
			//for username
			if( !(admins.stream().anyMatch(x->x.getUsername().equals(username)))  ) {
				//it will throw GlobalException
				throw new GlobalException("Invalid username");
			}
			//for password
			else if( !(fetchAdmin.getPassword().equals(password)) ) {
				//it will throw GlobalException
				throw new GlobalException("Invalid password");
			}
			//if all condition satisfies
			else {
				//setting updated values
				fetchAdmin.setPassword(bank.getPassword());

				//it will upadate the value first then will return
				session.beginTransaction();			//activating transaction 
				session.update(fetchAdmin);				//updating
				session.getTransaction().commit(); 	//saving to DB
				return fetchAdmin;
			}//else end

		}//try end
		catch(HibernateException he) {	//handeling hibernate exception
			he.printStackTrace();
		}//catch end
		catch(Exception e) {			//handeling all kind of exception
			e.printStackTrace();
		}//catch end
		return null;
	}

	//==========================================================================================================================

	//method for deleting admin account
	@Override
	public boolean deactivateAccount(String username, String password) {
		try{		// activating session
			//fetching admin data
			Bank fetchAdmin = (Bank)session.get(Bank.class, username);

			//validations
			//for username
			if( !(admins.stream().anyMatch(x-> {
				return x.getUsername().equals(username);
			}))  ) {
				//it will throw GlobalException
				throw new GlobalException("Invalid username entry");
			}
			//for password
			else if( !(fetchAdmin.getPassword().equals(password)) ) {
				//it will throw GlobalException
				throw new GlobalException("Invalid password entry");
			}
			//if all condition satisfies
			else {
				//it will upadate the value first then will return
				session.beginTransaction();			//activating transaction 
				session.delete(fetchAdmin);			//deleting
				session.getTransaction().commit(); 	//saving changes to DB
				return true;
			}
		}// try end
		catch(HibernateException he) {	//handeling hibernate exception
			he.printStackTrace();
		}//catch end
		catch(Exception e) {			//handeling all kind of exception
			e.printStackTrace();
		}//catch end	
		return false;
	}//method end

	//==========================================================================================================================	

	//method for deleting user account
	@Override
	public boolean deleteUser(String username, String password, int accountNo) {
		try{		// activating session
			//fetching admin data
			Bank fetchAdmin;
			fetchAdmin = (Bank)session.get(Bank.class, username);
			//fetching employee data
			Customer fetchUser;
			fetchUser = (Customer)session.get(Customer.class, accountNo);

			//validations
			//for username
			if( !(admins.stream().anyMatch(x->x.getUsername().equals(username)))  ) {
				//it will throw GlobalException
				throw new GlobalException("Invalid username entry");
			}
			//for password
			else if( !(fetchAdmin.getPassword().equals(password)) ) {
				//it will throw GlobalException
				throw new GlobalException("Invalid password entry");
			}
			//if all condition satisfies
			else {
				//it will upadate the value first then will return
				session.beginTransaction();			//activating transaction 
				session.delete(fetchUser);			//deleting
				session.getTransaction().commit(); 	//saving changes to DB
				return true;
			}//else end
		}// try end
		catch(HibernateException he) {	//handeling hibernate exception
			he.printStackTrace();
		}//catch end
		catch(Exception e) {			//handeling all kind of exception
			e.printStackTrace();
		}//catch end
		return false;
	}// method end

	/*=========================================================================================================================*/	

	//method for deleting employee account
	@Override
	public boolean deleteEmployee(String adminUsername, String adminPassword, String empUsername) {
		try{		// activating session
			//fetching admin data
			Bank fetchAdmin = (Bank)session.get(Bank.class, adminUsername);
			//fetching employee data
			Employee fetchEmp = (Employee)session.get(Employee.class, empUsername);

			//validations
			//for username
			if( !(admins.stream().anyMatch(x->x.getUsername().equals(adminUsername)))  ) {
				//it will throw GlobalException
				throw new GlobalException("Invalid username");
			}
			//for password
			else if( !(fetchAdmin.getPassword().equals(adminPassword)) ) {
				//it will throw GlobalException
				throw new GlobalException("Invalid password");
			}
			//if all condition satisfies
			else {
				//it will upadate the value first then will return
				session.beginTransaction();			//activating transaction 
				session.delete(fetchEmp);			//deleting
				session.getTransaction().commit(); 	//saving changes to DB
				return true;
			}//else end
		}// try end
		catch(HibernateException he) {	//handeling hibernate exception
			he.printStackTrace();
		}//catch end
		catch(Exception e) {			//handeling all kind of exception
			e.printStackTrace();
		}//catch end
		return false;
	}//method end
	/*==========================================================================================================================*/
}



