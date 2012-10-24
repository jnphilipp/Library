/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.library.html.sites;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import org.library.Functions;
import org.library.db.hibernate.classes.Binding;
import org.library.db.hibernate.classes.Book;
import org.library.db.hibernate.classes.Language;
import org.library.db.hibernate.classes.People;
import org.library.db.hibernate.classes.Publisher;
import org.library.html.HTML;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class HTMLAmazon_de extends HTML {
	private Book book = null;
	private String title = "";
	private final String[] month = {"Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"};

	@Override
	public void fetch(String page) throws MalformedURLException, IOException {
		super.fetch(page);

		ArrayList<String> re = super.getTags("span", "id=\"btAsinTitle\"", true);

		this.book = new Book();

		if ( !re.isEmpty() ) {
			this.book.setTitle(re.get(0).substring(0, re.get(0).indexOf("[") - 1));
			this.book.setBinding(new Binding(re.get(0).substring(re.get(0).lastIndexOf("[") + 1, re.get(0).lastIndexOf("]"))));
		}

		re = super.getTags("title", true);

		Set<People> coauthors = new HashSet<People>();
		if ( !re.isEmpty() ) {
			int i = re.get(0).indexOf(" ", re.get(0).indexOf("Amazon.de:")) + 1;
			String a = re.get(0).substring(i, re.get(0).indexOf(":", i));

			String[] auth = re.get(0).substring(i, re.get(0).indexOf(":", i)).split(", ");

			for ( i = 0; i < auth.length; i++ ) {
				People p = new People();
				p.setFirstnames(auth[i].substring(0, auth[i].lastIndexOf(" ")));
				p.setLastname(auth[i].substring(auth[i].lastIndexOf(" ") + 1));

				if ( i == 0 )
					this.book.setAuthor(p);
				else
					coauthors.add(p);
			}
		}
		book.setCoauthor(coauthors);

		re = super.getTags("b", "priceLarge", true);
		if ( !re.isEmpty() ) {
			try {
				this.book.setPrice(Functions.format(re.get(0).substring(4)));
			}
			catch ( ParseException e ) {
				Logger.getRootLogger().error(e);
			}
		}
		else {
			//alternativ place to parse the price
		}

		re = super.getTags("table");

		for ( String s : re ) {
			if ( s.contains("Produktinformation") ) {
				int i = s.indexOf("<li>", s.indexOf("<li>") + 5);
				if ( s.substring(i, s.indexOf("</li>", i)).contains(";") ) {
					this.book.setPublisher(new Publisher(s.substring(s.indexOf(":</b>", i) + 6, s.indexOf(";", s.indexOf(":</b>", i)))));
					i = s.indexOf(";", s.indexOf(":</b>", i));
				}
				else {
					this.book.setPublisher(new Publisher(s.substring(s.indexOf(":</b>", i) + 6, s.indexOf(" (", s.indexOf(":</b>", i)))));
					i = s.indexOf(" (", s.indexOf(":</b>", i));
				}

				org.apache.log4j.Logger.getRootLogger().info(s.substring(s.indexOf("(", i) + 1, s.indexOf(")", i)));
				this.book.setPublished(Functions.amazonToDate(s.substring(s.indexOf("(", i) + 1, s.indexOf(")", i)), this.month));

				i = s.indexOf("Sprache:</b>", i);
				this.book.setLanguage(new Language(s.substring(s.indexOf(" ", i) + 1, s.indexOf("</li>", i))));

				i = s.indexOf("ISBN-13:</b>", i);
				this.book.setIsbn(s.substring(s.indexOf(" ", i) + 1, s.indexOf("</li>", i)).replace("-", ""));
			}
		}
	}

	public String getTitle() {
		return this.title;
	}

	public Book getBook() {
		return this.book;
	}
}