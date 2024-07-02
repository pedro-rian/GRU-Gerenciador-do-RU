package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.model.Pessoa;
import br.com.ufrn.imd.gru.model.UsuarioGRU;
import br.com.ufrn.imd.gru.repository.CardapioRepository;
import br.com.ufrn.imd.gru.repository.PessoaRepository;
import br.com.ufrn.imd.gru.repository.UsuarioGRURepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EstatisticaGRUStrategy implements EstatisticaStrategy{

    UsuarioGRURepository usuarioGRURepository;
    PessoaRepository pessoaRepository;
    CardapioRepository cardapioRepository;

    @Override
    public long totalUsuarios(){
        return usuarioGRURepository.count();
    }
    @Override
    public long totalArtefatos(){
        return cardapioRepository.count();
    }
    @Override
    public Map<String, Double> calcularMedias(){
        List<Pessoa> pessoas = pessoaRepository.findAll();
        double mediaIMC = pessoas.stream().mapToDouble(Pessoa::getImc).average().orElse(0.0);
        double mediaPeso = pessoas.stream().mapToDouble(Pessoa::getPeso).average().orElse(0.0);

        Map<String, Double> medias = new HashMap<>();
        medias.put("mediaIMC", mediaIMC);
        medias.put("mediaPeso", mediaPeso);
        return medias;
    }
}
