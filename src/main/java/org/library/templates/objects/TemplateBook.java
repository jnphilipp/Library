/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.library.templates.objects;

import org.library.Functions;
import org.library.db.hibernate.HibernateBookFunctions;
import org.library.db.hibernate.classes.Book;
import org.library.mustache.MustacheBook;
import org.library.mustache.MustacheBookOverview;
import org.library.mustache.MustacheObject;
import org.library.templates.TemplatesObject;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class TemplateBook extends TemplatesObject {
	public TemplateBook(String tab) {
		super(tab);
	}

	public TemplateBook(String tab, String sort) {
		super(tab, sort);
	}

	public TemplateBook(String tab, String sort, String site) {
		super(tab, sort, site);
	}

	public TemplateBook(String tab, String sort, String site, String search) {
		super(tab, sort, site, search);
	}

	/*@Override
	public String generateHTMLCode(Object o) {
		if ( o instanceof Book )
			return generateHTMLCode((Book)o);
		else
			return "";
	}

	public String generateHTMLCode(Book book) {
		Mustache mustache = new DefaultMustacheFactory(Functions.getMustacheTemplateDirectory()).compile("TemplateBook.mustache");
		StringWriter writer = new StringWriter();
		mustache.execute(writer, this.generateBook(book));
		writer.flush();

		return writer.toString();
	}*/

	@Override
	public MustacheObject generateMustacheObject(Object o) {
		if ( o instanceof Book )
			return generateMustacheObject((Book)o);
		else
			return null;
	}

	public MustacheObject generateMustacheObject(Book book) {
		HibernateBookFunctions.updateChanged(book.getIsbn());

		TemplateBookOverview tbo = new TemplateBookOverview("search", this.sort, this.site, this.search);
		MustacheBook mbook = new MustacheBook((MustacheBookOverview)tbo.generateMustacheObject(book), this.getLink(book.getIsbn()), book.getTitle());
		mbook.addAuthor(Functions.getLanguage().getAuthor(book.hasCoauthors()), book.getAuthor().toString(), book.hasCoauthors(), book.getCoauthors());
		mbook.addLine("ISBN", book.getIsbn());
		mbook.addLine(Functions.getLanguage().getPublisher(), book.getPublisher().toString());
		mbook.addLine(Functions.getLanguage().getPublished(), book.getPublishedToString());
		mbook.addLine(Functions.getLanguage().getBinding(), book.getBinding().toString());
		mbook.addLine(Functions.getLanguage().getLanguage(), book.getLanguage().toString());
		mbook.addLine(Functions.getLanguage().getPrice(), Functions.toHTML(Functions.format(book.getPrice())));

		if ( book.getSeries() != null )
			mbook.addLine(Functions.getLanguage().getSeries(), Functions.getLanguage().getVolume() + " " + book.getVolume() + " " + Functions.getLanguage().getOf() + " " + book.getSeries().toString());
		if ( book.hasPurchased() )
			mbook.addLine(Functions.getLanguage().getPurchased(), book.getPurchasedToString());
		if ( book.hasRead() )
			mbook.addLine(Functions.getLanguage().getRead(), book.getReadToString());
		if ( book.getPath() != null && !book.getPath().equals("") )
			mbook.addEbook(Functions.getLanguage().getPath(), book.getPath());
			//mbook.addEbook(Functions.getLanguage().getPath(), this.getLink(book.getIsbn(), book.getPath()), Functions.getLanguage().getRead());

		mbook.addUpdate(Functions.getLanguage().getUpdate(), this.getLinkUpdate(book.getIsbn()));

		return mbook;
	}

	private String getLink(String isbn) {
		return this.getLink(isbn, "");
	}

	private String getLink(String isbn, String path) {
		return "?tab=" + this.tab + (search.equals("") ? "" : "&amp;search=" + this.search) + (this.site.equals("") ? "" : "&amp;site=" + this.site) + (this.sort.equals("") ? "" :  "&amp;sort=" + this.sort) + (path.equals("") ? "" : "&amp;path=" + path) + "#" + isbn;
	}

	private String getLinkUpdate(String isbn) {
		return "?tab=update" + (search.equals("") ? "" : "&amp;search=" + this.search) + (this.site.equals("") ? "" : "&amp;site=" + this.site) + (this.sort.equals("") ? "" :  "&amp;sort=" + this.sort) + "&amp;b=" + isbn;
	}
}