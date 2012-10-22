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

	public TemplateSiteFunctions(String tab, String sort, String site) {
		super(tab, sort, site);
	}
	
	public TemplateSiteFunctions(String tab, String sort, String site, String search) {
		super(tab, sort, site, search);
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
		sortfield.addLine(Functions.getLanguage().getAuthor(), this.getLink("dauthor"), this.getLink("author"), !this.sort.equals("dauthor"), !this.sort.equals("author"));
		sortfield.addLine(Functions.getLanguage().getTitle(), this.getLink("dtitle"), this.getLink("title"), !this.sort.equals("dtitle"), !this.sort.equals("title"));
		sortfield.addLine(Functions.getLanguage().getLanguage(), this.getLink("dlanguage"), this.getLink("language"), !this.sort.equals("dlanguage"), !this.sort.equals("language"));
		sortfield.addLine(Functions.getLanguage().getPublisher(), this.getLink("dpublisher"), this.getLink("publisher"), !this.sort.equals("dpublisher"), !this.sort.equals("publisher"));
		sortfield.addLine(Functions.getLanguage().getPublished(), this.getLink("dpublished"), this.getLink("published"), !this.sort.equals("dpublished"), !this.sort.equals("published"));
		sortfield.addLine(Functions.getLanguage().getPurchased(), this.getLink("dpurchased"), this.getLink("purchased"), !this.sort.equals("dpurchased"), !this.sort.equals("purchased"));
		sortfield.addLine(Functions.getLanguage().getRead(), this.getLink("dread"), this.getLink("read"), !this.sort.equals("dread"), !this.sort.equals("read"));

		return sortfield;
	}

	private String getLink(String sort) {
		return "?tab=" + this.tab + (this.search.equals("") ? "" : "&amp;search=" + this.search) + (this.site.equals("") ? "" : "&amp;site=" + this.site) + "&amp;sort=" + sort;
	}
}