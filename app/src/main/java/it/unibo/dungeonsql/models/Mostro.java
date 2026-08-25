package it.unibo.dungeonsql.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MOSTRO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mostro {

    @Id
    @Column(name = "CodiceScheda")
    private int codiceScheda;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "CodiceScheda", nullable = false)
    private Scheda scheda;

    @Column(name = "CR", nullable = false)
    private String cr;

    @Column(name = "ExpRilasciata", nullable = false)
    private String expRilasciata;

    @Column(name = "Velocita", nullable = false)
    private String velocita;
}