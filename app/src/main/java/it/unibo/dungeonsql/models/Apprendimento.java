package it.unibo.dungeonsql.models;

import it.unibo.dungeonsql.models.ids.ApprendimentoId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "APPRENDIMENTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Apprendimento {

    @EmbeddedId
    private ApprendimentoId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("codiceMagia")
    @JoinColumn(
        name = "CodiceMagia",
        nullable = false
    )
    private Magia magia;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("nomeClasse")
    @JoinColumn(
        name = "NomeClasse",
        nullable = false
    )
    private Classe classe;
}