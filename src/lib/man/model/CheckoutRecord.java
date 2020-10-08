package lib.man.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable {

	private static final long serialVersionUID = 8826494902880729587L;
	public static final String STORAGE_TYPE = "CHECKOUT_RECORD";

	private List<CheckoutRecordEntry> checkoutEntries = new ArrayList<CheckoutRecordEntry>();
	private LibraryMember owner;
	
	public CheckoutRecord(LibraryMember owner, CheckoutRecordEntry checkoutEntry) {
		this.owner = owner;
		this.checkoutEntries.add(checkoutEntry);
	}
	
	public CheckoutRecord(List<CheckoutRecordEntry> checkoutEntries, LibraryMember owner) {
		super();
		this.checkoutEntries = checkoutEntries;
		this.owner = owner;
	}
	
	public void addCheckoutRecordEntry(CheckoutRecordEntry checkoutEntry) {
		if (checkoutEntry == null) {
			throw new IllegalArgumentException("The checkout record entry must be not null.");
		}
		this.checkoutEntries.add(checkoutEntry);
	}

	public List<CheckoutRecordEntry> getCheckoutEntries() {
		return checkoutEntries;
	}

	public void setCheckoutEntries(List<CheckoutRecordEntry> checkoutEntries) {
		if (checkoutEntries != null && !checkoutEntries.isEmpty()) {
			this.checkoutEntries = checkoutEntries;
		}
	}

	public LibraryMember getOwner() {
		return owner;
	}

	public void setOwner(LibraryMember owner) {
		this.owner = owner;
	}
	
	public String getfirstName() {
		return owner.getFirstName();
	}
	
	public String getlastName() {
		return owner.getLastName();
	}
	public long getmemberId() {
		return owner.getMemberId();
	}
	
}
