package it.unibo.dungeonsql.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "OGGETTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Oggetto {

    @Id
    @Column(name = "CodiceOggetto", nullable = false)
    private int codiceOggetto;

    @Column(name = "Nome", nullable = false)
    private String nome;

    @Column(name = "Descrizione", nullable = false)
    private String descrizione;

    @Column(name = "Peso", nullable = false)
    private String peso;

    @Column(name = "EffettoMagico", nullable = false)
    private String effettoMagico;

    @Column(name = "TipoOggetto", nullable = false)
    private String tipoOggetto;

    @Column(name = "Danno")
    private String danno;

    @Column(name = "TipoArma")
    private String tipoArma;

    @Column(name = "ProprietaArma")
    private String proprietaArma;

    @Column(name = "BonusCA")
    private String bonusCA;

    @Column(name = "ReqArmatura")
    private String reqArmatura;

    @Column(name = "Furtiva")
    private String furtiva;

    @Column(name = "EffettoCons")
    private String effettoCons;

    @Column(name = "DurataCons")
    private String durataCons;

    // Relazione opzionale con la Campagna (oggetti homebrew/creati nella campagna)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "UsernameMasterCampagna", referencedColumnName = "UsernameMaster"),
        @JoinColumn(name = "NomeCampagna", referencedColumnName = "Nome")
    })
    private Campagna campagna;
}