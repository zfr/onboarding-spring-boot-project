package com.opsgenie.onboarding.advice;

import com.opsgenie.onboarding.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class WebRestControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetails handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ErrorDetails(new Date(), ex.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDetails handleInternalServerErrors(Throwable ex) {
        logger.error(ex.getMessage(), ex);

        return new ErrorDetails(new Date(), ex.getMessage());
    }

    public class ErrorDetails {
        private final Date timestamp;
        private final String message;

        ErrorDetails(Date timestamp, String message) {
            this.timestamp = timestamp;
            this.message = message;
        }

        public Date getTimestamp() {
            return timestamp;
        }

        public String getMessage() {
            return message;
        }
    }
}
