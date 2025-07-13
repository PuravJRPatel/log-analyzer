package com.logpipeline.loganalyzer;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class CSVHelper {
    private CSVHelper() {};
    public static List<Logs> parseLogs(MultipartFile file) throws IOException {
        List<Logs> logsList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CSVFormat csvFormat = CSVFormat.Builder.create().setHeader("userid","log","status","timestamp")
                    .setSkipHeaderRecord(true)
                    .setIgnoreHeaderCase(true)
                    .setTrim(true)
                    .build();

            CSVParser parser = new CSVParser(reader, csvFormat);
            for (CSVRecord record : parser) {
                Logs log = new Logs();
                log.setUserid(Integer.parseInt(record.get("userid")));
                log.setLog(record.get("log"));
                log.setStatus(record.get("status"));
                log.setTimestamp(LocalDateTime.parse(record.get("timestamp").replace(" ", "T")));

                logsList.add(log);
            }
        }
        return logsList;
    }
}
