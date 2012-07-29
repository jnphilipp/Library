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
public class Binding {
	private String binding = "";

	public Binding() {}

	public Binding(String binding) {
		this.binding = binding;
	}

	/**
	 * @return the binding
	 */
	public String getBinding() {
		return binding;
	}

	/**
	 * @param binding the binding to set
	 */
	public void setBinding(String binding) {
		this.binding = binding;
	}

	@Override
	public String toString() {
		return this.binding;
	}
}