package com.netflix.catalog.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Builder
@Getter
@ToString
public class CategoryRequest implements Serializable {

    private static final long serialVersionUID = 4146040394215488382L;

    private final Long id;
    private final String description;

}
