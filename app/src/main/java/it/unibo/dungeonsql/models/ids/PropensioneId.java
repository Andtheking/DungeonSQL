package it.unibo.dungeonsql.models.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode @Builder
public class PropensioneId implements Serializable {
    @Column(name = "NomeBackground")
    private String nomeBackground;

    @Column(name = "NomeSkill")
    private String nomeSkill;
}