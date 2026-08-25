package it.unibo.dungeonsql.dtos;

import it.unibo.dungeonsql.models.*;
import it.unibo.dungeonsql.services.SchedaService.RigaInventario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class SchedaPersonaggio {
    private final Scheda scheda;
    private final Personaggio personaggio;
    private final Campagna campagna;
    private final Utente creatore;
    private final List<Possesso> caratteristiche;
    private final List<Capacita> capacita;
    private final List<Progresso> progressi;
    private final List<RigaInventario> inventario;
    private final List<Magia> magie;
}