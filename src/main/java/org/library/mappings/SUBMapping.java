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
public class SUBMapping extends Mappings {
	@Override
	public void setSort(String column, boolean descend) {
		if ( column.equals("") ) {
			Object[] a = {"purchased", true};
			this.sort.add(a);
		}
		else if ( column.equals("author") ) {
			Object[] o = {"lastname", descend};
			this.sort.add(o.clone());

			o[0] = "firstnames";
			this.sort.add(o.clone());

			o[0] = "published";
			this.sort.add(o.clone());

			o[0] = "title";
			this.sort.add(o.clone());
		}
		else {
			Object[] o = {column, descend};
			this.sort.add(o);
		}
	}

	@Override
	public void setSearch(String search) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void setLimit(int limit) {
		this.limit = limit;
	}

	@Override
	public List<Book> getBooks() {
		Criteria c = this.session.createCriteria(Book.class);
		Criteria p = c.createCriteria("author");

		for ( Object[] o : this.sort ) {
			if ( o[0].equals("firstnames") || o[0].equals("lastname") )
				p.addOrder(Boolean.valueOf(o[1].toString()) ? Order.desc(o[0].toString()) : Order.asc(o[0].toString()));
			else
				c.addOrder(Boolean.valueOf(o[1].toString()) ? Order.desc(o[0].toString()) : Order.asc(o[0].toString()));
		}

		c.add(Restrictions.isNull("read"));
		c.add(Restrictions.isNotNull("purchased"));

		if ( this.limit != -1 )
			c.setMaxResults(this.limit);

		return c.list();
	}
}