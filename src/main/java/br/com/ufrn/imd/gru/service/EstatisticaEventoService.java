package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.model.Evento;
import br.com.ufrn.imd.gru.model.PessoaEvento;
import br.com.ufrn.imd.gru.repository.EventoRepository;
import br.com.ufrn.imd.gru.repository.UsuarioEventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EstatisticaEventoService implements EstatisticaStrategy{
    @Autowired
    private UsuarioEventoRepository usuarioEventoRepository;
    @Autowired
    private EventoRepository eventoRepository;

    public EstatisticaEventoService(UsuarioEventoRepository usuarioEventoRepository, EventoRepository eventoRepository) {
        this.usuarioEventoRepository = usuarioEventoRepository;
        this.eventoRepository = eventoRepository;
    }

    @Override
    public long calcularTotalUsuarios() {
        return usuarioEventoRepository.count();
    }

    public Map<String, Double> calcularTotalArtefatos() {
        Map<String, Double> map = new HashMap<>();
        map.put("Total de eventos cadastrados:", (double) eventoRepository.count());
        return map;
    }

    @Override
    public Map<String, Double> calcularMedias() {
        Map<String, Double> medias = new HashMap<>();
        List<PessoaEvento> usuarios = usuarioEventoRepository.findAll();
        List<Evento> eventos = eventoRepository.findAll();

        double mediaDeInscritos = usuarios.stream().mapToDouble(PessoaEvento::getQtdEventosInscritos).average().orElse(0.0);
        double mediaEventosConcluidosPorUsuario = usuarios.stream().mapToDouble(PessoaEvento::getQtdEventosConcluidos).average().orElse(0.0);
        double mediaCargaHorariaPorUsuario = eventos.stream().mapToDouble(Evento::getCargaHoraria).average().orElse(0.0);
        medias.put("Média de inscrição em eventos por usuário:", mediaDeInscritos);
        medias.put("Média de eventos concluídos por usuário:", mediaEventosConcluidosPorUsuario);
        medias.put("Média da carga horária dos eventos:", mediaCargaHorariaPorUsuario);

        return medias;
    }
}
