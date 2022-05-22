package com.assembleia.pautams.controller;

import com.assembleia.pautams.DTO.PautaDTO;
import com.assembleia.pautams.domain.Associado;
import com.assembleia.pautams.domain.Pauta;
import com.assembleia.pautams.domain.Voto;
import com.assembleia.pautams.exception.PautaNotFoundException;
import com.assembleia.pautams.service.PautaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "/pauta")
@AllArgsConstructor
@Slf4j
public class PautaController {

    @Autowired
    private final PautaService service;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> cadastrar(@RequestParam String nome) {
        if (!nome.isEmpty()) {
            PautaDTO pautaDTO = new PautaDTO(null, nome.toUpperCase(), new HashMap<>());

            Pauta pauta = service.fromDTO(pautaDTO);
            service.cadastrar(pauta);

            return ResponseEntity.status(HttpStatus.OK).body("Pauta cadastrada com sucesso");
        } else {
            return ResponseEntity.badRequest().body("Nome não pode ser vazio");
        }
    }

    @RequestMapping(path = "/votar", method = RequestMethod.POST)
    public ResponseEntity<String> votar(@RequestParam String cpf, @RequestParam String voto, @RequestParam String nomePauta) throws PautaNotFoundException {

        Pauta pauta = service.findByNome(nomePauta.toUpperCase());

        if (tempoLimite(pauta) < 90) {
            if (pauta != null) {
                Associado associadoCadastrado = service.findByAssociadoCpf(cpf);
                return ResponseEntity.ok(controleDeVoto(associadoCadastrado, pauta, voto));
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().body("Tempo excedido para votações na Pauta: " + pauta.getNome());
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> contagemDeVotos(@RequestParam String nome) throws PautaNotFoundException {
        Pauta pauta = service.findByNome(nome.toUpperCase());

        if (pauta != null) {
            return ResponseEntity.ok(service.contagemDeVotos(pauta));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private long tempoLimite(Pauta pauta) {
        long epochPauta = 0L;
        if (pauta != null) {
            epochPauta = pauta.getTimeStart().atZone(ZoneId.systemDefault()).toEpochSecond();
        }
        long epochNow = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
        return epochNow - epochPauta;
    }

    private String controleDeVoto(Associado associadoCadastrado, Pauta pauta, String voto) {
        boolean jaVotou = false;
        for (Map.Entry<String, Voto> pair : pauta.getVotacao().entrySet()) {
            if (Objects.equals(associadoCadastrado.getId(), pair.getKey())) {
                jaVotou = true;
                return "Associado já votou!";
            }
        }
        if (!jaVotou) {
            associadoCadastrado.setVoto(Voto.valueOf(voto.toUpperCase()));
            service.adicionarVotoNaPauta(pauta, associadoCadastrado);
        }
        return "Voto cadastrado com sucesso";
    }
}
