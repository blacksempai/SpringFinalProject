package com.javacourse.model;

import com.javacourse.controller.utils.parser.FirstGroupParser;
import com.javacourse.controller.utils.parser.ReportParser;
import com.javacourse.controller.utils.parser.SecondGroupParser;
import com.javacourse.controller.utils.parser.ThirdGroupParser;

public enum TaxGroup{
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
