<%-- 
    Document   : index
    Created on : Jun 13, 2012, 12:05:10 AM
    Author     : J. Nathanael Philipp
--%>

<%@page import="java.io.IOException"%>
<%@page import="java.awt.Desktop"%>
<%@page import="org.library.templates.TemplateSite"%>
<%@page import="org.library.SiteHandling.RequestHandling"%>
<%@page import="org.library.Functions"%>
<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Session"%>
<%@page contentType="text/html charset=utf-8" pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
Functions.setServletContext(getServletContext());

String tab = "", search = "", book = "", sort = "", addURL = "", message = "", site="0", path = "";

if ( request.getParameter("tab") != null )
	tab = request.getParameter("tab");

if ( request.getParameter("search") != null )
	search = request.getParameter("search");

if ( request.getParameter("b") != null )
	book = request.getParameter("b");

if ( request.getParameter("sort") != null )
	sort = request.getParameter("sort");

if ( request.getParameter("site") != null )
	site = request.getParameter("site");

if ( request.getParameter("amazon") != null )
	addURL = request.getParameter("amazon");

if ( request.getParameter("path") != null )
	path = request.getParameter("path");

message = RequestHandling.doRequestHandling(request, response, getServletContext());

if ( request.getParameter("bookIn") != null && (request.getParameter("bookIn").equals("add") || request.getParameter("bookIn").equals("update")) ) {
	search = request.getParameter("isbn");
	book = request.getParameter("isbn");
}

TemplateSite ts = new TemplateSite(tab, book, sort, site, search, message, addURL);
out.println(ts.generateHTMLCode());

if ( !path.equals("") )
	Functions.openFile(path);
%>