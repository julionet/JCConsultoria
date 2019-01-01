package br.com.portaljc.jcconsultoria.domains;

public class QuestionarioUsuario {

    private long idUsuario;
    private long idQuestionario;
    private boolean executa;
    private boolean visualiza;
    private boolean recebeEmail;

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdQuestionario() {
        return idQuestionario;
    }

    public void setIdQuestionario(long idQuestionario) {
        this.idQuestionario = idQuestionario;
    }

    public boolean isExecuta() {
        return executa;
    }

    public void setExecuta(boolean executa) {
        this.executa = executa;
    }

    public boolean isVisualiza() {
        return visualiza;
    }

    public void setVisualiza(boolean visualiza) {
        this.visualiza = visualiza;
    }

    public boolean isRecebeEmail() {
        return recebeEmail;
    }

    public void setRecebeEmail(boolean recebeEmail) {
        this.recebeEmail = recebeEmail;
    }
}
