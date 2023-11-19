package com.project.loggermanagement.controller;

import com.project.loggermanagement.dto.LogSearchDto;
import com.project.loggermanagement.service.LogIngectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/interface")
@RequiredArgsConstructor
@CrossOrigin
public class LogInterfaceController {


    private final LogIngectorService logIngectorService;

    @GetMapping("/search-filter")
    public String searchWithFilter(Model model,@RequestParam("page") Optional<Integer> page,
                                   @RequestParam("size") Optional<Integer> size,
                                   @RequestParam(value = "level", required = false) String level,
                                   @RequestParam(value = "message", required = false) String message,
                                   @RequestParam(value = "resourceId", required = false) String resourceId,
                                   @RequestParam(value = "traceId", required = false) String traceId,
                                   @RequestParam(value = "spanId", required = false) String spanId,
                                   @RequestParam(value = "commit", required = false) String commit,
                                   @RequestParam(value = "parentId", required = false) String parentId,
                                   @RequestParam(value = "keyword", required = false) String keyword,
                                   @RequestParam(value = "startDate", required = false) LocalDate startDate,
                                   @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        int currentPage = page.orElse(0);
        int pageSize = size.orElse(2);
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        List<LogSearchDto> filteredResponseList = logIngectorService.fetchLogsWithFilter(level, message, resourceId, traceId, spanId, commit, parentId,keyword,startDate,endDate, pageable);
        model.addAttribute("filteredResponseList", filteredResponseList);
        return "logSearch";
    }
}
