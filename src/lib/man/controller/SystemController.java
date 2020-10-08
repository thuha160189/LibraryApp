package lib.man.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lib.man.dataaccess.DataAccess;
import lib.man.dataaccess.DataAccessFacade;
import lib.man.model.*;

public class SystemController {
	private DataAccess dataAccess = new DataAccessFacade();
	
	// Library member controller
	public ObservableList<LibraryMember> getLibraryMembers(){
		// Add some sample data
		ObservableList<LibraryMember> listLibrabryMember = FXCollections.observableArrayList();
		dataAccess.findAllMembers().forEach(member -> {
			listLibrabryMember.add(member);
		});
    	return listLibrabryMember;
	}
	
	public long addNewLibraryMember(LibraryMember libraryMember) {
		long memberId = dataAccess.addNewLibraryMember(libraryMember);
		return memberId;
	}
	
	public boolean updateLibraryMember(LibraryMember libraryMember) {
		boolean bool = dataAccess.updateLibraryMember(libraryMember);
		return bool;
	}
	
	public LibraryMember finMemberById(long memberId) {
		return dataAccess.findLibraryMemberById(memberId);
	}
	
	public boolean deleteLibraryMember(LibraryMember libmem) {
		return true;
	}
	
	//Book controller
	public ObservableList<Book> getAllBooks(){
		// Add some sample data
		ObservableList<Book> listBooks = FXCollections.observableArrayList();
		dataAccess.getAllBooks().forEach(member -> {
			listBooks.add(member);
		});
    	return listBooks;
	}
	
	public void addNewBook(Book book) {
		dataAccess.addNewBook(book);
	}
	
	public void updateBook(Book book) {
		dataAccess.updateBook(book);
	}
	
	public boolean deleteBook(Book libmem) {
		return true;
	}
	
	// User controller
	public User checkUser(User user) {
		return dataAccess.checkUser(user);
	}
	
	public User getUser(String userName) {
		return dataAccess.getUser(userName);
	}
	
	public void addNewUser(User user) {
		dataAccess.addNewUser(user);
	}
	
	//Checkout record controller
	public void checkout(CheckoutRecord checkoutRecord) {
		dataAccess.checkout(checkoutRecord);
	}

	public CheckoutRecord findCheckOutRecordByMemberId(Long memberId) {
		return dataAccess.findCheckOutRecordByMemberId(memberId);
	}

	public ObservableList<CheckoutRecord> getCheckoutRecordEntries() {
		ObservableList<CheckoutRecord> listCheckout = FXCollections.observableArrayList();
		dataAccess.getCheckoutRecordEntries().forEach(c -> {
			listCheckout.add(c);
		});
    	return listCheckout;
	}
}
