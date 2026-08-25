package it.unibo.dungeonsql.models;

import java.time.LocalDate;

import it.unibo.dungeonsql.models.ids.CampagnaId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CAMPAGNA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Campagna {

    @EmbeddedId
    private CampagnaId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("usernameMaster")
    @JoinColumn(
        name = "UsernameMaster",
        nullable = false
    )
    private Utente master;

    @Column(name = "Descrizione", nullable = false)
    private String descrizione;

    @Column(name = "DataInizio", nullable = false)
    private LocalDate dataInizio;
}