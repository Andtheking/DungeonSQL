package it.unibo.dungeonsql.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "BACKGROUND")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Background {

    @Id
    @Column(name = "NomeBackground", nullable = false)
    private String nomeBackground;

    @Column(name = "Descrizione", nullable = false)
    private String descrizione;
}