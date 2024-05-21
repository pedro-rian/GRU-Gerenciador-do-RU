package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.dto.AssistenciaDTO;
import br.com.ufrn.imd.gru.model.Assistencia;
import br.com.ufrn.imd.gru.repository.AssistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AssistenciaService {

    private AssistenciaRepository assistenciaRepository;

    @Autowired
    public AssistenciaService(AssistenciaRepository assistenciaRepository){this.assistenciaRepository = assistenciaRepository;}

    public void cadastrarAssistencia(AssistenciaDTO assistenciaDTO){
        Assistencia assistencia = toEntity(assistenciaDTO);
        assistenciaRepository.save(assistencia);
    }

    public List<AssistenciaDTO> listarAssistenciasDaDataAtual(){
        LocalDate hoje = LocalDate.now();
        List<Assistencia> assistencias = assistenciaRepository.findByData(hoje);
        return assistencias.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<AssistenciaDTO> listarTodasAssistencias(){
        List<Assistencia> assistencias = assistenciaRepository.findAll();
        return assistencias.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public void validarCamposObrigatorios(String motivo,
                                           String descricao,
                                           String dataString,
                                           String horarioString) {
        if (Stream.of(motivo, descricao, dataString, horarioString).anyMatch(Objects::isNull) || descricao.isEmpty() || dataString.isEmpty() || horarioString.isEmpty()){
            throw new IllegalArgumentException("Preencha todos os campos obrigatórios.");
        }
    }

    public Date parseData(String dataString) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dataString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formato de data inválido.");
        }
    }

    public LocalTime parseHorario(String horarioString) {
        try {
            return LocalTime.parse(horarioString, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de horaŕio inválido.");
        }
    }

    public Assistencia toEntity(AssistenciaDTO assistenciaDTO) {
        validarCamposObrigatorios(assistenciaDTO.getMotivo(), assistenciaDTO.getDescricao(),
                assistenciaDTO.getData(), assistenciaDTO.getHorario());

        Date data = parseData(assistenciaDTO.getData());
        LocalTime horario = parseHorario(assistenciaDTO.getHorario());

        Assistencia assistencia = new Assistencia();

        assistencia.setMotivo(assistenciaDTO.getMotivo());
        assistencia.setDescricao(assistenciaDTO.getDescricao());
        assistencia.setData(data);
        assistencia.setHorario(horario);

        return assistencia;
    }

    public AssistenciaDTO toDTO(Assistencia assistencia){
        AssistenciaDTO assistenciaDTO = new AssistenciaDTO();
        assistenciaDTO.setMotivo(assistencia.getMotivo());
        assistenciaDTO.setDescricao(assistencia.getDescricao());
        assistenciaDTO.setData(assistencia.getData().toString());
        assistenciaDTO.setHorario(assistencia.getHorario().toString());
        return assistenciaDTO;
    }
}
