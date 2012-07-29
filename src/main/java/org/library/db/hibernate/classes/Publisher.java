/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.library.db.hibernate.classes;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class Publisher {
	private String name = "";

	public Publisher() {}

	public Publisher(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 
	 */
	@Override
	public String toString() {
		return this.name;
	}
}