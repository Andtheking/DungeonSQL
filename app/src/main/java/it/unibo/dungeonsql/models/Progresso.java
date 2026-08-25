package it.unibo.dungeonsql.models;

import it.unibo.dungeonsql.models.ids.ProgressoId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PROGRESSO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Progresso {

    @EmbeddedId
    private ProgressoId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("codiceScheda")
    @JoinColumn(
        name = "CodiceScheda",
        nullable = false
    )
    private Personaggio personaggio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("nomeClasse")
    @JoinColumn(
        name = "NomeClasse",
        nullable = false
    )
    private Classe classe;

    @Column(name = "Livello", nullable = false)
    private int livello;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(
            name = "NomeClasse",
            referencedColumnName = "NomeClasse",
            insertable = false,
            updatable = false
        ),
        @JoinColumn(
            name = "NomeSottoclasse",
            referencedColumnName = "NomeSottoclasse",
            insertable = false,
            updatable = false
        )
    })
    private Sottoclasse sottoclasse;
}