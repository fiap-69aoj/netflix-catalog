package com.netflix.catalog.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Builder
@Getter
public class MovieWatchResponse implements Serializable {

    private static final long serialVersionUID = 7705269005982090262L;

    private MovieResponse movie;

    // todo alterar para sql date
    private Date date;

}
