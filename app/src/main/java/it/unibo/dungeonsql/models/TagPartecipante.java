package it.unibo.dungeonsql.models;

import it.unibo.dungeonsql.models.ids.TagPartecipanteId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TAG_PARTECIPANTE")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TagPartecipante {
    @EmbeddedId
    private TagPartecipanteId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("codiceScheda")
    @JoinColumn(name = "CodiceScheda", nullable = false)
    private Scheda scheda;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
        @JoinColumn(name = "Username", referencedColumnName = "Username", insertable = false, updatable = false),
        @JoinColumn(name = "NomeCampagna", referencedColumnName = "NomeCampagna", insertable = false, updatable = false),
        @JoinColumn(name = "DataSvolgimento", referencedColumnName = "DataSvolgimento", insertable = false, updatable = false)
    })
    private Sessione sessione;
}