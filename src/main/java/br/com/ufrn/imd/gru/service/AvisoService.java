package br.com.ufrn.imd.gru.service;

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

@Service
public class AvisoService {

    @Autowired
    private AvisoRepository avisoRepository;

    public Aviso getAvisoDoDia(LocalDate dataAtual) {
        return avisoRepository.findByData(dataAtual);
    }
    public void cadastrarAviso(String titulo, String descricao, String dataString){

        Date data = parseData(dataString);


        Aviso aviso = new Aviso();
        aviso.setTitulo(titulo);
        aviso.setDescricao(descricao);
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
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dataString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formato de data inválido.");
        }
    }

    private boolean avisoJaCadastrado(Aviso aviso) {
        LocalDate data = aviso.getData().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return avisoRepository.existsAviso(data);
    }
}
