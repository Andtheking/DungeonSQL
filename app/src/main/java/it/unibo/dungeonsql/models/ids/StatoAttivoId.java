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
public class StatoAttivoId implements Serializable {

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Numero")
    private int numero;
}