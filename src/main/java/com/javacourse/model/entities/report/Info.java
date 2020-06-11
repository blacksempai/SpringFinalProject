package com.javacourse.model.entities.report;

import javax.persistence.*;

@Entity
@Table(name="report_info")
public class Info {
    @Id
    @Column(name = "report_info_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

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

    public Info() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public Period getSpecifiedPeriod() {
        return specifiedPeriod;
    }

    public void setSpecifiedPeriod(Period specifiedPeriod) {
        this.specifiedPeriod = specifiedPeriod;
    }

    public short getSpecifiedYear() {
        return specifiedYear;
    }

    public void setSpecifiedYear(short specifiedYear) {
        this.specifiedYear = specifiedYear;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

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
