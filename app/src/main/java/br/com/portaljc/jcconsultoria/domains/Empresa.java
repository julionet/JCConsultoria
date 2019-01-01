package br.com.portaljc.jcconsultoria.domains;

import java.io.Serializable;

public class Empresa implements Serializable {

    private long id;
    private long codigoEmpresa;
    private String razaoSocial;
    private String nomeFantasia;
    private String cpfCnpj;
    private String endereco;
    private String bairro;
    private String telefone;
    private String email;
    private long idEmpresaPrincipal;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(long codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getIdEmpresaPrincipal() {
        return idEmpresaPrincipal;
    }

    public void setIdEmpresaPrincipal(long idEmpresaPrincipal) {
        this.idEmpresaPrincipal = idEmpresaPrincipal;
    }
}
