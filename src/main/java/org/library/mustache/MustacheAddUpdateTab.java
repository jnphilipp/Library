/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.library.mustache;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class MustacheAddUpdateTab implements MustacheObject {
	private Amazon amazon = null;
	private MustacheBookField templateBookField = null;

	public MustacheAddUpdateTab() {}

	public MustacheAddUpdateTab(String button) {
		this.amazon = new Amazon(button);
	}

	public MustacheAddUpdateTab(MustacheBookField templateBookField) {
		this.templateBookField = templateBookField;
	}

	public MustacheAddUpdateTab(String button, MustacheBookField templateBookField) {
		this.amazon = new Amazon(button);
		this.templateBookField = templateBookField;
	}

	/**
	 * @return the amazon
	 */
	public Amazon getAmazon() {
		return amazon;
	}

	/**
	 * @return the templateBookField
	 */
	public MustacheBookField getTemplateBookField() {
		return templateBookField;
	}

	/**
	 * @param templateBookField the templateBookField to set
	 */
	public void setTemplateBookField(MustacheBookField templateBookField) {
		this.templateBookField = templateBookField;
	}

	public static class Amazon {
		private String button = "";

		public Amazon(String button) {
			this.button = button;
		}

		/**
		 * @return the button
		 */
		public String getButton() {
			return button;
		}
	}
}