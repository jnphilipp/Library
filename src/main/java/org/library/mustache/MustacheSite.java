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
	private List<Tab> tab = new ArrayList<Tab>();
	private Pane pane = null;

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
	 * @return the tab
	 */
	public List<Tab> getTab() {
		return tab;
	}

	public void addTab(boolean current, String href, String name) {
		this.tab.add(new Tab(current, href, name));
	}

	/**
	 * @return the pane
	 */
	public Pane getPane() {
		return pane;
	}

	public void addPane(MustacheBookTabs templateBookTabs) {
		this.pane = new Pane(templateBookTabs);
	}

	public void addPane(MustacheStatisticsTab templateStatisticsTab) {
		this.pane = new Pane(templateStatisticsTab);
	}

	public void addPane(MustacheAddUpdateTab templateAddUpdate) {
		this.pane = new Pane(templateAddUpdate);
	}

	public static class Tab {
		private boolean current = false;
		private String href = "";
		private String name = "";

		public Tab(boolean current, String href, String name) {
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

	public static class Pane {
		private MustacheBookTabs templateBookTabs = null;
		private MustacheStatisticsTab templateStatisticsTab = null;
		private MustacheAddUpdateTab templateAddUpdateTab = null;

		public Pane(MustacheBookTabs templateBookTabs) {
			this.templateBookTabs = templateBookTabs;
		}

		public Pane(MustacheStatisticsTab templateStatisticsTab) {
			this.templateStatisticsTab = templateStatisticsTab;
		}

		public Pane(MustacheAddUpdateTab templateAddUpdateTab) {
			this.templateAddUpdateTab = templateAddUpdateTab;
		}

		/**
		 * @return the templateBookTabs
		 */
		public MustacheBookTabs getTemplateBookTabs() {
			return templateBookTabs;
		}

		/**
		 * @return the templateStatistics
		 */
		public MustacheStatisticsTab getTemplateStatisticsTab() {
			return this.templateStatisticsTab;
		}

		/**
		 * @return the templateAddUpdateTab
		 */
		public MustacheAddUpdateTab getTemplateAddUpdateTab() {
			return this.templateAddUpdateTab;
		}
	}
}