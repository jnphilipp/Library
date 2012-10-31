/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.library.db.hibernate;

import org.hibernate.Session;
import org.library.util.HibernateUtil;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class HibernateBookFunctions {
	public static void updateChanged(String isbn) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		session.createSQLQuery("update book set isbn=:isbn where isbn=:isbn").setString("isbn", isbn).executeUpdate();

		session.getTransaction().commit();
		session.close();
	}
}