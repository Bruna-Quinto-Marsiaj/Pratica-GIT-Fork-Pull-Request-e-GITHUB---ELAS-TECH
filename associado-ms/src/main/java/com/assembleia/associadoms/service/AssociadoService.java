package com.assembleia.associadoms.service;

import com.assembleia.associadoms.domain.Associado;
import com.assembleia.associadoms.repository.AssociadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AssociadoService {

    @Autowired
    private final AssociadoRepository repository;

    public Associado cadastrar(Associado associado) {
        return repository.insert(associado);
    }

    public Associado findByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}
