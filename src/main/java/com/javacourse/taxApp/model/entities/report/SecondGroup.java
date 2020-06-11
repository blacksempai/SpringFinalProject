package com.javacourse.taxApp.model.entities.report;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="second_group")
public class SecondGroup {
    @Id
    @Column(name = "second_group_id")
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
    private double incomeVolume03;

    @Column(name = "taxed")
    private double taxedIncomeVolume04;

}
