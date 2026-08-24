package it.unibo.dungeonsql.models;

import it.unibo.dungeonsql.models.ids.ConoscenzaId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CONOSCENZA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Conoscenza {

    @EmbeddedId
    private ConoscenzaId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("codiceScheda")
    @JoinColumn(name = "CodiceScheda", nullable = false)
    private Scheda scheda;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("codiceMagia")
    @JoinColumn(name = "CodiceMagia", nullable = false)
    private Magia magia;
}