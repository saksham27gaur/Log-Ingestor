package com.project.loggermanagement.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Log{
    public String level;
    public String message;
    public String resourceId;
    public String timestamp;
    public String traceId;
    public String spanId;
    public String commit;
    public Metadata metadata;
}
