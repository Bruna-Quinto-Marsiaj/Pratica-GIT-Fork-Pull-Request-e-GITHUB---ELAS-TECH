package com.assembleia.pautams.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.HashMap;

@Data
public class Pauta {

    @Id
    private final String id;
    private final String nome;
    private final HashMap<String, Voto> votacao;
    private final LocalDateTime timeStart;

    public Pauta(String id, String nome, HashMap<String, Voto> votacao, LocalDateTime timeStart) {
        this.id = id;
        this.nome = nome;
        this.votacao = votacao;
        this.timeStart = timeStart;
    }

    public void adicionarVoto(String idAssociado, Voto voto) {

        votacao.put(idAssociado, voto);
    }
}
