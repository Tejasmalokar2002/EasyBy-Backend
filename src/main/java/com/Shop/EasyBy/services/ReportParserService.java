package com.Shop.EasyBy.services;

import com.Shop.EasyBy.entities.FundsTransferAmount;
import com.Shop.EasyBy.entities.ReportInfo;
import com.Shop.EasyBy.entities.ReportResponse;
import com.Shop.EasyBy.entities.SectionRow;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

@Service
public class ReportParserService {

//    public Map<String, Object> parseReport(Path filePath) throws IOException {
//        List<String> rawLines = Files.readAllLines(filePath);
//        List<String> lines = new ArrayList<>();
//        for (String l : rawLines) {
//            // Normalize non-breaking spaces and other whitespace, and trim
//            if (l != null) {
//                String ln = l.replace('\u00A0', ' ')
//                        .replace("\t", " ")
//                        .trim();
//                if (!ln.isEmpty()) {
//                    lines.add(ln);
//                }
//            }
//        }
//
//        // Debug: print first few lines
//        System.out.println("===== First 10 lines =====");
//        for (int i = 0; i < Math.min(10, lines.size()); i++) {
//            System.out.println("[" + i + "]: " + lines.get(i));
//        }
//        System.out.println("==========================");
//
//        Map<String, Object> result = new LinkedHashMap<>();
//        Map<String, String> fields = new LinkedHashMap<>();
//        Map<String, Object> reportInfo = new LinkedHashMap<>();
//        reportInfo.put("fields", fields);
//
//        Map<String, List<Map<String, Object>>> sections = new LinkedHashMap<>();
//
//        String currentSection = null;
//
//        boolean headersParsed = false;
//        List<String> columnHeaders = new ArrayList<>();
//
//        for (int i = 0; i < lines.size(); i++) {
//            String line = lines.get(i);
//
//            // 1. Parse key-value fields at top (e.g. lines 0-5)
//            if (line.contains(":") && !headersParsed) {
//                String[] parts = line.split(":", 2);
//                String key = normalizeKey(parts[0]);
//                String value = parts.length > 1 ? parts[1].trim().replaceAll("\\s{2,}", " ") : "";
//                fields.put(key, value);
//                continue;
//            }
//
//            // 2. Detect column headers: assume lines 6 and 7 are column header lines
//            if (!headersParsed && i == 6) {
//                // store first header line split by multiple spaces
//                List<String> firstHeaderLine = Arrays.stream(line.split("\\s{2,}"))
//                        .map(String::trim)
//                        .collect(Collectors.toList());
//                String nextLine = lines.get(i+1);
//                List<String> secondHeaderLine = Arrays.stream(nextLine.split("\\s{2,}"))
//                        .map(String::trim)
//                        .collect(Collectors.toList());
//
//                // merge header lines - example logic: if first line shorter, pad with empty
//                int maxLen = Math.max(firstHeaderLine.size(), secondHeaderLine.size());
//                for (int j = 0; j < maxLen; j++) {
//                    String part1 = j < firstHeaderLine.size() ? firstHeaderLine.get(j) : "";
//                    String part2 = j < secondHeaderLine.size() ? secondHeaderLine.get(j) : "";
//                    String combined = (part1 + " " + part2).trim();
//                    columnHeaders.add(normalizeKey(combined));
//                }
//
//                headersParsed = true;
//                i++; // skip next line as it's already consumed
//                continue;
//            }
//
//            // 3. Detect section header lines (all uppercase, no colon)
//            if (headersParsed && line.matches("^[A-Z][A-Z \\-]*$") && !line.contains(":")) {
//                currentSection = line.trim();
//                sections.putIfAbsent(currentSection, new ArrayList<>());
//                continue;
//            }
//
//            // 4. Parse section data lines
//            if (currentSection != null && headersParsed) {
//                // split by multiple spaces
//                String[] parts = line.split("\\s{2,}");
//                if (parts.length > 0) {
//                    Map<String, Object> row = new LinkedHashMap<>();
//                    row.put("label", parts[0].trim());
//
//                    Map<String, String> colMap = new LinkedHashMap<>();
//                    // parts[0] is label, parts[1..] are column values
//                    for (int k = 1; k < parts.length && k <= columnHeaders.size(); k++) {
//                        String colKey = columnHeaders.get(k - 1);
//                        colMap.put(colKey, parts[k].trim());
//                    }
//                    row.put("columns", colMap);
//
//                    sections.get(currentSection).add(row);
//                }
//            }
//        }
//
//        result.put("reportInfo", reportInfo);
//        result.put("sections", sections);
//        return result;
//    }
//
//    private String normalizeKey(String key) {
//        return key.toLowerCase()
//                .replaceAll("[^a-z0-9]", " ")
//                .replaceAll("\\s+", " ")
//                .trim()
//                .replace(" ", "_");
//    }


//    public ReportResponse parseReport(Path filePath) throws IOException {
//        List<String> lines = Files.readAllLines(filePath);
//
//        // First detect which report type it is
//        String reportId = lines.stream()
//                .filter(l -> l.startsWith("REPORT ID"))
//                .findFirst()
//                .map(l -> {
//                    Matcher m = Pattern.compile("REPORT ID:\\s+(\\S+)").matcher(l);
//                    if (m.find()) return m.group(1);
//                    return null;
//                })
//                .orElse(null);
//
//        if (reportId == null) {
//            throw new IllegalArgumentException("Unknown report format - missing REPORT ID");
//        }
//
//        if (reportId.equals("VSS-110")) {
//            return parseSettlementSummary(lines); // existing logic
//        } else if (reportId.equals("VSS-120")) {
//            return parseInterchangeValue(lines);  // new logic
//        } else {
//            throw new IllegalArgumentException("Unsupported report type: " + reportId);
//        }
//    }
//
//
//    private ReportResponse parseSettlementSummary(List<String> lines) {
//        ReportInfo reportInfo = new ReportInfo();
//        Map<String, List<SectionRow>> sections = new LinkedHashMap<>();
//
//        String currentSection = null;
//
//        for (String line : lines) {
//            line = line.trim();
//            if (line.isEmpty()) continue;
//
//            // Extract report header info
//            if (line.startsWith("REPORT ID")) {
//                Matcher m = Pattern.compile("REPORT ID:\\s+(\\S+)").matcher(line);
//                if (m.find()) reportInfo.setReportId(m.group(1));
//            }
//            if (line.startsWith("PAGE:")) {
//                reportInfo.setPage(Integer.parseInt(line.replaceAll("\\D+", "")));
//            }
//            if (line.startsWith("REPORTING FOR:")) {
//                String val = line.replace("REPORTING FOR:", "").trim();
//                int idx = val.indexOf("MASHREQBANK");
//                if (idx != -1) {
//                    val = val.substring(0, idx + "MASHREQBANK".length()).trim();
//                }
//                reportInfo.setReportingFor(val);
//            }
//
//            if (line.startsWith("ROLLUP TO:")) {
//                String val = line.replace("ROLLUP TO:", "").trim();
//                int idx = val.indexOf("MASHREQBANK");
//                if (idx != -1) {
//                    val = val.substring(0, idx + "MASHREQBANK".length()).trim();
//                }
//                reportInfo.setRollupTo(val);
//            }
//            if (line.startsWith("FUNDS XFER ENTITY:")) {
//                reportInfo.setFundsTransferEntity(line.replace("FUNDS XFER ENTITY:", "").trim());
//            }
//            if (line.contains("PROC DATE:")) {
//                Matcher m = Pattern.compile("PROC DATE:\\s+(\\S+)").matcher(line);
//                if (m.find()) reportInfo.setProcDate(m.group(1));
//            }
//            if (line.contains("REPORT DATE:")) {
//                Matcher m = Pattern.compile("REPORT DATE:\\s+(\\S+)").matcher(line);
//                if (m.find()) reportInfo.setReportDate(m.group(1));
//            }
//            if (line.startsWith("SETTLEMENT CURRENCY:")) {
//                reportInfo.setSettlementCurrency(line.replace("SETTLEMENT CURRENCY:", "").trim());
//            }
//
//            if (line.startsWith("FUNDS TRANSFER AMOUNT:")) {
//                String val = line.replace("FUNDS TRANSFER AMOUNT:", "").trim();
//                val = val.replaceAll("(\\d)(CR|DB)$", "$1 $2");
//                reportInfo.setFundsTransferAmount(val);
//            }
//
//            // Detect section headers
//            if (line.equals("INTERCHANGE VALUE") || line.equals("REIMBURSEMENT FEES")
//                    || line.equals("VISA CHARGES") || line.equals("TOTAL")) {
//                currentSection = line;
//                sections.put(currentSection, new ArrayList<>());
//                continue;
//            }
//
//            // Section rows
//            if (currentSection != null && line.startsWith("TOTAL")) {
//                SectionRow row = parseSectionRow(line);
//                sections.get(currentSection).add(row);
//            }
//        }
//
//        ReportResponse response = new ReportResponse();
//        response.setReportInfo(reportInfo);
//        response.setSections(sections);
//        return response;
//    }
//
//
//    private SectionRow parseSectionRow(String line) {
//        SectionRow row = new SectionRow();
//
//        String[] parts = line.split("\\s{2,}"); // split by multiple spaces
//        row.setLabel(parts[0].trim());
//
//        int idx = 1;
//        if (parts.length == 5) {
//            row.setCount(parts[idx++].trim()); // keep as string with commas
//        }
//
//        row.setCreditAmount(parts[idx++].trim());
//        row.setDebitAmount(parts[idx++].trim());
//
//        String totalStr = parts[idx].trim();
//        totalStr = totalStr.replaceAll("(\\d)(CR|DB)$", "$1 $2");
//        row.setTotalAmount(totalStr);
//
//
//        return row;
//    }
//
//
//
//
//
//    private ReportResponse parseInterchangeValue(List<String> lines) {
//        ReportInfo reportInfo = new ReportInfo();
//        Map<String, List<SectionRow>> sections = new LinkedHashMap<>();
//
//        String currentSection = null;
//
//        for (String line : lines) {
//            line = line.trim();
//            if (line.isEmpty()) continue;
//
//            // Header extraction (reuse from old logic)
//            if (line.startsWith("REPORT ID")) {
//                Matcher m = Pattern.compile("REPORT ID:\\s+(\\S+)").matcher(line);
//                if (m.find()) reportInfo.setReportId(m.group(1));
//            }
//            if (line.contains("PROC DATE:")) {
//                Matcher m = Pattern.compile("PROC DATE:\\s+(\\S+)").matcher(line);
//                if (m.find()) reportInfo.setProcDate(m.group(1));
//            }
//            if (line.contains("REPORT DATE:")) {
//                Matcher m = Pattern.compile("REPORT DATE:\\s+(\\S+)").matcher(line);
//                if (m.find()) reportInfo.setReportDate(m.group(1));
//            }
//            if (line.startsWith("SETTLEMENT CURRENCY:")) {
//                reportInfo.setSettlementCurrency(line.replace("SETTLEMENT CURRENCY:", "").trim());
//            }
//            if (line.startsWith("CLEARING CURRENCY:")) {
//                // you may add a new field in ReportInfo for this
//                reportInfo.setClearingCurrency(line.replace("CLEARING CURRENCY:", "").trim());
//            }
//
//            // Section detection
//            if (line.equals("ISSUER TRANSACTIONS")) {
//                currentSection = line;
//                sections.put(currentSection, new ArrayList<>());
//                continue;
//            }
//
//            // Transaction type rows inside ISSUER TRANSACTIONS
//            if (currentSection != null && (
//                    line.startsWith("ORIGINAL") || line.startsWith("TOTAL") || line.startsWith("NET"))) {
//
//                SectionRow row = parseIssuerTransactionRow(line);
//                if (row != null) {
//                    sections.get(currentSection).add(row);
//                }
//            }
//        }
//
//        ReportResponse response = new ReportResponse();
//        response.setReportInfo(reportInfo);
//        response.setSections(sections);
//        return response;
//    }
//
//    private SectionRow parseIssuerTransactionRow(String line) {
//        SectionRow row = new SectionRow();
//
//        String[] parts = line.split("\\s{2,}"); // split by multiple spaces
//        if (parts.length == 0) return null;
//
//        row.setLabel(parts[0].trim());
//
//        int idx = 1;
//        if (parts.length > 1) row.setCount(parts[idx++].trim());
//        if (parts.length > 2) row.setClearingAmount(parts[idx++].trim());
//        if (parts.length > 3) row.setCreditAmount(parts[idx++].trim());
//        if (parts.length > 4) row.setDebitAmount(parts[idx++].trim());
//
//        return row;
//    }





