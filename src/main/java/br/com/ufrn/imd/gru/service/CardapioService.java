package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.model.Cardapio;
import br.com.ufrn.imd.gru.repository.CardapioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

}
