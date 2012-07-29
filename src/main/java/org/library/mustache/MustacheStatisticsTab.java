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
public class MustacheStatisticsTab implements MustacheObject {
	/*private Statistics statistics;

	public MustacheStatisticsTab() {
		this.statistics = new Statistics();
	}

	public MustacheStatisticsTab(List<Line> line) {
		this.statistics = new Statistics(line);
	}

	public Statistics getStatistics() {
		return this.statistics;
	}*/

	public void addLine(String title, String count, String sum) {
		this.lines.add(new Line(title, count, sum));
	}

	/*public static class Statistics {*/
		private List<Line> lines = new ArrayList<Line>();

		/*public Statistics() {}

		public Statistics(List<Line> lines) {
			this.lines = lines;
		}*/

		public List<Line> getLine() {
			return this.lines;
		}

		/*public void addLine(Line line) {
			this.lines.add(line);
		}
	}*/

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