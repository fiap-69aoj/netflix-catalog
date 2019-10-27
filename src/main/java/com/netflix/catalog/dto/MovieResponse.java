package com.netflix.catalog.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
public class MovieResponse implements Serializable {

    private static final long serialVersionUID = -7320023237867704075L;

    private final Long id;
    private final String name;
    private final List<String> labels;
    private final List<String> categories;

}
