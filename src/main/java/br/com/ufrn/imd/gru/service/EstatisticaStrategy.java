package br.com.ufrn.imd.gru.service;

import java.util.Map;

public interface EstatisticaStrategy {
    long calcularTotalUsuarios();
    Map<String, Double> calcularTotalArtefatos();
    Map<String, Double> calcularMedias();
}
