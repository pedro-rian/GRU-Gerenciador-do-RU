package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.model.PessoaBiblioteca;
import br.com.ufrn.imd.gru.repository.LivroRepository;
import br.com.ufrn.imd.gru.repository.UsuarioBibliotecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EstatisticaBibliotecaService implements EstatisticaStrategy{
    @Autowired
    private UsuarioBibliotecaRepository usuarioBibliotecaRepository;
    @Autowired
    private LivroRepository livroRepository;

    public EstatisticaBibliotecaService(UsuarioBibliotecaRepository usuarioBibliotecaRepository, LivroRepository livroRepository) {
        this.usuarioBibliotecaRepository = usuarioBibliotecaRepository;
        this.livroRepository = livroRepository;
    }

    @Override
    public long calcularTotalUsuarios() {
        return usuarioBibliotecaRepository.count();
    }

    @Override
    public Map<String, Double> calcularTotalArtefatos() {
        Map<String, Double> map = new HashMap<>();
        map.put("Total de livros cadastrados", (double) livroRepository.count());
        return map;
    }

    @Override
    public Map<String, Double> calcularMedias() {
        Map<String, Double> medias = new HashMap<>();
        List<PessoaBiblioteca> usuarios = usuarioBibliotecaRepository.findAll();

        double qtdLivrosEmprestados = usuarios.stream().mapToInt(PessoaBiblioteca::getQtdLivrosEmprestados).sum();
        double mediaLivrosLidosPorUsuario = usuarios.stream().mapToDouble(PessoaBiblioteca::getQtdLivrosLidos).average().orElse(0.0);
        double mediaPaginasLidas = usuarios.stream().mapToDouble(PessoaBiblioteca::getQtdPaginasLidas).average().orElse(0.0);
        medias.put("Quantidade de livros emprestados:", qtdLivrosEmprestados);
        medias.put("Média de livros lidos por usuário:", mediaLivrosLidosPorUsuario);
        medias.put("Média de páginas lidas por usuário:", mediaPaginasLidas);

        return medias;
    }
}
