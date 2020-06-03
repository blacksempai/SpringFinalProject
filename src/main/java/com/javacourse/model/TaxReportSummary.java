package com.javacourse.model;

public class TaxReportSummary {
    private double totalIncome;
    private double sum15;
    private double sum5;
    private double sum3;
    private double totalSum;
    private double previousTotalSum;
    private double taxSum;
    private double amountIncrease;
    private double amountDecrease;
    private double fineAmount;

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getSum15() {
        return sum15;
    }

    public void setSum15(double sum15) {
        this.sum15 = sum15;
    }

    public double getSum5() {
        return sum5;
    }

    public void setSum5(double sum5) {
        this.sum5 = sum5;
    }

    public double getSum3() {
        return sum3;
    }

    public void setSum3(double sum3) {
        this.sum3 = sum3;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }

    public double getPreviousTotalSum() {
        return previousTotalSum;
    }

    public void setPreviousTotalSum(double previousTotalSum) {
        this.previousTotalSum = previousTotalSum;
    }

    public double getTaxSum() {
        return taxSum;
    }

    public void setTaxSum(double taxSum) {
        this.taxSum = taxSum;
    }

    public double getAmountIncrease() {
        return amountIncrease;
    }

    public void setAmountIncrease(double amountIncrease) {
        this.amountIncrease = amountIncrease;
    }

    public double getAmountDecrease() {
        return amountDecrease;
    }

    public void setAmountDecrease(double amountDecrease) {
        this.amountDecrease = amountDecrease;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }
}
