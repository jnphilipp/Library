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
import org.library.mappings.WishListMapping;
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
public class TemplateWishListTab extends TemplatesTabs {
	public TemplateWishListTab() {}

	public TemplateWishListTab(String book) {
		super(book);
	}

	public TemplateWishListTab(String book, String sort) {
		super(book, (sort.equals("") ? "published" : sort));
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
		MustacheBookTabs wishlist = new MustacheBookTabs();
		TemplateSiteFunctions tsf = new TemplateSiteFunctions("wish", this.sort);
		TemplateBookOverview tbo = new TemplateBookOverview("wish", this.sort);
		TemplateBook tb = new TemplateBook("wish", this.sort);
		wishlist.setTemplateSortfield((MustacheSortfield)tsf.generateMustacheObject("sortfield"));

		WishListMapping wlm = new WishListMapping();
		wlm.setSort((this.sort.startsWith("d") ? this.sort.substring(1) : this.sort), this.sort.startsWith("d"));
		wlm.setLimit(100);
		wlm.open();
		wlm.beginTransaction();
		List<Book> result = wlm.getBooks();

		int count = 0;
		float sum = 0.0f;

		for ( Book b : (List<Book>) result ) {
			if ( this.book.equals(b.getIsbn()) )
				wishlist.addBook((MustacheBook)tb.generateMustacheObject(b));
			else
				wishlist.addBook((MustacheBookOverview)tbo.generateMustacheObject(b));

			count++;
			sum += b.getPrice();
		}

		wishlist.setCount(String.valueOf(count));
		wishlist.setSum(String.valueOf(Functions.toHTML(Functions.format(sum))));

		wlm.close();

		return wishlist;
	}
}