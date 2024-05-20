package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.dto.AvisoDTO;
import br.com.ufrn.imd.gru.model.Aviso;
import br.com.ufrn.imd.gru.repository.AvisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class AvisoService {

    @Autowired
    private AvisoRepository avisoRepository;

    public Aviso getAvisoDoDia(LocalDate dataAtual) {
        return avisoRepository.findByData(dataAtual);
    }

    public List<Aviso> getAvisos() {
        return avisoRepository.findAll();
    }

    public void cadastrarAviso(AvisoDTO avisoDTO) {
        List<String> errors = new ArrayList<>();

        if (avisoDTO.getTitulo() == null || avisoDTO.getTitulo().isEmpty()) {
            errors.add("Título é um campo obrigatório.");
        }
        if (avisoDTO.getDescricao() == null || avisoDTO.getDescricao().isEmpty()) {
            errors.add("Descrição é um campo obrigatório.");
        }
        if (avisoDTO.getData() == null) {
            errors.add("Data é um campo obrigatório.");
        } else {
            Date data = parseData(avisoDTO.getData().toString());
            LocalDate localDate = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (avisoJaCadastrado(localDate)) {
                errors.add("Aviso já cadastrado para essa data.");
            }
        }

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", errors));
        }

        Date data = parseData(avisoDTO.getData().toString());

        Aviso aviso = new Aviso();
        aviso.setTitulo(avisoDTO.getTitulo());
        aviso.setDescricao(avisoDTO.getDescricao());
        aviso.setData(data);

        avisoRepository.save(aviso);
    }

    private Date parseData(String dataString) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
            return format.parse(dataString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formato de data inválido.");
        }
    }


    private boolean avisoJaCadastrado(LocalDate data) {
        return avisoRepository.existsAviso(data);
    }
}
