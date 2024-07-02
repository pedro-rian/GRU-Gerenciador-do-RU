package br.com.ufrn.imd.gru.service;

import java.util.Map;

public class EstatisticaContext {
    EstatisticaStrategy estatisticaStrategy;

    public void setEstatisticaStrategy(EstatisticaStrategy estatisticaStrategy) {
        this.estatisticaStrategy = estatisticaStrategy;
    }

    public long totalUsuarios() {
        return estatisticaStrategy.totalUsuarios();
    }

    public long totalArtefatos() {
        return estatisticaStrategy.totalArtefatos();
    }

    public Map<String, Double> calcularMedias() {
        return estatisticaStrategy.calcularMedias();
    }
}
