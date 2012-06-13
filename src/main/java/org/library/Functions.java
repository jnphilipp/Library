/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.library;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import javax.servlet.ServletContext;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class Functions {
	private static ServletContext servletContext;

	public static ServletContext getServletContext() {
		return servletContext;
	}

	public static void setServletContext(ServletContext nServletContext) {
		servletContext = nServletContext;
	}

	public static String getImagePath(String isbn) {
		if ( (new File(servletContext.getRealPath("images/books/" + isbn + ".jpg"))).exists() )
			return "./images/books/" + isbn + ".jpg";
		else
			return "./images/book.jpg";
	}

	public static String dateToString(Date date) {
		String sp[] = date.toString().split("-");
		return sp[2] + "." + sp[1] + "." + sp[0];
	}

	public static String format(float n) {
		NumberFormat f = DecimalFormat.getCurrencyInstance(Locale.GERMANY);
		return f.format(n);
	}
}