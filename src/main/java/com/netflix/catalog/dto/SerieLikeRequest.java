package com.netflix.catalog.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Getter
@ToString
public class SerieLikeRequest implements Serializable {

    private static final long serialVersionUID = -8302734050654199997L;

    @NotNull
    private Long idUser;

    @NotNull
    private Long idSerie;

}
