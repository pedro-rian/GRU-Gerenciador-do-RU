package br.com.ufrn.imd.gru.service;

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
    public void cadastrarCardapio(String tipoCardapioString, String tipoRefeicaoString, String pratoPrincipal,
                                  String acompanhamento, String dataString) {
        Date data = null;
        try {
            data = new SimpleDateFormat("yyyy-MM-dd").parse(dataString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        validarCardapio(tipoCardapioString, tipoRefeicaoString, pratoPrincipal, acompanhamento, data);


    }

    public void validarCardapio(String tipoCardapioString, String tipoRefeicaoString, String pratoPrincipal,
                                String acompanhamento, Date data) {
        if (tipoCardapioString == null || tipoRefeicaoString == null ||
                pratoPrincipal == null || acompanhamento == null || data == null) {
            throw new IllegalArgumentException("Todos os campos do cardápio são obrigatórios.");
        }

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
        salvarCardapio(cardapio);
    }

    public boolean cardapioJaCadastrado(Cardapio cardapio) {
        LocalDate data = cardapio.getData().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return cardapioRepository.existsByDataAndTipoCardapioAndTipoRefeicao(
                data, cardapio.getTipoCardapio(), cardapio.getTipoRefeicao()
        );
    }

    public void salvarCardapio(Cardapio cardapio) {

        if (cardapioJaCadastrado(cardapio)) {
            throw new IllegalArgumentException("Já existe um cardápio cadastrado para esta data, tipo de cardápio e tipo de refeição.");
        }

        cardapioRepository.save(cardapio);
    }


}
