package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.repository.LivroRepository;
import br.com.ufrn.imd.gru.repository.UsuarioBibliotecaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EstatisticaBibliotecaEstrategy implements EstatisticaStrategy {

    UsuarioBibliotecaRepository usuarioBibliotecaRepository;
    LivroRepository livroRepository;

    @Override
    public long totalUsuarios(){
        return usuarioBibliotecaRepository.count();
    }
    @Override
    public long totalArtefatos(){
        return livroRepository.count();
    }
    @Override
    public Map<String, Double> calcularMedias(){
        return null;
    }
}
