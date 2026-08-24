package it.unibo.dungeonsql.models;

import it.unibo.dungeonsql.models.ids.SessioneId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "SESSIONE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sessione {

    @EmbeddedId
    private SessioneId id;

    // Relazione con Campagna (la chiave primaria di Campagna è composta da UsernameMaster e Nome)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
        @JoinColumn(name = "Username", referencedColumnName = "UsernameMaster", insertable = false, updatable = false),
        @JoinColumn(name = "NomeCampagna", referencedColumnName = "Nome", insertable = false, updatable = false)
    })
    private Campagna campagna;

    @Column(name = "Diario")
    private String diario;
}