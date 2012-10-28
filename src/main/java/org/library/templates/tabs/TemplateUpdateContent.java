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
import org.library.mustache.MustacheAddUpdateContent;
import org.library.mustache.MustacheBookField;
import org.library.mustache.MustacheBookContent;
import org.library.mustache.MustacheObject;
import org.library.templates.TemplateBookField;
import org.library.templates.TemplatesContent;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class TemplateUpdateContent extends TemplatesContent {
	private String search = "";

	public TemplateUpdateContent() {}

	public TemplateUpdateContent(String book) {
		super(book);
	}

	public TemplateUpdateContent(String book, String sort) {
		super(book, sort);
	}

	public TemplateUpdateContent(String book, String sort, String site) {
		super(book, sort, site);
	}

	public TemplateUpdateContent(String book, String sort, String site, String search) {
		super(book, sort, site);
		this.search = search;
	}

	@Override
	public String generateHTMLCode() {
		Mustache mustache = new DefaultMustacheFactory(Functions.getMustacheTemplateDirectory()).compile("TemplateAddUpdateTab.mustache");
		StringWriter writer = new StringWriter();
		mustache.execute(writer, (MustacheBookContent)this.generateMustacheObject());
		writer.flush();

		return writer.toString();
	}

	@Override
	public MustacheObject generateMustacheObject() {
		MustacheAddUpdateContent update = new MustacheAddUpdateContent();

		UpdateMapping um = new UpdateMapping();
		um.open();
		um.beginTransaction();

		um.setSearch(this.book);
		List<Book> result = um.getBooks();

		update.setTemplateBookField((MustacheBookField)new TemplateBookField(sort, site, search, result.get(0), true).generateMustacheObject());

		um.close();

		return update;
	}
}