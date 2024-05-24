package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.dto.CardapioDTO;
import br.com.ufrn.imd.gru.model.Cardapio;
import br.com.ufrn.imd.gru.model.TipoCardapio;
import br.com.ufrn.imd.gru.model.TipoRefeicao;
import br.com.ufrn.imd.gru.repository.CardapioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.time.ZoneId;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class CardapioService {
    private CardapioRepository cardapioRepository;

    @Autowired
    public CardapioService(CardapioRepository cardapioRepository) {
        this.cardapioRepository = cardapioRepository;
    }

    public List<Cardapio> getCardapiosAtuais(LocalDate dataAtual) {
        return cardapioRepository.findByData(dataAtual);
    }

    public List<Cardapio> getCardapiosConvencionais(LocalDate dataAtual) {
        return cardapioRepository.findByDataAndTipoCardapio(dataAtual, TipoCardapio.PRINCIPAL);
    }

    public List<Cardapio> getCardapiosVegetarianos(LocalDate dataAtual) {
        return cardapioRepository.findByDataAndTipoCardapio(dataAtual, TipoCardapio.VEGETARIANO);
    }

    public void cadastrarCardapio(CardapioDTO cardapioDTO) {
        validarCamposObrigatorios(cardapioDTO);

        Cardapio cardapio = convertToEntity(cardapioDTO);

        if (cardapioJaCadastrado(cardapio)) {
            throw new IllegalArgumentException("Já existe um cardápio cadastrado para esta data, tipo de cardápio e tipo de refeição.");
        }

        cardapioRepository.save(cardapio);
    }

    private void validarCamposObrigatorios(CardapioDTO cardapioDTO) {
        if (cardapioDTO.getTipoCardapio() == null || cardapioDTO.getTipoRefeicao() == null ||
                cardapioDTO.getPratoPrincipal() == null || cardapioDTO.getAcompanhamento() == null ||
                cardapioDTO.getData() == null) {
            throw new IllegalArgumentException("Todos os campos do cardápio são obrigatórios.");
        }
    }

    /*public void cadastrarCardapio(String tipoCardapioString,
                                  String tipoRefeicaoString,
                                  String pratoPrincipal,
                                  String acompanhamento,
                                  String dataString) {
        validarCamposObrigatorios(tipoCardapioString, tipoRefeicaoString, pratoPrincipal, acompanhamento, dataString);
        Date data = parseData(dataString);

        TipoCardapio tipoCardapio = TipoCardapio.valueOf(tipoCardapioString);
        TipoRefeicao tipoRefeicao = TipoRefeicao.valueOf(tipoRefeicaoString);

        Cardapio cardapio = new Cardapio();
        cardapio.setTipoCardapio(tipoCardapio);
        cardapio.setTipoRefeicao(tipoRefeicao);
        cardapio.setPratoPrincipal(pratoPrincipal);
        cardapio.setAcompanhamento(acompanhamento);
        cardapio.setData(data);

        if (cardapioJaCadastrado(cardapio)) {
            throw new IllegalArgumentException("Já existe um cardápio cadastrado para esta data, tipo de cardápio e tipo de refeição.");
        }

        cardapioRepository.save(cardapio);
    }

    private void validarCamposObrigatorios(String tipoCardapioString, String tipoRefeicaoString, String pratoPrincipal,
                                           String acompanhamento, String dataString) {
        if (Stream.of(tipoCardapioString, tipoRefeicaoString, pratoPrincipal, acompanhamento, dataString)
                .anyMatch(Objects::isNull) || dataString.isEmpty()) {
            throw new IllegalArgumentException("Todos os campos do cardápio são obrigatórios.");
        }
    }*/

    private Date parseData(String dataString) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dataString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formato de data inválido.");
        }
    }


    private boolean cardapioJaCadastrado(Cardapio cardapio) {
        LocalDate data = cardapio.getData().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return cardapioRepository.existsByDataAndTipoCardapioAndTipoRefeicao(
                data, cardapio.getTipoCardapio(), cardapio.getTipoRefeicao()
        );
    }

    public Cardapio findByCardapio(TipoCardapio tipoCardapio, TipoRefeicao tipoRefeicao, LocalDate date) {
        return cardapioRepository.findByTipoCardapioAndTipoRefeicaoAndData(tipoCardapio, tipoRefeicao, date);
    }

    public Cardapio findById(Long id) {
        return cardapioRepository.findById(id).orElse(null);
    }

    public List<CardapioDTO> getAllCardapios() {
        return cardapioRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public void atualizarCardapio(long id, CardapioDTO cardapioDTO) {
        validarCamposObrigatorios(cardapioDTO);

        Optional<Cardapio> cardapioOptional = cardapioRepository.findById(id);

        if (cardapioOptional.isPresent()) {
            Cardapio cardapio = cardapioOptional.get();
            cardapio.setTipoCardapio(cardapioDTO.getTipoCardapio());
            cardapio.setTipoRefeicao(cardapioDTO.getTipoRefeicao());
            cardapio.setPratoPrincipal(cardapioDTO.getPratoPrincipal());
            cardapio.setAcompanhamento(cardapioDTO.getAcompanhamento());
            cardapio.setData(cardapioDTO.getData());

            cardapioRepository.save(cardapio);
        } else {
            throw new IllegalArgumentException("Não existe cardápio cadastrado com esses dados para atualizar.");
        }
    }

    public List<Cardapio> getCardapios(){
        return cardapioRepository.findAll();
    }

    public void deleteById(long id) {
        Optional<Cardapio> cardapioOptional = cardapioRepository.findById(id);
        if (cardapioOptional.isPresent()) {
            cardapioRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Cardápio com o ID " + id + " não encontrado.");
        }
    }

    /*public void atualizarCardapio(long id, String tipoCardapioString,
                                  String tipoRefeicaoString,
                                  String pratoPrincipal,
                                  String acompanhamento,
                                  String dataString) {
        validarCamposObrigatorios(tipoCardapioString, tipoRefeicaoString, pratoPrincipal, acompanhamento, dataString);
        Date data = parseData(dataString);

        TipoCardapio tipoCardapio = TipoCardapio.valueOf(tipoCardapioString);
        TipoRefeicao tipoRefeicao = TipoRefeicao.valueOf(tipoRefeicaoString);

        Optional<Cardapio> cardapioOptional = cardapioRepository.findById(id);

        if (cardapioOptional.isPresent()) {
            Cardapio cardapio = cardapioOptional.get();
            cardapio.setTipoCardapio(tipoCardapio);
            cardapio.setTipoRefeicao(tipoRefeicao);
            cardapio.setPratoPrincipal(pratoPrincipal);
            cardapio.setAcompanhamento(acompanhamento);
            cardapio.setData(data);

            cardapioRepository.save(cardapio);
        } else{
            throw new IllegalArgumentException("Não existe cardápio cadastrado com esses dados para atualizar");
        }
    }*/

    private CardapioDTO convertToDTO(Cardapio cardapio) {
        CardapioDTO cardapioDTO = new CardapioDTO();
        cardapioDTO.setPratoPrincipal(cardapio.getPratoPrincipal());
        cardapioDTO.setAcompanhamento(cardapio.getAcompanhamento());
        cardapioDTO.setTipoRefeicao(cardapio.getTipoRefeicao());
        cardapioDTO.setTipoCardapio(cardapio.getTipoCardapio());
        cardapioDTO.setData(cardapio.getData());
        return cardapioDTO;
    }

    private Cardapio convertToEntity(CardapioDTO cardapioDTO) {
        Cardapio cardapio = new Cardapio();
        cardapio.setPratoPrincipal(cardapioDTO.getPratoPrincipal());
        cardapio.setAcompanhamento(cardapioDTO.getAcompanhamento());
        cardapio.setTipoRefeicao(cardapioDTO.getTipoRefeicao());
        cardapio.setTipoCardapio(cardapioDTO.getTipoCardapio());
        cardapio.setData(cardapioDTO.getData());
        return cardapio;
    }
}
