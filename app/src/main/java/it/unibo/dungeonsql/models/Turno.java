package it.unibo.dungeonsql.models;

import it.unibo.dungeonsql.models.ids.TurnoId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TURNO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Turno {

    @EmbeddedId
    private TurnoId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
        @JoinColumn(name = "Username", referencedColumnName = "Username", insertable = false, updatable = false),
        @JoinColumn(name = "Nome", referencedColumnName = "Nome", insertable = false, updatable = false),
        @JoinColumn(name = "DataSvolgimento", referencedColumnName = "DataSvolgimento", insertable = false, updatable = false),
        @JoinColumn(name = "NumCombattimento", referencedColumnName = "NumCombattimento", insertable = false, updatable = false)
    })
    private Combattimento combattimento;
}