package com.logpipeline.loganalyzer;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping ("api/v1/logs")
public class LogsController {

    private final LogsService logsService;
    private final SparkService sparkService;

    public LogsController(LogsService logsService, SparkService sparkService) {
        this.logsService = logsService;
        this.sparkService = sparkService;
    }

    @GetMapping
    public List<Logs> getLogs() {
        return logsService.getAllLogs();
    }

    @GetMapping("/stats")
    public Map<String, Long> getStatsByStatus() {
        return sparkService.countByStatus();
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadLogs(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
        }

        try {
            List<Logs> logs = CSVHelper.parseLogs(file);
            logsService.insertBulkLogs(logs);
            return ResponseEntity.status(HttpStatus.OK).body("Logs uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
