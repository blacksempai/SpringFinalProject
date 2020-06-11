package com.javacourse.taxApp.dao;

import com.javacourse.taxApp.model.entities.Inspector;

import javax.transaction.Transactional;

@Transactional
public interface InspectorDAO extends AbstractAccountDAO<Inspector> {
}
