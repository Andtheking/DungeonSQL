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
public class CombattimentoId implements Serializable {

    @Column(name = "Username")
    private String username;

    @Column(name = "Nome")
    private String nomeCampagna; // Corrisponde al nome della campagna/sessione nello schema

    @Column(name = "DataSvolgimento")
    private String dataSvolgimento;

    @Column(name = "NumCombattimento")
    private String numCombattimento;
}