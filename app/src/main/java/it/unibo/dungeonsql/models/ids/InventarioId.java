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
public class InventarioId implements Serializable {

    @Column(name = "CodiceScheda")
    private int codiceScheda;

    @Column(name = "CodiceOggetto")
    private int codiceOggetto;
}