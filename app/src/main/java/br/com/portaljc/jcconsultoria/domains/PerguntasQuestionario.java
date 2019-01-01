package br.com.portaljc.jcconsultoria.domains;

public class PerguntasQuestionario {

    private long id;
    private long idQuestionario;
    private int codigoSetor;
    private String descricaoPergunta;
    private int tipoPergunta;
    private boolean utilizaSubresposta;
    private int statusPergunta;
    private boolean capturaFoto;
    private boolean capturaTexto;
    private SubResposta[] listSubResposta;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdQuestionario() {
        return idQuestionario;
    }

    public void setIdQuestionario(long idQuestionario) {
        this.idQuestionario = idQuestionario;
    }

    public int getCodigoSetor() {
        return codigoSetor;
    }

    public void setCodigoSetor(int codigoSetor) {
        this.codigoSetor = codigoSetor;
    }

    public String getDescricaoPergunta() {
        return descricaoPergunta;
    }

    public void setDescricaoPergunta(String descricaoPergunta) {
        this.descricaoPergunta = descricaoPergunta;
    }

    public int getTipoPergunta() {
        return tipoPergunta;
    }

    public void setTipoPergunta(int tipoPergunta) {
        this.tipoPergunta = tipoPergunta;
    }

    public boolean isUtilizaSubresposta() {
        return utilizaSubresposta;
    }

    public void setUtilizaSubresposta(boolean utilizaSubresposta) {
        this.utilizaSubresposta = utilizaSubresposta;
    }

    public int getStatusPergunta() {
        return statusPergunta;
    }

    public void setStatusPergunta(int statusPergunta) {
        this.statusPergunta = statusPergunta;
    }

    public boolean isCapturaFoto() {
        return capturaFoto;
    }

    public void setCapturaFoto(boolean capturaFoto) {
        this.capturaFoto = capturaFoto;
    }

    public boolean isCapturaTexto() {
        return capturaTexto;
    }

    public void setCapturaTexto(boolean capturaTexto) {
        this.capturaTexto = capturaTexto;
    }

    public SubResposta[] getListSubResposta() {
        return listSubResposta;
    }

    public void setListSubResposta(SubResposta[] listSubResposta) {
        this.listSubResposta = listSubResposta;
    }
}
