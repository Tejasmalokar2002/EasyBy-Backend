package com.Shop.EasyBy.controllers;

import com.Shop.EasyBy.entities.ReportInfo;
import com.Shop.EasyBy.entities.ReportResponse;
import com.Shop.EasyBy.entities.SectionRow;
import com.Shop.EasyBy.services.ReportParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    private final ReportParserService parserService;

    @Autowired
    public ReportController(ReportParserService parserService) {
        this.parserService = parserService;
    }

    @PostMapping("/upload")
    public ReportResponse uploadReport(@RequestParam("file") MultipartFile file) throws IOException {
        // Save temporarily
        Path tempFile = Files.createTempFile("report-", ".txt");
        file.transferTo(tempFile.toFile());

        // Parse and return JSON
        return parserService.parseReport(tempFile);
    }

//    @PostMapping("/upload")
//    public Map<String, Object> uploadReport(@RequestParam("file") MultipartFile file) throws IOException {
//        Path tempFile = Files.createTempFile("report-", ".txt");
//        file.transferTo(tempFile.toFile());
//        return parserService.parseReport(tempFile);  // ✅ Directly return the parsed JSON
//    }


}

