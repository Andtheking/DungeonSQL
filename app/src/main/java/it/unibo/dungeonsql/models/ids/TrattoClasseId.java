package it.unibo.dungeonsql.models.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode @Builder
public class TrattoClasseId implements Serializable {
    @Column(name = "NomeClasse")
    private String nomeClasse;

    @Column(name = "NomeTratto")
    private String nome;
}