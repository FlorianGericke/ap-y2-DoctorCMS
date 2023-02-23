package com.endava.doctorsapi.tables.department;

import com.endava.doctorsapi.tables.general.base.EntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "departments")
public class Department extends EntityBase {

	@Column(name = "name", nullable = false)
	private String name;


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
