package com.assembleia.pautams.DTO;

import com.assembleia.pautams.domain.Voto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@Data
@AllArgsConstructor
public class PautaDTO {

    private String id;
    private String nome;
    private HashMap<String, Voto> votacao;

}
