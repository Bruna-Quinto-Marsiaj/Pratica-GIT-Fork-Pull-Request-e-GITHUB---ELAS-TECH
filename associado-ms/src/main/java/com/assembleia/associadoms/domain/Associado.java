package com.assembleia.associadoms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Associado {
    @Id
    private String id;
    private String cpf;
    private Voto voto;
}
