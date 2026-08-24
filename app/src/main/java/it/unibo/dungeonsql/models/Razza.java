package it.unibo.dungeonsql.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "RAZZA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Razza {

    @Id
    @Column(name = "NomeRazza", nullable = false)
    private String nomeRazza;

    @Column(name = "Descrizione", nullable = false)
    private String descrizione;

    @Column(name = "VelocitaBase", nullable = false)
    private String velocitaBase;

    @Column(name = "Scurovisione", nullable = false)
    private String scurovisione;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NomeRazzaPadre")
    private Razza razzaPadre;

    @OneToMany(
        mappedBy = "razzaPadre",
        fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<Razza> sottoRazze = new ArrayList<>();
}