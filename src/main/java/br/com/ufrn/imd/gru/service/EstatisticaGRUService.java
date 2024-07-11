package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.model.PessoaGRU;
import br.com.ufrn.imd.gru.repository.CardapioRepository;
import br.com.ufrn.imd.gru.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EstatisticaGRUService implements EstatisticaStrategy{

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private CardapioRepository cardapioRepository;

    public EstatisticaGRUService(PessoaRepository pessoaRepository, CardapioRepository cardapioRepository) {
        this.pessoaRepository = pessoaRepository;
        this.cardapioRepository = cardapioRepository;
    }

    @Override
    public long calcularTotalUsuarios() {
        return pessoaRepository.count();
    }

    @Override
    public Map<String, Double> calcularTotalArtefatos() {
        Map<String, Double> map = new HashMap<>();
        map.put("Total de cardápios cadastrados:", (double) cardapioRepository.count());
        return map;
    }

    @Override
    public Map<String, Double> calcularMedias() {
        Map<String, Double> medias = new HashMap<>();
        List<PessoaGRU> pessoaGRUS = pessoaRepository.findAll();

        double mediaIMC = pessoaGRUS.stream().mapToDouble(PessoaGRU::getImc).average().orElse(0.0);
        double mediaAltura = pessoaGRUS.stream().mapToDouble(PessoaGRU::getAltura).average().orElse(0.0);
        double quantidadeSobrepeso = (double) pessoaGRUS.stream().filter(pessoa -> pessoa.getImc() >= 25).count();
        medias.put("Média do IMC dos consumidores:", mediaIMC);
        medias.put("Média da altura dos consumidores", mediaAltura);
        medias.put("Quant. de consumidores com sobrepeso", quantidadeSobrepeso);

        return medias;
    }
}
