package it.unibo.dungeonsql.models;

import it.unibo.dungeonsql.models.ids.CombattimentoId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "COMBATTIMENTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Combattimento {

    @EmbeddedId
    private CombattimentoId id;

    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
        @JoinColumn(name = "Username", referencedColumnName = "Username", insertable = false, updatable = false),
        @JoinColumn(name = "Nome", referencedColumnName = "NomeCampagna", insertable = false, updatable = false),
        @JoinColumn(name = "DataSvolgimento", referencedColumnName = "DataSvolgimento", insertable = false, updatable = false)
    })
    private Sessione sessione;
}