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
public class MustacheAddUpdateContent implements MustacheObject {
	private Amazon amazon = null;
	private MustacheBookField templateBookField = null;

	public MustacheAddUpdateContent() {}

	public MustacheAddUpdateContent(String button) {
		this.amazon = new Amazon(button);
	}

	public MustacheAddUpdateContent(MustacheBookField templateBookField) {
		this.templateBookField = templateBookField;
	}

	public MustacheAddUpdateContent(String button, MustacheBookField templateBookField) {
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