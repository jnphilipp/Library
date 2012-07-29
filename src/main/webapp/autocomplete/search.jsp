<%-- 
    Document   : search
    Created on : Jun 11, 2012, 5:33:03 PM
    Author     : J. Nathanael Philipp
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="org.hibernate.Query"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.library.Functions"%>
<%@page import="org.library.util.HibernateUtil"%>
<%@page contentType="text/html charset=utf-8" pageEncoding="UTF-8"%>
<%
try {
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	Functions.setServletContext(getServletContext());

	Session s = HibernateUtil.getSessionFactory().openSession();
	s.beginTransaction();

	String query = request.getParameter("term");
	List<String> data = new ArrayList<String>();
	data.add(query);

	if ( query.length() >= 2 ) {
		query = query.replaceAll("\\*", "%");
		if ( !query.endsWith("%") )
			query += "%";

		if ( query.startsWith("w:") ) {
			Query q = s.createSQLQuery("SELECT DISTINCT CAST(EXTRACT(YEAR FROM published) AS TEXT), EXTRACT(YEAR FROM published) AS year, 1 AS month FROM book WHERE CAST(EXTRACT(YEAR FROM published) AS TEXT) LIKE :search UNION ALL SELECT DISTINCT (CASE WHEN EXTRACT(MONTH FROM published) < 10 THEN '0' || EXTRACT(MONTH FROM published) ELSE CAST(EXTRACT(MONTH FROM published) AS TEXT) END || '.' || EXTRACt(YEAR FROM published)), EXTRACT(YEAR FROM published) AS year, EXTRACt(MONTH FROM published) AS month FROM book WHERE (EXTRACt(MONTH FROM published) || '.' || EXTRACT(YEAR FROM published)) LIKE :search ORDER BY year DESC, month DESC");
			q.setString("search", "%" + query.substring(2));
			List<Object[]> r = q.list();

			for ( Object[] o : r )
				data.add("w:" + o[0]);
		}
		else if ( query.startsWith("p:") ) {
			Query q = s.createSQLQuery("SELECT DISTINCT CAST(EXTRACT(YEAR FROM purchased) AS TEXT), EXTRACT(YEAR FROM purchased) AS year, 1 AS month FROM book WHERE CAST(EXTRACT(YEAR FROM purchased) AS TEXT) LIKE :search UNION ALL SELECT DISTINCT (CASE WHEN EXTRACT(MONTH FROM purchased) < 10 THEN '0' || EXTRACT(MONTH FROM purchased) ELSE CAST(EXTRACT(MONTH FROM purchased) AS TEXT) END || '.' || EXTRACt(YEAR FROM purchased)), EXTRACT(YEAR FROM purchased) AS year, EXTRACt(MONTH FROM purchased) AS month FROM book WHERE (EXTRACt(MONTH FROM purchased) || '.' || EXTRACT(YEAR FROM purchased)) LIKE :search ORDER BY year DESC, month DESC");
			q.setString("search", "%" + query.substring(2));
			List<Object[]> r = q.list();

			for ( Object[] o : r )
				data.add("p:" + o[0]);
		}
		else if ( query.startsWith("r:") ) {
			Query q = s.createSQLQuery("SELECT DISTINCT CAST(EXTRACT(YEAR FROM read) AS TEXT), EXTRACT(YEAR FROM read) AS year, 1 AS month FROM book WHERE CAST(EXTRACT(YEAR FROM read) AS TEXT) LIKE :search UNION ALL SELECT DISTINCT (CASE WHEN EXTRACT(MONTH FROM read) < 10 THEN '0' || EXTRACT(MONTH FROM read) ELSE CAST(EXTRACT(MONTH FROM read) AS TEXT) END || '.' || EXTRACt(YEAR FROM read)), EXTRACT(YEAR FROM read) AS year, EXTRACt(MONTH FROM read) AS month FROM book WHERE (EXTRACt(MONTH FROM read) || '.' || EXTRACT(YEAR FROM read)) LIKE :search ORDER BY year DESC, month DESC");
			q.setString("search", "%" + query.substring(2));
			List<Object[]> r = q.list();

			for ( Object[] o : r )
				data.add("r:" + o[0]);
		}
		else {
			List r = s.createSQLQuery("SELECT name FROM Language WHERE name LIKE :search ORDER BY name").setString("search", query).list();
			for ( Object o : r )
				data.add(o.toString());

			r = s.createSQLQuery("SELECT name FROM publisher WHERE name LIKE :search ORDER BY name").setString("search", query).list();
			for ( Object o : r )
				data.add(o.toString());

			r = s.createSQLQuery("SELECT title FROM book WHERE title LIKE :search ORDER BY title").setString("search", query).list();
			for ( Object o : r )
				data.add(o.toString());

			r = s.createSQLQuery("SELECT firstnames, lastname FROM People WHERE (firstnames || ' ' || lastname) LIKE :search ORDER BY lastname, firstnames").setString("search", query).list();
			for ( Object[] o :(List<Object[]>) r )
				data.add(o[0] + " " + o[1]);
		}
	}

	s.close();
	out.println(new JSONArray().fromObject(data));
}
catch ( Exception e ) {
	out.println(e);
}
%>