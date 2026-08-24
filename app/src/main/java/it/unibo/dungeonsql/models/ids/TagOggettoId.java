package it.unibo.dungeonsql.models.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode @Builder
public class TagOggettoId implements Serializable {
    @Column(name = "CodiceOggetto") private String codiceOggetto;
    @Column(name = "Username") private String username;
    @Column(name = "NomeCampagna") private String nomeCampagna;
    @Column(name = "DataSvolgimento") private String dataSvolgimento;
}