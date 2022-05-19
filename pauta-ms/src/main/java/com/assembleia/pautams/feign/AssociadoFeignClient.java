package com.assembleia.pautams.feign;

import com.assembleia.pautams.domain.Associado;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "associado-ms", url = "http://localhost:8001", path = "/associado")
public interface AssociadoFeignClient {

    @GetMapping
    ResponseEntity<Associado> findByCpf(@RequestParam String cpf);
}
