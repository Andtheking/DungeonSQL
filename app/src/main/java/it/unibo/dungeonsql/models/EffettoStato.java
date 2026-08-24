package it.unibo.dungeonsql.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "EFFETTO_STATO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EffettoStato {

    @Id
    @Column(name = "Nome", nullable = false)
    private String nome;

    @Column(name = "Descrizione", nullable = false)
    private String descrizione;
}