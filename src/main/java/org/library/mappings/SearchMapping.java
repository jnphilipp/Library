/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.library.mappings;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.library.db.hibernate.classes.Book;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class SearchMapping extends Mappings {
	/*@Override
	public void setSort(String column, boolean descend) {
		if ( column.equals("") || column.equals("author") ) {
			Object[] o = {"lastname", descend};
			this.sort.add(o.clone());

			o[0] = "firstnames";
			this.sort.add(o.clone());

			o[0] = "published";
			this.sort.add(o.clone());

			o[0] = "title";
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
	}*/

	@Override
	public void setSearch(String search) {
		this.search = search;
	}

	@Override
	public void setLimit(int limit) {
		this.limit = limit;
	}

	@Override
	public List<Book> getBooks() {
		Criteria c = this.createCriteria();

		if ( this.limit != -1 )
			c.setMaxResults(this.limit);
		if ( this.offset != -1 )
			c.setFirstResult(this.offset);

		return c.list();
	}

	@Override
	public int getNextArrow(int nOffset, int nLimit)  {
		Criteria c = this.createCriteria();

		c.setMaxResults(nLimit);
		c.setFirstResult(nOffset);

		return c.list().size();
	}

	private Criteria createCriteria() {
		Criteria c = this.session.createCriteria(Book.class);
		Criteria p = c.createCriteria("author");

		for ( Object[] o : this.sort ) {
			if ( o[0].equals("firstnames") || o[0].equals("lastname") )
				p.addOrder(Boolean.valueOf(o[1].toString()) ? Order.desc(o[0].toString()) : Order.asc(o[0].toString()));
			else
				c.addOrder(Boolean.valueOf(o[1].toString()) ? Order.desc(o[0].toString()) : Order.asc(o[0].toString()));
		}

		if ( this.search.equals("") ) {
			c.add(Restrictions.isNull("read"));
			c.add(Restrictions.isNotNull("purchased"));
		}
		else if ( this.search.startsWith("w:") ) {
			c.add(Restrictions.isNull("purchased"));

			this.search = this.search.substring(2).replaceAll("\\*", "%");
			List isbn;

			if ( !this.search.contains(".") ) {
				if ( this.search.contains("%") )
					isbn = this.session.createSQLQuery("SELECT isbn FROM book WHERE cast(extract(year from published) as text) like :year").setString("year", this.search).list();
				else
					isbn = this.session.createSQLQuery("SELECT isbn FROM book WHERE cast(extract(year from published) as text) = :year").setString("year", this.search).list();
			}
			else {
					if ( this.search.contains("%") )
					isbn = this.session.createSQLQuery("SELECT isbn FROM book WHERE cast(extract(year from published) as text) like :year").setString("year", this.search).list();
				else
					isbn = this.session.createSQLQuery("SELECT isbn FROM book WHERE (CASE WHEN EXTRACT(MONTH FROM published) < 10 THEN '0' || EXTRACT(MONTH FROM published) ELSE CAST(EXTRACT(MONTH FROM published) AS TEXT) END || '.' || extract(year from published)) = :date").setString("date", this.search).list();	
			}

			if ( !isbn.isEmpty() )
				c.add(Restrictions.in("isbn", isbn));
			else
				c.add(Restrictions.isNull("isbn"));
		}
		else if ( this.search.startsWith("p:") ) {
			c.add(Restrictions.isNotNull("purchased"));

			this.search = this.search.substring(2).replaceAll("\\*", "%");
			List isbn;

			if ( !this.search.contains(".") ) {
				if ( this.search.contains("%") )
					isbn = this.session.createSQLQuery("SELECT isbn FROM book WHERE cast(extract(year from purchased) as text) like :year").setString("year", this.search).list();
				else
					isbn = this.session.createSQLQuery("SELECT isbn FROM book WHERE cast(extract(year from purchased) as text) = :year").setString("year", this.search).list();
			}
			else {
					if ( this.search.contains("%") )
					isbn = this.session.createSQLQuery("SELECT isbn FROM book WHERE cast(extract(year from purchased) as text) like :year").setString("year", this.search).list();
				else
					isbn = this.session.createSQLQuery("SELECT isbn FROM book WHERE (CASE WHEN EXTRACT(MONTH FROM purchased) < 10 THEN '0' || EXTRACT(MONTH FROM purchased) ELSE CAST(EXTRACT(MONTH FROM purchased) AS TEXT) END || '.' || extract(year from purchased)) = :date").setString("date", this.search).list();	
			}

			if ( !isbn.isEmpty() )
				c.add(Restrictions.in("isbn", isbn));
			else
				c.add(Restrictions.isNull("isbn"));
		}
		else if ( this.search.startsWith("r:") ) {
			c.add(Restrictions.isNotNull("read"));

			this.search = this.search.substring(2).replaceAll("\\*", "%");
			List isbn;

			if ( !this.search.contains(".") ) {
				if ( this.search.contains("%") )
					isbn = this.session.createSQLQuery("SELECT isbn FROM book WHERE cast(extract(year from read) as text) like :year").setString("year", this.search).list();
				else
					isbn = this.session.createSQLQuery("SELECT isbn FROM book WHERE cast(extract(year from read) as text) = :year").setString("year", this.search).list();
			}
			else {
					if ( this.search.contains("%") )
					isbn = this.session.createSQLQuery("SELECT isbn FROM book WHERE cast(extract(year from read) as text) like :year").setString("year", this.search).list();
				else
					isbn = this.session.createSQLQuery("SELECT isbn FROM book WHERE (CASE WHEN EXTRACT(MONTH FROM read) < 10 THEN '0' || EXTRACT(MONTH FROM read) ELSE CAST(EXTRACT(MONTH FROM read) AS TEXT) END || '.' || extract(year from read)) = :date").setString("date", this.search).list();	
			}

			if ( !isbn.isEmpty() )
				c.add(Restrictions.in("isbn", isbn));
			else
				c.add(Restrictions.isNull("isbn"));
		}
		else {
			try {
				this.search = this.search.replaceAll("\\*", "%");
				List isbn;

				if ( this.search.contains("%") )
					isbn = this.session.createSQLQuery("SELECT isbn FROM book WHERE author IN (SELECT id FROM people WHERE (firstnames || ' ' || lastname) LIKE :search) OR title LIKE :search OR isbn LIKE :search OR publisher LIKE :search").setString("search", this.search).list();
				else
					isbn = this.session.createSQLQuery("SELECT isbn FROM book WHERE author IN (SELECT id FROM people WHERE (firstnames || ' ' || lastname) = :search) OR title = :search OR isbn = :search OR publisher = :search").setString("search", this.search).list();

				if ( !isbn.isEmpty() )
					c.add(Restrictions.in("isbn", isbn));
				else
					c.add(Restrictions.isNull("isbn"));
			}
			catch ( Exception e ) {
				Logger.getRootLogger().error(e);
			}
		}

		return c;
	}
}