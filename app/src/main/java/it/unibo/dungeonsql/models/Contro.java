package it.unibo.dungeonsql.models;

import it.unibo.dungeonsql.models.ids.ControId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CONTRO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contro {

    @EmbeddedId
    private ControId id;

    @Column(name = "Danno", nullable = false)
    private int danno;

    @Column(name = "Esito", nullable = false)
    private String esito;

    @Column(name = "Sconfitto", nullable = false)
    private Boolean sconfitto;

    // Relazione con l'azione
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
        @JoinColumn(name = "Username", referencedColumnName = "Username", insertable = false, updatable = false),
        @JoinColumn(name = "NomeCampagna", referencedColumnName = "NomeCampagna", insertable = false, updatable = false),
        @JoinColumn(name = "DataSvolgimento", referencedColumnName = "DataSvolgimento", insertable = false, updatable = false),
        @JoinColumn(name = "NumCombattimento", referencedColumnName = "NumCombattimento", insertable = false, updatable = false),
        @JoinColumn(name = "NumTurno", referencedColumnName = "NumTurno", insertable = false, updatable = false),
        @JoinColumn(name = "NumAzione", referencedColumnName = "NumAzione", insertable = false, updatable = false)
    })
    private Azione azione;

    // Relazione con l'istanza bersaglio (target)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
        @JoinColumn(name = "Username", referencedColumnName = "Username", insertable = false, updatable = false),
        @JoinColumn(name = "NomeCampagna", referencedColumnName = "Nome", insertable = false, updatable = false),
        @JoinColumn(name = "DataSvolgimento", referencedColumnName = "DataSvolgimento", insertable = false, updatable = false),
        @JoinColumn(name = "NumCombattimento", referencedColumnName = "NumCombattimento", insertable = false, updatable = false),
        @JoinColumn(name = "NumeroIstanza", referencedColumnName = "NumeroIstanza", insertable = false, updatable = false)
    })
    private IstanzaComb istanzaBersaglio;
}