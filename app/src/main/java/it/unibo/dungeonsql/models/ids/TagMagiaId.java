package it.unibo.dungeonsql.models.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode @Builder
public class TagMagiaId implements Serializable {
    @Column(name = "CodiceMagia") private int codiceMagia;
    @Column(name = "Username") private String username;
    @Column(name = "NomeCampagna") private String nomeCampagna;
    @Column(name = "DataSvolgimento") private LocalDate dataSvolgimento;
}