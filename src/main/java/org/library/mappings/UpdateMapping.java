/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.library.mappings;

import java.util.List;
import org.library.db.hibernate.classes.Book;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class UpdateMapping extends Mappings {
	@Override
	public void setSort(String column, boolean descend) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void setSearch(String search) {
		this.search = search;
	}

	@Override
	public void setLimit(int limit) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public List<Book> getBooks() {
		return this.session.createQuery("from Book where isbn='" + this.search + "'").list();
	}
}