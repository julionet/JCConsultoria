package br.com.portaljc.jcconsultoria.domains;

import java.util.Date;

public class Usuario {
    private long id;
    private String login;
    private String nome;
    private String senha;
    private boolean controlaValidade;
    private int diasExpiracaoCadastro;
    private Date dataExpiracaoCadastro;
    private Date dataUltimaAtualizacao;
    private String email;
    private long idEmpresaUsuario;
    private boolean usuarioAdmin;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public boolean isControlaValidade() {
        return controlaValidade;
    }
    public void setControlaValidade(boolean controlaValidade) {
        this.controlaValidade = controlaValidade;
    }
    public int getDiasExpiracaoCadastro() {
        return diasExpiracaoCadastro;
    }
    public void setDiasExpiracaoCadastro(int diasExpiracaoCadastro) {
        this.diasExpiracaoCadastro = diasExpiracaoCadastro;
    }
    public Date getDataExpiracaoCadastro() {
        return dataExpiracaoCadastro;
    }
    public void setDataExpiracaoCadastro(Date dataExpiracaoCadastro) {
        this.dataExpiracaoCadastro = dataExpiracaoCadastro;
    }
    public Date getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }
    public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public long getIdEmpresaUsuario() {
        return idEmpresaUsuario;
    }
    public void setIdEmpresaUsuario(long idEmpresaUsuario) {
        this.idEmpresaUsuario = idEmpresaUsuario;
    }
    public boolean isUsuarioAdmin() {
        return usuarioAdmin;
    }
    public void setUsuarioAdmin(boolean usuarioAdmin) {
        this.usuarioAdmin = usuarioAdmin;
    }
}
