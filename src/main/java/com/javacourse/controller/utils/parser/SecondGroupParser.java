package com.javacourse.controller.utils.parser;

import com.javacourse.controller.utils.parser.ReportParser;
import com.javacourse.model.entities.report.FirstGroup;
import com.javacourse.model.entities.report.ThirdGroup;

import javax.servlet.http.HttpServletRequest;

public class SecondGroupParser extends ReportParser {
    @Override
    protected FirstGroup parseFirstGroup(HttpServletRequest request) {
        return new FirstGroup();
    }

    @Override
    protected ThirdGroup parseThirdGroup(HttpServletRequest request) {
        return new ThirdGroup();
    }
}
