package com.endava.doctorsapi.tables.patient;

import com.endava.doctorsapi.general.base.BaseEntity;
import com.endava.doctorsapi.tables.address.Address;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "patients")
@SQLDelete(sql = "UPDATE patients SET state = 'deleted', deleted_at = current_date WHERE id=?")
@Where(clause = "state IN ('created', 'updated')")
public class Patient extends BaseEntity {
	@Column(name = "insurance_number", nullable = false, unique = true)
	private String insuranceNumber;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "age", nullable = false)
	private int age;

	@Column(name = "phone")
	private String phone;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Address address;

	public Patient(String insuranceNumber, String firstName, String lastName, int age) {
		this.insuranceNumber = insuranceNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public Patient(String insuranceNumber, String firstName, String lastName, int age, String phone, Address address) {
		this.insuranceNumber = insuranceNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.phone = phone;
		this.address = address;
	}

	public Patient() {
	}

	@Override
	public String toString() {
		return "Patient{" +
				"insuranceNumber=" + insuranceNumber +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", age=" + age +
				", phone='" + phone + '\'' +
				", address=" + address +
				", id=" + id +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				", deletedAt=" + deletedAt +
				", state='" + state + '\'' +
				'}';
	}

	public String getInsuranceNumber() {
		return insuranceNumber;
	}

	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
