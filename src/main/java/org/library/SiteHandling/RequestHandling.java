/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.library.SiteHandling;

import org.library.db.hibernate.classes.Book;
import org.library.db.hibernate.classes.People;
import org.library.db.hibernate.classes.Language;
import org.library.db.hibernate.classes.Binding;
import org.library.db.hibernate.classes.Publisher;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;
import org.library.Functions;
import org.library.db.hibernate.HibernatePeopleFunctions;
import org.library.util.HibernateUtil;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class RequestHandling {
	public static String doRequestHandling(HttpServletRequest request, HttpServletResponse response, ServletContext servlet) throws ParseException {
		String message = "";

		if ( request.getParameter("bookIn") != null && request.getParameter("bookIn").equals("add") && request.getParameter("submit") != null && request.getParameter("title") != null && request.getParameter("isbn") != null && request.getParameter("author") != null && request.getParameter("publisher") != null && request.getParameter("published") != null && request.getParameter("binding") != null && request.getParameter("language") != null && request.getParameter("price") != null ) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			People author = HibernatePeopleFunctions.get(request.getParameter("author").substring(0, request.getParameter("author").lastIndexOf(" ")), request.getParameter("author").substring(request.getParameter("author").lastIndexOf(" ") + 1), session);

			Set<People> coauthor = new HashSet<People>();
			if ( request.getParameter("coauthor") != null && !request.getParameter("coauthor").equals("") ) {
				String[] sp = request.getParameter("coauthor").split(", ");

				for ( String auth : sp ) {
					People p = HibernatePeopleFunctions.get(auth.substring(0, auth.lastIndexOf(" ")), auth.substring(auth.lastIndexOf(" ") + 1), session);

					coauthor.add(p);
				}
			}


			Book book = new Book();
			book.setIsbn(request.getParameter("isbn"));
			book.setTitle(request.getParameter("title"));
			book.setLanguage(new Language(request.getParameter("language")));
			book.setBinding(new Binding(request.getParameter("binding")));
			book.setPrice(Functions.format(request.getParameter("price")));
			book.setPublished(Functions.stringToDate(request.getParameter("published")));
			book.setAuthor(author);
			book.setPublisher(new Publisher(request.getParameter("publisher")));
			book.setCoauthor(coauthor);

			if ( request.getParameter("purchased") != null )
				book.setPurchased(Functions.stringToDate(request.getParameter("purchased")));

			if ( request.getParameter("read") != null )
				book.setRead(Functions.stringToDate(request.getParameter("read")));

			session.save(book);
			session.getTransaction().commit();

			session.close();

			message = Functions.getLanguage().getMessage_add(book.toShortString());
		}
		else if ( request.getParameter("bookIn") != null && request.getParameter("bookIn").equals("update") && request.getParameter("submit") != null && request.getParameter("title") != null && request.getParameter("isbn") != null && request.getParameter("isbn_old") != null && request.getParameter("author") != null && request.getParameter("publisher") != null && request.getParameter("published") != null && request.getParameter("binding") != null && request.getParameter("language") != null && request.getParameter("price") != null ) {
			if ( !request.getParameter("isbn").equals(request.getParameter("isbn_old")) ) {
				return "isbn nocht identisch.";
			}

			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			People author = HibernatePeopleFunctions.get(request.getParameter("author").substring(0, request.getParameter("author").lastIndexOf(" ")), request.getParameter("author").substring(request.getParameter("author").lastIndexOf(" ") + 1), session);

			Set<People> coauthor = new HashSet<People>();
			if ( request.getParameter("coauthor") != null && !request.getParameter("coauthor").equals("") ) {
				String[] sp = request.getParameter("coauthor").split(", ");

				for ( String auth : sp ) {
					People p = HibernatePeopleFunctions.get(auth.substring(0, auth.lastIndexOf(" ")), auth.substring(auth.lastIndexOf(" ") + 1), session);

					coauthor.add(p);
				}
			}


			Book book = new Book();
			book.setIsbn(request.getParameter("isbn"));
			book.setTitle(request.getParameter("title"));
			book.setLanguage(new Language(request.getParameter("language")));
			book.setBinding(new Binding(request.getParameter("binding")));
			book.setPrice(Functions.format(request.getParameter("price")));
			book.setPublished(Functions.stringToDate(request.getParameter("published")));
			book.setAuthor(author);
			book.setPublisher(new Publisher(request.getParameter("publisher")));
			book.setCoauthor(coauthor);

			if ( request.getParameter("purchased") != null )
				book.setPurchased(Functions.stringToDate(request.getParameter("purchased")));

			if ( request.getParameter("read") != null )
				book.setRead(Functions.stringToDate(request.getParameter("read")));

			session.update(book);
			session.getTransaction().commit();

			session.close();

			message = "The book " + book.toShortString() + " has been successfully updated.";
		}

		return message;
	}
}