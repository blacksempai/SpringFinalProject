package com.javacourse.controller.utils.parser;

import com.javacourse.model.entities.report.*;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ReportParser {

    public Report parseRequest(HttpServletRequest request)
            throws NumberFormatException, NullPointerException {
        Report report = new Report();
        report.setReportBody(parseReportBody(request));
        report.setStatus(Report.Status.PENDING);
        report.setDeclarationSubmissionDate(new Timestamp(new Date().getTime()));
        return report;
    }

    private ReportBody parseReportBody(HttpServletRequest request){
        ReportBody reportBody = new ReportBody();
        reportBody.setInfo(parseInfo(request));
        reportBody.setFirstGroup(parseFirstGroup(request));
        reportBody.setSecondGroup(parseSecondGroup(request));
        reportBody.setThirdGroup(parseThirdGroup(request));
        reportBody.setErrorReport(parseErrorReport(request));
        reportBody.setGeneral(parseGeneral(request));
        return reportBody;
    }

    private Info parseInfo(HttpServletRequest request) {
        Info info = new Info();
        info.setYear(Short.parseShort(request.getParameter(ReportTag.YEAR.getTag())));
        info.setSpecifiedYear(Short.parseShort(request.getParameter(ReportTag.SPECIFIED_YEAR.getTag())));
        info.setPeriod(getPeriod(request.getParameter(ReportTag.PERIOD.getTag())));
        info.setSpecifiedPeriod(getPeriod(request.getParameter(ReportTag.SPECIFIED_PERIOD.getTag())));
        info.setType(getType(request.getParameter(ReportTag.TYPE.getTag())));
        info.setAuthorityName(request.getParameter(ReportTag.AUTHORITY_NAME.getTag()));
        return info;
    }

    protected FirstGroup parseFirstGroup(HttpServletRequest request) {
        FirstGroup firstGroup = new FirstGroup();
        firstGroup.setFirstQuarter(Double.parseDouble(request.getParameter(ReportTag.FIRST_GROUP_ADVANCED_PAYMENT_I.getTag())));
        firstGroup.setSecondQuarter(Double.parseDouble(request.getParameter(ReportTag.FIRST_GROUP_ADVANCED_PAYMENT_II.getTag())));
        firstGroup.setThirdQuarter(Double.parseDouble(request.getParameter(ReportTag.FIRST_GROUP_ADVANCED_PAYMENT_III.getTag())));
        firstGroup.setForthQuarter(Double.parseDouble(request.getParameter(ReportTag.FIRST_GROUP_ADVANCED_PAYMENT_IV.getTag())));
        firstGroup.setIncomeVolume01(Double.parseDouble(request.getParameter(ReportTag.FIRST_GROUP_INCOME_VOLUME.getTag())));
        firstGroup.setTaxedIncomeVolume02(Double.parseDouble(request.getParameter(ReportTag.FIRST_GROUP_TAXED_INCOME_VOLUME.getTag())));
        return firstGroup;
    }

    protected SecondGroup parseSecondGroup(HttpServletRequest request) {
        SecondGroup secondGroup = new SecondGroup();
        secondGroup.setFirstQuarter(Double.parseDouble(request.getParameter(ReportTag.SECOND_GROUP_ADVANCED_PAYMENT_I.getTag())));
        secondGroup.setSecondQuarter(Double.parseDouble(request.getParameter(ReportTag.SECOND_GROUP_ADVANCED_PAYMENT_II.getTag())));
        secondGroup.setThirdQuarter(Double.parseDouble(request.getParameter(ReportTag.SECOND_GROUP_ADVANCED_PAYMENT_III.getTag())));
        secondGroup.setForthQuarter(Double.parseDouble(request.getParameter(ReportTag.SECOND_GROUP_ADVANCED_PAYMENT_IV.getTag())));
        secondGroup.setIncomeVolume03(Double.parseDouble(request.getParameter(ReportTag.SECOND_GROUP_INCOME_VOLUME.getTag())));
        secondGroup.setTaxedIncomeVolume04(Double.parseDouble(request.getParameter(ReportTag.SECOND_GROUP_TAXED_INCOME_VOLUME.getTag())));
        return secondGroup;
    }

    protected ThirdGroup parseThirdGroup(HttpServletRequest request){
        ThirdGroup thirdGroup = new ThirdGroup();
        thirdGroup.setThreePercentTaxed05(Double.parseDouble(request.getParameter(ReportTag.THIRD_GROUP_3_PERCENT_TAXED.getTag())));
        thirdGroup.setFivePercentTaxed06(Double.parseDouble(request.getParameter(ReportTag.THIRD_GROUP_5_PERCENT_TAXED.getTag())));
        thirdGroup.setFifteenPercentTaxed07(Double.parseDouble(request.getParameter(ReportTag.THIRD_GROUP_15_PERCENT_TAXED.getTag())));
        return thirdGroup;
    }

    private ErrorReport parseErrorReport(HttpServletRequest request){
        ErrorReport errorReport = new ErrorReport();
        errorReport.setFinePercent(Short.parseShort(request.getParameter(ReportTag.FINE_PERCENT.getTag())));
        errorReport.setPennySum20(Double.parseDouble(request.getParameter(ReportTag.PENNY_SUM.getTag())));
        errorReport.setSingleTax15(Double.parseDouble(request.getParameter(ReportTag.SINGLE_TAX_AMOUNT.getTag())));
        errorReport.setSpecifiedTax16(Double.parseDouble(request.getParameter(ReportTag.SPECIFIED_TAX_AMOUNT.getTag())));
        return errorReport;
    }

    private General parseGeneral(HttpServletRequest request) {
        General general = new General();
        general.setEmployeesNumber(Integer.parseInt(request.getParameter(ReportTag.EMPLOYEES_AMOUNT.getTag())));
        general.setActivities(parseActivities(request, general));
        return general;
    }

    private List<BusinessActivity> parseActivities(HttpServletRequest request, General general) {
        List<BusinessActivity> activities = new LinkedList<>();
        for (int i = 1; i < 9; i++) {//shitty code, idk how (or don't want) to fix it =(
            if (request.getParameter(ReportTag.BUSINESS_ACTIVITY_CODE.getTag()+i)==null||
                    request.getParameter(ReportTag.BUSINESS_ACTIVITY.getTag()+i)==null)
                continue;
            if (request.getParameter(ReportTag.BUSINESS_ACTIVITY_CODE.getTag()+i).equals("")||
                    request.getParameter(ReportTag.BUSINESS_ACTIVITY.getTag()+i).equals(""))
                continue;//not break because stupid user can skip field;
            BusinessActivity activity = new BusinessActivity();
            activity.setCode(request.getParameter(ReportTag.BUSINESS_ACTIVITY_CODE.getTag()+i));
            activity.setName(request.getParameter(ReportTag.BUSINESS_ACTIVITY.getTag()+i));
            activity.setGeneral(general);
            activities.add(activity);
        }
        return activities;
    }

    private Info.Type getType(String param){
        switch (param){
            case "01zvitna":
                return Info.Type.SIMPLE;
            case "02zvitnanova":
                return Info.Type.SIMPLE_NEW;
            case "03utochn":
                return Info.Type.SPECIFYING;
            case "04dovidkovo":
                return Info.Type.REFERENCING;
            default:
                return null;
        }
    }

    private Info.Period getPeriod(String param){
        switch (param) {
            case "1kvartal":
                return Info.Period.I_QUARTER;
            case "2kvartal":
                return Info.Period.II_QUARTER;
            case "3kvartal":
                return Info.Period.III_QUARTER;
            case "4kvartal":
                return Info.Period.IV_QUARTER;
            default:
                return null;
        }
    }
}
