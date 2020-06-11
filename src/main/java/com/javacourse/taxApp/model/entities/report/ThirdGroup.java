package com.javacourse.taxApp.model.entities.report;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="third_group")
public class ThirdGroup {
    @Id
    @Column(name = "third_group_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "3_percent")
    private double threePercentTaxed05;

    @Column(name = "5_percent")
    private double fivePercentTaxed06;

    @Column(name = "15_percent")
    private double fifteenPercentTaxed07;

}
