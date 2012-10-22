/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.library.mustache;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class MustacheSite implements MustacheObject {
	private boolean message = false;
	private String messagetext = "";
	private List<NavElem> navElem = new ArrayList<NavElem>();
	private Content content = null;

	public MustacheSite() {
		this.message = false;
	}

	public MustacheSite(String message) {
		this.message = !message.equals("");
		this.messagetext = message;
	}

	/**
	 * @return the message
	 */
	public boolean isMessage() {
		return message;
	}

	/**
	 * @return the messageText
	 */
	public String getMessagetext() {
		return messagetext;
	}

	/**
	 * @param messagetext the messagetext to set
	 */
	public void setMessagetext(String messagetext) {
		this.messagetext = messagetext;
		this.message = !this.messagetext.equals("");
	}

	/**
	 * @return the nav
	 */
	public List<NavElem> getNavElem() {
		return this.navElem;
	}

	public void addNavElem(boolean current, String href, String name) {
		this.navElem.add(new NavElem(current, href, name));
	}

	/**
	 * @return the content
	 */
	public Content getContent() {
		return this.content;
	}

	public void addContent(MustacheBookContent templateBookContent) {
		this.content = new Content(templateBookContent);
	}

	public void addContent(MustacheStatisticsContent templateStatisticsContent) {
		this.content = new Content(templateStatisticsContent);
	}

	public void addContent(MustacheAddUpdateContent templateAddUpdateContent) {
		this.content = new Content(templateAddUpdateContent);
	}

	public static class NavElem {
		private boolean current = false;
		private String href = "";
		private String name = "";

		public NavElem(boolean current, String href, String name) {
			this.current = current;
			this.href = href;
			this.name = name;
		}

		/**
		 * @return the current
		 */
		public boolean isCurrent() {
			return current;
		}

		/**
		 * @return the href
		 */
		public String getHref() {
			return href;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
	}

	public static class Content {
		private MustacheBookContent templateBookContent = null;
		private MustacheStatisticsContent templateStatisticsContent = null;
		private MustacheAddUpdateContent templateAddUpdateContent = null;

		public Content(MustacheBookContent templateBookContent) {
			this.templateBookContent = templateBookContent;
		}

		public Content(MustacheStatisticsContent templateStatisticsContent) {
			this.templateStatisticsContent = templateStatisticsContent;
		}

		public Content(MustacheAddUpdateContent templateAddUpdateContent) {
			this.templateAddUpdateContent = templateAddUpdateContent;
		}

		/**
		 * @return the templateBookContent
		 */
		public MustacheBookContent getTemplateBookContent() {
			return templateBookContent;
		}

		/**
		 * @return the templateStatistics
		 */
		public MustacheStatisticsContent getTemplateStatisticsContent() {
			return this.templateStatisticsContent;
		}

		/**
		 * @return the templateAddUpdateContent
		 */
		public MustacheAddUpdateContent getTemplateAddUpdateContent() {
			return this.templateAddUpdateContent;
		}
	}
}