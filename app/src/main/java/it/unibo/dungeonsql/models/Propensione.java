package it.unibo.dungeonsql.models;

import it.unibo.dungeonsql.models.ids.PropensioneId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PROPENSIONE")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Propensione {

    @EmbeddedId
    private PropensioneId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("nomeBackground")
    @JoinColumn(name = "NomeBackground", nullable = false)
    private Background background;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("nomeSkill")
    @JoinColumn(name = "NomeSkill", nullable = false)
    private Skill skill;
}