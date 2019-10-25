package com.netflix.catalog.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class MovieResponse implements Serializable {

    private static final long serialVersionUID = -7320023237867704075L;

    private final Long id;

}
