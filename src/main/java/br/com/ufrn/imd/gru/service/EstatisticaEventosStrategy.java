package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.repository.EventoRepository;
import br.com.ufrn.imd.gru.repository.UsuarioEventoRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EstatisticaEventosStrategy implements EstatisticaStrategy{

    UsuarioEventoRepository usuarioEventoRepository;
    EventoRepository eventoRepository;

    @Override
    public long totalUsuarios(){
        return usuarioEventoRepository.count();
    }
    @Override
    public long totalArtefatos(){
        return eventoRepository.count();
    }
    @Override
    public Map<String, Double> calcularMedias(){
        return null;
    }
}
