package it.unibo.dungeonsql.models;

import it.unibo.dungeonsql.models.ids.AbilitazioneRisorsaId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ABILITAZIONE_RISORSA")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AbilitazioneRisorsa {

    @EmbeddedId
    private AbilitazioneRisorsaId id;

    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
        @JoinColumn(name = "CodiceScheda", referencedColumnName = "CodiceScheda", insertable = false, updatable = false),
        @JoinColumn(name = "NomeClasse", referencedColumnName = "NomeClasse", insertable = false, updatable = false)
    })
    private Progresso progresso;

    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
        @JoinColumn(name = "NomeClasse", referencedColumnName = "NomeClasse", insertable = false, updatable = false),
        @JoinColumn(name = "NomeRisorsa", referencedColumnName = "NomeRisorsa", insertable = false, updatable = false)
    })
    private RisorsaClasse risorsaClasse;
}