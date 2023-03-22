package com.endava.doctorsapi.tables.facility_department;

import com.endava.doctorsapi.general.base.BaseEntity;
import com.endava.doctorsapi.tables.department.Department;
import com.endava.doctorsapi.tables.doctor.Doctor;
import com.endava.doctorsapi.tables.facility.Facility;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "facilities_departments")
public class FacilityDepartment {

	@EmbeddedId
	private FacilityDepartmentKey id = new FacilityDepartmentKey();

	@ManyToOne
	@MapsId("facilityId")
	@JoinColumn(name = "facility_id")
	private Facility facility;

	@ManyToOne
	@MapsId("departmentId")
	@JoinColumn(name = "department_id")
	private Department department;

	@ManyToOne
	@MapsId("doctorId")
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;

	public FacilityDepartment(Facility facility, Department department, Doctor doctor) {
		this.facility = facility;
		this.department = department;
		this.doctor = doctor;
	}

	public FacilityDepartment() {
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


