package com.endava.doctorsapi.tables.facility_department;


import com.endava.doctorsapi.tables.department.Department;
import com.endava.doctorsapi.tables.doctor.Doctor;
import com.endava.doctorsapi.tables.facility.Facility;
import jakarta.persistence.*;

@Entity
@Table(name = "Facility_Departments")
public class FacilityDepartment {

	@EmbeddedId
	private FacilityDepartmentKey id = new FacilityDepartmentKey();

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@MapsId("doctorId")
	private Doctor doctor;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@MapsId("facilityId")
	private Facility facility;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@MapsId("departmentId")
	private Department department;


	public FacilityDepartment() {
	}

	public FacilityDepartment(Facility facility, Department department, Doctor doctor) {
		this.doctor = doctor;
		this.facility = facility;
		this.department = department;
	}

	public FacilityDepartmentKey getId() {
		return id;
	}

	public void setId(FacilityDepartmentKey id) {
		this.id = id;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
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
}


