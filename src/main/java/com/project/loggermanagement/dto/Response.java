package com.project.loggermanagement.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Response<T> {

    private int status;
    private String message;
    private T data;
    private Integer count;
    private Date date;
    private String additionalMessage;

    public Response(String message, T data) {
        this.data = data;
        this.message = message;
        this.date = new Date();
        this.status = HttpStatus.OK.value();
    }
    public Response(Date date, int status, String message, T data) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
        this.date = date;
    }


}
