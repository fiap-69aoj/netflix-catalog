package com.netflix.catalog.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class SerieResponse implements Serializable {

    private static final long serialVersionUID = 6912428338394487467L;

    private final Long id;
    private final String name;

}
