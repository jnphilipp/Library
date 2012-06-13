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
public class Publisher {
	private int id = -1;
	private String name = "";

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the verlag
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param verlag the verlag to set
	 */
	public void setName(String verlag) {
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