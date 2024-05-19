package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.dto.AvisoDTO;
import br.com.ufrn.imd.gru.model.Aviso;
import br.com.ufrn.imd.gru.model.Cardapio;
import br.com.ufrn.imd.gru.repository.AvisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
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
    public void cadastrarAviso(AvisoDTO avisoDTO){

        Date data = parseData(avisoDTO.getData().toString());


        Aviso aviso = new Aviso();
        aviso.setTitulo(avisoDTO.getTitulo());
        aviso.setDescricao(avisoDTO.getDescricao());
        aviso.setData(data);

        if (aviso.getTitulo() == null || aviso.getTitulo().isEmpty() ||
                aviso.getDescricao() == null || aviso.getDescricao().isEmpty()) {
            throw new IllegalArgumentException("Preencha todos os campos");
        } else if (avisoJaCadastrado(aviso)) {
            throw new IllegalArgumentException("Aviso já cadastrado para essa data");
        }

        avisoRepository.save(aviso);
    }
    private Date parseData(String dataString) {
        if (dataString == null || dataString.isEmpty()) {
            throw new IllegalArgumentException("Data inválida. O campo data está vazio.");
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
            return format.parse(dataString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formato de data inválido.");
        }
    }


    private boolean avisoJaCadastrado(Aviso aviso) {
        LocalDate data = aviso.getData().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return avisoRepository.existsAviso(data);
    }
}
