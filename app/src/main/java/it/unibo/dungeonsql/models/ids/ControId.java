package it.unibo.dungeonsql.models.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ControId implements Serializable {

    @Column(name = "Username")
    private String username;

    @Column(name = "NomeCampagna")
    private String nomeCampagna;

    @Column(name = "DataSvolgimento")
    private LocalDate dataSvolgimento;

    @Column(name = "NumCombattimento")
    private int numCombattimento;

    @Column(name = "NumTurno")
    private int numTurno;

    @Column(name = "NumAzione")
    private int numAzione;

    @Column(name = "NumeroIstanza")
    private int numeroIstanza;
}