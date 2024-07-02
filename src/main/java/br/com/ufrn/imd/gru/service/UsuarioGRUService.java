package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.model.Usuario;
import br.com.ufrn.imd.gru.model.UsuarioGRU;
import br.com.ufrn.imd.gru.repository.UsuarioGRURepository;

public class UsuarioGRUService implements CadastroUsuarioStrategy{

    UsuarioGRURepository usuarioGRURepository;

    @Override
    public Usuario cadastrarUsuario(Usuario usuario) {
        if (usuario instanceof UsuarioGRU) {
            return usuarioGRURepository.save((UsuarioGRU) usuario);
        } else {
            throw new IllegalArgumentException("Tipo de usuário inválido para o sistema");
        }
    }
}
