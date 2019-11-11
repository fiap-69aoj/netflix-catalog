package com.netflix.catalog.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Builder
@Getter
@ToString
public class SerieWatchedRequest implements Serializable {

    private static final long serialVersionUID = 5654043413968134762L;

    @NotNull
    private final Long idUser;

    @NotNull
    private final Long idSerie;

    private final Date date;

}
