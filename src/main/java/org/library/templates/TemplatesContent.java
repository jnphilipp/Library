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
	protected int maxBookCount = 0;

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

	public TemplatesContent(String book, String sort, String site, int maxBookCount) {
		this.book = book;
		this.sort = sort;
		this.site = site;
		this.maxBookCount = maxBookCount;
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

	/**
	 * @return the maxBookCount
	 */
	public int getMaxBookCount() {
		return maxBookCount;
	}

	/**
	 * @param maxBookCount the maxBookCount to set
	 */
	public void setMaxBookCount(int maxBookCount) {
		this.maxBookCount = maxBookCount;
	}

	public abstract String generateHTMLCode();
	public abstract MustacheObject generateMustacheObject();
}