package it.unibo.dungeonsql.models.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode @Builder
public class TagPartecipanteId implements Serializable {
    @Column(name = "CodiceScheda") private String codiceScheda;
    @Column(name = "Username") private String username;
    @Column(name = "NomeCampagna") private String nomeCampagna;
    @Column(name = "DataSvolgimento") private String dataSvolgimento;
}