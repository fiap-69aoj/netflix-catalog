package com.netflix.catalog.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigInteger;

@Builder
@Getter
@EqualsAndHashCode(of = "idCategory")
public class MovieWatchedByCategoryResponse implements Serializable {

    private static final long serialVersionUID = -955593357836312087L;

    private BigInteger idMovie;
    private String nameMovie;
    private BigInteger idCategory;
    private String nameCategory;
    private BigInteger amount;

}
