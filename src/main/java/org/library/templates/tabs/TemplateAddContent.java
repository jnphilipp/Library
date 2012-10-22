/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.library.templates.tabs;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import java.io.IOException;
import java.io.StringWriter;
import java.net.MalformedURLException;
import org.library.Functions;
import org.library.db.hibernate.classes.Book;
import org.library.html.sites.HTMLAmazon_de;
import org.library.mustache.MustacheAddUpdateContent;
import org.library.mustache.MustacheBookField;
import org.library.mustache.MustacheObject;
import org.library.templates.TemplateBookField;
import org.library.templates.TemplatesContent;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class TemplateAddContent extends TemplatesContent {
	private Book cBook = null;

	public TemplateAddContent() {}

	public TemplateAddContent(String amazon) throws MalformedURLException, IOException {
		this.fetch(amazon);
	}

	public final void fetch(String link) throws MalformedURLException, IOException {
		if ( link.equals("") )
			return;

		HTMLAmazon_de html = new HTMLAmazon_de();
		html.fetch(link);
		this.cBook = html.getBook();
	}

	@Override
	public String generateHTMLCode() {
		Mustache mustache = new DefaultMustacheFactory(Functions.getMustacheTemplateDirectory()).compile("TemplateAddUpdateTab.mustache");
		StringWriter writer = new StringWriter();
		mustache.execute(writer, this.generateMustacheObject());
		writer.flush();

		return writer.toString();
	}

	@Override
	public MustacheObject generateMustacheObject() {
		return new MustacheAddUpdateContent(Functions.getLanguage().getFetch(), (MustacheBookField)new TemplateBookField(this.cBook, false).generateMustacheObject());
	}
}