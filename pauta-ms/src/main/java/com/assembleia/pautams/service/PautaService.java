package com.assembleia.pautams.service;

import com.assembleia.pautams.DTO.PautaDTO;
import com.assembleia.pautams.domain.Associado;
import com.assembleia.pautams.domain.Pauta;
import com.assembleia.pautams.domain.Voto;
import com.assembleia.pautams.feign.AssociadoFeignClient;
import com.assembleia.pautams.repository.PautaRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PautaService {

    @Autowired
    private final PautaRepository repository;
    @Autowired
    private final AssociadoFeignClient associadoFeignClient;
    @Autowired
    private final RabbitTemplate sender;
    @Value("${spring.rabbitmq.exchange}")
    private String exchange;
    @Value("${spring.rabbitmq.routingKey}")
    private String routingKey;

    public PautaService(PautaRepository repository, AssociadoFeignClient associadoFeignClient, RabbitTemplate sender) {
        this.repository = repository;
        this.associadoFeignClient = associadoFeignClient;
        this.sender = sender;
    }

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

    public Associado findByAssociadoCpf(String cpf) {
        return associadoFeignClient.findByCpf(cpf).getBody();
    }

    public String contagemDeVotos(Pauta pauta) {
        long votosSim = pauta.getVotacao().values().stream().filter(voto -> voto.equals(Voto.SIM)).count();
        long votosNao = pauta.getVotacao().values().stream().filter(voto -> voto.equals(Voto.NAO)).count();

        sender.convertAndSend(exchange, routingKey, "VOTOS SIM: " + votosSim + " VOTOS NÃO: " + votosNao);

        return String.format("VOTOS SIM: " + votosSim + " VOTOS NÃO: " + votosNao);
    }

    public Pauta fromDTO(PautaDTO pautaDTO) {
        return new Pauta(null, pautaDTO.getNome(), pautaDTO.getVotacao(), LocalDateTime.now());
    }
}
