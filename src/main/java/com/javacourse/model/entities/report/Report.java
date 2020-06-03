package com.javacourse.model.entities.report;

import com.javacourse.model.entities.Inspector;
import com.javacourse.model.entities.User;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="report")
public class Report {
    @Id
    @Column(name="report_id")
    private Integer id;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne()
    @JoinColumn(name = "inspector_id")
    private Inspector inspector;

    @Column(name="date")
    private Timestamp declarationSubmissionDate;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private Report.Status status;

    @Column(name="review")
    private String review;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "report_id")
    private ReportBody reportBody;

    public Report() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Inspector getInspector() {
        return inspector;
    }

    public void setInspector(Inspector inspector) {
        this.inspector = inspector;
    }

    public Timestamp getDeclarationSubmissionDate() {
        return declarationSubmissionDate;
    }

    public void setDeclarationSubmissionDate(Timestamp declarationSubmissionDate) {
        this.declarationSubmissionDate = declarationSubmissionDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public ReportBody getReportBody() {
        return reportBody;
    }

    public void setReportBody(ReportBody reportBody) {
        this.reportBody = reportBody;
    }

    public enum Status{
        PENDING,
        DECLINED,
        ACCEPTED
    }
}
