/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.library.db.hibernate;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.library.db.hibernate.classes.People;
import org.library.util.HibernateUtil;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class HibernatePeopleFunctions {
	public static void create(String name) {
		create(name.substring(0, name.lastIndexOf(" ")), name.substring(name.lastIndexOf(" ") + 1));
	}

	public static void create(String firstnames, String lastname) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		People p = new People();
		p.setFirstnames(firstnames);
		p.setLastname(lastname);

		session.save(p);
		session.getTransaction().commit();
		session.close();
	}

	public static People get(String name) {
		return get(-1, name.substring(0, name.lastIndexOf(" ")), name.substring(name.lastIndexOf(" ") + 1));
	}

	public static People get(String firstnames, String lastname) {
		return get(-1, firstnames, lastname);
	}

	public static People get(int id, String firstnames, String lastname) {
		List<People> l = getList(id, firstnames, lastname);

		if ( l == null || l.isEmpty() )
			return null;
		else
			return l.get(0);
	}

	public static List<People> getList(String name) {
		return getList(-1, name.substring(0, name.lastIndexOf(" ")), name.substring(name.lastIndexOf(" ") + 1));
	}

	public static List<People> getList(String firstnames, String lastname) {
		return getList(-1, firstnames, lastname);
	}

	public static List<People> getList(int id, String firstnames, String lastname) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		if ( id == -1 && !firstnames.equals("") && !lastname.equals("") ) {
			Query q = session.createQuery("from People where firstnames=? and lastname=?");
			q.setString(0, firstnames);
			q.setString(1, lastname);

			List re = q.list();

			return re;
		}
		else if ( id != -1 && firstnames.equals("") && lastname.equals("") ) {
			Query q = session.createQuery("from People where id=?");
			q.setInteger(0, id);

			List re = q.list();

			return re;
		}
		else if ( id != -1 && !firstnames.equals("") && !lastname.equals("") ) {
			Query q = session.createQuery("from People where id=? and firstnames=? and lastname=?");
			q.setInteger(0, id);
			q.setString(1, firstnames);
			q.setString(2, lastname);

			List re = q.list();

			return re;
		}

		return null;
	}

	public static People get(String firstnames, String lastname, Session session) {
		People p;

		Query q = session.createQuery("from People where firstnames=:first and lastname=:last");
		q.setString("first", firstnames);
		q.setString("last", lastname);
		List list = q.list();

		if ( list.isEmpty() ) {
			p = new People();
			p.setFirstnames(firstnames);
			p.setLastname(lastname);
		}
		else
			p = (People)list.get(0);

		return p;
	}
}