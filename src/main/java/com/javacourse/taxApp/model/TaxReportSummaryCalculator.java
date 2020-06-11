package com.javacourse.taxApp.model;


import com.javacourse.taxApp.model.entities.report.Info;
import com.javacourse.taxApp.model.entities.report.Report;
import com.javacourse.taxApp.model.entities.report.ReportBody;
import com.javacourse.taxApp.service.ReportService;

import java.util.List;

public class TaxReportSummaryCalculator {
    public static TaxReportSummary calculateSummary(Report report, ReportService reportService){
        TaxReportSummary summary = new TaxReportSummary();
        double totalIncome = calculateTotalIncome(report.getReportBody());
        double sum3 = calculateSum3(report.getReportBody());
        double sum5 = calculateSum5(report.getReportBody());
        double sum15 = calculateSum15(report.getReportBody());
        double totalSum = calculateTotalSum(sum3,sum5,sum15);
        double previousTotalSum = calculatePreviousTotalSum(report,reportService);
        double taxSum = calculateTaxSum(totalSum, previousTotalSum);
        double amountIncrease = calculateAmountIncrease(report.getReportBody());
        double amountDecrease = calculateAmountDecrease(report.getReportBody());
        double fineAmount = calculateFineAmount(report.getReportBody());
        summary.setTotalIncome(totalIncome);
        summary.setSum3(sum3);
        summary.setSum5(sum5);
        summary.setSum15(sum15);
        summary.setTotalSum(totalSum);
        summary.setTaxSum(taxSum);
        summary.setPreviousTotalSum(previousTotalSum);
        summary.setAmountIncrease(amountIncrease);
        summary.setAmountDecrease(amountDecrease);
        summary.setFineAmount(fineAmount);
        return summary;
    }

    public static Double calculateTotalIncome(ReportBody report){
        double a1 = report.getFirstGroup().getIncomeVolume01();
        double a2 = report.getFirstGroup().getTaxedIncomeVolume02();
        double a3 = report.getSecondGroup().getIncomeVolume03();
        double a4 = report.getSecondGroup().getTaxedIncomeVolume04();
        double a5 = report.getThirdGroup().getThreePercentTaxed05();
        double a6 = report.getThirdGroup().getFivePercentTaxed06();
        double a7 = report.getThirdGroup().getFifteenPercentTaxed07();
        return a1+a2+a3+a4+a5+a6+a7;
    }

    public static double calculateSum15(ReportBody report){
        double a2 = report.getFirstGroup().getTaxedIncomeVolume02();
        double a4 = report.getSecondGroup().getTaxedIncomeVolume04();
        double a7 = report.getThirdGroup().getFifteenPercentTaxed07();
        return (a2+a4+a7)*0.15;
    }

    public static double calculateSum3(ReportBody report){
        double a5 = report.getThirdGroup().getThreePercentTaxed05();
        return a5*0.03;
    }

    public static double calculateSum5(ReportBody report){
        double a6 = report.getThirdGroup().getFivePercentTaxed06();
        return a6*0.05;
    }

    public static double calculateTotalSum(double sum3, double sum5, double sum15){
        return sum3 + sum5 + sum15;
    }

    public static double calculatePreviousTotalSum(Report report, ReportService reportService){
        Report previousReport = getPreviousReport(report,reportService);
        if (previousReport==null)
            return 0;
        double sum3 = calculateSum3(previousReport.getReportBody());
        double sum5 = calculateSum5(previousReport.getReportBody());
        double sum15 = calculateSum15(previousReport.getReportBody());
        return calculateTotalSum(sum3,sum5,sum15);
    }

    public static Report getPreviousReport(Report report, ReportService reportService){
        Report previousReport = null;
        List<Report> reports = reportService.getAll(report.getUser());
        long reportDate = 0;
        for (Report r : reports) {
            if (r.getId().equals(report.getId()))
                continue;
            if (!r.getReportBody().getInfo().getType().equals(Info.Type.SIMPLE))
                continue;
            if (r.getDeclarationSubmissionDate().getTime()>reportDate){
                reportDate = r.getDeclarationSubmissionDate().getTime();
                previousReport = r;
            }
        }
        return previousReport;
    }

    public static double calculateTaxSum(double totalSum, double previousTotalSum){
        if (totalSum - previousTotalSum > 0)
            return totalSum - previousTotalSum;
        return 0.0;
    }

    public static double calculateAmountIncrease(ReportBody report){
        double amountChange = calculateAmountChange(report);
        if (amountChange>0)
            return amountChange;
        return 0;
    }

    public static double calculateAmountDecrease(ReportBody report){
        double amountChange = calculateAmountChange(report);
        if (amountChange<0)
            return -amountChange;
        return 0;
    }

    private static double calculateAmountChange(ReportBody report){
        double a15 = report.getErrorReport().getSingleTax15();
        double a16 = report.getErrorReport().getSpecifiedTax16();
        return a16 - a15;
    }

    public static double calculateFineAmount(ReportBody report){
        double amountIncrease = calculateAmountIncrease(report);
        short percent = report.getErrorReport().getFinePercent();
        return amountIncrease/100*percent;
    }

}
