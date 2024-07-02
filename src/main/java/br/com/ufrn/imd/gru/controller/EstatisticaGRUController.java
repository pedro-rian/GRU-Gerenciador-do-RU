package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.service.EstatisticaBibliotecaEstrategy;
import br.com.ufrn.imd.gru.service.EstatisticaContext;
import br.com.ufrn.imd.gru.service.EstatisticaEventosStrategy;
import br.com.ufrn.imd.gru.service.EstatisticaGRUStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public class EstatisticaGRUController {
    @RestController
    @RequestMapping("/estatisticas")
    public class EstatisticaController {

        private EstatisticaContext estatisticaContext;

        @Autowired
        private EstatisticaGRUStrategy estatisticaGRUStrategy;

        @Autowired
        private EstatisticaEventosStrategy estatisticaEventoStrategy;

        @Autowired
        private EstatisticaBibliotecaEstrategy estatisticaBibliotecaStrategy;

        @PostMapping("/set-strategy")
        public void setStrategy(@RequestParam String tipoStrategy) {
            switch (tipoStrategy.toLowerCase()) {
                case "gru":
                    estatisticaContext.setEstatisticaStrategy(estatisticaGRUStrategy);
                    break;
                case "evento":
                    estatisticaContext.setEstatisticaStrategy(estatisticaEventoStrategy);
                    break;
                case "biblioteca":
                    estatisticaContext.setEstatisticaStrategy(estatisticaBibliotecaStrategy);
                    break;
                default:
                    throw new IllegalArgumentException("Strategy não reconhecido: " + tipoStrategy);
            }
        }

        @GetMapping("/total-usuarios")
        public long totalUsuarios() {
            return estatisticaContext.totalUsuarios();
        }

        @GetMapping("/total-artefatos")
        public long totalArtefatos() {
            return estatisticaContext.totalArtefatos();
        }

        @GetMapping("/medias")
        public Map<String, Double> calcularMedias() {
            return estatisticaContext.calcularMedias();
        }
    }

}
