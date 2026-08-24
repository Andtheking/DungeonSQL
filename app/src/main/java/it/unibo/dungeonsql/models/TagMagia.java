package it.unibo.dungeonsql.models;

import it.unibo.dungeonsql.models.ids.TagMagiaId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TAG_MAGIA")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TagMagia {
    @EmbeddedId
    private TagMagiaId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("codiceMagia")
    @JoinColumn(name = "CodiceMagia", nullable = false)
    private Magia magia;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
        @JoinColumn(name = "Username", referencedColumnName = "Username", insertable = false, updatable = false),
        @JoinColumn(name = "NomeCampagna", referencedColumnName = "NomeCampagna", insertable = false, updatable = false),
        @JoinColumn(name = "DataSvolgimento", referencedColumnName = "DataSvolgimento", insertable = false, updatable = false)
    })
    private Sessione sessione;
}