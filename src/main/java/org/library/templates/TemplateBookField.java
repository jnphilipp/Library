/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.library.templates;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import java.io.StringWriter;
import org.library.Functions;
import org.library.db.hibernate.classes.Book;
import org.library.mustache.MustacheBookField;
import org.library.mustache.MustacheObject;

/**
 *
 * @author J. Nathanael Philipp
 * @version 1.0
 */
public class TemplateBookField extends TemplatesContent {
	private Book cBook = null;
	private boolean update = true;

	public TemplateBookField(Book book, boolean update) {
		this.update = update;
		this.cBook = book;
	}

	@Override
	public String generateHTMLCode() {
    Mustache mustache = new DefaultMustacheFactory(Functions.getMustacheTemplateDirectory()).compile("TemplateBookField.mustache");
		StringWriter writer = new StringWriter();
		mustache.execute(writer, this.generateMustacheObject());
		writer.flush();

		return writer.toString();
	}

	@Override
	public MustacheObject generateMustacheObject() {
		MustacheBookField bookfield = new MustacheBookField((this.update ? Functions.getLanguage().getUpdate() : Functions.getLanguage().getAdd()) + " " + Functions.getLanguage().getBook());

		if ( this.update && this.cBook != null ) {
			bookfield.addImage(Functions.getImagePath(this.cBook.getIsbn()), this.cBook.toShortString());
			bookfield.addOldIsbn(this.cBook.getIsbn());
		}

		bookfield.addFiled(Functions.getLanguage().getTitle(), "title", "", (this.cBook == null ? "" : this.cBook.getTitle()), true);
		bookfield.addFiled(Functions.getLanguage().getSeries(), "series", "series", (this.cBook == null || this.cBook.getSeries() == null ? "" : this.cBook.getSeries().toString()), false);
		bookfield.addFiled(Functions.getLanguage().getVolume(), "volume", "", (this.cBook == null || this.cBook.getVolume() == 0.0f ? "" : String.valueOf(this.cBook.getVolume())), false);
		bookfield.addFiled("ISBN", "isbn", "", (this.cBook == null ? "" : this.cBook.getIsbn()), true);
		bookfield.addFiled(Functions.getLanguage().getAuthor(), "author", "author", (this.cBook == null ? "" : this.cBook.getAuthor().toString()), true);
		bookfield.addFiled(Functions.getLanguage().getCoauthor(), "coauthor", "coauthor", (this.cBook == null ? "" : this.cBook.getCoauthors()));
		bookfield.addFiled(Functions.getLanguage().getPublisher(), "publisher", "publisher", (this.cBook == null ? "" : this.cBook.getPublisher().toString()), true);
		bookfield.addFiled(Functions.getLanguage().getPublished(), "published", "published", (this.cBook == null ? "" : this.cBook.getPublishedToString()), true);
		bookfield.addFiled(Functions.getLanguage().getBinding(), "binding", "binding", (this.cBook == null ? "" : this.cBook.getBinding().toString()), true);
		bookfield.addFiled(Functions.getLanguage().getLanguage(), "language", "language", (this.cBook == null ? "" : this.cBook.getLanguage().toString()), true);
		bookfield.addFiled(Functions.getLanguage().getPrice(), "price", "", (this.cBook == null ? "" : Functions.toHTML(this.cBook.getPrice(true))), true);
		bookfield.addFiled(Functions.getLanguage().getPurchased(), "purchased", "purchased", (this.cBook == null ? "" : this.cBook.getPurchasedToString()));
		bookfield.addFiled(Functions.getLanguage().getRead(), "read", "read", (this.cBook == null ? "" : this.cBook.getReadToString()));
		bookfield.addFiled(Functions.getLanguage().getEbook(), "ebook", "", (this.cBook == null ? "" : this.cBook.getPath()));

		if ( this.update )
			bookfield.addButton(Functions.getLanguage().getUpdate(), "update");
		else
			bookfield.addButton(Functions.getLanguage().getAdd(), "add");

		return bookfield;
	}
}