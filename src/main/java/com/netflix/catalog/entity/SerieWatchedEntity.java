package com.netflix.catalog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "serie_watched")
public class SerieWatchedEntity {

    @EmbeddedId
    private SerieWatchedEntityPK id;

    @NotNull
    private Date date;

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class SerieWatchedEntityPK implements Serializable {

        private static final long serialVersionUID = -520473895627696910L;

        @NotNull
        private Long idUser;

        @NotNull
        @OneToOne
        @JoinColumn(name = "id_serie", referencedColumnName = "id")
        private SerieEntity serie;

    }

}
