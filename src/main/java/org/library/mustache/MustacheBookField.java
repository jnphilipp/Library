/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.library.mustache;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class MustacheBookField implements MustacheObject {
	private String legend = "";
	private Image image = null;
	private List<Field> fields = new ArrayList<Field>();
	private Button button = null;
	private OldIsbn oldIsbn = null;

	public MustacheBookField(String legend) {
		this.legend = legend;
	}

	/**
	 * @return the legend
	 */
	public String getLegend() {
		return this.legend;
	}

	/**
	 * @return the image
	 */
	public Image getImage() {
		return this.image;
	}

	public void addImage(String path, String title) {
		this.image = new Image(path, title);
	}

	/**
	 * @return the fields
	 */
	public List<Field> getField() {
		return this.fields;
	}

	public void addFiled(String label, String name, String id, String value) {
		this.fields.add(new Field(label, name, id, value));
	}

	/**
	 * @return the button
	 */
	public Button getButton() {
		return this.button;
	}

	public void addButton(String button, String bookin) {
		this.button = new Button(button, bookin);
	}

	/**
	 * @return the oldIsbn
	 */
	public OldIsbn getOldIsbn() {
		return this.oldIsbn;
	}

	public void addOldIsbn(String isbn) {
		this.oldIsbn = new OldIsbn(isbn);
	}
	
	public static class Image {
		private String path = "";
		private String title = "";

		public Image(String path, String title) {
			this.path = path;
			this.title = title;
		}

		/**
		 * @return the path
		 */
		public String getPath() {
			return path;
		}

		/**
		 * @return the title
		 */
		public String getTitle() {
			return title;
		}
	}

	public static class Field {
		private String label = "";
		private String name = "";
		private String id = "";
		private String value = "";
		private boolean required = false;

		public Field(String label, String name, String id, String value) {
			this.label= label;
			this.name = name;
			this.id = id;
			this.value = value;
		}

		public Field(String label, String name, String id, String value, boolean required) {
			this.label= label;
			this.name = name;
			this.id = id;
			this.value = value;
			this.required = required;
		}

		/**
		 * @return the label
		 */
		public String getLabel() {
			return label;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @return the id
		 */
		public String getId() {
			return id;
		}

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/**
		 * @return the required
		 */
		public boolean isRequired() {
			return required;
		}
	}

	public static class Button {
		private String button = "";
		private String bookin = "";

		public Button(String button, String bookin) {
			this.button = button;
			this.bookin = bookin;
		}

		/**
		 * @return the button
		 */
		public String getButton() {
			return button;
		}

		/**
		 * @return the bookin
		 */
		public String getBookin() {
			return bookin;
		}
	}

	public static class OldIsbn {
		private String isbn = "";

		public OldIsbn(String isbn) {
			this.isbn = isbn;
		}

		/**
		 * @return the isbn
		 */
		public String getIsbn() {
			return isbn;
		}
	}
}