package com.javacourse.model.entities.report;

import javax.persistence.*;

@Entity
@Table(name="report_errors")
public class ErrorReport {
    @Id
    @Column(name = "report_errors_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "single_tax")
    private double singleTax15;

    @Column(name = "spec_tax")
    private double specifiedTax16;

    @Column(name = "fine_percent")
    private short finePercent;

    @Column(name = "penny_sum")
    private double pennySum20;

    public ErrorReport() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSingleTax15() {
        return singleTax15;
    }

    public void setSingleTax15(double singleTax15) {
        this.singleTax15 = singleTax15;
    }

    public double getSpecifiedTax16() {
        return specifiedTax16;
    }

    public void setSpecifiedTax16(double specifiedTax16) {
        this.specifiedTax16 = specifiedTax16;
    }

    public short getFinePercent() {
        return finePercent;
    }

    public void setFinePercent(short finePercent) {
        this.finePercent = finePercent;
    }

    public double getPennySum20() {
        return pennySum20;
    }

    public void setPennySum20(double pennySum20) {
        this.pennySum20 = pennySum20;
    }

}
