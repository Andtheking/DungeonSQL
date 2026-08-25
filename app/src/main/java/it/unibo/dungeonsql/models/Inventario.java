package it.unibo.dungeonsql.models;

import it.unibo.dungeonsql.models.ids.InventarioId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "INVENTARIO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventario {

    @EmbeddedId
    private InventarioId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("codiceScheda")
    @JoinColumn(name = "CodiceScheda", nullable = false)
    private Scheda scheda;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("codiceOggetto")
    @JoinColumn(name = "CodiceOggetto", nullable = false)
    private Oggetto oggetto;

    @Column(name = "Quantita", nullable = false)
    private int quantita;
}