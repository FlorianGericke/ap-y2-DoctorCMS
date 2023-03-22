package com.endava.doctorsapi.tables.facility_department;

import com.endava.doctorsapi.tables.department.Department;
import com.endava.doctorsapi.tables.doctor.Doctor;
import com.endava.doctorsapi.tables.facility.Facility;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FacilityDepartmentKey implements Serializable {

	@ManyToOne()
	@JoinColumn(name = "facility_id")
	private Facility facility;

	@ManyToOne()
	@JoinColumn(name = "department_id")
	private Department department;

	@ManyToOne()
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;


	public FacilityDepartmentKey(Facility facility, Department department, Doctor doctor) {
		this.facility = facility;
		this.department = department;
		this.doctor = doctor;
	}

	public FacilityDepartmentKey() {

	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof FacilityDepartmentKey that)) return false;
		return getFacility().equals(that.getFacility()) && getDepartment().equals(that.getDepartment()) && getDoctor().equals(that.getDoctor());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getFacility(), getDepartment(), getDoctor());
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
}
