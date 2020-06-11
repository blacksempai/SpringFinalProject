package com.javacourse.taxApp.model.entities.report;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="report_info")
public class Info {
    @Id
    @Column(name = "report_info_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Info.Type type;

    @Column(name = "period")
    @Enumerated(EnumType.STRING)
    private Info.Period period;

    @Column(name = "year")
    private short year;

    @Column(name = "specified_period")
    @Enumerated(EnumType.STRING)
    private Info.Period specifiedPeriod;

    @Column(name = "specified_year")
    private short specifiedYear;

    @Column(name = "authority_name")
    private String authorityName;

    public enum Period{
        I_QUARTER,
        II_QUARTER,
        III_QUARTER,
        IV_QUARTER
    }

    public enum Type{
        SIMPLE,
        SIMPLE_NEW,
        SPECIFYING,
        REFERENCING
    }
}
