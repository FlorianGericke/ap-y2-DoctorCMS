package com.endava.doctorsapi.tables.address;

import com.endava.doctorsapi.general.base.BaseEntity;
import com.endava.doctorsapi.tables.facility.Facility;
import com.endava.doctorsapi.tables.patient.Patient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "addresses")
@SQLDelete(sql = "UPDATE addresses SET state = 'deleted', deleted_at = current_date WHERE id=?")
@Where(clause = "state IN ('created', 'updated')")
public class Address extends BaseEntity {

	@Column(name = "street", nullable = false)
	private String street;

	@Column(name = "house_number", nullable = false)
	private String houseNumber;

	@Column(name = "zip", nullable = false)
	private int postCode;

	@Column(name = "location", nullable = false)
	private String location;


	@ManyToMany(mappedBy = "addresses", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Facility> facilities = new HashSet<>();

	@OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Patient> patients;

	public Address(String street, String houseNumber, int postCode, String location) {
		this.street = street;
		this.houseNumber = houseNumber;
		this.postCode = postCode;
		this.location = location;
	}

	public Address() {
	}

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

	public Set<Facility> getFacilities() {
		return facilities;
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

	public void assignFacility(Facility facility) {
		facilities.add(facility);
	}
}
