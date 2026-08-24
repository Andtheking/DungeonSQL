package it.unibo.dungeonsql.models.ids;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class CampagnaId implements Serializable {

    @Column(name = "UsernameMaster", nullable = false)
    private String usernameMaster;

    @Column(name = "Nome", nullable = false)
    private String nome;
}