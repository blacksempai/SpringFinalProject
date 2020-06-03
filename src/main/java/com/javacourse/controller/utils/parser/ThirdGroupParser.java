package com.javacourse.controller.utils.parser;

import com.javacourse.controller.utils.parser.ReportParser;
import com.javacourse.model.entities.report.FirstGroup;
import com.javacourse.model.entities.report.SecondGroup;

import javax.servlet.http.HttpServletRequest;

public class ThirdGroupParser extends ReportParser {
    @Override
    protected FirstGroup parseFirstGroup(HttpServletRequest request) {
        return new FirstGroup();
    }

    @Override
    protected SecondGroup parseSecondGroup(HttpServletRequest request) {
        return new SecondGroup();
    }
}
