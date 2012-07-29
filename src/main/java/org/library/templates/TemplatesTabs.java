/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.library.templates;

import org.library.mustache.MustacheObject;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public abstract class TemplatesTabs {
	protected String book = "";
	protected String sort = "";

	public TemplatesTabs() {}

	public TemplatesTabs(String book) {
		this.book = book;
	}

	public TemplatesTabs(String book, String sort) {
		this.book = book;
		this.sort = sort;
	}

	/**
	 * @return the book
	 */
	public String getBook() {
		return this.book;
	}

	/**
	 * @param book the book to set
	 */
	public void setBook(String book) {
		this.book = book;
	}

	/**
	 * @return the sort
	 */
	public String getSort() {
		return this.sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

	public abstract String generateHTMLCode();
	public abstract MustacheObject generateMustacheObject();
}