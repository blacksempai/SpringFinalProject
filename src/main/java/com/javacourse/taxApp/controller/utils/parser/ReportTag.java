package com.javacourse.taxApp.controller.utils.parser;

public enum ReportTag {
    TYPE("0104zvitna"),
    PERIOD("zvitn_period"),
    YEAR("zvitnrik"),
    SPECIFIED_PERIOD("zvitn_period_utochn"),
    SPECIFIED_YEAR("zvitnrik_utochn"),
    AUTHORITY_NAME("nazva_dpi"),
    EMPLOYEES_AMOUNT("chiselnist"),
    BUSINESS_ACTIVITY_CODE("kodkved"),
    BUSINESS_ACTIVITY("nazvakved"),
    FIRST_GROUP_ADVANCED_PAYMENT_I("gr1_kv1"),
    FIRST_GROUP_ADVANCED_PAYMENT_II("gr1_kv2"),
    FIRST_GROUP_ADVANCED_PAYMENT_III("gr1_kv3"),
    FIRST_GROUP_ADVANCED_PAYMENT_IV("gr1_kv4"),
    FIRST_GROUP_INCOME_VOLUME("rd01"),
    FIRST_GROUP_TAXED_INCOME_VOLUME("rd02"),
    SECOND_GROUP_ADVANCED_PAYMENT_I("gr2_kv1"),
    SECOND_GROUP_ADVANCED_PAYMENT_II("gr2_kv2"),
    SECOND_GROUP_ADVANCED_PAYMENT_III("gr2_kv3"),
    SECOND_GROUP_ADVANCED_PAYMENT_IV("gr2_kv4"),
    SECOND_GROUP_INCOME_VOLUME("rd03"),
    SECOND_GROUP_TAXED_INCOME_VOLUME("rd04"),
    THIRD_GROUP_3_PERCENT_TAXED("rd05"),
    THIRD_GROUP_5_PERCENT_TAXED("rd06"),
    THIRD_GROUP_15_PERCENT_TAXED("rd07"),
    SINGLE_TAX_AMOUNT("rd15"),
    SPECIFIED_TAX_AMOUNT("rd16"),
    FINE_PERCENT("rd19dod"),
    PENNY_SUM("rd20");

    private String tag;

    ReportTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
