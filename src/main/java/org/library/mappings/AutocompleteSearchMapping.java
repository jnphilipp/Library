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
public class AutocompleteSearchMapping extends Mappings {
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
		List result = this.session.createQuery(this.hqlQuery()).list();

		return result;
	}

	private String hqlQuery() {
			return "from Buecher where titel like '%" + this.search + "%' order by " + this.sort;
	}
}