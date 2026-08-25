package it.unibo.dungeonsql.dtos;

import it.unibo.dungeonsql.models.*;
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
    private final List<Possesso> caratteristiche;
    private final List<Capacita> capacita;
    private final List<Progresso> progressi;
    private final List<Object[]> inventario;
    private final List<Magia> magie;
}