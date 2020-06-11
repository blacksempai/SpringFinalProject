package com.javacourse.taxApp.model.entities.report;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="first_group")
public class FirstGroup {
    @Id
    @Column(name = "first_group_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

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

}
