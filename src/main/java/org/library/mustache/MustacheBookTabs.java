/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.library.mustache;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class MustacheBookTabs implements MustacheObject {
	private Searchfield searchfield = null;
	private String count = "";
	private String sum = "";
	private MustacheSortfield templateSortfield = null;
	private List<Books> books = new ArrayList<Books>();

	public MustacheBookTabs() {}

	public MustacheBookTabs(String legend, String button) {
		this.searchfield = new Searchfield(legend, button);
	}

	/**
	 * @return the searchfield
	 */
	public Searchfield getSearchfield() {
		return this.searchfield;
	}

	/**
	 * @return the count
	 */
	public String getCount() {
		return this.count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(String count) {
		this.count = count;
	}

	/**
	 * @return the sum
	 */
	public String getSum() {
		return this.sum;
	}

	/**
	 * @param sum the sum to set
	 */
	public void setSum(String sum) {
		this.sum = sum;
	}

	/**
	 * @return the templateSortfield
	 */
	public MustacheSortfield getTemplateSortfield() {
		return this.templateSortfield;
	}

	/**
	 * @param templateSortfield the templateSortfield to set
	 */
	public void setTemplateSortfield(MustacheSortfield templateSortfield) {
		this.templateSortfield = templateSortfield;
	}

	/**
	 * @return the books
	 */
	public List<Books> getBooks() {
		return this.books;
	}

	public void addBook(Books books) {
		this.books.add(books);
	}

	public void addBook(MustacheBook templateBook) {
		this.addBook(new Books(templateBook));
	}

	public void addBook(MustacheBookOverview templateBookOverview) {
		this.addBook(new Books(templateBookOverview));
	}

	public static class Searchfield {
		private String legend = "";
		private String button = "";

		public Searchfield(String legend, String button) {
			this.legend = legend;
			this.button = button;
		}

		/**
		 * @return the legend
		 */
		public String getLegend() {
			return this.legend;
		}

		/**
		 * @return the button
		 */
		public String getButton() {
			return this.button;
		}
	}

	public static class Books {
		private boolean overview = true;
		private MustacheBook templateBook = null;
		private MustacheBookOverview templateBookOverview = null;

		public Books(MustacheBookOverview templateBookOverview) {
			this.overview = true;
			this.templateBookOverview = templateBookOverview;
		}

		public Books(MustacheBook templateBook) {
			this.overview = false;
			this.templateBook = templateBook;
		}

		/**
		 * @return the overview
		 */
		public boolean isOverview() {
			return overview;
		}

		/**
		 * @return the templateBook
		 */
		public MustacheBook getTemplateBook() {
			return templateBook;
		}

		/**
		 * @return the templateBookOverview
		 */
		public MustacheBookOverview getTemplateBookOverview() {
			return templateBookOverview;
		}
	}
}