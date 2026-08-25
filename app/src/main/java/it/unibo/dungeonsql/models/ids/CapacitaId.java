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
public class CapacitaId implements Serializable {

    @Column(name = "NomeSkill", nullable = false)
    private String nomeSkill;

    @Column(name = "CodiceScheda", nullable = false)
    private int codiceScheda;
}