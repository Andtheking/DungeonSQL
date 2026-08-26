package it.unibo.dungeonsql.models;

import it.unibo.dungeonsql.models.ids.AbilitazioneTrattoId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ABILITAZIONE_TRATTO")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AbilitazioneTratto {

    @EmbeddedId
    private AbilitazioneTrattoId id;

    @Column(name = "MaxQuantita", nullable = false)
    private int maxQuantita;

    @Column(name = "Quantita", nullable = false)
    private int quantita;

    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
        @JoinColumn(name = "CodiceScheda", referencedColumnName = "CodiceScheda", insertable = false, updatable = false),
        @JoinColumn(name = "NomeClasse", referencedColumnName = "NomeClasse", insertable = false, updatable = false)
    })
    private Progresso progresso;

    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
        @JoinColumn(name = "NomeClasse", referencedColumnName = "NomeClasse", insertable = false, updatable = false),
        @JoinColumn(name = "NomeTratto", referencedColumnName = "NomeTratto", insertable = false, updatable = false)
    })
    private TrattoClasse trattoClasse;
}