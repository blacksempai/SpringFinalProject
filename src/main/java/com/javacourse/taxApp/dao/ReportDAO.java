package com.javacourse.taxApp.dao;

import com.javacourse.taxApp.model.entities.Inspector;
import com.javacourse.taxApp.model.entities.User;
import com.javacourse.taxApp.model.entities.report.Report;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportDAO extends CrudRepository<Report, Long> {

    List<Report> findAllByUser(User user);

    List<Report> findAllByInspector(Inspector inspector);
}
