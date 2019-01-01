package br.com.portaljc.jcconsultoria.domains;

public class SubResposta {

    private long id;
    private long idPergunta;
    private String descricaoSubResposta;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdPergunta() {
        return idPergunta;
    }

    public void setIdPergunta(long idPergunta) {
        this.idPergunta = idPergunta;
    }

    public String getDescricaoSubResposta() {
        return descricaoSubResposta;
    }

    public void setDescricaoSubResposta(String descricaoSubResposta) {
        this.descricaoSubResposta = descricaoSubResposta;
    }
}
