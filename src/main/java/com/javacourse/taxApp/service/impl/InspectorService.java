package com.javacourse.taxApp.service.impl;

import com.javacourse.taxApp.model.entities.Inspector;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("inspectorService")
public class InspectorService extends AccountServiceImpl<Inspector> { }
