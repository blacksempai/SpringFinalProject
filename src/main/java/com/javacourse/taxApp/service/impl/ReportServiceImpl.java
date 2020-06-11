package com.javacourse.taxApp.service.impl;

import com.javacourse.taxApp.dao.AbstractAccountDAO;
import com.javacourse.taxApp.dao.ReportDAO;
import com.javacourse.taxApp.model.entities.Inspector;
import com.javacourse.taxApp.model.entities.User;
import com.javacourse.taxApp.model.entities.report.Report;
import com.javacourse.taxApp.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    ReportDAO reportDAO;
    @Autowired
    AbstractAccountDAO<Inspector> inspectorDAO;

    @Override
    @Transactional
    public Report save(Report report) {
        return reportDAO.save(report);
    }

    @Override
    @Transactional
    public void delete(Report report) {
        reportDAO.delete(report);
    }

    @Override
    @Transactional
    public Report changeInspector(Report report) {
        Inspector inspector = report.getInspector();
        inspector.setComplaintNumber(inspector.getComplaintNumber()+1);
        inspector.setReportsInService(inspector.getReportsInService()-1);
        inspectorDAO.save(inspector);

        inspector = getAnotherInspector(inspector);
        inspector.setReportsInService(inspector.getReportsInService()+1);
        report.setInspector(inspector);
        inspectorDAO.save(inspector);
        return save(report);
    }

    private Inspector getAnotherInspector(Inspector inspector){
        Inspector anotherInspector = null;
        List<Inspector> inspectors = inspectorDAO.findAll();
        inspectors.remove(inspector);
        return inspectors.stream()
                .min(Comparator.comparing(Inspector::getReportsInService))
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    @Transactional
    public Inspector chooseInspector() {
        return inspectorDAO.findAll()
                .stream()
                .min(Comparator.comparing(Inspector::getReportsInService))
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    @Transactional
    public List<Report> getAll(User user) {
        return reportDAO.findAllByUser(user);
    }

    @Override
    @Transactional
    public List<Report> getAll(Inspector inspector) {
        return reportDAO.findAllByInspector(inspector);
    }
}
