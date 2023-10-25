package com.example.SiteProject.SiteProject.entities;

import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

public class BaseEntity {
     @CreationTimestamp
     @Column(name = "CREATED_DATE")
     private ZonedDateTime createdDate;
     @UpdateTimestamp
     @Column(name = "LAST_MODIFIED_DATE")
     private ZonedDateTime lastModifiedDate;

     @PrePersist
     public void prePersist() {
          ZonedDateTime now = ZonedDateTime.now();
          this.createdDate = now;
          this.lastModifiedDate = now;
     }

     @PreUpdate
     public void preUpdate() {
          this.lastModifiedDate = ZonedDateTime.now();
     }
}
