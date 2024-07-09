package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.model.Evento;
import br.com.ufrn.imd.gru.repository.EventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {
    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public List<Evento> getEventosAtuais() {
        return eventoRepository.findAll();
    }
}
