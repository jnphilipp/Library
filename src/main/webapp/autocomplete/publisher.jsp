<%-- 
    Document   : publisher
    Created on : Jun 11, 2012, 5:33:03 PM
    Author     : J. Nathanael Philipp
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="net.sf.json.JSONArray"%>
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

	query = query.replaceAll("\\*", "%");
	if ( !query.endsWith("%") )
		query += "%";

	List r = s.createSQLQuery("SELECT name FROM publisher WHERE name LIKE :search ORDER BY name").setString("search", query).list();
	for ( Object o : r )
		data.add(o.toString());

	s.close();
	out.println(new JSONArray().fromObject(data));
}
catch ( Exception e ) {
	out.println(e);
}
%>