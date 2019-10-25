package com.netflix.catalog.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@Getter
@Entity
@Table(name = "movie")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String image;

    @NotNull
    private String rating;

    @NotNull
    private String summary;

    @NotNull
    private Date releaseDate;

}
