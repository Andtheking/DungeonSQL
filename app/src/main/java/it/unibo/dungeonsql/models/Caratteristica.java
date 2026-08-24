package it.unibo.dungeonsql.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CARATTERISTICA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Caratteristica {

    @Id
    @Column(name = "NomeCaratteristica", nullable = false)
    private String nome;
}