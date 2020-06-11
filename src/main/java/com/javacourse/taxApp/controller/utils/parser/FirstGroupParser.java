package com.javacourse.taxApp.controller.utils.parser;

import com.javacourse.taxApp.model.entities.report.SecondGroup;
import com.javacourse.taxApp.model.entities.report.ThirdGroup;

import javax.servlet.http.HttpServletRequest;

public class FirstGroupParser extends ReportParser {
    @Override
    protected SecondGroup parseSecondGroup(HttpServletRequest request) {
        return new SecondGroup();
    }

    @Override
    protected ThirdGroup parseThirdGroup(HttpServletRequest request) {
        return new ThirdGroup();
    }
}
