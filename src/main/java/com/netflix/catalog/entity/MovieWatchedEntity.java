package com.netflix.catalog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie_watched")
public class MovieWatchedEntity {

    @EmbeddedId
    private MovieWatchedEntityPK id;

    @NotNull
    private Date date;

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class MovieWatchedEntityPK implements Serializable {

        private static final long serialVersionUID = -520473895627696910L;

        @NotNull
        private Long idUser;

        @NotNull
        @OneToOne
        @JoinColumn(name = "id_movie", referencedColumnName = "id")
        private MovieEntity movie;

    }

}
