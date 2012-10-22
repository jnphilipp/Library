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
public class MustacheStatisticsContent implements MustacheObject {
	private List<Line> lines = new ArrayList<Line>();

	public void addLine(String title, String count, String sum) {
		this.lines.add(new Line(title, count, sum));
	}

	public List<Line> getLine() {
		return this.lines;
	}

	public static class Line {
		private String title = "";
		private String count = "";
		private String sum = "";

		public Line(String title, String count, String sum) {
			this.title = title;
			this.count = count;
			this.sum = sum;
		}

		/**
		 * @return the title
		 */
		public String getTitle() {
			return this.title;
		}

		/**
		 * @return the count
		 */
		public String getCount() {
			return this.count;
		}

		/**
		 * @return the sum
		 */
		public String getSum() {
			return this.sum;
		}
	}
}