/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.library.templates;

import org.library.mustache.MustacheObject;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public abstract class TemplatesObject {
	protected String tab = "";
	protected String sort = "";
	protected String site = "";
	protected String search = "";

	public TemplatesObject(String tab) {
		this.tab = tab;
	}

	public TemplatesObject(String tab, String sort) {
		this.tab = tab;
		this.sort = sort;
	}

	public TemplatesObject(String tab, String sort, String site) {
		this.tab = tab;
		this.sort = sort;
		this.site = site;
	}

	public TemplatesObject(String tab, String sort, String site, String search) {
		this.tab = tab;
		this.sort = sort;
		this.site = site;
		this.search = search;
	}

	//public abstract String generateHTMLCode(Object o);
	public abstract MustacheObject generateMustacheObject(Object o);
}