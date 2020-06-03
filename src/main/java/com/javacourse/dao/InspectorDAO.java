package com.javacourse.dao;

import com.javacourse.model.entities.Account;
import com.javacourse.model.entities.Inspector;

import java.util.List;

public interface InspectorDAO{

    void create(Inspector inspector);

    List<Inspector> getAllInspectors();

    Inspector getById(int id);

    Inspector getByLogin(String login);

    void update(Inspector inspector);

    boolean isExists(String login);

    Inspector getInspectorWithLessReportsInService();
}
