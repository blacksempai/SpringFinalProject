package com.javacourse.model.entities.report;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="report_general")
public class General {
    @Id
    @Column(name = "report_general_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "employees_number")
    private int employeesNumber;

    @OneToMany(mappedBy = "general", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BusinessActivity> activities = new ArrayList<>();

    public General() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(int employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public List<BusinessActivity> getActivities() {
        return activities;
    }

    public void setActivities(List<BusinessActivity> activities) {
        this.activities = activities;
    }

}
