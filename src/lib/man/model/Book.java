package lib.man.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Book implements Serializable{

	private static final long serialVersionUID = -284993669099407157L;
	public static final String STORAGE_TYPE = "BOOK";
	
	
	private String isbn;
	private String title;
	private List<BookCopy> copies = new ArrayList<BookCopy>();
	private List<Author> authors = new ArrayList<Author>();
	private int maxCheckout;
	
	public Book() {}
	
	public Book(String isbn, String title, List<BookCopy> copies, List<Author> authors) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.copies = copies;
		this.authors = authors;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<BookCopy> getCopies() {
		return copies;
	}

	public void setCopies(List<BookCopy> copies) {
		if (copies == null || copies.isEmpty()) {
			return;
		}
		this.copies = copies;
	}
	
	public void addBookCopy(BookCopy copy) {
		if (copy != null) {
			this.copies.add(copy);
		}
	}

//	public String getAuthors() {
//		StringBuilder sb = new StringBuilder();
//		for(Author a: authors) {
//			sb.append(a.getFirstName() + " "+ a.getLastName());
//		}
//		return sb.toString();
//	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		if (authors == null || authors.isEmpty()) {
			return;
		}
		this.authors = authors;
	}
	
	public void addAuthor(Author auth) {
		if (auth != null) {
			this.authors.add(auth);
		}
	}

	public int getMaxCheckout() {
		return maxCheckout;
	}

	public void setMaxCheckout(int maxCheckout) {
		this.maxCheckout = maxCheckout;
	}
}
