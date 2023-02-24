package com.endava.doctorsapi.tables.department;

import com.endava.doctorsapi.tables.general.base.EntityBase;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "departments")
@SQLDelete(sql = "UPDATE departments SET state = 'deleted', deleted_at = current_date WHERE id=?")
@Where(clause = "state IN ('created', 'updated')")
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
