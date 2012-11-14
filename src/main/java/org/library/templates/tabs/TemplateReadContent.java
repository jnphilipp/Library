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
import org.library.mappings.ReadMapping;
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
public class TemplateReadContent extends TemplatesContent {
	public TemplateReadContent() {}

	public TemplateReadContent(String book) {
		super(book);
	}

	public TemplateReadContent(String book, String sort) {
		super(book, (sort.equals("") ? "dread" : sort));
	}

	public TemplateReadContent(String book, String sort, String site) {
		super(book, (sort.equals("") ? "dread" : sort), site);
	}

	public TemplateReadContent(String book, String sort, String site, int maxBookCount) {
		super(book, (sort.equals("") ? "dread" : sort), site, maxBookCount);
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
		MustacheBookContent read = new MustacheBookContent();
		TemplateSiteFunctions tsf = new TemplateSiteFunctions("read", this.sort);
		TemplateBookOverview tbo = new TemplateBookOverview("read", this.sort, this.site);
		TemplateBook tb = new TemplateBook("read", this.sort, this.site);
		read.setTemplateSortfield((MustacheSortfield)tsf.generateMustacheObject("sortfield"));
		read.setLeft_arrow(!this.site.equals("0"));
		read.setPrev_site("?tab=read" + (sort.equals("") ? "" : "&amp;sort=" + this.sort) + (this.site.equals("1") ? "" : "&amp;site=" + (Integer.parseInt(this.site) - 1)));
		read.setNext_site("?tab=read" + (sort.equals("") ? "" : "&amp;sort=" + this.sort) + "&amp;site=" + (Integer.parseInt(this.site) + 1));

		ReadMapping rm = new ReadMapping();
		rm.setSort((this.sort.startsWith("d") ? this.sort.substring(1) : this.sort), this.sort.startsWith("d"));
		rm.setLimit(this.maxBookCount);
		rm.setOffset(Integer.parseInt(this.site) * this.maxBookCount);
		rm.open();
		rm.beginTransaction();
		List<Book> result = rm.getBooks();
		read.setRight_arrow(result.size() >= this.maxBookCount);

		int count = 0;
		float sum = 0.0f;

		for ( Book b : (List<Book>) result ) {
			if ( this.book.equals(b.getIsbn()) )
				read.addBook((MustacheBook)tb.generateMustacheObject(b));
			else
				read.addBook((MustacheBookOverview)tbo.generateMustacheObject(b));

			count++;
			sum += b.getPrice();
		}

		read.setCount(String.valueOf(count));
		read.setSum(String.valueOf(Functions.toHTML(Functions.format(sum))));

		rm.close();

		return read;
	}
}