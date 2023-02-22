package com.endava.doctorsapi.department;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "state", nullable = false)
    private String state;

    public Department(String name) {
        this.name = name;
    }

   public Department() {

   }

   @PrePersist
   public void onPrePersist() {
        this.createdAt = LocalDateTime.now();
        this.state = EntityStates.CREATED.toString();
   }

   @PreUpdate
   public void onPreUpdate() {
        if (state.equals(EntityStates.DELETED.toString())) {
            return;
        }
        this.updatedAt = LocalDateTime.now();
        this.state = EntityStates.UPDATED.toString();
   }

   @PostRemove
    public void onPostRemove() {
        this.deletedAt = LocalDateTime.now();
        this.state = EntityStates.DELETED.toString();
       System.out.println(getName() + " is removed");
   }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
