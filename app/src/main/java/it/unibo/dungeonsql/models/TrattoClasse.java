package it.unibo.dungeonsql.models;

import it.unibo.dungeonsql.models.ids.TrattoClasseId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TRATTO_CLASSE")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TrattoClasse {

    @EmbeddedId
    private TrattoClasseId id;

    @Column(name = "Descrizione", nullable = false)
    private String descrizione;

    @Column(name = "LivelloRichiesto", nullable = false)
    private int livelloRichiesto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("nomeClasse")
    @JoinColumn(name = "NomeClasse", nullable = false)
    private Classe classe;
}