package com.javacourse.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="inspector")
@PrimaryKeyJoinColumn(name="account_id")
public class Inspector extends Account {
    @Column(name="full_name")
    private String fullName;

    @Column(name="complaint_number")
    private Integer complaintNumber;

    @Column(name="reports_in_service")
    private Integer reportsInService;

    public Inspector() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getComplaintNumber() {
        return complaintNumber;
    }

    public void setComplaintNumber(Integer complaintNumber) {
        this.complaintNumber = complaintNumber;
    }

    public Integer getReportsInService() {
        return reportsInService;
    }

    public void setReportsInService(Integer reportsInService) {
        this.reportsInService = reportsInService;
    }

}
