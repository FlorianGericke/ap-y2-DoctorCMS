package com.endava.doctorsapi;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@MappedSuperclass
public class EntityBase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	protected Long id;

	@Column(name = "created_at", nullable = false)
	protected LocalDateTime createdAt;

	@Column(name = "updated_at")
	protected LocalDateTime updatedAt;

	@Column(name = "deleted_at")
	protected LocalDateTime deletedAt;

	@Column(name = "state", nullable = false)
	protected String state;

	@PrePersist
	public void onPrePersist() {
		this.createdAt = LocalDateTime.now();
		this.state = EntityStates.CREATED.toString();
	}

	@PreUpdate
	public void onPreUpdate() {
		if(state.equals(EntityStates.DELETED.toString())){
			return;
		}
		this.updatedAt = LocalDateTime.now();
		this.state = EntityStates.UPDATED.toString();
	}

	@PostRemove
	public void onPostRemove() {
		this.deletedAt = LocalDateTime.now();
		this.state = EntityStates.DELETED.toString();
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
