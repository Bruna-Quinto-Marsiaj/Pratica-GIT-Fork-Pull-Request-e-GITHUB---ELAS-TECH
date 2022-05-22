package com.assembleia.pautams.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class Associado {
    private final String id;
    private String cpf;
    private Voto voto;
}
