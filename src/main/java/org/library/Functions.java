/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.library;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.library.db.LibraryDatabase;
import org.library.language.Language;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class Functions {
	public static final String MUSTACHE_TEMPLATE_DIRECTORY = "/WEB-INF/templates/";
	private static ServletContext servletContext = null;
	private static Language language = null;
	private static LibraryDatabase db = null;

	public static File getMustacheTemplateDirectory() {
		return new File(getServletContext().getRealPath(MUSTACHE_TEMPLATE_DIRECTORY));
	}

	public static ServletContext getServletContext() {
		return servletContext;
	}

	public static void setServletContext(ServletContext nServletContext) {
		servletContext = nServletContext;
	}

	public static Language getLanguage() {
		if ( language == null ) {
			try {
				language = new Language();
			}
			catch ( IOException e ) {
				Logger.getLogger(Functions.class).error("Load language.", e);
			}
		}

		return language;
	}

	/*public static LibraryDatabase getInstanceLibraryDatabase() {
		try {
			if ( db == null )
				db = new LibraryDatabase();

			db.connect();
		}
		catch ( Exception e ) {
			Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, e);
		}

		return db;
	}*/

	public static String getImagePath(String isbn) {
		if ( (new File(servletContext.getRealPath("images/books/" + isbn + ".jpg"))).exists() )
			return "./images/books/" + isbn + ".jpg";
		else
			return "./images/book.jpg";
	}

	public static String dateToString(Date date) {
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
		return (c.get(Calendar.DAY_OF_MONTH) < 10 ? "0" + c.get(Calendar.DAY_OF_MONTH) : c.get(Calendar.DAY_OF_MONTH)) + "." + (c.get(Calendar.MONTH) < 9 ? "0" + (c.get(Calendar.MONTH) + 1) : (c.get(Calendar.MONTH) + 1)) + "." + c.get(Calendar.YEAR);
	}

	public static Date stringToDate(String timestamp) {
		if ( timestamp.equals("") )
			return null;

		GregorianCalendar c = new GregorianCalendar(Integer.valueOf(timestamp.substring(timestamp.lastIndexOf(".") + 1)), Integer.valueOf(timestamp.substring(timestamp.indexOf(".") + 1, timestamp.lastIndexOf("."))) - 1, Integer.valueOf(timestamp.substring(0, timestamp.indexOf("."))));

		return c.getTime();
	}

	public static String convertStringDate(String toConvert) {
		//if ( toConvert.contains(".") ) {
			String[] split = toConvert.split(".");

			if ( split.length == 1 )
				return "2020";//split[0];
			else if ( split.length == 2 )
				return split[1] + "%-" + split[0];
			else if ( split.length == 3 )
				return split[2] + "-" + split[1] + "-" + split[0];
		//}
		return toConvert;
	}

	public static Date amazonToDate(String amazon, String[] months) {
		int day = 1;
		int month = 1;
		int year = Integer.parseInt(amazon.substring(amazon.length() - 4));

		if ( amazon.contains(".") )
			day = Integer.parseInt(amazon.substring(0, amazon.indexOf(".")));

		if ( amazon.contains(months[0]))
			month = 0;
		else if ( amazon.contains(months[1]) )
			month = 1;
		else if ( amazon.contains(months[2]) )
			month = 2;
		else if ( amazon.contains(months[3]) )
			month = 3;
		else if ( amazon.contains(months[4]) )
			month = 4;
		else if ( amazon.contains(months[5]) )
			month = 5;
		else if ( amazon.contains(months[6]) )
			month = 6;
		else if ( amazon.contains(months[7]) )
			month = 7;
		else if ( amazon.contains(months[8]) )
			month = 8;
		else if ( amazon.contains(months[9]) )
			month = 9;
		else if ( amazon.contains(months[10]) )
			month = 10;
		else if ( amazon.contains(months[11]) )
			month = 11;

		GregorianCalendar c = new GregorianCalendar(year, month, day);

		return c.getTime();
	}

	public static String format(float n) {
		NumberFormat f = DecimalFormat.getCurrencyInstance(Locale.GERMANY);
		return f.format(n);
	}

	public static float format(String n) throws ParseException {
		NumberFormat f = NumberFormat.getInstance(Locale.GERMANY);
		Number num = f.parse(n);
		return num.floatValue();
	}

	public static String toHTML(String s) {
		s = s.replace("€", "&euro;");
		s = s.replace("£", "&pound;");
		s = s.replace("¥", "&yen;");
		s = s.replace("¤", "&curren;");

		return s;
	}

	/**
	 * Opens a file. Doesn't work when startet from maven.
	 * @param file file to open
	 */
	public static void openFile(String file) {
		try {
			Desktop desktop = null;

			if (Desktop.isDesktopSupported()) {
				desktop = Desktop.getDesktop();
				File f = new File(file);
				desktop.open(f);
			}
			else
				Logger.getRootLogger().error("Desktop not supported.");
		}
		catch ( IOException e ) {
			Logger.getLogger(Functions.class).error("Can't open File: " + file, e);
		}
	}
}
