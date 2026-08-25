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
public class CombattimentoId implements Serializable {

    @Column(name = "Username")
    private String username;

    @Column(name = "Nome")
    private String nomeCampagna;

    @Column(name = "DataSvolgimento")
    private LocalDate dataSvolgimento;

    @Column(name = "NumCombattimento")
    private int numCombattimento;
}