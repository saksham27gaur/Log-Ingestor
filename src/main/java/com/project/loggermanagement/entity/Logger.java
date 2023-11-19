package com.project.loggermanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "logger")
@Getter
@Setter
@NoArgsConstructor

public class Logger extends AbstractEntity{
    @Column(name = "level")
    private String level;
    @Column(name = "message")
    public String message;
    @Column(name = "resource_id")
    public String resourceId;
    @Column(name = "time_stamp")
    public String timeStamp;
    @Column(name = "trace_id")
    public String traceId;
    @Column(name = "span_id")
    public String spanId;
    @Column(name = "commit")
    public String commit;
    @Column(name = "parent_id")
    public String parentId;
    @Column(name = "json_string")
    public String jsonString;
}
