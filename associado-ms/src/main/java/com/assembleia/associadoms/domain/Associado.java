package com.assembleia.associadoms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Associado {
    @Id
    private final String id;
    private final String cpf;
    private final Voto voto;
}
