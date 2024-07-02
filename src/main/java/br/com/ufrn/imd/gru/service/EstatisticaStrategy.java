package br.com.ufrn.imd.gru.service;

import java.util.List;
import java.util.Map;

public interface EstatisticaStrategy {
    public long totalUsuarios();
    public long totalArtefatos();
    public Map<String, Double> calcularMedias();
}
