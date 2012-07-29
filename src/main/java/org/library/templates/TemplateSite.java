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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.library.Functions;
import org.library.mustache.*;
import org.library.templates.tabs.*;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class TemplateSite extends TemplatesTabs {
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

	public TemplateSite(String tab, String book, String sort, String search) {
		super(book, sort);
		this.tab = tab;
		this.search = search;
	}

	public TemplateSite(String tab, String book, String sort, String search, String message) {
		super(book, sort);
		this.tab = tab;
		this.search = search;
		this.message = message;
	}

	public TemplateSite(String tab, String book, String sort, String search, String message, String addURL) {
		super(book, sort);
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
		site.addTab((this.tab.equals("") || this.tab.equals("search")), "?tab=search", Functions.getLanguage().getTabSearch());
		site.addTab(this.tab.equals("library"), "?tab=library", Functions.getLanguage().getTabLibrary());
		site.addTab(this.tab.equals("purchased"), "?tab=purchased", Functions.getLanguage().getTabPurchased());
		site.addTab(this.tab.equals("sub"), "?tab=sub", Functions.getLanguage().getTabSUB());
		site.addTab(this.tab.equals("read"), "?tab=read", Functions.getLanguage().getTabRead());
		site.addTab(this.tab.equals("wish"), "?tab=wish", Functions.getLanguage().getTabWishList());
		site.addTab(this.tab.equals("statistics"), "?tab=statistics", Functions.getLanguage().getTabStatistics());

		if ( !this.tab.equals("update") )
			site.addTab(this.tab.equals("add"), "?tab=add", Functions.getLanguage().getTabAdd());

		if ( this.tab.equals("") || this.tab.equals("search") )
			site.addPane((MustacheBookTabs)new TemplateSearchTab(this.book, this.sort, this.search).generateMustacheObject());
		else if ( this.tab.equals("library") )
			site.addPane((MustacheBookTabs)new TemplateLibraryTab(this.book, this.sort).generateMustacheObject());
		else if ( this.tab.equals("purchased") )
			site.addPane((MustacheBookTabs)new TemplatePurchasedTab(this.book, this.sort).generateMustacheObject());
		else if ( this.tab.equals("sub") )
			site.addPane((MustacheBookTabs)new TemplateSUBTab(this.book, this.sort).generateMustacheObject());
		else if ( this.tab.equals("read") )
			site.addPane((MustacheBookTabs)new TemplateReadTab(this.book, this.sort).generateMustacheObject());
		else if ( this.tab.equals("wish") )
			site.addPane((MustacheBookTabs)new TemplateWishListTab(this.book, this.sort).generateMustacheObject());
		else if ( this.tab.equals("statistics") )
			site.addPane((MustacheStatisticsTab)new TemplateStatisticsTab().generateMustacheObject());
		else if ( this.tab.equals("update") ) {
			site.addTab(true, "?tab=update&amp;b=" + this.book, Functions.getLanguage().getTabUpdate());
			site.addPane((MustacheAddUpdateTab)new TemplateUpdateTab(this.book).generateMustacheObject());
		}
		else if ( this.tab.equals("add") )
			try {
				site.addPane((MustacheAddUpdateTab)new TemplateAddTab(this.addURL).generateMustacheObject());
			}
			catch (MalformedURLException ex) {
				Logger.getLogger(TemplateSite.class.getName()).log(Level.SEVERE, null, ex);
			}
			catch (IOException ex) {
				Logger.getLogger(TemplateSite.class.getName()).log(Level.SEVERE, null, ex);
			}

		return site;
	}
}