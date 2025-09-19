package com.Shop.EasyBy.entities;

import java.util.List;
import java.util.Map;

public class ReportResponse {
    private ReportInfo reportInfo;
    private Map<String, List<SectionRow>> sections;

    public ReportInfo getReportInfo() {
        return reportInfo;
    }

    public void setReportInfo(ReportInfo reportInfo) {
        this.reportInfo = reportInfo;
    }

    public Map<String, List<SectionRow>> getSections() {
        return sections;
    }

    public void setSections(Map<String, List<SectionRow>> sections) {
        this.sections = sections;
    }
}
