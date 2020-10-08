package lib.man.model;

import java.io.Serializable;

public class Person implements Serializable{
	
	private static final long serialVersionUID = 7890481667140731124L;
	private String firstName;
	private String lastName;
	private Address address;
	private String phone;
	

	public Person() {
		this.firstName = "";
		this.lastName = "";
		this.address = null;
		this.phone = "";
	}
	
	public Person(String firstName, String lastName, Address address, String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	

}
