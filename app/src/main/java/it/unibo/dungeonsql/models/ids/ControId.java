package it.unibo.dungeonsql.models.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

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
    private String dataSvolgimento;

    @Column(name = "NumCombattimento")
    private String numCombattimento;

    @Column(name = "NumTurno")
    private String numTurno;

    @Column(name = "NumAzione")
    private String numAzione;

    @Column(name = "NumeroIstanza")
    private String numeroIstanza;
}