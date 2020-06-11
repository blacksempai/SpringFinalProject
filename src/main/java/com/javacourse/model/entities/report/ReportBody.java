package com.javacourse.model.entities.report;

import javax.persistence.*;

@Entity
@Table(name="report_body")
public class ReportBody {
    @Id
    @Column(name="report_body_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "info_id")
    private Info info;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "general_id")
    private General general;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "first_group_id")
    private FirstGroup firstGroup;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "second_group_id")
    private SecondGroup secondGroup;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "third_group_id")
    private ThirdGroup thirdGroup;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "error_id")
    private ErrorReport errorReport;


    public ReportBody() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public General getGeneral() {
        return general;
    }

    public void setGeneral(General general) {
        this.general = general;
    }

    public FirstGroup getFirstGroup() {
        return firstGroup;
    }

    public void setFirstGroup(FirstGroup firstGroup) {
        this.firstGroup = firstGroup;
    }

    public SecondGroup getSecondGroup() {
        return secondGroup;
    }

    public void setSecondGroup(SecondGroup secondGroup) {
        this.secondGroup = secondGroup;
    }

    public ThirdGroup getThirdGroup() {
        return thirdGroup;
    }

    public void setThirdGroup(ThirdGroup thirdGroup) {
        this.thirdGroup = thirdGroup;
    }

    public ErrorReport getErrorReport() {
        return errorReport;
    }

    public void setErrorReport(ErrorReport errorReport) {
        this.errorReport = errorReport;
    }

}
