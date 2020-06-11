package com.javacourse.taxApp.model.entities.report;

import com.javacourse.taxApp.model.entities.Inspector;
import com.javacourse.taxApp.model.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name="report")
public class Report {
    @Id
    @Column(name="report_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "body_id")
    private ReportBody reportBody;

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

    public enum Status{
        PENDING,
        DECLINED,
        ACCEPTED
    }
}
