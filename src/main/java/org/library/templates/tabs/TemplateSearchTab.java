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
import org.library.mappings.SearchMapping;
import org.library.mustache.*;
import org.library.templates.TemplatesTabs;
import org.library.templates.objects.TemplateBook;
import org.library.templates.objects.TemplateBookOverview;
import org.library.templates.objects.TemplateSiteFunctions;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class TemplateSearchTab extends TemplatesTabs {
	private String search = "";

	public TemplateSearchTab() {}

	public TemplateSearchTab(String book) {
		super(book);
	}

	public TemplateSearchTab(String book, String sort) {
		super(book, (sort.equals("") ? "dpurchased" : sort));
	}

	public TemplateSearchTab(String book, String sort, String search) {
		super(book, (sort.equals("") ? "dpurchased" : sort));
		this.search = search;
	}

	@Override
	public String generateHTMLCode() {
		Mustache mustache = new DefaultMustacheFactory(Functions.getMustacheTemplateDirectory()).compile("TemplateBookTabs.mustache");
		StringWriter writer = new StringWriter();
		mustache.execute(writer, (MustacheBookTabs)this.generateMustacheObject());
		writer.flush();

		return writer.toString();
	}

	@Override
	public MustacheObject generateMustacheObject() {
		MustacheBookTabs mbookTabs = new MustacheBookTabs(Functions.getLanguage().getTabSearch(), Functions.getLanguage().getSearch());
		TemplateSiteFunctions tsf = new TemplateSiteFunctions("search", this.sort, this.search);
		TemplateBookOverview tbo = new TemplateBookOverview("search", this.sort, this.search);
		TemplateBook tb = new TemplateBook("search", this.sort, this.search);

		mbookTabs.setTemplateSortfield((MustacheSortfield)tsf.generateMustacheObject("sortfield"));

		SearchMapping sm = new SearchMapping();
		sm.setSearch(this.search);
		sm.setSort((this.sort.startsWith("d") ? this.sort.substring(1) : this.sort), this.sort.startsWith("d"));

		if ( this.search.equals("") )
			sm.setLimit(30);

		sm.open();
		sm.beginTransaction();
		List<Book> result = sm.getBooks();

		int count = 0;
		float sum = 0.0f;

		for ( Book b : (List<Book>) result ) {
			if ( this.book.equals(b.getIsbn()) )
				mbookTabs.addBook((MustacheBook)tb.generateMustacheObject(b));
			else
				mbookTabs.addBook((MustacheBookOverview)tbo.generateMustacheObject(b));

			count++;
			sum += b.getPrice();
		}

		mbookTabs.setCount(String.valueOf(count));
		mbookTabs.setSum(String.valueOf(Functions.toHTML(Functions.format(sum))));

		sm.close();

		return mbookTabs;
	}
}