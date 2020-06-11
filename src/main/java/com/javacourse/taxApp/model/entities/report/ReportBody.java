package com.javacourse.taxApp.model.entities.report;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="report_body")
public class ReportBody {
    @Id
    @Column(name="report_body_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

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

}
