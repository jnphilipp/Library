/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.library.language;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import org.library.Functions;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class Language {
	private String add = "";
	private String and = "";
	private String author = "";
	private String authors = "";
	private String binding = "";
	private String book = "";
	private String by = "";
	private String coauthor = "";
	private String coauthors = "";
	private String fetch = "";
	private String language = "";
	private String library_tab = "";
	private String message_add_1 = "";
	private String message_add_2 = "";
	private String message_update_1 = "";
	private String message_update_2 = "";
	private String price = "";
	private String published = "";
	private String publishedBy= "";
	private String publisher = "";
	private String purchased = "";
	private String purchased_this_year = "";
	private String purchased_this_month = "";
	private String read = "";
	private String read_this_year = "";
	private String read_this_month = "";
	private String search = "";
	private String sort = "";
	private String tab_add = "";
	private String tab_library = "";
	private String tab_purchased = "";
	private String tab_read = "";
	private String tab_search = "";
	private String tab_statistics = "";
	private String tab_sub = "";
	private String tab_update = "";
	private String tab_wishlist = "";
	private String title = "";
	private String update = "";
	private String which = "";

	public Language() throws IOException {
		this.load();
	}

	private void load() throws IOException {
		InputStream inputStream = Functions.getServletContext().getResourceAsStream("/WEB-INF/language.properties");
		Properties property = new Properties();
		property.load(inputStream);
		Enumeration<Object> keys = property.keys();

		while ( keys.hasMoreElements() ) {
			String key = keys.nextElement().toString();
			this.setValue(key, property.getProperty(key));
		}

		inputStream.close();
	}

	public void setValue(String key, String value) {
		if ( key.equals("add") )
			this.add = value;
		else if ( key.equals("and") )
			this.and = value;
		else if ( key.equals("author") )
			this.author = value;
		else if ( key.equals("authors") )
			this.authors = value;
		else if ( key.equals("binding") )
			this.binding = value;
		else if ( key.equals("book") )
			this.book = value;
		else if ( key.equals("by") )
			this.by = value;
		else if ( key.equals("coauthor") )
			this.coauthor = value;
		else if ( key.equals("coauthors") )
			this.coauthors = value;
		else if ( key.equals("fetch") )
			this.fetch = value;
		else if ( key.equals("language") )
			this.language = value;
		else if ( key.equals("message_add_1") )
			this.message_add_1 = value;
		else if ( key.equals("message_add_2") )
			this.message_add_2 = value;
		else if ( key.equals("message_update_1") )
			this.message_add_1 = value;
		else if ( key.equals("message_update_2") )
			this.message_add_2 = value;
		else if ( key.equals("price") )
			this.price = value;
		else if ( key.equals("published") )
			this.published = value;
		else if ( key.equals("publishedBy") )
			this.publishedBy = value;
		else if ( key.equals("publisher") )
			this.publisher = value;
		else if ( key.equals("purchased") )
			this.purchased = value;
		else if ( key.equals("purchased_this_year") )
			this.purchased_this_year = value;
		else if ( key.equals("purchased_this_month") )
			this.purchased_this_month = value;
		else if ( key.equals("read") )
			this.read = value;
		else if ( key.equals("read_this_year") )
			this.read_this_year = value;
		else if ( key.equals("read_this_month") )
			this.read_this_month = value;
		else if ( key.equals("search") )
			this.search = value;
		else if ( key.equals("sort") )
			this.sort = value;
		else if ( key.equals("tab_add") )
			this.tab_add = value;
		else if ( key.equals("tab_library") )
			this.tab_library = value;
		else if ( key.equals("tab_purchased") )
			this.tab_purchased = value;
		else if ( key.equals("tab_read") )
			this.tab_read = value;
		else if ( key.equals("tab_search") )
			this.tab_search = value;
		else if ( key.equals("tab_statistics") )
			this.tab_statistics = value;
		else if ( key.equals("tab_sub") )
			this.tab_sub = value;
		else if ( key.equals("tab_update") )
			this.tab_update = value;
		else if ( key.equals("tab_wishlist") )
			this.tab_wishlist = value;
		else if ( key.equals("title") )
			this.title = value;
		else if ( key.equals("update") )
			this.update = value;
		else if ( key.equals("which") )
			this.which = value;
	}

	/**
	 * @return the add
	 */
	public String getAdd() {
		return this.add;
	}

	/**
	 * @return the and
	 */
	public String getAnd() {
		return this.and;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return this.author;
	}

	/**
	 * @return the authors
	 */
	public String getAuthors() {
		return this.authors;
	}

	/**
	 * @return the authors
	 */
	public String getAuthor(boolean plural) {
		if ( plural )
			return this.authors;
		else
			return this.author;
	}

	/**
	 * @return the binding
	 */
	public String getBinding() {
		return this.binding;
	}

	/**
	 * @return the book
	 */
	public String getBook() {
		return this.book;
	}

	/**
	 * @return the by
	 */
	public String getBy() {
		return this.by;
	}

	/**
	 * @return the coauthor
	 */
	public String getCoauthor() {
		return this.coauthor;
	}

	/**
	 * @return the coauthors
	 */
	public String getCoauthors() {
		return this.coauthors;
	}

	/**
	 * @return the coauthor
	 */
	public String getCoauthor(boolean plural) {
		if ( plural )
			return coauthors;
		else
			return this.coauthor;
	}

	/**
	 * @return the fetch
	 */
	public String getFetch() {
		return this.fetch;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return this.language;
	}

	/**
	 * @return the language
	 */
	public String getMessage_add(String book) {
		return this.message_add_1 + " " + book + " " + this.message_add_2;
	}

	/**
	 * @return the language
	 */
	public String getMessage_update(String book) {
		return this.message_update_1 + " " + book + " " + this.message_update_2;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return this.price;
	}

	/**
	 * @return the published
	 */
	public String getPublished() {
		return this.published;
	}

	/**
	 * @return the publishedBy
	 */
	public String getPublishedBy() {
		return this.publishedBy;
	}

	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return this.publisher;
	}

	/**
	 * @return the purchased
	 */
	public String getPurchased() {
		return this.purchased;
	}

	/**
	 * @return the purchased_this_year
	 */
	public String getPurchasedThisYear() {
		return this.purchased_this_year;
	}

	/**
	 * @return the purchased_this_month
	 */
	public String getPurchasedThisMonth() {
		return this.purchased_this_month;
	}

	/**
	 * @return the read
	 */
	public String getRead() {
		return this.read;
	}

	/**
	 * @return the read_this_year
	 */
	public String getReadThisYear() {
		return this.read_this_year;
	}

	/**
	 * @return the read_this_month
	 */
	public String getReadThisMonth() {
		return this.read_this_month;
	}

	/**
	 * @return the search
	 */
	public String getSearch() {
		return this.search;
	}

	/**
	 * @return the sort
	 */
	public String getSort() {
		return this.sort;
	}

	/**
	 * @return the tab_add
	 */
	public String getTabAdd() {
		return this.tab_add;
	}

	/**
	 * @return the tab_library
	 */
	public String getTabLibrary() {
		return this.tab_library;
	}

	/**
	 * @return the tab_purchased
	 */
	public String getTabPurchased() {
		return this.tab_purchased;
	}

	/**
	 * @return the tab_read
	 */
	public String getTabRead() {
		return this.tab_read;
	}

	/**
	 * @return the tab_search
	 */
	public String getTabSearch() {
		return this.tab_search;
	}

	/**
	 * @return the tab_statistics
	 */
	public String getTabStatistics() {
		return this.tab_statistics;
	}

	/**
	 * @return the tab_sub
	 */
	public String getTabSUB() {
		return this.tab_sub;
	}

	/**
	 * @return the tab_update
	 */
	public String getTabUpdate() {
		return this.tab_update;
	}

	/**
	 * @return the tab_wishlist
	 */
	public String getTabWishList() {
		return this.tab_wishlist;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * @return the update
	 */
	public String getUpdate() {
		return this.update;
	}

	/**
	 * @return the which
	 */
	public String getWhich() {
		return this.which;
	}
}