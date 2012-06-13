/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.library.db.classes;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class People {
	private int id = -1;
	private String firstnames = "";
	private String lastname = "";

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the vorname
	 */
	public String getFirstnames() {
		return this.firstnames;
	}

	/**
	 * @param firstnames the firstnames to set
	 */
	public void setFirstnames(String firstnames) {
		this.firstnames = firstnames;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return this.lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return 
	 */
	@Override
	public String toString() {
		return this.firstnames + " " + this.lastname;
	}
}