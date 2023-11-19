package com.project.loggermanagement.controller;

import com.project.loggermanagement.dto.Log;
import com.project.loggermanagement.dto.LogSearchDto;
import com.project.loggermanagement.dto.Response;
import com.project.loggermanagement.service.LogIngectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/log")
@RequiredArgsConstructor
public class LogController {

    private final LogIngectorService logIngectorService;

    @PostMapping("/internal/api/v1/log-ingestion")
    public ResponseEntity<Response<String>> insertLogs(@RequestBody Log log) {
        return ResponseEntity.ok(new Response<>(new Date(), HttpStatus.OK.value(), "Log inserted successfully",
                logIngectorService.createLogs(log)));
    }
    @GetMapping("/internal/api/v1/search")
    public ResponseEntity<Response<List<LogSearchDto>>> searchWithoutFilter(@RequestParam("searchString") String searchString) {
        return ResponseEntity.ok(new Response<>(new Date(), HttpStatus.OK.value(), "Logs fetched successfully",
                logIngectorService.fetchLogsWithoutFilter(searchString)));
    }

    @GetMapping("/internal/api/v1/search-filter")
    public ResponseEntity<Response<List<LogSearchDto>>> searchWithFilter(@RequestParam(value = "level", required = false) String level,
                                                                         @RequestParam(value = "message", required = false) String message,
                                                                         @RequestParam(value = "resourceId", required = false) String resourceId,
                                                                         @RequestParam(value = "traceId", required = false) String traceId,
                                                                         @RequestParam(value = "spanId", required = false) String spanId,
                                                                         @RequestParam(value = "commit", required = false) String commit,
                                                                         @RequestParam(value = "parentId", required = false) String parentId,
                                                                         @RequestParam(value = "searchString", required = false) String searchString,
                                                                         @RequestParam(value = "startDate", required = false) LocalDate startDate,
                                                                         @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        return ResponseEntity.ok(new Response<>(new Date(), HttpStatus.OK.value(), "Logs fetched successfully",
                logIngectorService.fetchLogsWithFilter(level, message, resourceId, traceId, spanId, commit, parentId, searchString,startDate,endDate,null)));
    }

}
