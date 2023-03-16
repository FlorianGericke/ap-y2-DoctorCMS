package com.endava.doctorsapi.tables.facility;

import com.endava.doctorsapi.general.base.EntityBase;
import com.endava.doctorsapi.tables.address.Address;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "facilities")
@SQLDelete(sql = "UPDATE facilities SET state = 'deleted', deleted_at = current_date WHERE id=?")
@Where(clause = "state IN ('created', 'updated')")
public class Facility extends EntityBase {

	@Column(name = "name", nullable = false)
	private String name;

	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
		name= "facility_address",
			joinColumns = @JoinColumn(name = "facility_id"),
			inverseJoinColumns = @JoinColumn(name ="address_id")
	)
	private Set<Address> addresses = new HashSet<>();

	public Facility(String name) {
		this.name = name;
	}

	public Facility() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	@Override
	public String toString() {
		return "Facility{" +
				"name='" + name + '\'' +
				", id=" + id +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				", deletedAt=" + deletedAt +
				", state='" + state + '\'' +
				'}';
	}

	public void assignAddress(Address e) {
		addresses.add(e);
	}
}
