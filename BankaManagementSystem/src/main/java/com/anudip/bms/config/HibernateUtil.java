package com.anudip.bms.config;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.anudip.bms.entity.Bank;
import com.anudip.bms.entity.Employee;
import com.anudip.bms.entity.Customer;

public class HibernateUtil 
{
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if(sessionFactory==null) {
			try {

				Configuration configuration = new Configuration();

				// Hibernate settings equivalent to hibernate.cfg.xml's properties
				Properties properties = new Properties();
				properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
				properties.put(Environment.URL, "jdbc:mysql://localhost:3306/mandi");
				properties.put(Environment.USER, "root");
				properties.put(Environment.PASS, "root");
				properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");

				properties.put(Environment.SHOW_SQL, "true");
				properties.put(Environment.USE_SQL_COMMENTS, "true");
				properties.put(Environment.FORMAT_SQL, "true");

				properties.put(Environment.HBM2DDL_AUTO, "update");

				//setting all properties into configuration 
				configuration.setProperties(properties);

				//mapping entity classes
				configuration.addAnnotatedClass(Customer.class);
				configuration.addAnnotatedClass(Employee.class);
				configuration.addAnnotatedClass(Bank.class);

				//building service registry
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
				//creating session factory with help of service registry
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				return sessionFactory;

			} 
			//handeling Exceptions
			catch (HibernateException he) {
				he.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}

	//invoking and activating session factory in this method
	public static Session getSession() {
		return getSessionFactory().openSession();
	}

}
