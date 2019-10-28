package com.netflix.catalog.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
public class SerieResponse implements Serializable {

    private static final long serialVersionUID = 6912428338394487467L;

    private final Long id;
    private final String name;
    private final List<String> labels;
    private final List<String> categories;

}
