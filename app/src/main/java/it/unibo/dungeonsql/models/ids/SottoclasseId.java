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
@Builder
@EqualsAndHashCode
public class SottoclasseId implements Serializable {

    @Column(name = "NomeClasse", nullable = false)
    private String nomeClasse;

    @Column(name = "NomeSottoclasse", nullable = false)
    private String nomeSottoclasse;
}
