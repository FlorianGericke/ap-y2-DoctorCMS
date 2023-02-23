package com.endava.doctorsapi.tables.address;

import com.endava.doctorsapi.tables.general.base.EntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "addresses")
public class Address extends EntityBase {

	@Column(name = "street", nullable = false)
	private String street;

	@Column(name = "house_number", nullable = false)
	private String houseNumber;

	@Column(name = "zip", nullable = false)
	private int postCode;

	@Column(name = "location", nullable = false)
	private String location;


	public Address(String street, String houseNumber, int postCode, String location) {
		this.street = street;
		this.houseNumber = houseNumber;
		this.postCode = postCode;
		this.location = location;
	}

	public Address() {}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStreet() {
		return street;
	}

	@Column(name = "street", nullable = false)
	public String getHouseNumber() {
		return houseNumber;
	}

	public int getPostCode() {
		return postCode;
	}

	public String getLocation() {
		return location;
	}

	@Override
	public String toString() {
		return "Adresse{" +
				"id=" + id +
				",street='" + street + '\'' +
				", houseNumber='" + houseNumber + '\'' +
				", postCode=" + postCode +
				", location='" + location + '\'' +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				", deletedAt=" + deletedAt +
				", state='" + state + '\'' +
				'}';
	}
}
