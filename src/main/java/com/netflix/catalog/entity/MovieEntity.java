package com.netflix.catalog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_movie", nullable = false)
    private List<MovieLabelEntity> labels;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "movie_category",
        joinColumns = @JoinColumn(name = "id_movie"),
        inverseJoinColumns = @JoinColumn(name = "id_category")
    )
    private List<CategoryEntity> categories;

}
