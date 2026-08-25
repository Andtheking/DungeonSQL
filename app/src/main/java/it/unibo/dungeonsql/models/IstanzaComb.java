package it.unibo.dungeonsql.models;

import it.unibo.dungeonsql.models.ids.IstanzaCombId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ISTANZA_COMB")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IstanzaComb {

    @EmbeddedId
    private IstanzaCombId id;

    @Column(name = "Iniziativa", nullable = false)
    private String iniziativa;

    @Column(name = "HP", nullable = false)
    private int hp;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
        @JoinColumn(name = "Username", referencedColumnName = "Username", insertable = false, updatable = false),
        @JoinColumn(name = "Nome", referencedColumnName = "Nome", insertable = false, updatable = false),
        @JoinColumn(name = "DataSvolgimento", referencedColumnName = "DataSvolgimento", insertable = false, updatable = false),
        @JoinColumn(name = "NumCombattimento", referencedColumnName = "NumCombattimento", insertable = false, updatable = false)
    })
    private Combattimento combattimento;

    // Questa è la chiave diretta che punta alla scheda (sia essa di un personaggio o di un mostro)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CodiceScheda", nullable = false)
    private Scheda scheda;
}