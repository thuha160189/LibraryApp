package lib.man.dataaccess;

import java.util.Collection;
import java.util.List;

import lib.man.model.*;


public interface DataAccess {
	//Book function
	public void addNewBook(Book book);

	public void updateBook(Book newBook);

	public Book findBookById(String id);
	
	public Collection<Book> getAllBooks();
	  
	//LibraryMember function
	public long addNewLibraryMember(LibraryMember libraryMember);

	public boolean updateLibraryMember(LibraryMember libraryMember);

	public LibraryMember findLibraryMemberById(Long id);

	public Collection<LibraryMember> findAllMembers();
	
	//CheckoutRecord function
	public void checkout(CheckoutRecord checkoutRecord);

	public CheckoutRecord findCheckOutRecordByMemberId(Long memberId);

	public Collection<CheckoutRecord> getCheckoutRecordEntries();
	
	//User function
	public void addNewUser(User user);

	public User getUser(String username);
	
	public User checkUser(User user);
}