    public ReportResponse parseReport(Path filePath) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        ReportInfo reportInfo = new ReportInfo();
        Map<String, List<SectionRow>> sections = new LinkedHashMap<>();

        String currentSection = null;

        for (String line : lines) {
            line = line.trim();

            if (line.isEmpty()) continue;

            // Extract report header info
            if (line.startsWith("REPORT ID")) {
                Matcher m = Pattern.compile("REPORT ID:\\s+(\\S+)").matcher(line);
                if (m.find()) {
                    reportInfo.setReportId(m.group(1));
                }
            }

            if (line.startsWith("PAGE:")) {
                reportInfo.setPage(Integer.parseInt(line.replaceAll("\\D+", "")));
            }

            if (line.startsWith("REPORTING FOR:")) {
                String val = line.replace("REPORTING FOR:", "").trim();
                int idx = val.indexOf("MASHREQBANK");
                if (idx != -1) {
                    val = val.substring(0, idx + "MASHREQBANK".length()).trim();
                }
                reportInfo.setReportingFor(val);
            }

            if (line.startsWith("ROLLUP TO:")) {
                String val = line.replace("ROLLUP TO:", "").trim();
                int idx = val.indexOf("MASHREQBANK");
                if (idx != -1) {
                    val = val.substring(0, idx + "MASHREQBANK".length()).trim();
                }
                reportInfo.setRollupTo(val);
            }

            if (line.startsWith("FUNDS XFER ENTITY:")) {
                reportInfo.setFundsTransferEntity(line.replace("FUNDS XFER ENTITY:", "").trim());
            }

            if (line.contains("PROC DATE:")) {
                Matcher m = Pattern.compile("PROC DATE:\\s+(\\S+)").matcher(line);
                if (m.find()) {
                    reportInfo.setProcDate(m.group(1));
                }
            }

            if (line.contains("REPORT DATE:")) {
                Matcher m = Pattern.compile("REPORT DATE:\\s+(\\S+)").matcher(line);
                if (m.find()) {
                    reportInfo.setReportDate(m.group(1));
                }
            }

            if (line.startsWith("SETTLEMENT CURRENCY:")) {
                reportInfo.setSettlementCurrency(line.replace("SETTLEMENT CURRENCY:", "").trim());
            }

            if (line.startsWith("FUNDS TRANSFER AMOUNT:")) {
                String val = line.replace("FUNDS TRANSFER AMOUNT:", "").trim();
                // Insert space before CR/DB (e.g., "12345CR" → "12345 CR")
                val = val.replaceAll("(\\d)(CR|DB)$", "$1 $2");
                reportInfo.setFundsTransferAmount(val);
            }

            // Detect section headers
            if (line.equals("INTERCHANGE VALUE") ||
                    line.equals("REIMBURSEMENT FEES") ||
                    line.equals("VISA CHARGES") ||
                    line.equals("TOTAL")) {

                currentSection = line;
                sections.put(currentSection, new ArrayList<>());
                continue;
            }

            // Section rows
            if (currentSection != null && line.startsWith("TOTAL")) {
                SectionRow row = parseSectionRow(line);
                sections.get(currentSection).add(row);
            }
        }

        ReportResponse response = new ReportResponse();
        response.setReportInfo(reportInfo);
        response.setSections(sections);

        return response;
    }

    private SectionRow parseSectionRow(String line) {
        SectionRow row = new SectionRow();
        String[] parts = line.split("\\s{2,}"); // Split by two or more spaces

        row.setLabel(parts[0].trim());

        int idx = 1;
        if (parts.length == 5) {
            row.setCount(parts[idx++].trim()); // Keep as string with commas
        }

        row.setCreditAmount(parts[idx++].trim());
        row.setDebitAmount(parts[idx++].trim());

        String totalStr = parts[idx].trim();
        // Format "123CR" or "456DB" → "123 CR"/"456 DB"
        totalStr = totalStr.replaceAll("(\\d)(CR|DB)$", "$1 $2");

        row.setTotalAmount(totalStr);

        return row;
    }

}

