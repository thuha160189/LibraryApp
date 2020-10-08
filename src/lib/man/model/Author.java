package lib.man.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Author extends Person implements Serializable{

	private static final long serialVersionUID = 1648296739938896042L;
	private String credentials;
	private String bio;
	private List<Book> bookList = new ArrayList<Book>();
	
	public Author(String firstName, String lastName, Address address, String phone, String credentials, String bio,
			List<Book> bookList) {
		super(firstName, lastName, address, phone);
		this.credentials = credentials;
		this.bio = bio;
		this.bookList = bookList;
	}

	public String getCredentials() {
		return credentials;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
	
	public void addBook(Book book) {
		if (book != null) {
			this.bookList.add(book);
		}
	}
	
	
}
