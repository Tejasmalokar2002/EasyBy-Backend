package com.Shop.EasyBy.entities;

public class ReportInfo {
    private String reportId;
    private int page;
    private String reportingFor;
    private String rollupTo;
    private String fundsTransferEntity;
    private String procDate;
    private String reportDate;
    private String settlementCurrency;
    private String fundsTransferAmount;
    private String ClearingCurrency;

    // getters/setters

    @Override
    public String toString() {
        return "ReportInfo{" +
                "reportId='" + reportId + '\'' +
                ", page=" + page +
                ", reportingFor='" + reportingFor + '\'' +
                ", rollupTo='" + rollupTo + '\'' +
                ", fundsTransferEntity='" + fundsTransferEntity + '\'' +
                ", procDate='" + procDate + '\'' +
                ", reportDate='" + reportDate + '\'' +
                ", settlementCurrency='" + settlementCurrency + '\'' +
                ", fundsTransferAmount='" + fundsTransferAmount + '\'' +
                ", ClearingCurrency='" + ClearingCurrency + '\'' +
                '}';
    }

    public String getClearingCurrency() {
        return ClearingCurrency;
    }

    public void setClearingCurrency(String clearingCurrency) {
        ClearingCurrency = clearingCurrency;
    }

    public String getFundsTransferAmount() {
        return fundsTransferAmount;
    }

    public void setFundsTransferAmount(String fundsTransferAmount) {
        this.fundsTransferAmount = fundsTransferAmount;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getReportingFor() {
        return reportingFor;
    }

    public void setReportingFor(String reportingFor) {
        this.reportingFor = reportingFor;
    }

    public String getRollupTo() {
        return rollupTo;
    }

    public void setRollupTo(String rollupTo) {
        this.rollupTo = rollupTo;
    }

    public String getFundsTransferEntity() {
        return fundsTransferEntity;
    }

    public void setFundsTransferEntity(String fundsTransferEntity) {
        this.fundsTransferEntity = fundsTransferEntity;
    }

    public String getProcDate() {
        return procDate;
    }

    public void setProcDate(String procDate) {
        this.procDate = procDate;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getSettlementCurrency() {
        return settlementCurrency;
    }

    public void setSettlementCurrency(String settlementCurrency) {
        this.settlementCurrency = settlementCurrency;
    }


}
