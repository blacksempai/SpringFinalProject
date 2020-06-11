package com.javacourse.taxApp.model;

import com.javacourse.taxApp.controller.utils.parser.FirstGroupParser;
import com.javacourse.taxApp.controller.utils.parser.ReportParser;
import com.javacourse.taxApp.controller.utils.parser.SecondGroupParser;
import com.javacourse.taxApp.controller.utils.parser.ThirdGroupParser;

public enum TaxGroup {
    FIRST(new FirstGroupParser()),
    SECOND(new SecondGroupParser()),
    THIRD(new ThirdGroupParser());

    private ReportParser parser;

    TaxGroup(ReportParser parser){
        this.parser = parser;
    }

    public ReportParser getReportParser(){
        return this.parser;
    }
}
