package com.project.loggermanagement.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.loggermanagement.dto.Log;
import com.project.loggermanagement.dto.LogSearchDto;
import com.project.loggermanagement.entity.Logger;
import com.project.loggermanagement.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LogIngectorService {

    private final LogRepository logRepository;

    public String createLogs(Log log) {
        Logger logger = new Logger();
        loggerIngestion(log, logger);

        logRepository.save(logger);
        return "true";

    }

    private void loggerIngestion(Log log, Logger logger) {
        logger.setCreatedDate();
        logger.setLevel(log.getLevel());
        logger.setMessage(log.getMessage());
        logger.setResourceId(log.getResourceId());
        logger.setTimeStamp(log.getTimestamp());
        logger.setTraceId(log.getTraceId());
        logger.setSpanId(log.getSpanId());
        logger.setCommit(log.getCommit());
        logger.setParentId(log.getMetadata().getParentResourceId());
        String logJsonString = convertObjectToJsonString(log);

        logger.setJsonString(logJsonString);
    }

    private String convertObjectToJsonString(Log log) {
        try {
            // Create ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Convert object to JSON string
            return objectMapper.writeValueAsString(log);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception according to your needs
            return null;
        }
    }

    public List<LogSearchDto> fetchLogsWithoutFilter(String searchString) {
        List<Logger> loggerList = logRepository.findAll();
        List<LogSearchDto> logsResponseList = new ArrayList<>();
        generateResponseList(searchString, loggerList, logsResponseList);
        return logsResponseList;
    }

    private void generateResponseList(String searchString, List<Logger> loggerList, List<LogSearchDto> logsResponseList) {
        for (Logger list : loggerList) {
            boolean status = searchLogWithoutFilter(list.getJsonString(), searchString);
            if (status) {
                LogSearchDto logSearchDto = new LogSearchDto();
                logSearchDto.setCreateDate(list.getCreatedDate());
                logSearchDto.setMessage(list.getJsonString());
                logsResponseList.add(logSearchDto);
            }
        }
    }

    private boolean searchLogWithoutFilter(String jsonString, String searchString) {
        if (Objects.isNull(jsonString))
            return false;
        String modifiedString = getModifiedStringWithoutCharacters(jsonString);
        Set<String> words = new HashSet<>(Arrays.asList(modifiedString.split("\\s+")));

        // Check if all words in the search string are present in the modified string (case-insensitive)
        return Arrays.stream(searchString.split("\\s+"))
                .allMatch(searchWord -> words.stream().anyMatch(word -> word.equalsIgnoreCase(searchWord)));

    }

    private static String getModifiedStringWithoutCharacters(String jsonString) {
        String regex = "\"|\\:|\\,|\\{|\\}";
        String modifiedString = jsonString.replaceAll(regex, " ");
        return modifiedString;
    }


    public List<LogSearchDto> fetchLogsWithFilter(String level, String message, String resourceId, String traceId, String spanId, String commit, String parentId, String searchString, LocalDate startDate, LocalDate endDate, Pageable pageable) {


        List<Logger> filteredLogs = new ArrayList<>();
        List<LogSearchDto> responseList = new ArrayList<>();
        if (searchString != null && !searchString.isEmpty())
        responseList = fetchLogsWithoutFilter(searchString);
        // Example: Filter by level
        if (level != null && !level.isEmpty())
            filteredLogs = logRepository.findByLevel(level);

        // Example: Filter by message
        if (message != null && !message.isEmpty()) {
            List<Logger> logsByMessage = logRepository.findByMessageContaining(message);
            // Merge or combine the results as needed
            if (filteredLogs.isEmpty())
                filteredLogs.addAll(logsByMessage);
            filteredLogs.retainAll(logsByMessage);
        }

        // Example: Filter by resourceId
        if (resourceId != null && !resourceId.isEmpty()) {
            List<Logger> logsByResourceId = logRepository.findByResourceId(resourceId);
            // Merge or combine the results as needed
            if (filteredLogs.isEmpty())
                filteredLogs.addAll(logsByResourceId);
            filteredLogs.retainAll(logsByResourceId);
        }
        if (traceId != null && !traceId.isEmpty()) {
            List<Logger> logsByTraceId = logRepository.findByTraceId(traceId);
            // Merge or combine the results as needed
            if (filteredLogs.isEmpty())
                filteredLogs.addAll(logsByTraceId);
            filteredLogs.retainAll(logsByTraceId);
        }
        if (spanId != null && !spanId.isEmpty()) {
            List<Logger> logsBySpanId = logRepository.findBySpanId(spanId);
            // Merge or combine the results as needed
            if (filteredLogs.isEmpty())
                filteredLogs.addAll(logsBySpanId);
            filteredLogs.retainAll(logsBySpanId);
        }
        if (commit != null && !commit.isEmpty()) {
            List<Logger> logsByCommit = logRepository.findByCommit(commit);
            // Merge or combine the results as needed
            if (filteredLogs.isEmpty())
                filteredLogs.addAll(logsByCommit);
            filteredLogs.retainAll(logsByCommit);
        }
        if (parentId != null && !parentId.isEmpty()) {
            List<Logger> logsByParentId = logRepository.findByParentId(parentId);
            // Merge or combine the results as needed
            if (filteredLogs.isEmpty())
                filteredLogs.addAll(logsByParentId);
            filteredLogs.retainAll(logsByParentId);
        }
        if (startDate != null && endDate != null) {
            LocalDateTime startDateTime = startDate.atStartOfDay();
            LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

            // Convert LocalDateTime to epoch seconds
            long startTimestamp = startDateTime.toEpochSecond(ZoneOffset.UTC) * 1000;
            long endTimestamp = endDateTime.toEpochSecond(ZoneOffset.UTC) * 1000;
            List<Logger> logsByStartAndEndDate = logRepository.findByCreatedDateBetween(startTimestamp, endTimestamp);
            // Merge or combine the results as needed
            if (filteredLogs.isEmpty())
                filteredLogs.addAll(logsByStartAndEndDate);
            filteredLogs.retainAll(logsByStartAndEndDate);
        }

        for (Logger list : filteredLogs) {
            if (!Objects.isNull(list.getJsonString())) {
                LogSearchDto logSearchDto = new LogSearchDto();
                logSearchDto.setCreateDate(list.getCreatedDate());
                logSearchDto.setMessage(list.getJsonString());
                responseList.add(logSearchDto);
            }
        }
        return responseList;
    }
}

