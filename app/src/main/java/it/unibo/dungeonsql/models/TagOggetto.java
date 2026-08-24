package it.unibo.dungeonsql.models;

import it.unibo.dungeonsql.models.ids.TagOggettoId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TAG_OGGETTO")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TagOggetto {
    @EmbeddedId
    private TagOggettoId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("codiceOggetto")
    @JoinColumn(name = "CodiceOggetto", nullable = false)
    private Oggetto oggetto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
        @JoinColumn(name = "Username", referencedColumnName = "Username", insertable = false, updatable = false),
        @JoinColumn(name = "NomeCampagna", referencedColumnName = "NomeCampagna", insertable = false, updatable = false),
        @JoinColumn(name = "DataSvolgimento", referencedColumnName = "DataSvolgimento", insertable = false, updatable = false)
    })
    private Sessione sessione;
}