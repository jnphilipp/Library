/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.library.mappings;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.library.db.hibernate.classes.Book;
import org.library.util.HibernateUtil;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public abstract class Mappings {
	protected Session session = null;
	protected String search = "";
	protected ArrayList<Object[]> sort = new ArrayList<Object[]>();
	protected int limit = -1;
	protected int offset = -1;

	public void setSort(String column, boolean descend) {
		if ( column.equals("") ||column.equals("author") ) {
			Object[] o = {"lastname", descend};
			this.sort.add(o.clone());
			o[0] = "firstnames";
			this.sort.add(o.clone());
			o[0] = "series";
			this.sort.add(o.clone());
			o[0] = "volume";
			this.sort.add(o.clone());
			o[0] = "purchased";
			this.sort.add(o.clone());
		}
		else if ( column.equals("series") ) {
			Object[] o = {"series", descend};
			this.sort.add(o);
			o[0] = "volume";
			this.sort.add(o.clone());
		}
		else {
			Object[] o = {column, descend};
			this.sort.add(o);
		}
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public void open() {
		if ( this.session == null )
			this.session = HibernateUtil.getSessionFactory().openSession();
	}

	public void beginTransaction() {
		if ( this.session.isOpen() )
			this.session.beginTransaction();
	}

	public void commit() {
		this.session.getTransaction().commit();
	}

	public void close () {
		if ( this.session.isOpen() ) {
			this.session.close();
		}
	}

	public List sqlQuery(String query) {
		return this.session.createSQLQuery(query).list();
	}

	public abstract void setSearch(String search);
	public abstract List<Book> getBooks();
	public abstract int getNextArrow(int nOffset, int nLimit);
}