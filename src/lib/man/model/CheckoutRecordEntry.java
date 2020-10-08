package lib.man.model;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutRecordEntry implements Serializable{

	private static final long serialVersionUID = 6238095847861745959L;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private BookCopy book;
	
	public CheckoutRecordEntry(LocalDate checkoutDate, LocalDate dueDate, BookCopy book) {
		super();
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
		this.book = book;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public BookCopy getBook() {
		return book;
	}

	public void setBook(BookCopy book) {
		this.book = book;
	}
	
	
	
}
