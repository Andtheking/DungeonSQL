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
public class TurnoId implements Serializable {

    @Column(name = "Username")
    private String username;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "DataSvolgimento")
    private int dataSvolgimento;

    @Column(name = "NumCombattimento")
    private String numCombattimento;

    @Column(name = "NumTurno")
    private String numTurno;
}