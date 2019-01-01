package br.com.portaljc.jcconsultoria.domains;

public class RetornoUsuario extends Retorno {
    private ListUsuario usuarios;

    public ListUsuario getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ListUsuario usuarios) {
        this.usuarios = usuarios;
    }
}
