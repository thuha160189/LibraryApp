package lib.man.model;

public class LibraryMember extends Person {
	
	private static final long serialVersionUID = -7206984963573838605L;
	public static final String STORAGE_TYPE = "LIBRARY_MEMBER";
	
	
	private long memberId;
	private CheckoutRecord checkoutRecord;
	
	public LibraryMember() {}
	
	public LibraryMember(String firstName, String lastName, Address address, String phone) {
		super(firstName, lastName, address, phone);
	}
	
	public LibraryMember(long memberId, String firstName, String lastName, Address address, String phone, CheckoutRecord checkoutRecord) {
		super(firstName, lastName, address, phone);
		this.memberId = memberId;
		this.checkoutRecord = checkoutRecord;
	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public CheckoutRecord getCheckoutRecord() {
		return checkoutRecord;
	}

	public void setCheckoutRecord(CheckoutRecord checkoutRecord) {
		this.checkoutRecord = checkoutRecord;
	}
	
	

}
