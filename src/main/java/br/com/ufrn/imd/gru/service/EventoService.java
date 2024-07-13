package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.dto.EventoDto;
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

    public List<EventoDto> getEventosAtuais() {
        return eventoRepository.findAll().stream()
                .map(evento -> {
                    EventoDto dto = new EventoDto();
                    dto.setId(evento.getId());
                    dto.setTitulo(evento.getTitulo());
                    dto.setDescricao(evento.getDescricao());
                    dto.setData(evento.getData());
                    return dto;
                })
                .collect(java.util.stream.Collectors.toList());
    }
}
