/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.library.templates.objects;

import org.library.Functions;
import org.library.mustache.MustacheObject;
import org.library.mustache.MustacheSortfield;
import org.library.templates.TemplatesObject;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class TemplateSiteFunctions extends TemplatesObject {
	public TemplateSiteFunctions(String tab) {
		super(tab);
	}

	public TemplateSiteFunctions(String tab, String sort) {
		super(tab, sort);
	}
	
	public TemplateSiteFunctions(String tab, String sort, String search) {
		super(tab, sort, search);
	}

	/*@Override
	public String generateHTMLCode(Object o) {
		throw new UnsupportedOperationException("Not supported yet.");
	}*/

	@Override
	public MustacheObject generateMustacheObject(Object o) {
		if ( o.equals("sortfield") )
			return this.sortfield();
		else
			return null;
	}

	private MustacheSortfield sortfield() {
		MustacheSortfield sortfield = new MustacheSortfield(Functions.getLanguage().getSort());
		sortfield.addLine(Functions.getLanguage().getAuthor(), this.tab, (this.search.equals("") ? "" : this.search), (this.sort.equals("dauthor") ? "" : "dauthor"), (this.sort.equals("author") ? "" : "author"));
		sortfield.addLine(Functions.getLanguage().getTitle(), this.tab, (this.search.equals("") ? "" : this.search), (this.sort.equals("dtitle") ? "" : "dtitle"), (this.sort.equals("title") ? "" : "title"));
		sortfield.addLine(Functions.getLanguage().getLanguage(), this.tab, (this.search.equals("") ? "" : this.search), (this.sort.equals("dlanguage") ? "" : "dlanguage"), (this.sort.equals("language") ? "" : "language"));
		sortfield.addLine(Functions.getLanguage().getPublisher(), this.tab, (this.search.equals("") ? "" : this.search), (this.sort.equals("dpublisher") ? "" : "dpublisher"), (this.sort.equals("publisher") ? "" : "publisher"));
		sortfield.addLine(Functions.getLanguage().getPublished(), this.tab, (this.search.equals("") ? "" : this.search), (this.sort.equals("dpublished") ? "" : "dpublished"), (this.sort.equals("published") ? "" : "published"));
		sortfield.addLine(Functions.getLanguage().getPurchased(), this.tab, (this.search.equals("") ? "" : this.search), (this.sort.equals("dpurchased") ? "" : "dpurchased"), (this.sort.equals("purchased") ? "" : "purchased"));
		sortfield.addLine(Functions.getLanguage().getRead(), this.tab, (this.search.equals("") ? "" : this.search), (this.sort.equals("dread") ? "" : "dread"), (this.sort.equals("read") ? "" : "read"));

		return sortfield;
	}

	private String getLink(String sort) {
		return "?tab=" + this.tab + (search.equals("") ? "" : "&search=" + this.search) + "&sort=" + sort;
	}
}