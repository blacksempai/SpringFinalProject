package com.javacourse.taxApp.model.entities.report;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="report_general")
public class General {
    @Id
    @Column(name = "report_general_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employees_number")
    private int employeesNumber;

    @OneToMany(mappedBy = "general", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BusinessActivity> activities = new ArrayList<>();

}
