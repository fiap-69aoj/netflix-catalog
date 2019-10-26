package com.netflix.catalog.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Builder
public class StandardException implements Serializable {

    private static final long serialVersionUID = -2799148621726748186L;

    private final Integer httpStatus;
    private final String message;
    private final Long timestamp;

}
