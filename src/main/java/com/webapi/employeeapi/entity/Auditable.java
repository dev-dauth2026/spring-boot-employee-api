package com.webapi.employeeapi.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@MappedSuperclass
public abstract class Auditable {
	@Column(name="created_at", nullable=false, updatable=false)
	private Instant createdAt;
	@Column(name="updated_at", nullable=false)
	private Instant updatedAt;
	
	@PrePersist 
	void onCreate() {
		var now = Instant.now();
		createdAt = now;
		updatedAt = now;
	}
	
	@PreUpdate
	  void onUpdate() {
	    this.updatedAt = Instant.now();
	  }
	
	public Instant getCreatedAt() {
		return createdAt;
	}
	
	public Instant getUpdatedAt() {
		return updatedAt;
	}
}
