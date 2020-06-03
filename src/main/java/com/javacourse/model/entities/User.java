package com.javacourse.model.entities;

import com.javacourse.model.TaxGroup;

import javax.persistence.*;

@Entity
@Table(name="user")
@PrimaryKeyJoinColumn(name="account_id")
public class User extends Account{
    @Column(name="full_name")
    private String fullName;

    @Column(name="company_name")
    private String company;

    @Column(name="passport")
    private String passport;

    @Column(name="address")
    private String address;

    @Column(name="tax_group")
    @Enumerated(EnumType.STRING)
    private TaxGroup group;

    public User() {
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getPassport() {
        return passport;
    }
    public void setPassport(String passport) {
        this.passport = passport;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public TaxGroup getGroup() {
        return group;
    }
    public void setGroup(TaxGroup group) {
        this.group = group;
    }

}

