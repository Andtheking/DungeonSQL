package it.unibo.dungeonsql.models;

import it.unibo.dungeonsql.models.ids.CapacitaId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CAPACITA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Capacita {

    @EmbeddedId
    private CapacitaId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("nomeSkill")
    @JoinColumn(
        name = "NomeSkill",
        nullable = false
    )
    private Skill skill;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("codiceScheda")
    @JoinColumn(
        name = "CodiceScheda",
        nullable = false
    )
    private Scheda scheda;

    @Column(name = "LivelloCapacita", nullable = false)
    private String livelloCapacita;
}