package it.unibo.dungeonsql.models;

import it.unibo.dungeonsql.models.ids.StatoAttivoId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "STATO_ATTIVO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatoAttivo {

    @EmbeddedId
    private StatoAttivoId id;

    @Column(name = "Scaduto", nullable = false)
    private Boolean scaduto;

    @Column(name = "Durata", nullable = false)
    private String durata;

    @Column(name = "Note")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Nome", referencedColumnName = "Nome", insertable = false, updatable = false)
    private EffettoStato effettoStato;

    // Afflizione (IstanzaComb afflitta)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
        @JoinColumn(name = "AFF_Username", referencedColumnName = "Username"),
        @JoinColumn(name = "AFF_Nome", referencedColumnName = "Nome"),
        @JoinColumn(name = "AFF_DataSvolgimento", referencedColumnName = "DataSvolgimento"),
        @JoinColumn(name = "AFF_NumCombattimento", referencedColumnName = "NumCombattimento"),
        @JoinColumn(name = "AFF_Numero", referencedColumnName = "NumeroIstanza")
    })
    private IstanzaComb istanzaAfflitta;

    // Causa (Azione che ha causato lo stato - opzionale)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "SUD_Username", referencedColumnName = "Username"),
        @JoinColumn(name = "SUD_Nome", referencedColumnName = "NomeCampagna"),
        @JoinColumn(name = "SUD_DataSvolgimento", referencedColumnName = "DataSvolgimento"),
        @JoinColumn(name = "SUD_NumCombattimento", referencedColumnName = "NumCombattimento"),
        @JoinColumn(name = "SUD_NumTurno", referencedColumnName = "NumTurno"),
        @JoinColumn(name = "NumAzione", referencedColumnName = "NumAzione")
    })
    private Azione azioneCausa;
}