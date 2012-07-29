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
	private Sortfield sortfield = null;

	public MustacheSortfield(String sort) {
		this.sortfield = new Sortfield(sort);
	}

	/**
	 * @return the sortfield
	 */
	public Sortfield getSortfield() {
		return sortfield;
	}

	public void addLine(String th, String tab, String downsort, String upsort) {
		this.sortfield.addLine(new Line(th, tab, downsort, upsort));
	}

	public void addLine(String th, String tab, boolean down, String downsort, boolean up, String upsort) {
		this.sortfield.addLine(new Line(th, tab, down, downsort, up, upsort));
	}

	public void addLine(String th, String tab, String search, String downsort, String upsort) {
		this.sortfield.addLine(new Line(th, tab, search, downsort, upsort));
	}

	public void addLine(String th, String tab, boolean showsearch, String search, boolean down, String downsort, boolean up, String upsort) {
		this.sortfield.addLine(new Line(th, tab, showsearch, search, down, downsort, up, upsort));
	}

	public static class Sortfield {
		private String sort = "";
		private List<Line> line = new ArrayList<Line>();

		public Sortfield(String sort) {
			this.sort = sort;
		}

		/**
		 * @return the sort
		 */
		public String getSort() {
			return sort;
		}

		/**
		 * @return the line
		 */
		public List<Line> getLine() {
			return line;
		}

		public void addLine(Line line) {
			this.line.add(line);
		}
	}

	public static class Line {
		private String th = "";
		private String tab = "";
		private boolean showsearch = false;
		private String search = "";
		private boolean down = false;
		private String downsort = "";
		private boolean up = false;
		private String upsort = "";

		public Line(String th, String tab, String downsort, String upsort) {
			this.th = th;
			this.tab = tab;
			this.down = !downsort.equals("");
			this.downsort = downsort;
			this.up = !upsort.equals("");
			this.upsort = upsort;
		}

		public Line(String th, String tab, boolean down, String downsort, boolean up, String upsort) {
			this.th = th;
			this.tab = tab;
			this.downsort = downsort;
			this.upsort = upsort;
		}

		public Line(String th, String tab, String search, String downsort, String upsort) {
			this.th = th;
			this.tab = tab;
			this.showsearch = !search.equals("");
			this.search = search;
			this.down = !downsort.equals("");
			this.downsort = downsort;
			this.up = !upsort.equals("");
			this.upsort = upsort;
		}

		public Line(String th, String tab, boolean showsearch, String search, boolean down, String downsort, boolean up, String upsort) {
			this.th = th;
			this.tab = tab;
			this.showsearch = showsearch;
			this.search = search;
			this.down = down;
			this.downsort = downsort;
			this.up = up;
			this.upsort = upsort;
		}

		/**
		 * @return the th
		 */
		public String getTh() {
			return th;
		}

		/**
		 * @return the tab
		 */
		public String getTab() {
			return tab;
		}

		/**
		 * @return the showsearch
		 */
		public boolean isShowsearch() {
			return showsearch;
		}

		/**
		 * @return the search
		 */
		public String getSearch() {
			return search;
		}

		/**
		 * @return the down
		 */
		public boolean isDown() {
			return down;
		}

		/**
		 * @return the downsort
		 */
		public String getDownsort() {
			return downsort;
		}

		/**
		 * @return the up
		 */
		public boolean isUp() {
			return up;
		}

		/**
		 * @return the upsort
		 */
		public String getUpsort() {
			return upsort;
		}
	}
}