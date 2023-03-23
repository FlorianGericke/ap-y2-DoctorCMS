package com.endava.doctorsapi.tables.department;

import com.endava.doctorsapi.general.base.BaseEntity;
import com.endava.doctorsapi.tables.facility_department.FacilityDepartment;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Set;

@Entity
@Table(name = "departments")
@SQLDelete(sql = "UPDATE departments SET state = 'deleted', deleted_at = current_date WHERE id=?")
@Where(clause = "state IN ('created', 'updated')")
public class Department extends BaseEntity {

	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(mappedBy = "department")
	private Set<FacilityDepartment> facilityDepartments;


	public Department(String name) {
		this.name = name;
	}

	public Department() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<FacilityDepartment> getFacilityDepartments() {
		return facilityDepartments;
	}

	public void setFacilityDepartments(Set<FacilityDepartment> facilityDepartments) {
		this.facilityDepartments = facilityDepartments;
	}

	@Override
	public String toString() {
		return "Department{" +
				"name='" + name + '\'' +
				", id=" + id +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				", deletedAt=" + deletedAt +
				", state='" + state + '\'' +
				'}';
	}
}
