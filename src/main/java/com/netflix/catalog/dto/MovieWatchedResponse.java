package com.netflix.catalog.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
public class MovieWatchedResponse implements Serializable {

    private static final long serialVersionUID = 6135130553549995052L;

    private Long idUser;
    private List<MovieWatchResponse> movies;

}
