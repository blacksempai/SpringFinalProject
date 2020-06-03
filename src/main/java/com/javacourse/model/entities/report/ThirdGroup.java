package com.javacourse.model.entities.report;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="third_group")
public class ThirdGroup {
    @Id
    @Column(name = "third_group_id")
    private int id;

    @Column(name = "3_percent")
    private double threePercentTaxed05;

    @Column(name = "5_percent")
    private double fivePercentTaxed06;

    @Column(name = "15_percent")
    private double fifteenPercentTaxed07;

    public ThirdGroup() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getThreePercentTaxed05() {
        return threePercentTaxed05;
    }

    public void setThreePercentTaxed05(double threePercentTaxed05) {
        this.threePercentTaxed05 = threePercentTaxed05;
    }

    public double getFivePercentTaxed06() {
        return fivePercentTaxed06;
    }

    public void setFivePercentTaxed06(double fivePercentTaxed06) {
        this.fivePercentTaxed06 = fivePercentTaxed06;
    }

    public double getFifteenPercentTaxed07() {
        return fifteenPercentTaxed07;
    }

    public void setFifteenPercentTaxed07(double fifteenPercentTaxed07) {
        this.fifteenPercentTaxed07 = fifteenPercentTaxed07;
    }
}
