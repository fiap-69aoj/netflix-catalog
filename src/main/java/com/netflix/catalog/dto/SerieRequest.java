package com.netflix.catalog.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Builder
@Getter
@ToString
public class SerieRequest implements Serializable {

    private static final long serialVersionUID = 8933883612670129354L;

    @NotNull
    private final String name;

    private final String image;

    @NotNull
    private final String rating;

    @NotNull
    private final String summary;

    @NotNull
    private final Date releaseDate;

    @NotNull
    private final List<String> labels;

    @NotNull
    private final List<CategoryRequest> categories;

}
