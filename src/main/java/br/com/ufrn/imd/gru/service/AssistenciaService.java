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

    private void validarCamposObrigatorios(String motivo,
                                           String descricao,
                                           String dataString,
                                           String horarioString) {
        if (Stream.of(motivo, descricao, dataString, horarioString).anyMatch(Objects::isNull) || descricao.isEmpty() || dataString.isEmpty() || horarioString.isEmpty()){
            throw new IllegalArgumentException("Preencha todos os campos obrigatórios.");
        }
    }

    private Date parseData(String dataString) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dataString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formato de data inválido.");
        }
    }

    private LocalTime parseHorario(String horarioString) {
        try {
            return LocalTime.parse(horarioString, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de horaŕio inválido.");
        }
    }

    private Assistencia toEntity(AssistenciaDTO assistenciaDTO) {
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
}
