package it.unibo.dungeonsql.models;

import it.unibo.dungeonsql.models.ids.PossessoId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "POSSESSO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Possesso {

    @EmbeddedId
    private PossessoId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("nomeCaratteristica")
    @JoinColumn(
        name = "NomeCaratteristica",
        nullable = false
    )
    private Caratteristica caratteristica;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("codiceScheda")
    @JoinColumn(
        name = "CodiceScheda",
        nullable = false
    )
    private Scheda scheda;

    @Column(name = "Punteggio", nullable = false)
    private int punteggio;

    @Column(name = "CompetenzaSalvezza", nullable = false)
    private Boolean competenzaSalvezza;
}