/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.library.templates.tabs;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.hibernate.Session;
import org.library.Functions;
import org.library.mustache.MustacheObject;
import org.library.mustache.MustacheStatisticsTab;
import org.library.templates.TemplatesTabs;
import org.library.util.HibernateUtil;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class TemplateStatisticsTab extends TemplatesTabs {
	@Override
	public String generateHTMLCode() {
		Mustache mustache = new DefaultMustacheFactory(Functions.getMustacheTemplateDirectory()).compile("TemplateStatistcisTab.mustache");
		StringWriter writer = new StringWriter();
		mustache.execute(writer, this.generateMustacheObject());
		writer.flush();

		return writer.toString();
	}

	@Override
	public MustacheObject generateMustacheObject() {
		MustacheStatisticsTab statistics = new MustacheStatisticsTab();

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		statistics.addLine(Functions.getLanguage().getTabLibrary(), session.createSQLQuery("select count(*) from Book").list().get(0).toString(), Functions.toHTML(Functions.format(Float.parseFloat(session.createSQLQuery("select sum(price) from Book").list().get(0).toString()))));
		statistics.addLine(Functions.getLanguage().getTabPurchased(), session.createSQLQuery("select count(*) from Book where purchased is not null").list().get(0).toString(), Functions.toHTML(Functions.format(Float.parseFloat(session.createSQLQuery("select sum(price) from Book where purchased is not null").list().get(0).toString()))));
		statistics.addLine(Functions.getLanguage().getTabSUB(), session.createSQLQuery("select count(*) from Book where read is null and purchased is not null").list().get(0).toString(), Functions.toHTML(Functions.format(Float.parseFloat(session.createSQLQuery("select sum(price) from Book where read is null and purchased is not null").list().get(0).toString()))));
		statistics.addLine(Functions.getLanguage().getTabRead(), session.createSQLQuery("select count(*) from Book where read is not null").list().get(0).toString(), Functions.toHTML(Functions.format(Float.parseFloat(session.createSQLQuery("select sum(price) from Book where read is not null").list().get(0).toString()))));
		statistics.addLine(Functions.getLanguage().getTabWishList(), session.createSQLQuery("select count(*) from Book where purchased is null").list().get(0).toString(), Functions.toHTML(Functions.format(Float.parseFloat(session.createSQLQuery("select sum(price) from Book where purchased is null").list().get(0).toString()))));
		statistics.addLine(Functions.getLanguage().getPurchasedThisYear(), session.createSQLQuery("select count(*) from Book where purchased is not null and extract(year from purchased) = '" + new GregorianCalendar().get(Calendar.YEAR) + "'").list().get(0).toString(), Functions.toHTML(Functions.format(Float.parseFloat(session.createSQLQuery("select sum(price) from Book where purchased is not null and extract(year from purchased) = '" + new GregorianCalendar().get(Calendar.YEAR) + "'").list().get(0).toString()))));
		statistics.addLine(Functions.getLanguage().getPurchasedThisMonth(), session.createSQLQuery("select count(*) from Book where purchased is not null and extract(year from purchased) = '" + new GregorianCalendar().get(Calendar.YEAR) + "' and extract(month from purchased) = '" + (new GregorianCalendar().get(Calendar.MONTH) + 1) + "'").list().get(0).toString(), Functions.toHTML(Functions.format(Float.parseFloat(session.createSQLQuery("select sum(price) from Book where purchased is not null and extract(year from purchased) = '" + new GregorianCalendar().get(Calendar.YEAR) + "' and extract(month from purchased) = '" + (new GregorianCalendar().get(Calendar.MONTH) + 1) + "'").list().get(0).toString()))));
		statistics.addLine(Functions.getLanguage().getReadThisYear(), session.createSQLQuery("select count(*) from Book where read is not null and extract(year from read) = '" + new GregorianCalendar().get(Calendar.YEAR) + "'").list().get(0).toString(), Functions.toHTML(Functions.format(Float.parseFloat(session.createSQLQuery("select sum(price) from Book where read is not null and extract(year from read) = '" + new GregorianCalendar().get(Calendar.YEAR) + "'").list().get(0).toString()))));
		statistics.addLine(Functions.getLanguage().getReadThisMonth(), session.createSQLQuery("select count(*) from Book where read is not null and extract(year from read) = '" + new GregorianCalendar().get(Calendar.YEAR) + "' and extract(month from read) = '" + (new GregorianCalendar().get(Calendar.MONTH) + 1) + "'").list().get(0).toString(), Functions.toHTML(Functions.format(Float.parseFloat(session.createSQLQuery("select sum(price) from Book where read is not null and extract(year from read) = '" + new GregorianCalendar().get(Calendar.YEAR) + "' and extract(month from read) = '" + (new GregorianCalendar().get(Calendar.MONTH) + 1) + "'").list().get(0).toString()))));

		session.close();
		return statistics;
	}
}