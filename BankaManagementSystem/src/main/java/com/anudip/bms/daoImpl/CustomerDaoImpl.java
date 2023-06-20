package com.anudip.bms.daoImpl;

import java.util.List;
import java.util.regex.Pattern;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.validator.internal.properties.javabean.JavaBeanHelper;

import com.anudip.bms.config.HibernateUtil;
import com.anudip.bms.dao.CustomerDao;
import com.anudip.bms.entity.Customer;
import com.anudip.bms.exception.GlobalException;

public class CustomerDaoImpl implements CustomerDao
{

	//creating session globally so each method can access this
	Session session = HibernateUtil.getSession();

	//fetching all datas from DB and storing into list reference
	private List<Customer> users = session.createQuery("FROM BankUser",Customer.class).getResultList();

	//==========================================================================================================================

	//method for creating user account
	public boolean createAccount(Customer customer)
	{

		try{	//activating sessio	

			//validation of user details
			//for account holdername
			if( (customer.getAccountHolder().length()>25) || (customer.getAccountHolder()==null) ) {
				//it will throw a global exception
				throw new GlobalException("Account holder name can not be more than 10 letters.");
			}

			//for account no
			else if( (users.stream().anyMatch(x->x.getAccountNo()==customer.getAccountNo())) || (Integer.toString(customer.getAccountNo()).length()>10)){
				//it will throw a global exception
				throw new GlobalException("A/c number already taken or size is more than 10 digits.");
			}

			//for account type
			else if( !(customer.getAccountType().contains("saving") || customer.getAccountType().contains("current")) ) {
				//it will throw a global exception
				throw new GlobalException("Invalid account type... (saving/current)");
			}

			//for account branch
			else if( (customer.getBranch().length()>15) || (customer.getBranch()==null) ) {
				//it will throw a global exception
				throw new GlobalException("Branch name length can't be more than 15 letters");
			}

			//for contact no
			else if( (users.stream().anyMatch(x->x.getContact()==customer.getContact())) || !(Long.toString(user.getContact()).length()==10) || !(Long.toString(user.getContact()).startsWith("6") || Long.toString(user.getContact()).startsWith("7") || Long.toString(user.getContact()).startsWith("8") || Long.toString(user.getContact()).startsWith("9")) || (user.getContact()==0) ) {
				//it will throw a global exception 
				throw new GlobalException("Invalid contact number or already taken.");
			}

			//for email (using regex pattern for email validation)
			else if( (users.stream().anyMatch(x->x.getEmail().equals(customer.getEmail())) ) || !(Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE).matcher(user.getEmail()).matches()) || (user.getEmail()==null) ) {
				//it will throw a global exception
				throw new GlobalException("Invalid email id or already taken.");
			}

			//for pin
			else if( !(Integer.toString(user.getPin()).length()==4) ) {
				//it will throw a global exception
				throw new GlobalException("Invalid pin (size must be 4 digits)");
			}

			//for balance
			else if( (user.getBalance()<0) ) {
				//it will throw a global exception
				throw new GlobalException("Invalid balance entry (Only positive value can be accepted.)");
			}

			//if all condition satisfies
			else {
				//adding user to DB
				session.beginTransaction();
				session.save(user);

				//saving to DB
				session.getTransaction().commit();
				return true;
			}
		}catch(HibernateException he) {	//handeling hibernate exception
			he.printStackTrace();
		}catch(Exception e) {			//handeling all kind of exception
			e.printStackTrace();
		}	
		return false;
	}//end method

	//==========================================================================================================================

	// method for displaying user balance
	public Customer balanceInquiry(int accountNo, int pin) {
		try{ 	// activating session 
			//fetching user data
			Customer fetchUser = (Customer)session.get(Customer.class, accountNo);
			//validation
			if( (users.stream().anyMatch(x->x.getAccountNo()==accountNo)) && (fetchUser.getPin()==pin) ) {	//if condition meets
				return fetchUser;	//returning fetching data
			}//if end
			else {	// otherwise
				//it will throw GlobalException
				throw new GlobalException("Invalid credentials (Incorrect A/c number or pin)");
			}//else end
		}//try end
		catch(HibernateException he) {	//handeling hibernate exception
			he.printStackTrace();
		}//catch end
		catch(Exception e) {			//handeling all kind of exception
			e.printStackTrace();
		}//catch end
		return null;
	}//method end

	//==========================================================================================================================

	//method for updating details into user DB
	public Customer deposite(int accountNo , int pin, Customer user) {	
		try{	// activating session
			//activating transaction
			session.beginTransaction();
			//fetching user data
			Customer fetchUser = (Customer)session.get(Customer.class, accountNo);

			//validation
			if( (users.stream().anyMatch(x->x.getAccountNo()==accountNo)) && (user.getBalance()>0) && (pin==fetchUser.getPin()) ) {	//if condition meets

				fetchUser.setBalance(fetchUser.getBalance() + user.getBalance()); 	//setting updated value

				//saving data
				session.update(fetchUser);
				session.getTransaction().commit();
				return fetchUser;
			}//if end
			else {	//otherwise
				//it will throw GlobalException
				throw new GlobalException("Invalid credentials or deposite ammount");
			}//else end

		}// try end
		catch(HibernateException he) {	//handeling hibernate exception
			he.printStackTrace();
		}//catch end
		catch(Exception e) {			//handeling all kind of exception
			e.printStackTrace();
		}//catch end
		return null;
	}//method end

	//==========================================================================================================================

	//method for updating details into user DB
	public Customer withdrawal(int accountNo, int pin, Customer user) {		
		try{	// activating session
			//activating transaction
			session.beginTransaction();
			//fetching user data
			Customer fetchUser = (Customer)session.get(Customer.class, accountNo);

			//validation
			if( (users.stream().anyMatch(x->x.getAccountNo() == accountNo)) && (user.getBalance()>0 && (pin == fetchUser.getPin()) ) && (fetchUser.getBalance() > user.getBalance()) ) {	//if condition meets
				//setting updated value
				fetchUser.setBalance(fetchUser.getBalance() - user.getBalance());	//setting updated value
				//saving data
				session.update(fetchUser);
				session.getTransaction().commit();
				return fetchUser;
			}//if end
			else {	//otherwise
				//it will throw GlobalException 
				throw new GlobalException("Invalid Credentials/Deposite ammount or A/c is out of Balance");
			}//else end
		}//try end
		catch(HibernateException he) {	//handeling hibernate exception
			he.printStackTrace();
		}//catch end
		catch(Exception e) {			//handeling all kind of exception
			e.printStackTrace();
		}//catch end
		return null;
	}// method end

	//==========================================================================================================================	

	//method for updating details into user DB
	public Customer changePin(int accountNo , int pin, Customer user) {
		try{	// activating session
			//activating transaction
			session.beginTransaction();
			//fetching user data
			Customer fetchUser = (Customer)session.get(Customer.class, accountNo);

			//validation
			if( (users.stream().anyMatch(x->x.getAccountNo()==accountNo)) && (user.getPin()==fetchUser.getPin()) )  {	//if condition meets

				//setting updated value
				fetchUser.setPin(user.getPin());

				//saving data
				session.saveOrUpdate(fetchUser);
				session.getTransaction().commit();
				return fetchUser;	//returning the upadated value
			}//if end
		}//try end
		catch(HibernateException he) {	//handeling hibernate exception
			he.printStackTrace();
		}//catch end
		catch(Exception e) {			//handeling all kind of exception
			e.printStackTrace();
		}//catch end
		return null;
	}//method end

	//==========================================================================================================================	

	//method for deleting existing user account
	public boolean deleteAccount(int accountNo, int pin) {
		try{		// activating session
			session.beginTransaction();
			//fetching user data
			Customer fetchUser = (Customer)session.get(Customer.class, accountNo);

			//validation
			if( users.stream().anyMatch(x->x.getAccountNo()==accountNo) && (pin==fetchUser.getPin()) ) {
				//deleting the user
				session.delete(fetchUser);

				//saving changes in DB
				session.getTransaction().commit();
				return true;
			}//if end
		}// try end
		catch(HibernateException he) {	//handeling hibernate exception
			he.printStackTrace();
		}//catch end
		catch(Exception e) {			//handeling all kind of exception
			e.printStackTrace();
		}//catch end
		return false;
	}// method end

	//==========================================================================================================================	

}
