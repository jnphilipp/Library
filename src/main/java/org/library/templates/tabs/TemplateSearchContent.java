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
import org.library.templates.TemplatesContent;
import org.library.templates.objects.TemplateBook;
import org.library.templates.objects.TemplateBookOverview;
import org.library.templates.objects.TemplateSiteFunctions;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class TemplateSearchContent extends TemplatesContent {
	private String search = "";

	public TemplateSearchContent() {}

	public TemplateSearchContent(String book) {
		super(book);
	}

	public TemplateSearchContent(String book, String sort) {
		super(book, (sort.equals("") ? "dpurchased" : sort));
	}

	public TemplateSearchContent(String book, String sort, String site) {
		super(book, (sort.equals("") ? "dpurchased" : sort), site);
	}

	public TemplateSearchContent(String book, String sort, String site, String search) {
		super(book, (sort.equals("") ? "dpurchased" : sort), site);
		this.search = search;
	}

	@Override
	public String generateHTMLCode() {
		Mustache mustache = new DefaultMustacheFactory(Functions.getMustacheTemplateDirectory()).compile("TemplateBookTabs.mustache");
		StringWriter writer = new StringWriter();
		mustache.execute(writer, (MustacheBookContent)this.generateMustacheObject());
		writer.flush();

		return writer.toString();
	}

	@Override
	public MustacheObject generateMustacheObject() {
		MustacheBookContent mbookTabs = new MustacheBookContent(Functions.getLanguage().getTabSearch(), Functions.getLanguage().getSearch());
		TemplateSiteFunctions tsf = new TemplateSiteFunctions("search", this.sort, this.site, this.search);
		TemplateBookOverview tbo = new TemplateBookOverview("search", this.sort, this.site, this.search);
		TemplateBook tb = new TemplateBook("search", this.sort, this.site, this.search);
		mbookTabs.setTemplateSortfield((MustacheSortfield)tsf.generateMustacheObject("sortfield"));
		mbookTabs.setLeft_arrow(!this.site.equals("0"));
		mbookTabs.setPrev_site("?tab=search" + (this.search.equals("") ? "" : "&amp;" + this.search) + (this.site.equals("1") ? "" : "&amp;site=" + (Integer.parseInt(this.site) - 1)));
		mbookTabs.setNext_site("?tab=search" + (this.search.equals("") ? "" : "&amp;" + this.search) + "&amp;site=" + (Integer.parseInt(this.site) + 1));

		SearchMapping sm = new SearchMapping();
		sm.setSearch(this.search);
		sm.setSort((this.sort.startsWith("d") ? this.sort.substring(1) : this.sort), this.sort.startsWith("d"));

		if ( this.search.equals("") ) {
			sm.setLimit(20);
			sm.setOffset(Integer.parseInt(this.site) * 20);
		}

		sm.open();
		sm.beginTransaction();
		List<Book> result = sm.getBooks();
		mbookTabs.setRight_arrow(sm.getNextArrow((Integer.parseInt(this.site)) * 20, 20) >= 20);

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