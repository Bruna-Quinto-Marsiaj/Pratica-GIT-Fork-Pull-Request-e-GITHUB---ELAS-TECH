package com.assembleia.pautams.DTO;

import com.assembleia.pautams.domain.Voto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@Data
@AllArgsConstructor
public class PautaDTO {

    private final String id;
    private final String nome;
    private final HashMap<String, Voto> votacao;

}
