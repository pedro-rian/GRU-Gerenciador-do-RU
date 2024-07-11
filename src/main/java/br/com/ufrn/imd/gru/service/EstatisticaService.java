package br.com.ufrn.imd.gru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EstatisticaService {

    private final EstatisticaGRUService estatisticaGRUService;
    private final EstatisticaEventoService estatisticaEventoService;
    private final EstatisticaBibliotecaService estatisticaBibliotecaService;

    private EstatisticaStrategy estrategia;

    @Autowired
    public EstatisticaService(EstatisticaGRUService estatisticaGRUService,
                              EstatisticaEventoService estatisticaEventoService,
                              EstatisticaBibliotecaService estatisticaBibliotecaService) {
        this.estatisticaGRUService = estatisticaGRUService;
        this.estatisticaEventoService = estatisticaEventoService;
        this.estatisticaBibliotecaService = estatisticaBibliotecaService;
    }

    public void setEstrategia(String tipoSistema) {
        switch (tipoSistema) {
            case "GRU":
                this.estrategia = estatisticaGRUService;
                break;
            case "EVE":
                this.estrategia = estatisticaEventoService;
                break;
            case "BIB":
                this.estrategia = estatisticaBibliotecaService;
                break;
            default:
                throw new IllegalArgumentException("Tipo de sistema desconhecido: " + tipoSistema);
        }
    }

    public long calcularTotalUsuarios(){
        return estrategia.calcularTotalUsuarios();
    }
    public Map<String, Double> calcularTotalArtefatos(){
        return estrategia.calcularTotalArtefatos();
    }
    public Map<String, Double> calcularMedias(){
        return estrategia.calcularMedias();
    }
}
