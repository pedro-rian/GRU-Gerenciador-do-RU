package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.model.Aviso;
import br.com.ufrn.imd.gru.repository.AvisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AvisoService {

    @Autowired
    private AvisoRepository avisoRepository;

    public Aviso getAvisoDoDia(LocalDate dataAtual) {
        return avisoRepository.findByData(dataAtual);
    }
}
