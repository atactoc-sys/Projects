package com.anudip.bms.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;

import com.anudip.bms.config.HibernateUtil;
import com.anudip.bms.dao.EmployeeDao;
import com.anudip.bms.entity.Employee;
import com.anudip.bms.entity.Customer;
import com.anudip.bms.exception.GlobalException;
import com.mysql.cj.Session;

public class EmployeeDaoImpl implements EmployeeDao
{

	//creating session globally so each and every method can have access of this
	org.hibernate.Session session = HibernateUtil.getSession();

	//fetching all datas from DB and storing into list reference
	private List<Customer> users = session.createQuery("FROM BankUser", Customer.class).getResultList();
	private List<Employee> emps = session.createQuery("FROM BankEmployee", Employee.class).getResultList();

	//==========================================================================================================================

	//method for creating employee account
	@Override
	public boolean createEmp(Employee employee)
	{
		try
		{	//activating session
			//adding employee to DB
			session.beginTransaction();
			session.save(employee);

			//saving to DB
			session.getTransaction().commit();
			return employee;
		}catch(HibernateException he) {	//handeling hibernate exception
			he.printStackTrace();
		}catch(Exception e) {			//handeling all kind of exception
			e.printStackTrace();
		}//end catch
		return false;
	}//end method

	//==========================================================================================================================

	//method for fetching all employee details
	@Override
	public List<Customer> fetchAllUsers(String username, String password) {
		try{	//activating session
			//fetching employee data
			Employee fetchEmp;
			fetchEmp = (Employee)session.get(Employee.class, username);

			//validations
			//for username
			if( !(emps.stream().anyMatch(x-> {
				return x.getUsername().equals(username);
			}))  ) {
				//it will throw GlobalException
				throw new GlobalException("Invalid username entry");
			}
			//for password
			else if( !(fetchEmp.getPassword().equals(password)) ) {
				//it will throw GlobalException
				throw new GlobalException("Invalid password entry");
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

		}catch(HibernateException he) {	//handeling hibernate exception
			he.printStackTrace();
		}catch(Exception e) {			//handeling all kind of exception
			e.printStackTrace();
		}//end catch	

		return null;
	}//end method

	//==========================================================================================================================

	//method for updating password
	@Override
	public Employee changePassword(String username, String password, Employee employee) {
		try{	//activating session
			//fetching employee data
			Employee fetchEmp;
			fetchEmp = (Employee)session.get(Employee.class,username);

			//validations
			//for username
			if( !(emps.stream().anyMatch(x-> {
				return x.getUsername().equals(username);
			}))  ) {
				//it will throw GlobalException
				throw new GlobalException("Invalid username entry");
			}
			//for password
			else if( !(fetchEmp.getPassword().equals(password)) ) {
				//it will throw GlobalException
				throw new GlobalException("Invalid password entry");
			}
			//if all condition satisfies
			else {
				//setting updated values
				fetchEmp.setPassword(employee.getPassword());
				//it will upadate the value first then will return
				session.beginTransaction();			//activating transaction 
				session.update(fetchEmp);				//updating
				session.getTransaction().commit(); 	//saving to DB
				return fetchEmp;
			}

		}catch(HibernateException he) {	//handeling hibernate exception
			he.printStackTrace();
		}catch(Exception e) {			//handeling all kind of exception
			e.printStackTrace();
		}//end catch	
		return null;
	}//end method

	//==========================================================================================================================

	//method for deleting existing employee account
	@Override
	public boolean deactivateAccount(String username, String password) {
		try{		// activating session
			//fetching employee data
			Employee fetchEmp;
			fetchEmp = (Employee)session.get(Employee.class, username);

			//validations
			//for username
			if( !(emps.stream().anyMatch(x-> {
				return x.getUsername().equals(username);
			}))  ) {
				///it will throw GlobalException
				throw new GlobalException("Invalid username entry");
			}
			//for password
			else if( !(fetchEmp.getPassword().equals(password)) ) {
				//it will throw GlobalException
				throw new GlobalException("Invalid password entry");
			}
			//if all condition satisfies
			else {
				//it will upadate the value first then will return
				session.beginTransaction();			//activating transaction 
				session.delete(fetchEmp);			//deleting
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
	}//end method

	//==========================================================================================================================

}
