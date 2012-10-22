/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.library.templates.tabs;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import java.io.StringWriter;
import java.util.List;
import org.library.Functions;
import org.library.db.hibernate.classes.Book;
import org.library.mappings.SUBMapping;
import org.library.mustache.*;
import org.library.templates.TemplatesContent;
import org.library.templates.objects.TemplateBook;
import org.library.templates.objects.TemplateBookOverview;
import org.library.templates.objects.TemplateSiteFunctions;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class TemplateSUBContent extends TemplatesContent {
	public TemplateSUBContent() {}

	public TemplateSUBContent(String book) {
		super(book);
	}

	public TemplateSUBContent(String book, String sort) {
		super(book, sort);
	}

	public TemplateSUBContent(String book, String sort, String site) {
		super(book, sort, site);
	}

	@Override
	public String generateHTMLCode() {
		Mustache mustache = new DefaultMustacheFactory(Functions.getMustacheTemplateDirectory()).compile("TemplateBookTabs.mustache");
		StringWriter writer = new StringWriter();
		mustache.execute(writer, this.generateMustacheObject());
		writer.flush();

		return writer.toString();
	}

	@Override
	public MustacheObject generateMustacheObject() {
		MustacheBookContent sub = new MustacheBookContent();
		TemplateSiteFunctions tsf = new TemplateSiteFunctions("sub", this.sort);
		TemplateBookOverview tbo = new TemplateBookOverview("sub", this.sort, this.site);
		TemplateBook tb = new TemplateBook("sub", this.sort, this.site);
		sub.setTemplateSortfield((MustacheSortfield)tsf.generateMustacheObject("sortfield"));
		sub.setLeft_arrow(!this.site.equals("0"));
		sub.setPrev_site("?tab=sub" + (this.site.equals("1") ? "" : "&amp;site=" + (Integer.parseInt(this.site) - 1)));
		sub.setNext_site("?tab=sub" + "&amp;site=" + (Integer.parseInt(this.site) + 1));

		SUBMapping sm = new SUBMapping();
		sm.setSort((this.sort.startsWith("d") ? this.sort.substring(1) : this.sort), this.sort.startsWith("d"));
		sm.setLimit(30);
		sm.setOffset(Integer.parseInt(this.site) * 30);
		sm.open();
		sm.beginTransaction();
		List<Book> result = sm.getBooks();
		sub.setRight_arrow(sm.getNextArrow((Integer.parseInt(this.site)) * 30, 30) >= 30);

		int count = 0;
		float sum = 0.0f;

		for ( Book b : (List<Book>) result ) {
			if ( this.book.equals(b.getIsbn()) )
				sub.addBook((MustacheBook)tb.generateMustacheObject(b));
			else
				sub.addBook((MustacheBookOverview)tbo.generateMustacheObject(b));

			count++;
			sum += b.getPrice();
		}

		sub.setCount(String.valueOf(count));
		sub.setSum(String.valueOf(Functions.toHTML(Functions.format(sum))));

		sm.close();

		return sub;
	}
}