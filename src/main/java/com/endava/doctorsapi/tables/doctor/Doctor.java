package com.endava.doctorsapi.tables.doctor;

import com.endava.doctorsapi.general.base.EntityBase;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "doctors")
@SQLDelete(sql = "UPDATE doctors SET state = 'deleted', deleted_at = current_date WHERE id=?")
@Where(clause = "state IN ('created', 'updated')")
public class Doctor extends EntityBase {
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	public Doctor(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Doctor() {

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

	@Override
	public String toString() {
		return "Doctor{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				", deletedAt=" + deletedAt +
				", state='" + state + '\'' +
				'}';
	}
}
