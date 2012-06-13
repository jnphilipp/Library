<%@page import="org.library.db.classes.Book"%>
<%@page import="java.util.List"%>
<%@page import="org.library.util.HibernateUtil"%>
<%@page import="org.hibernate.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
				<%
				Session s = HibernateUtil.getSessionFactory().openSession();
				s.beginTransaction();

				List re = s.createQuery("from Book").setMaxResults(10).list();

				for ( Book b : (List<Book>) re )
					out.println("<p>" + b.toShortString() + "</p>");

				s.getTransaction().commit();
				s.close();
				%>
    </body>
</html>
