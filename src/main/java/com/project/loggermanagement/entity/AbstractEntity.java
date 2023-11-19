package com.project.loggermanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass

public class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;

    @Column(name = "created_date", updatable = false)
    protected Long createdDate;

    @Column(name = "status")
    protected Boolean status;

    @Column(name = "created_by")
    protected Long createdBy;

    @Column(name = "updated_date")
    protected Long updatedDate;

    @Column(name = "updated_by")
    protected Long updatedBy;

    //status, createdOn, createdBy, lastModified, lastModifiedBy

    public void setCreatedDate() {
        this.createdDate = System.currentTimeMillis();
    }

    public void setCreatedDate(long timeStamp) {
        this.createdDate = timeStamp;
    }

    public void setUpdatedDate() {
        this.updatedDate = System.currentTimeMillis();
    }

    public void createdBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public void updatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

}
