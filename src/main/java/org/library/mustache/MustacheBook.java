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
public class MustacheBook implements MustacheObject {
	private Book book = null;

	public MustacheBook(MustacheBookOverview templateBookOverview, String href, String title) {
		this.book = new Book(templateBookOverview, href, title);
	}

	public void addAuthor(String th, String author) {
		this.book.setAuthor(new Author(th, author));
	}

	public void addAuthor(String th, String author, boolean coauthor, String coauthors) {
		this.book.setAuthor(new Author(th, author, coauthor, coauthors));
	}

	public void addLine(String th, String td) {
		this.book.addLine(new Line(th, td));
	}

	public void addEbook(String ebook, String href, String read) {
		this.book.setEbook(new Ebook(ebook, href, read));
	}

	public void addUpdate(String isbn, String update) {
		this.book.setUpdate(new Update(isbn, update));
	}

	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}

	public static class Book {
		private MustacheBookOverview templateBookOverview = null;
		private String image = "";
		private String ititle = "";
		private String href = "";
		private String title = "";
		private Author author = null;
		private List<Line> line = new ArrayList<Line>();
		private Ebook ebook = null;
		private Update update = null;

		public Book(MustacheBookOverview templateBookOverview, String href, String title) {
			this.templateBookOverview = templateBookOverview;
			this.image = this.templateBookOverview.getOverview().getImage();
			this.ititle = this.templateBookOverview.getOverview().getTitle();
			this.href = href;
			this.title = title;
		}

		/**
		 * @return the templateBookOverview
		 */
		public MustacheBookOverview getTemplateBookOverview() {
			return templateBookOverview;
		}

		/**
		 * @return the image
		 */
		public String getImage() {
			return image;
		}

		/**
		 * @return the ititle
		 */
		public String getItitle() {
			return ititle;
		}

		/**
		 * @return the href
		 */
		public String getHref() {
			return href;
		}

		/**
		 * @return the title
		 */
		public String getTitle() {
			return title;
		}

		/**
		 * @return the author
		 */
		public Author getAuthor() {
			return author;
		}

		/**
		 * @param author the author to set
		 */
		public void setAuthor(Author author) {
			this.author = author;
		}

		/**
		 * @return the line
		 */
		public List<Line> getLine() {
			return line;
		}

		public void addLine(Line line) {
			this.line.add(line);
		}

		/**
		 * @return the ebook
		 */
		public Ebook getEbook() {
			return ebook;
		}

		/**
		 * @param ebook the ebook to set
		 */
		public void setEbook(Ebook ebook) {
			this.ebook = ebook;
		}

		/**
		 * @return the update
		 */
		public Update getUpdate() {
			return update;
		}

		/**
		 * @param update the update to set
		 */
		public void setUpdate(Update update) {
			this.update = update;
		}
	}

	public static class Author {
		private String th = "";
		private String author = "";
		private boolean coauthor = false;
		private String coauthors = "";

		public Author(String th, String author) {
			this.th = th;
			this.author = author;
		}

		public Author(String th, String author, boolean coauthor, String coauthors) {
			this.th = th;
			this.author = author;
			this.coauthor = coauthor;
			this.coauthors = coauthors;
		}

		/**
		 * @return the th
		 */
		public String getTh() {
			return this.th;
		}

		/**
		 * @return the author
		 */
		public String getAuthor() {
			return author;
		}

		/**
		 * @return the coauthor
		 */
		public boolean isCoauthor() {
			return coauthor;
		}

		/**
		 * @return the coauthors
		 */
		public String getCoauthors() {
			return coauthors;
		}
	}

	public static class Line {
		private String th = "";
		private String td = "";

		public Line(String th, String td) {
			this.th = th;
			this.td = td;
		}

		/**
		 * @return the th
		 */
		public String getTh() {
			return th;
		}

		/**
		 * @return the td
		 */
		public String getTd() {
			return td;
		}
	}

	public static class Ebook {
		private String ebook = "";
		private String href = "";
		private String read = "";

		public Ebook(String ebook, String href, String read) {
			this.ebook = ebook;
			this.href = href;
			this.read = read;
		}

		/**
		 * @return the ebook
		 */
		public String getEbook() {
			return ebook;
		}

		/**
		 * @return the href
		 */
		public String getHref() {
			return href;
		}

		/**
		 * @return the read
		 */
		public String getRead() {
			return read;
		}
	}

	public static class Update {
		private String isbn = "";
		private String update = "";

		public Update(String isbn, String update) {
			this.isbn = isbn;
			this.update = update;
		}

		/**
		 * @return the isbn
		 */
		public String getIsbn() {
			return isbn;
		}

		/**
		 * @return the update
		 */
		public String getUpdate() {
			return update;
		}
	}
}