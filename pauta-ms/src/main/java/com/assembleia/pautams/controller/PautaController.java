package com.assembleia.pautams.controller;

import com.assembleia.pautams.DTO.PautaDTO;
import com.assembleia.pautams.domain.Associado;
import com.assembleia.pautams.domain.Pauta;
import com.assembleia.pautams.domain.Voto;
import com.assembleia.pautams.exception.PautaNotFoundException;
import com.assembleia.pautams.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/pauta")
public class PautaController {

    @Autowired
    private PautaService service;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> cadastrar(@RequestParam String nome) {
        PautaDTO pautaDTO = new PautaDTO(null, nome.toUpperCase(), new HashMap<>());
        Pauta pauta = service.fromDTO(pautaDTO);
        pauta = service.cadastrar(pauta);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pauta.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(path = "/votar", method = RequestMethod.POST)
    public ResponseEntity<Associado> votar(@RequestParam String cpf, @RequestParam String voto, @RequestParam String nomePauta) throws PautaNotFoundException {

        Pauta pauta = service.findByNome(nomePauta.toUpperCase());

        if (pauta != null) {
            //TODO: Associado não pode votar 2x e deve poder votar pela API
            Associado associadoCadastrado = service.findByAssociadoCpf(cpf);
            associadoCadastrado.setVoto(Voto.valueOf(voto.toUpperCase()));
            service.adicionarVotoNaPauta(pauta, associadoCadastrado);

            return ResponseEntity.ok(associadoCadastrado);
        } else {
            throw new PautaNotFoundException();
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> contagemDeVotos(@RequestParam String nome) throws PautaNotFoundException {
        Pauta pauta = service.findByNome(nome.toUpperCase());

        if (pauta != null) {
            return ResponseEntity.ok(service.contagemDeVotos(pauta));
        } else {
            throw new PautaNotFoundException();
        }
    }
}
