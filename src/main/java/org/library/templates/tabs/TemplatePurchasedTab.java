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
import org.library.mappings.PurchasedMapping;
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
public class TemplatePurchasedTab extends TemplatesTabs {
	public TemplatePurchasedTab() {}

	public TemplatePurchasedTab(String book) {
		super(book);
	}

	public TemplatePurchasedTab(String book, String sort) {
		super(book, (sort.equals("") ? "dpurchased" : sort));
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
		MustacheBookTabs library = new MustacheBookTabs();
		TemplateSiteFunctions tsf = new TemplateSiteFunctions("purchased", this.sort);
		TemplateBookOverview tbo = new TemplateBookOverview("purchased", this.sort);
		TemplateBook tb = new TemplateBook("purchased", this.sort);
		library.setTemplateSortfield((MustacheSortfield)tsf.generateMustacheObject("sortfield"));

		PurchasedMapping pm = new PurchasedMapping();
		pm.setSort((this.sort.startsWith("d") ? this.sort.substring(1) : this.sort), this.sort.startsWith("d"));
		pm.open();
		pm.beginTransaction();
		List<Book> result = pm.getBooks();

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

		pm.close();

		return library;
	}
}