/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.library.db.hibernate.classes;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class Language {
	private String language = "";

	public Language() {}

	public Language(String language) {
		this.language = language;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return this.language;
	}
}