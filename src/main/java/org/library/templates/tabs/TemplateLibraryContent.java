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
import org.library.mappings.LibraryMapping;
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
public class TemplateLibraryContent extends TemplatesContent {
	public TemplateLibraryContent() {}

	public TemplateLibraryContent(String book) {
		super(book);
	}

	public TemplateLibraryContent(String book, String sort) {
		super(book, sort);
	}

	public TemplateLibraryContent(String book, String sort, String site) {
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
		MustacheBookContent library = new MustacheBookContent();
		TemplateSiteFunctions tsf = new TemplateSiteFunctions("library", this.sort, this.site);
		TemplateBookOverview tbo = new TemplateBookOverview("library", this.sort, this.site);
		TemplateBook tb = new TemplateBook("library", this.sort, this.site);
		library.setTemplateSortfield((MustacheSortfield)tsf.generateMustacheObject("sortfield"));
		library.setLeft_arrow(!this.site.equals("0"));
		library.setRight_arrow(true);
		library.setPrev_site("?tab=library" + (this.site.equals("1") ? "" : "&amp;site=" + (Integer.parseInt(this.site) - 1)));
		library.setNext_site("?tab=library&amp;site=" + (Integer.parseInt(this.site) + 1));

		LibraryMapping lm = new LibraryMapping();
		lm.setSort((this.sort.startsWith("d") ? this.sort.substring(1) : this.sort), this.sort.startsWith("d"));
		lm.setLimit(30);
		lm.setOffset(Integer.parseInt(this.site) * 30);
		lm.open();
		lm.beginTransaction();
		List<Book> result = lm.getBooks();
		library.setRight_arrow(lm.getNextArrow((Integer.parseInt(this.site)) * 30, 30) >= 30);

		int count = 0;
		float sum = 0.0f;

		for ( Book b : (List<Book>) result ) {
			if ( this.book.equals(b.getIsbn()) )
				library.addBook((MustacheBook)tb.generateMustacheObject(b));
			else
				library.addBook((MustacheBookOverview)tbo.generateMustacheObject(b));

			count++;
			sum += b.getPrice();
		}

		library.setCount(String.valueOf(count));
		library.setSum(String.valueOf(Functions.toHTML(Functions.format(sum))));

		lm.close();

		return library;
	}
}