/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.library.mappings;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.library.db.hibernate.classes.Book;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class WishListMapping extends Mappings {
	@Override
	public void setSearch(String search) {
		throw new UnsupportedOperationException("Not supported yet.");
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

	private Criteria createCriteria() {
		Criteria c = this.session.createCriteria(Book.class);
		Criteria p = c.createCriteria("author");

		for ( Object[] o : this.sort ) {
			if ( o[0].equals("firstnames") || o[0].equals("lastname") )
				p.addOrder(Boolean.valueOf(o[1].toString()) ? Order.desc(o[0].toString()) : Order.asc(o[0].toString()));
			else
				c.addOrder(Boolean.valueOf(o[1].toString()) ? Order.desc(o[0].toString()) : Order.asc(o[0].toString()));
		}

		c.add(Restrictions.isNull("purchased"));
		return c;
	}
}