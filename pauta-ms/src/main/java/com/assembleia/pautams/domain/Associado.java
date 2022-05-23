package com.assembleia.pautams.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Associado {
    private String id;
    private String cpf;
    private Voto voto;
}
