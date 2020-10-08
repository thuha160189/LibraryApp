package lib.man.model;

import java.io.Serializable;

public class BookCopy implements Serializable {

	private static final long serialVersionUID = 4418236616627175939L;
	private Integer copyNum;
	private Book oriBook;
	private boolean checkouted;
	
	public BookCopy(Integer copyNum, Book oriBook, boolean checkouted) {
		super();
		this.copyNum = copyNum;
		this.oriBook = oriBook;
		this.checkouted = checkouted;
	}

	public Integer getCopyNum() {
		return copyNum;
	}

	public void setCopyNum(Integer copyNum) {
		this.copyNum = copyNum;
	}

	public Book getOriBook() {
		return oriBook;
	}

	public void setOriBook(Book oriBook) {
		this.oriBook = oriBook;
	}

	public boolean isCheckouted() {
		return checkouted;
	}

	public void setCheckouted(boolean checkouted) {
		this.checkouted = checkouted;
	}
	
}
