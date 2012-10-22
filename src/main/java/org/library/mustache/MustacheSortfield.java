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
public class MustacheSortfield implements MustacheObject {
	private String sort = "";
	private List<Line> line = new ArrayList<Line>();

		public MustacheSortfield(String sort) {
			this.sort = sort;
		}

	public void addLine(String link_title, String href_down, String href_up, boolean down, boolean up) {
		this.line.add(new Line(link_title, href_down, href_up, down, up));
	}

	public String getSort() {
		return this.sort;
	}

	public List<Line> getLine() {
		return this.line;
	}

	public static class Line {
		private String link_title;
		private String href_down = "";
		private String href_up = "";
		private boolean down = false;
		private boolean up = false;

		public Line(String link_title, String href_down, String href_up, boolean down, boolean up) {
			this.link_title = link_title;
			this.href_down = href_down;
			this.href_up = href_up;
			this.down = down;
			this.up = up;
		}

		/**
		 * @return the down
		 **/
		public boolean isDown() {
			return down;
		}

		/**
		 * @return the up
		 */
		public boolean isUp() {
			return up;
		}

		/**
		 * @return the link_title
		 */
		public String getLink_title() {
			return this.link_title;
		}

		/**
		 * @return the href_down
		 */
		public String getHref_down() {
			return this.href_down;
		}

		/**
		 * @return the href_up
		 */
		public String getHref_up() {
			return this.href_up;
		}
	}
}