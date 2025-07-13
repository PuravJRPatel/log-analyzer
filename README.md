A **Data Engineering** Pipeline for ingesting, storing, and analyzing logs using:
- **Spring Boot** (Java 21)
- **PostgreSQL** (via Docker)
- **Apache Spark** (via Docker)
- **Apache Commons CSV** for CSV parsing

- Upload CSV files containing log data via REST API
- Store logs in PostgreSQL
- Analyze logs using Apache Spark (by status count)
- Modular service layers: `Logs`, `LogService`, `LogController`, `SparkService`
- Fully containerized using Docker Compose
