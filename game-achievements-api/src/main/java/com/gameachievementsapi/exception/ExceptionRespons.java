package com.gameachievementsapi.exception;

import java.time.LocalDate;

public class ExceptionRespons {
    private LocalDate timestamp = LocalDate.now();
    private String message;
    private String details;

    public ExceptionRespons(String message, String details) {
        super();
        this.message = message;
        this.details = details;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
