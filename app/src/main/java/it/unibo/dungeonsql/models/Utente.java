package it.unibo.dungeonsql.models; 

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "UTENTE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utente {

    @Id
    @Column(name = "Username", nullable = false)
    private String username;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Password", nullable = false)
    private String password;
}