package com.javacourse.taxApp.service;

import com.javacourse.taxApp.model.entities.Inspector;
import com.javacourse.taxApp.model.entities.User;
import com.javacourse.taxApp.model.entities.report.Report;

import java.util.List;

public interface ReportService {

    Report save(Report report);

    void delete(Report report);

    Report changeInspector(Report report);

    Inspector chooseInspector();

    List<Report> getAll(User user);

    List<Report> getAll(Inspector inspector);
}
