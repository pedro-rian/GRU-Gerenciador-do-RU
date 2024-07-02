package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.model.Usuario;

public class UsuarioContext {
    private CadastroUsuarioStrategy cadastroUsuarioStrategy;

    public void setCadastroUsuarioStrategy(CadastroUsuarioStrategy cadastroUsuarioStrategy) {
        this.cadastroUsuarioStrategy = cadastroUsuarioStrategy;
    }

    public Usuario cadastrarUsuario(Usuario usuario) {
        return cadastroUsuarioStrategy.cadastrarUsuario(usuario);
    }
}
