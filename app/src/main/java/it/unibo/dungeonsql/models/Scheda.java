package it.unibo.dungeonsql.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "SCHEDA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Scheda {

    @Id
    @Column(name = "CodiceScheda", nullable = false)
    private int codiceScheda;

    @Column(name = "Nome", nullable = false)
    private String nome;

    @Column(name = "MaxHP", nullable = false)
    private int maxHp;

    @Column(name = "CA", nullable = false)
    private int ca;

    @Column(name = "Taglia", nullable = false)
    private String taglia;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "UsernameCreatore",
        nullable = false
    )
    private Utente creatore;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
        @JoinColumn(
            name = "UsernameMaster",
            referencedColumnName = "UsernameMaster",
            nullable = false
        ),
        @JoinColumn(
            name = "NomeCampagna",
            referencedColumnName = "Nome",
            nullable = false
        )
    })
    private Campagna campagna;

    @OneToOne(
        mappedBy = "scheda",
        fetch = FetchType.LAZY
    )
    private Personaggio personaggio;
}