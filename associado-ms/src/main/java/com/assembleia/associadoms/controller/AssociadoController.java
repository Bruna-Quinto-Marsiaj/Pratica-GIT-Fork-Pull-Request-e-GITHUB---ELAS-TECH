package com.assembleia.associadoms.controller;

import com.assembleia.associadoms.domain.Associado;
import com.assembleia.associadoms.service.AssociadoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/associado")
@AllArgsConstructor
public class AssociadoController {

    @Autowired
    private final AssociadoService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> cadastrar(@RequestParam String cpf) {
        Associado associado = service.findByCpf(cpf);

        if (!cpf.isEmpty()) {
            if (associado == null) {
                Associado novoAssociado = new Associado(null, cpf, null);
                service.cadastrar(novoAssociado);

                return ResponseEntity.status(HttpStatus.OK).body("Associado criado com sucesso");
            } else {
                return ResponseEntity.badRequest().body("Associado já cadastrado");
            }
        } else {
            return ResponseEntity.badRequest().body("CPF não pode ser vazio");
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> findByCpf(@RequestParam String cpf) {
        if (!cpf.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(service.findByCpf(cpf).toString());
        } else {
            return ResponseEntity.badRequest().body("CPF não pode ser nulo ou vazio");
        }
    }
}
