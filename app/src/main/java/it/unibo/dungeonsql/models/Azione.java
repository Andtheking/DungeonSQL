package it.unibo.dungeonsql.models;

import it.unibo.dungeonsql.models.ids.AzioneId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "AZIONE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Azione {

    @EmbeddedId
    private AzioneId id;

    @Column(name = "Tipo", nullable = false)
    private String tipo;

    @Column(name = "NumeroIstanza", nullable = false)
    private String numeroIstanza;

    // Relazione opzionale con Oggetto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CodiceOggetto")
    private Oggetto oggetto;

    // Relazione opzionale con Magia
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CodiceMagia")
    private Magia magia;

    // Relazione con l'istanza che esegue l'azione
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
        @JoinColumn(name = "Username", referencedColumnName = "Username", insertable = false, updatable = false),
        @JoinColumn(name = "NomeCampagna", referencedColumnName = "Nome", insertable = false, updatable = false),
        @JoinColumn(name = "DataSvolgimento", referencedColumnName = "DataSvolgimento", insertable = false, updatable = false),
        @JoinColumn(name = "NumCombattimento", referencedColumnName = "NumCombattimento", insertable = false, updatable = false),
        @JoinColumn(name = "NumeroIstanza", referencedColumnName = "NumeroIstanza", insertable = false, updatable = false)
    })
    private IstanzaComb istanzaComb;

    // Relazione con il Turno in cui avviene
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
        @JoinColumn(name = "Username", referencedColumnName = "Username", insertable = false, updatable = false),
        @JoinColumn(name = "NomeCampagna", referencedColumnName = "Nome", insertable = false, updatable = false),
        @JoinColumn(name = "DataSvolgimento", referencedColumnName = "DataSvolgimento", insertable = false, updatable = false),
        @JoinColumn(name = "NumCombattimento", referencedColumnName = "NumCombattimento", insertable = false, updatable = false),
        @JoinColumn(name = "NumTurno", referencedColumnName = "NumTurno", insertable = false, updatable = false)
    })
    private Turno turno;
}