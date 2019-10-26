package com.netflix.catalog.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class CatalogException extends RuntimeException {

    private static final long serialVersionUID = -9195304899803330065L;

    private HttpStatus httpStatus;
    private String message;

}
