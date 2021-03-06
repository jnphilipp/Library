/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.library.templates.objects;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import java.io.StringWriter;
import org.library.Functions;
import org.library.db.hibernate.classes.Book;
import org.library.mustache.MustacheBookOverview;
import org.library.mustache.MustacheObject;
import org.library.templates.TemplatesObject;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class TemplateBookOverview extends TemplatesObject {
	public TemplateBookOverview(String tab) {
		super(tab);
	}

	public TemplateBookOverview(String tab, String sort) {
		super(tab, sort);
	}

	public TemplateBookOverview(String tab, String sort, String site) {
		super(tab, sort, site);
	}

	public TemplateBookOverview(String tab, String sort, String site, String search) {
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
		Mustache mustache = new DefaultMustacheFactory(Functions.getMustacheTemplateDirectory()).compile("TemplateBookOverview.mustache");
		StringWriter writer = new StringWriter();
		mustache.execute(writer, this.generateBookOverview(book));
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
		MustacheBookOverview bookoverview = new MustacheBookOverview(book.getIsbn(), this.getLink(book.getIsbn()), Functions.getImagePath(book.getIsbn()), book.toShortString());
		bookoverview.addLine(Functions.getLanguage().getTitle(), book.getTitle());
		if ( book.getSeries() != null )
			bookoverview.addLine(Functions.getLanguage().getSeries(), Functions.getLanguage().getVolume() + " " + book.getVolume() + " " + Functions.getLanguage().getOf() + " " + book.getSeries().toString());
		bookoverview.addLine(Functions.getLanguage().getAuthor(), book.getAuthor().toString());
		bookoverview.addLine(Functions.getLanguage().getPublisher(), book.getPublisher().toString());
		bookoverview.addLine(Functions.getLanguage().getPublished(), book.getPublishedToString());
		bookoverview.addLine(Functions.getLanguage().getLanguage(), book.getLanguage().toString());
		bookoverview.addLine(Functions.getLanguage().getPrice(), Functions.toHTML(Functions.format(book.getPrice())));

		if ( book.hasPurchased() )
			bookoverview.addLine(Functions.getLanguage().getPurchased(), book.getPurchasedToString());
		if ( book.hasRead() )
			bookoverview.addLine(Functions.getLanguage().getRead(), book.getReadToString());

		return bookoverview;
	}

	private String getLink(String isbn) {
		return "?tab=" + this.tab + (search.equals("") ? "" : "&amp;search=" + this.search) + (this.site.equals("") ? "" : "&amp;site=" + this.site) + (this.sort.equals("") ? "" :  "&amp;sort=" + this.sort) + "&amp;b=" + isbn + "#" + isbn;
	}
}