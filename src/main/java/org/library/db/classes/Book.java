/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.library.db.classes;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.library.Functions;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class Book {
	private String isbn = "";
	private String title = "";
	private String language = "";
	private String format = "";
	private float price = 0.0f;
	private Date published = null;
	private Publisher publisher = null;
	//private String rezension = "";
	private People author = null;
	private Date purchased = null;
	private Date read = null;
	private Set<People> coauthor = new HashSet<People>(0);

	/**
	 * @return the ibsn
	 */
	public String getIsbn() {
		return this.isbn;
	}

	/**
	 * @param ibsn the ibsn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the titel
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the titel to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the sprache
	 */
	public String getLanguage() {
		return this.language;
	}

	/**
	 * @param sprache the sprache to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return this.format;
	}

	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	public String getPrice(boolean format) {
		return Functions.format(this.price);
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * @return the published
	 */
	public Date getPublished() {
		return this.published;
	}

	public String getPublishedToString() {
		return (this.published == null ? "" : Functions.dateToString(this.published));
	}

	/**
	 * @param published the published to set
	 */
	public void setPublished(Date published) {
		this.published = published;
	}

	/**
	 * @return the rezension
	 *
	public String getRezension() {
		return rezension;
	}

	/**
	 * @param rezension the rezension to set
	 *
	public void setRezension(String rezension) {
		this.rezension = rezension;
	}*/

	/**
	 * @return the publisher
	 */
	public Publisher getPublisher() {
		return this.publisher;
	}

	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return the author
	 */
	public People getAuthor() {
		return this.author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(People author) {
		this.author = author;
	}

	/**
	 * @return the purchased
	 */
	public Date getPurchased() {
		return this.purchased;
	}

	public String getPurchasedToString() {
		return (this.purchased == null ? "" : Functions.dateToString(this.purchased));
	}

	/**
	 * @param purchased the purchased to set
	 */
	public void setPurchased(Date purchased) {
		this.purchased = purchased;
	}

	/**
	 * @return the gelesenam
	 */
	public Date getRead() {
		return this.read;
	}

	public String getReadToString() {
		return (this.read == null ? "" : Functions.dateToString(this.read));
	}

	/**
	 * @param read the read to set
	 */
	public void setRead(Date read) {
		this.read = read;
	}

	/**
	 * @return the koautor
	 */
	public Set<People> getCoauthor() {
		return coauthor;
	}

	public String getCoauthors() {
		String re = "";

		if ( !this.coauthor.isEmpty() ) {
			Iterator<People> it = this.coauthor.iterator();

			while ( it.hasNext() ) {
				People p = it.next();
				re += p + ", ";
			}

			re = re.substring(0, re.length() - 2);
		}

		return re;
	}

	/**
	 * @param coauthor the coauthor to set
	 */
	public void setCoauthor(Set<People> coauthor) {
		this.coauthor = coauthor;
	}

	public boolean hasPurchased() {
		return this.purchased != null;
	}

	public boolean hasRead() {
		return this.read != null;
	}

	/*public boolean hasRezension() {
		return (this.rezension.equals("") ? false : true);
	}*/

	public boolean hasCoauthors() {
		return !this.coauthor.isEmpty();
	}

	/**
	 * @return 
	 */
	@Override
	public String toString() {
		String re = this.title + " von " + this.author + " und ";

		if ( !this.coauthor.isEmpty() ) {
			Iterator<People> it = this.coauthor.iterator();

			while ( it.hasNext() ) {
				People p = it.next();
				re += p + ", ";
			}

			re = re.substring(0, re.length() - 2);
		}

		return re + " erschienen bei " + this.publisher + " (" + this.isbn + ", " + this.format + "," + this.language + ", " + this.published.toString() + ", " + this.price + (this.purchased == null ? "" : ", " + this.purchased.toString()) + (this.read == null ? "" : ", " + this.read.toString()) /*+ (!this.rezension.equals("") ? ", " + this.rezension : "")*/ + ")";
	}

	public String toShortString() {
			return this.title + " von " + this.author;
	}
}