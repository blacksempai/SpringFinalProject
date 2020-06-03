package com.javacourse.model.entities.report;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="first_group")
public class FirstGroup {
    @Id
    @Column(name = "first_group_id")
    private int id;

    @Column(name = "first_quarter")
    private double firstQuarter;

    @Column(name = "second_quarter")
    private double secondQuarter;

    @Column(name = "third_quarter")
    private double thirdQuarter;

    @Column(name = "fourth_quarter")
    private double forthQuarter;

    @Column(name = "income")
    private double incomeVolume01;

    @Column(name = "taxed")
    private double taxedIncomeVolume02;

    public FirstGroup() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getFirstQuarter() {
        return firstQuarter;
    }

    public void setFirstQuarter(double firstQuarter) {
        this.firstQuarter = firstQuarter;
    }

    public double getSecondQuarter() {
        return secondQuarter;
    }

    public void setSecondQuarter(double secondQuarter) {
        this.secondQuarter = secondQuarter;
    }

    public double getThirdQuarter() {
        return thirdQuarter;
    }

    public void setThirdQuarter(double thirdQuarter) {
        this.thirdQuarter = thirdQuarter;
    }

    public double getForthQuarter() {
        return forthQuarter;
    }

    public void setForthQuarter(double forthQuarter) {
        this.forthQuarter = forthQuarter;
    }

    public double getIncomeVolume01() {
        return incomeVolume01;
    }

    public void setIncomeVolume01(double incomeVolume01) {
        this.incomeVolume01 = incomeVolume01;
    }

    public double getTaxedIncomeVolume02() {
        return taxedIncomeVolume02;
    }

    public void setTaxedIncomeVolume02(double taxedIncomeVolume02) {
        this.taxedIncomeVolume02 = taxedIncomeVolume02;
    }
}
