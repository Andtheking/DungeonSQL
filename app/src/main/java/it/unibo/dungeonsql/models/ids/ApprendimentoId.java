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
public class ApprendimentoId implements Serializable {

    @Column(name = "CodiceMagia", nullable = false)
    private int codiceMagia;

    @Column(name = "NomeClasse", nullable = false)
    private String nomeClasse;
}