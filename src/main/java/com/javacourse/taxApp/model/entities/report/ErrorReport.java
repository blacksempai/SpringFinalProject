package com.javacourse.taxApp.model.entities.report;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="report_errors")
public class ErrorReport {
    @Id
    @Column(name = "report_errors_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "single_tax")
    private double singleTax15;

    @Column(name = "spec_tax")
    private double specifiedTax16;

    @Column(name = "fine_percent")
    private short finePercent;

    @Column(name = "penny_sum")
    private double pennySum20;

}
