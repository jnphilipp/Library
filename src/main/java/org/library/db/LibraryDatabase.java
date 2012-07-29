/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.library.db;

import java.sql.SQLException;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class LibraryDatabase extends DatabasePostgreSQL {
	public LibraryDatabase() throws ClassNotFoundException {
		super();
	}

	public boolean connect() throws SQLException {
		return super.connect("localhost:5432/library", "library", "library", true);
	}
}