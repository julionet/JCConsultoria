package br.com.portaljc.jcconsultoria;

import br.com.portaljc.jcconsultoria.domains.Usuario;

public class GlobalSingleton {

    private static volatile GlobalSingleton _instance;

    private GlobalSingleton() { }

    public static GlobalSingleton get_instance() {
        if (_instance == null) {
            synchronized (GlobalSingleton.class) {
                if (_instance == null) {
                    _instance = new GlobalSingleton();
                }
            }
        }
        return  _instance;
    }

    private Usuario usuarioLogado = null;

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuario) {
        usuarioLogado = usuario;
    }
}
