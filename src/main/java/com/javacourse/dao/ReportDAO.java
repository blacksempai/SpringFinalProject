package com.javacourse.dao;

import com.javacourse.model.entities.Inspector;
import com.javacourse.model.entities.User;
import com.javacourse.model.entities.report.Report;

import java.util.List;

public interface ReportDAO {

    void create(Report report);

    void update(Report report);

    void delete(Report report);

    List<Report> getReportsByUser(User user);

    List<Report> getReportsByInspector(Inspector inspector);
}
