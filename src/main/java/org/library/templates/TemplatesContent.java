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
public abstract class TemplatesContent {
	protected String book = "";
	protected String sort = "";
	protected String site = "0";

	public TemplatesContent() {}

	public TemplatesContent(String book) {
		this.book = book;
	}

	public TemplatesContent(String book, String sort) {
		this.book = book;
		this.sort = sort;
	}

	public TemplatesContent(String book, String sort, String site) {
		this.book = book;
		this.sort = sort;
		this.site = site;
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

	/**
	 * @return the site
	 */
	public String getSite() {
		return this.site;
	}

	/**
	 * @param site the site to set
	 */
	public void setSite(String site) {
		this.site = site;
	}

	public abstract String generateHTMLCode();
	public abstract MustacheObject generateMustacheObject();
}