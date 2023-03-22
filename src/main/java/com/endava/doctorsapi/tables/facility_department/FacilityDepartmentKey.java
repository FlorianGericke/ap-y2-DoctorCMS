package com.endava.doctorsapi.tables.facility_department;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FacilityDepartmentKey implements Serializable {
	@Column(name = "facility_Id", nullable = false)
	private long facilityId;

	@Column(name = "department_Id", nullable = false)
	private long departmentId;

	@Column(name = "doctor_Id", nullable = false)
	private long doctorId;


	public FacilityDepartmentKey(long facilityId, long departmentId, long doctorId) {
		this.facilityId = facilityId;
		this.departmentId = departmentId;
		this.doctorId = doctorId;
	}

	public FacilityDepartmentKey() {
	}

	public long getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(long facilityId) {
		this.facilityId = facilityId;
	}

	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	public long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof FacilityDepartmentKey that)) return false;
		return getFacilityId() == that.getFacilityId() && getDepartmentId() == that.getDepartmentId() && getDoctorId() == that.getDoctorId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getFacilityId(), getDepartmentId(), getDoctorId());
	}
}
