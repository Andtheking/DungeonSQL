package it.unibo.dungeonsql.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CLASSE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Classe {

    @Id
    @Column(name = "NomeClasse", nullable = false)
    private String nomeClasse;

    @OneToMany(
        mappedBy = "classe",
        fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<Sottoclasse> sottoclassi = new ArrayList<>();
}