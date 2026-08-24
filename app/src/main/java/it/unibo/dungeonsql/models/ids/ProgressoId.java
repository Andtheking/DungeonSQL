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
public class ProgressoId implements Serializable {

    @Column(name = "CodiceScheda", nullable = false)
    private String codiceScheda;

    @Column(name = "NomeClasse", nullable = false)
    private String nomeClasse;
}