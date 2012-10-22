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
import org.library.templates.TemplatesContent;
import org.library.templates.objects.TemplateBook;
import org.library.templates.objects.TemplateBookOverview;
import org.library.templates.objects.TemplateSiteFunctions;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class TemplateWishListContent extends TemplatesContent {
	public TemplateWishListContent() {}

	public TemplateWishListContent(String book) {
		super(book);
	}

	public TemplateWishListContent(String book, String sort) {
		super(book, (sort.equals("") ? "published" : sort));
	}

	public TemplateWishListContent(String book, String sort, String site) {
		super(book, (sort.equals("") ? "published" : sort), site);
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
		MustacheBookContent wishlist = new MustacheBookContent();
		TemplateSiteFunctions tsf = new TemplateSiteFunctions("wish", this.sort);
		TemplateBookOverview tbo = new TemplateBookOverview("wish", this.sort);
		TemplateBook tb = new TemplateBook("wish", this.sort);
		//wishlist.setTemplateSortfield((MustacheSortfield)tsf.generateMustacheObject("sortfield"));
		wishlist.setLeft_arrow(!this.site.equals("0"));
		wishlist.setPrev_site("?tab=sub" + (this.site.equals("1") ? "" : "&amp;site=" + (Integer.parseInt(this.site) - 1)));
		wishlist.setNext_site("?tab=sub" + "&amp;site=" + (Integer.parseInt(this.site) + 1));

		WishListMapping wlm = new WishListMapping();
		wlm.setSort((this.sort.startsWith("d") ? this.sort.substring(1) : this.sort), this.sort.startsWith("d"));
		wlm.setLimit(30);
		wlm.setOffset(Integer.parseInt(this.site) * 30);
		wlm.open();
		wlm.beginTransaction();
		List<Book> result = wlm.getBooks();
		wishlist.setRight_arrow(wlm.getNextArrow((Integer.parseInt(this.site)) * 30, 30) >= 30);

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