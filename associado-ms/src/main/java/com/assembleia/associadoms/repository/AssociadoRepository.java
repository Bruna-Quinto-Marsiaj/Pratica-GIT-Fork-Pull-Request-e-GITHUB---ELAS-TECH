package com.assembleia.associadoms.repository;

import com.assembleia.associadoms.domain.Associado;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends MongoRepository<Associado, String> {
    Associado findByCpf(String cpf);
}
