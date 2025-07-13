package com.logpipeline.loganalyzer;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.count;


@Service
public class SparkService {
    private SparkSession spark;

    public SparkSession getSparkSession() {
        if (spark == null) {
            spark = SparkSession.builder()
                    .appName("LogAnalyzer")
                    .master("local[*]")
                    .config("spark.ui.enabled", "false")
                    .config("spark.driver.bindAddress", "127.0.0.1")
                    .getOrCreate();
        }
        return spark;
    }

    public Map<String, Long> countByStatus() {
        SparkSession spark = getSparkSession();
        String jdbcUrl = "jdbc:postgresql://localhost:5332/logs";
        String username = "puravjrpatel";
        String password = "password";

        Dataset<Row> logsDF = spark.read()
                .format("jdbc")
                .option("url", jdbcUrl)
                .option("dbtable","logs")
                .option("user",username)
                .option("password", password)
                .load();

        Dataset<Row> countDF = logsDF.groupBy("status").agg(count(col("status")).alias("count"));
        return countDF.collectAsList().stream()
                .collect(Collectors.toMap(
                        row -> row.getString(0),
                        row -> row.getLong(1)
                ));
    }
}
