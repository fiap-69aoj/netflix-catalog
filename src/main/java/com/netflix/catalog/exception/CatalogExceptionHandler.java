package com.netflix.catalog.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class CatalogExceptionHandler {

    @ExceptionHandler(CatalogException.class)
    public ResponseEntity<StandardException> catalogException(CatalogException e, HttpServletRequest request) {
        log.error("Route error: {}, {}", request.getRequestURI(), e.getMessage());

        StandardException standardException = new StandardException(e.getHttpStatus().value(), e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(e.getHttpStatus().value()).body(standardException);
    }

}
