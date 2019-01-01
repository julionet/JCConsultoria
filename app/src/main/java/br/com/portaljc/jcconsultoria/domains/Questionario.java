package br.com.portaljc.jcconsultoria.domains;

import java.io.Serializable;
import java.util.Date;

public class Questionario implements Serializable {

    private long id;
    private String nomeQuestionario;
    private int statusQuestionario;
    private int tipoQuestionario;
    private Date dataUltimaAtualizacao;
    private PerguntasQuestionario[] listPerguntas;
    private Long[] listEmpresas;
    private QuestionarioUsuario[] listUsuarios;

    private String tipoQuestionarioDescricao;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeQuestionario() {
        return nomeQuestionario;
    }

    public void setNomeQuestionario(String nomeQuestionario) {
        this.nomeQuestionario = nomeQuestionario;
    }

    public int getStatusQuestionario() {
        return statusQuestionario;
    }

    public void setStatusQuestionario(int statusQuestionario) {
        this.statusQuestionario = statusQuestionario;
    }

    public int getTipoQuestionario() {
        return tipoQuestionario;
    }

    public void setTipoQuestionario(int tipoQuestionario) {
        this.tipoQuestionario = tipoQuestionario;
    }

    public Date getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    public PerguntasQuestionario[] getListPerguntas() {
        return listPerguntas;
    }

    public void setListPerguntas(PerguntasQuestionario[] listPerguntas) {
        this.listPerguntas = listPerguntas;
    }

    public Long[] getListEmpresas() {
        return listEmpresas;
    }

    public void setListEmpresas(Long[] listEmpresas) {
        this.listEmpresas = listEmpresas;
    }

    public QuestionarioUsuario[] getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(QuestionarioUsuario[] listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public String getTipoQuestionarioDescricao() {
        return tipoQuestionarioDescricao;
    }

    public void setTipoQuestionarioDescricao(String tipoQuestionarioDescricao) {
        this.tipoQuestionarioDescricao = tipoQuestionarioDescricao;
    }
}
