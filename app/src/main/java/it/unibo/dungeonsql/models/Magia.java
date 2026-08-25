package it.unibo.dungeonsql.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MAGIA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Magia {

    @Id
    @Column(name = "CodiceMagia", nullable = false)
    private int codiceMagia;

    @Column(name = "Nome", nullable = false)
    private String nome;

    @Column(name = "Descrizione", nullable = false)
    private String descrizione;

    @Column(name = "Livello", nullable = false)
    private String livello;

    @Column(name = "Rituale", nullable = false)
    private String rituale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(
            name = "UsernameMasterCampagna",
            referencedColumnName = "UsernameMaster"
        ),
        @JoinColumn(
            name = "NomeCampagna",
            referencedColumnName = "Nome"
        )
    })
    private Campagna campagna;
}