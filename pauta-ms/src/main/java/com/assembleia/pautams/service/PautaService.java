package com.assembleia.pautams.service;

import com.assembleia.pautams.DTO.PautaDTO;
import com.assembleia.pautams.domain.Associado;
import com.assembleia.pautams.domain.Pauta;
import com.assembleia.pautams.domain.Voto;
import com.assembleia.pautams.feign.AssociadoFeignClient;
import com.assembleia.pautams.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautaService {

    @Autowired
    private PautaRepository repository;
    @Autowired
    private AssociadoFeignClient associadoFeignClient;

    public Pauta cadastrar(Pauta pauta) {
        return repository.save(pauta);
    }

    public Pauta findByNome(String nome) {
        return repository.findByNome(nome);
    }

    public void adicionarVotoNaPauta(Pauta pauta, Associado associado) {
        associadoFeignClient.findByCpf(associado.getCpf());
        pauta.adicionarVoto(associado.getId(), associado.getVoto());
        repository.save(pauta);
    }

    public String contagemDeVotos(Pauta pauta) {

        long votosSim = pauta.getVotacao().values().stream().filter(voto -> voto.equals(Voto.SIM)).count();
        long votosNao = pauta.getVotacao().values().stream().filter(voto -> voto.equals(Voto.NAO)).count();

        return "VOTOS SIM: " + votosSim + " VOTOS NÃO: " + votosNao;
    }

    public Pauta fromDTO(PautaDTO pautaDTO) {
        return new Pauta(null, pautaDTO.getNome(), pautaDTO.getVotacao());
    }

    public Associado findByAssociadoCpf(String cpf) {
        return associadoFeignClient.findByCpf(cpf).getBody();
    }
}
