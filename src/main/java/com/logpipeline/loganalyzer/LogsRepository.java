package com.logpipeline.loganalyzer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LogsRepository extends JpaRepository<Logs, Integer> {
}
