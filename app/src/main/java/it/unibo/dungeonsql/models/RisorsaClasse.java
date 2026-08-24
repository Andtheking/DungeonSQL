package it.unibo.dungeonsql.models;

import it.unibo.dungeonsql.models.ids.RisorsaClasseId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "RISORSA_CLASSE")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RisorsaClasse {

    @EmbeddedId
    private RisorsaClasseId id;

    @Column(name = "Recupero", nullable = false)
    private String recupero;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("nomeClasse")
    @JoinColumn(name = "NomeClasse", nullable = false)
    private Classe classe;
}