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
public class MustacheBookOverview implements MustacheObject {
	private Overview overview = null;

	public MustacheBookOverview(String id, String href, String image, String title) {
		this.overview = new Overview(id, href, image, title);
	}

	public MustacheBookOverview(String id, String href, String image, String title, List<Line> line) {
		this.overview = new Overview(id, href, image, title, line);
	}

	public void addLine(Line line) {
		this.overview.addLine(line);
	}

	public void addLine(String th, String td) {
		this.overview.addLine(new Line(th, td));
	}

	/**
	 * @return the overview
	 */
	public Overview getOverview() {
		return overview;
	}

	public static class Overview {
		private String id = "";
		private String href = "";
		private String image = "";
		private String title = "";
		private List<Line> line = new ArrayList<Line>();

		public Overview(String id, String href, String image, String title) {
			this.id = id;
			this.href = href;
			this.image = image;
			this.title = title;
		}

		public Overview(String id, String href, String image, String title, List<Line> line) {
			this.id = id;
			this.href = href;
			this.image = image;
			this.title = title;
			this.line = line;
		}

		public void addLine(Line line) {
			this.line.add(line);
		}

		/**
		 * @return the id
		 */
		public String getId() {
			return id;
		}

		/**
		 * @return the href
		 */
		public String getHref() {
			return href;
		}

		/**
		 * @return the image
		 */
		public String getImage() {
			return image;
		}

		/**
		 * @return the title
		 */
		public String getTitle() {
			return title;
		}

		/**
		 * @return the line
		 */
		public List<Line> getLine() {
			return line;
		}
	}

	public static class Line {
		private String th = "";
		private String td = "";

		public Line(String th, String td) {
			this.th = th;
			this.td = td;
		}

		/**
		 * @return the th
		 */
		public String getTh() {
			return th;
		}

		/**
		 * @return the td
		 */
		public String getTd() {
			return td;
		}
	}
}