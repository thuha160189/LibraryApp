package lib.man.dataaccess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import lib.man.model.Book;
import lib.man.model.CheckoutRecord;
import lib.man.model.CheckoutRecordEntry;
import lib.man.model.LibraryMember;
import lib.man.model.User;
import lib.man.utils.FileUtils;

public class DataAccessFacade implements DataAccess {

	private static HashMap<String, Book> books;
	private static HashMap<Long, LibraryMember> libraryMembers;
	private static HashMap<String, CheckoutRecord> checkoutRecords;
	private static HashMap<String, User> users;
	
	@Override
	public void addNewBook(Book book) {
		@SuppressWarnings("unchecked")
		HashMap<String, Book> bookMap = (HashMap<String, Book>) readDataFromFile(books, Book.STORAGE_TYPE);
		bookMap.put(book.getIsbn(), book);
		FileUtils.writeObject(bookMap, Book.STORAGE_TYPE);
		books = bookMap;
	}

	@Override
	public void updateBook(Book updatedBook) {
		addNewBook(updatedBook);
	}

	@Override
	public Book findBookById(String isbn) {
		HashMap<String, Book> bookMap = (HashMap<String, Book>) readDataFromFile(books, Book.STORAGE_TYPE);
		return bookMap.get(isbn);
	}

	@Override
	public Collection<Book> getAllBooks() {
		HashMap<String, Book> bookMap = (HashMap<String, Book>) readDataFromFile(books, Book.STORAGE_TYPE);
		return bookMap.values();
	}

	@Override
	public long addNewLibraryMember(LibraryMember libraryMember) {
		HashMap<Long, LibraryMember> memberMap =  (HashMap<Long, LibraryMember>)readDataFromFile(libraryMembers, LibraryMember.STORAGE_TYPE);
		long newId =  getNextLibraryMemberId(memberMap);
		libraryMember.setMemberId(newId);
		memberMap.put(newId, libraryMember);
		FileUtils.writeObject(memberMap, LibraryMember.STORAGE_TYPE);
		libraryMembers = memberMap;
		return newId;
	}
	
	private long getNextLibraryMemberId(HashMap<Long, LibraryMember> memberMap) {
		if (memberMap.isEmpty()) {
			return 1L;
		}
		return memberMap.keySet().size() + 1;
	}

	@Override
	public boolean updateLibraryMember(LibraryMember libraryMember) {
		addNewLibraryMember(libraryMember);
		return true;
	}

	@Override
	public LibraryMember findLibraryMemberById(Long id) {
		HashMap<Long, LibraryMember> memberMap =  (HashMap<Long, LibraryMember>)readDataFromFile(libraryMembers, LibraryMember.STORAGE_TYPE);
		return memberMap.get(id);
	}

	@Override
	public Collection<LibraryMember> findAllMembers() {
		HashMap<Long, LibraryMember> memberMap =  (HashMap<Long, LibraryMember>)readDataFromFile(libraryMembers, LibraryMember.STORAGE_TYPE);
		return memberMap.values();
	}

	@Override
	public void checkout(CheckoutRecord checkoutRecord) {
		HashMap<String, CheckoutRecord> checkoutMap = (HashMap<String, CheckoutRecord>)readDataFromFile(checkoutRecords, CheckoutRecord.STORAGE_TYPE);
		LibraryMember mem = checkoutRecord.getOwner();
		List<CheckoutRecordEntry> currentEntries = new ArrayList<CheckoutRecordEntry>();
		if (checkoutMap.size() > 0) {
			currentEntries = checkoutMap.get(mem.getMemberId()).getCheckoutEntries();
		}
		checkoutRecord.getCheckoutEntries().addAll(currentEntries);
		checkoutMap.put(String.valueOf(mem.getMemberId()), checkoutRecord);
		FileUtils.writeObject(checkoutMap, CheckoutRecord.STORAGE_TYPE);
		checkoutRecords = checkoutMap;
	}

	@Override
	public CheckoutRecord findCheckOutRecordByMemberId(Long memberId) {
		HashMap<String, CheckoutRecord> checkoutMap = (HashMap<String, CheckoutRecord>)readDataFromFile(checkoutRecords, CheckoutRecord.STORAGE_TYPE);
		return checkoutMap.get(memberId);
	}

	@Override
	public Collection<CheckoutRecord> getCheckoutRecordEntries() {
		HashMap<String, CheckoutRecord> checkoutMap = (HashMap<String, CheckoutRecord>)readDataFromFile(checkoutRecords, CheckoutRecord.STORAGE_TYPE);
		//return checkoutMap.values().stream().map(v -> v.getCheckoutEntries()).collect(Collectors.toList());
		return checkoutMap.values();
	}

	@Override
	public void addNewUser(User user) {
		HashMap<String, User> userMap = (HashMap<String, User>)readDataFromFile(users, User.STORAGE_TYPE);
		if (!userMap.containsKey(user.getUserName())) {
			userMap.put(user.getUserName(), user);
			FileUtils.writeObject(userMap, User.STORAGE_TYPE);
			users = userMap;
		}
	}

	@Override
	public User getUser(String username) {
		HashMap<String, User> userMap = (HashMap<String, User>)readDataFromFile(users, User.STORAGE_TYPE);
		return userMap.get(username);
	}
	
	@Override
	public User checkUser(User user) {
		HashMap<String, User> userMap = (HashMap<String, User>)readDataFromFile(users, User.STORAGE_TYPE);
		
		User dbUser = userMap.get(user.getUserName());
		if (dbUser != null && user.getPassword().equals(dbUser.getPassword())) {
			return dbUser;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private Object readDataFromFile(Object currentData, String type) {
		//return current book data in session
		if (currentData != null) {
			return currentData;
		}
		
		//read book data from file in the first time
		try {
			
			Object result = FileUtils.readObject(type);
			currentData = result != null ? result : new HashMap();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return currentData;
		
	}
	
	

}
