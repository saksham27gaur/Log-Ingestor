package com.project.loggermanagement.repository;

import com.project.loggermanagement.entity.Logger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepository extends JpaRepository<Logger,Long> {

    List<Logger> findByLevel(String level);

    List<Logger> findByResourceId(String resourceId);

    List<Logger> findByTraceId(String resourceId);

    List<Logger> findBySpanId(String resourceId);

    List<Logger> findByCommit(String resourceId);

    List<Logger> findByParentId(String resourceId);

    List<Logger> findByMessageContaining(String message);

    List<Logger> findByCreatedDateBetween(long startTimestamp, long endTimestamp);
}
