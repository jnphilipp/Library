/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.library.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class HibernateUtil {
	/**
	 * Session factory
	 */
	private static final SessionFactory sessionFactory;
	/**
	 * Service registry
	 */
	private static final ServiceRegistry serviceRegistry;

	static {
		try {
			// Create the SessionFactory from standard (hibernate.cfg.xml) 
			// config file.
			Configuration configuration = new Configuration();
			configuration.configure();
			serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			// Log the exception. 
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * 
	 * @return the SessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}