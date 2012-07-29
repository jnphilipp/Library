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
import org.library.mappings.UpdateMapping;
import org.library.mustache.MustacheAddUpdateTab;
import org.library.mustache.MustacheBookField;
import org.library.mustache.MustacheBookTabs;
import org.library.mustache.MustacheObject;
import org.library.templates.TemplateBookField;
import org.library.templates.TemplatesTabs;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class TemplateUpdateTab extends TemplatesTabs {
	public TemplateUpdateTab() {}

	public TemplateUpdateTab(String book) {
		super(book);
	}

	@Override
	public String generateHTMLCode() {
		Mustache mustache = new DefaultMustacheFactory(Functions.getMustacheTemplateDirectory()).compile("TemplateAddUpdateTab.mustache");
		StringWriter writer = new StringWriter();
		mustache.execute(writer, (MustacheBookTabs)this.generateMustacheObject());
		writer.flush();

		return writer.toString();
	}

	@Override
	public MustacheObject generateMustacheObject() {
		MustacheAddUpdateTab update = new MustacheAddUpdateTab();

		UpdateMapping um = new UpdateMapping();
		um.open();
		um.beginTransaction();

		um.setSearch(this.book);
		List<Book> result = um.getBooks();

		update.setTemplateBookField((MustacheBookField)new TemplateBookField(result.get(0), true).generateMustacheObject());

		um.close();

		return update;
	}
}