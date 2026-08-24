package it.unibo.dungeonsql.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PERSONAGGIO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Personaggio {

    @Id
    @Column(name = "CodiceScheda", nullable = false)
    private String codiceScheda;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(
        name = "CodiceScheda",
        nullable = false
    )
    private Scheda scheda;

    @Column(name = "Allineamento", nullable = false)
    private String allineamento;

    @Column(name = "HP", nullable = false)
    private String hp;

    @Column(name = "ExpAccumulata", nullable = false)
    private String expAccumulata;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "NomeBackground",
        nullable = false
    )
    private Background background;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "NomeRazza",
        nullable = false
    )
    private Razza razza;
}