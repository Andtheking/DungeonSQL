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
public class PossessoId implements Serializable {

    @Column(name = "NomeCaratteristica", nullable = false)
    private String nomeCaratteristica;

    @Column(name = "CodiceScheda", nullable = false)
    private String codiceScheda;
}