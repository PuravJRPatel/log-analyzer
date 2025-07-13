package com.logpipeline.loganalyzer;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Set;

@Service
public class LogsService {

    private final LogsRepository logsRepository;

    public LogsService(LogsRepository logsRepository) {
        this.logsRepository = logsRepository;
    }


    public List<Logs> getAllLogs() {
        return logsRepository.findAll();
    }

    public void insertLog(Logs log) {
        logsRepository.save(log);
    }

    public void insertBulkLogs(List<Logs> logs) {
        logsRepository.saveAll(logs);
    }
}
