/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.library.templates;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import java.io.IOException;
import java.io.StringWriter;
import java.net.MalformedURLException;
import org.apache.log4j.Logger;
import org.library.Functions;
import org.library.mustache.*;
import org.library.templates.tabs.*;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class TemplateSite extends TemplatesContent {
	private String tab = "";
	private String search = "";
	private String message = "";
	private String addURL= "";

	public TemplateSite(String tab) {
		this.tab = tab;
	}

	public TemplateSite(String tab, String book) {
		super(book);
		this.tab = tab;
	}

	public TemplateSite(String tab, String book, String sort) {
		super(book, sort);
		this.tab = tab;
	}

	public TemplateSite(String tab, String book, String sort, String site) {
		super(book, sort, site);
		this.tab = tab;
	}

	public TemplateSite(String tab, String book, String sort, String site, String search) {
		super(book, sort, site);
		this.tab = tab;
		this.search = search;
	}

	public TemplateSite(String tab, String book, String sort, String site, String search, String message) {
		super(book, sort, site);
		this.tab = tab;
		this.search = search;
		this.message = message;
	}

	public TemplateSite(String tab, String book, String sort, String site, String search, String message, String addURL) {
		super(book, sort, site);
		this.tab = tab;
		this.search = search;
		this.message = message;
		this.addURL = addURL;
	}

	@Override
	public String generateHTMLCode() {
		Mustache mustache = new DefaultMustacheFactory(Functions.getMustacheTemplateDirectory()).compile("TemplateSite.mustache");
		StringWriter writer = new StringWriter();
		mustache.execute(writer, this.generateMustacheObject());
		writer.flush();

		return writer.toString();
	}

	@Override
	public MustacheObject generateMustacheObject() {
		MustacheSite site = new MustacheSite();
		if ( !this.message.equals("") )
			site.setMessagetext(message);

		site.addNavElem((this.tab.equals("") || this.tab.equals("search")), "?tab=search", Functions.getLanguage().getTabSearch());
		site.addNavElem(this.tab.equals("library"), "?tab=library", Functions.getLanguage().getTabLibrary());
		site.addNavElem(this.tab.equals("purchased"), "?tab=purchased", Functions.getLanguage().getTabPurchased());
		site.addNavElem(this.tab.equals("sub"), "?tab=sub", Functions.getLanguage().getTabSUB());
		site.addNavElem(this.tab.equals("read"), "?tab=read", Functions.getLanguage().getTabRead());
		site.addNavElem(this.tab.equals("wish"), "?tab=wish", Functions.getLanguage().getTabWishList());
		site.addNavElem(this.tab.equals("statistics"), "?tab=statistics", Functions.getLanguage().getTabStatistics());
		site.addNavElem(this.tab.equals("preferences"), "?tab=preferences", "Preferences");

		if ( !this.tab.equals("update") )
			site.addNavElem(this.tab.equals("add"), "?tab=add", Functions.getLanguage().getTabAdd());

		if ( this.tab.equals("") || this.tab.equals("search") )
			site.addContent((MustacheBookContent)new TemplateSearchContent(this.book, this.sort, this.site, this.search, Functions.getMaxBookCount("search")).generateMustacheObject());
		else if ( this.tab.equals("library") )
			site.addContent((MustacheBookContent)new TemplateLibraryContent(this.book, this.sort, this.site, Functions.getMaxBookCount("library")).generateMustacheObject());
		else if ( this.tab.equals("purchased") )
			site.addContent((MustacheBookContent)new TemplatePurchasedContent(this.book, this.sort, this.site, Functions.getMaxBookCount("purchased")).generateMustacheObject());
		else if ( this.tab.equals("sub") )
			site.addContent((MustacheBookContent)new TemplateSUBContent(this.book, this.sort, this.site, Functions.getMaxBookCount("sub")).generateMustacheObject());
		else if ( this.tab.equals("read") )
			site.addContent((MustacheBookContent)new TemplateReadContent(this.book, this.sort, this.site, Functions.getMaxBookCount("read")).generateMustacheObject());
		else if ( this.tab.equals("wish") )
			site.addContent((MustacheBookContent)new TemplateWishListContent(this.book, this.sort, this.site, Functions.getMaxBookCount("wish")).generateMustacheObject());
		else if ( this.tab.equals("statistics") )
			site.addContent((MustacheStatisticsContent)new TemplateStatisticsContent().generateMustacheObject());
		else if ( this.tab.equals("update") ) {
			site.addNavElem(true, "?tab=update&amp;b=" + this.book, Functions.getLanguage().getTabUpdate());
			site.addContent((MustacheAddUpdateContent)new TemplateUpdateContent(this.book, this.sort, this.site, this.search).generateMustacheObject());
		}
		else if ( this.tab.equals("add") ) {
			try {
				site.addContent((MustacheAddUpdateContent)new TemplateAddContent(this.addURL).generateMustacheObject());
			}
			catch ( MalformedURLException e ) {
				Logger.getLogger(TemplateSite.class).error(e);
			}
			catch ( IOException e ) {
				Logger.getLogger(TemplateSite.class).error(e);
			}
		}

		return site;
	}
}