package com.javacourse.model.entities.report;

import javax.persistence.*;

@Entity
@Table(name="second_group")
public class SecondGroup {
    @Id
    @Column(name = "second_group_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_quarter")
    private double firstQuarter;

    @Column(name = "second_quarter")
    private double secondQuarter;

    @Column(name = "third_quarter")
    private double thirdQuarter;

    @Column(name = "fourth_quarter")
    private double forthQuarter;

    @Column(name = "income")
    private double incomeVolume03;

    @Column(name = "taxed")
    private double taxedIncomeVolume04;

    public SecondGroup() {
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

    public double getIncomeVolume03() {
        return incomeVolume03;
    }

    public void setIncomeVolume03(double incomeVolume03) {
        this.incomeVolume03 = incomeVolume03;
    }

    public double getTaxedIncomeVolume04() {
        return taxedIncomeVolume04;
    }

    public void setTaxedIncomeVolume04(double taxedIncomeVolume04) {
        this.taxedIncomeVolume04 = taxedIncomeVolume04;
    }

}
