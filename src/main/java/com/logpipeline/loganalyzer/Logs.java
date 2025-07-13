package com.logpipeline.loganalyzer;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jdk.jfr.Enabled;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity

public class Logs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userid;
    private String log;
    private String status;
    private LocalDateTime timestamp;

    public Logs(LocalDateTime timestamp, String status, String log, Integer userid, Integer id) {
        this.timestamp = timestamp;
        this.status = status;
        this.log = log;
        this.userid = userid;
        this.id = id;
    }

    public Logs() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Logs logs = (Logs) o;
        return Objects.equals(id, logs.id) && Objects.equals(userid, logs.userid) && Objects.equals(log, logs.log) && Objects.equals(status, logs.status) && Objects.equals(timestamp, logs.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userid, log, status, timestamp);
    }
}

