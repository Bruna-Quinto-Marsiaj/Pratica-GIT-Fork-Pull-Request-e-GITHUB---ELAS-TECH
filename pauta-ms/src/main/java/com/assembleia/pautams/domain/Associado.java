package com.assembleia.pautams.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Associado {
    private final String id;
    private final String cpf;
    private final Voto voto;
}
