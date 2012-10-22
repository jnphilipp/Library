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
import org.library.templates.TemplatesContent;
import org.library.templates.objects.TemplateBook;
import org.library.templates.objects.TemplateBookOverview;
import org.library.templates.objects.TemplateSiteFunctions;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class TemplatePurchasedContent extends TemplatesContent {
	public TemplatePurchasedContent() {}

	public TemplatePurchasedContent(String book) {
		super(book);
	}

	public TemplatePurchasedContent(String book, String sort) {
		super(book, (sort.equals("") ? "dpurchased" : sort));
	}

	public TemplatePurchasedContent(String book, String sort, String site) {
		super(book, (sort.equals("") ? "dpurchased" : sort), site);
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
		MustacheBookContent purchased = new MustacheBookContent();
		TemplateSiteFunctions tsf = new TemplateSiteFunctions("purchased", this.sort, this.site);
		TemplateBookOverview tbo = new TemplateBookOverview("purchased", this.sort, this.site);
		TemplateBook tb = new TemplateBook("purchased", this.sort, this.site);
		purchased.setTemplateSortfield((MustacheSortfield)tsf.generateMustacheObject("sortfield"));
		purchased.setLeft_arrow(!this.site.equals("0"));
		purchased.setPrev_site("?tab=purchased" + (this.site.equals("1") ? "" : "&amp;site=" + (Integer.parseInt(this.site) - 1)));
		purchased.setNext_site("?tab=purchased" + "&amp;site=" + (Integer.parseInt(this.site) + 1));

		PurchasedMapping pm = new PurchasedMapping();
		pm.setSort((this.sort.startsWith("d") ? this.sort.substring(1) : this.sort), this.sort.startsWith("d"));
		pm.setLimit(30);
		pm.setOffset(Integer.parseInt(this.site) * 30);
		pm.open();
		pm.beginTransaction();
		List<Book> result = pm.getBooks();
		purchased.setRight_arrow(pm.getNextArrow((Integer.parseInt(this.site)) * 30, 30) >= 30);

		int count = 0;
		float sum = 0.0f;

		for ( Book b : (List<Book>) result ) {
			if ( this.book.equals(b.getIsbn()) )
				purchased.addBook((MustacheBook)tb.generateMustacheObject(b));
			else
				purchased.addBook((MustacheBookOverview)tbo.generateMustacheObject(b));

			count++;
			sum += b.getPrice();
		}

		purchased.setCount(String.valueOf(count));
		purchased.setSum(String.valueOf(Functions.toHTML(Functions.format(sum))));

		pm.close();

		return purchased;
	}
}