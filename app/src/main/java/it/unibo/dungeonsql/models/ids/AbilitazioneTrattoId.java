package it.unibo.dungeonsql.models.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode @Builder
public class AbilitazioneTrattoId implements Serializable {
    @Column(name = "CodiceScheda")
    private int codiceScheda;

    @Column(name = "NomeClasse")
    private String nomeClasse;

    @Column(name = "NomeTratto")
    private String nomeTratto;
}