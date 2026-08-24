package it.unibo.dungeonsql.models;

import it.unibo.dungeonsql.models.ids.SottoclasseId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "SOTTOCLASSE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sottoclasse {

    @EmbeddedId
    private SottoclasseId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("nomeClasse")
    @JoinColumn(
        name = "NomeClasse",
        nullable = false
    )
    private Classe classe;
}