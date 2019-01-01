package br.com.portaljc.jcconsultoria.domains;

public class Retorno {
    private boolean execucao;
    private String mensagemRetorno;

    public boolean isExecucao() {
        return execucao;
    }

    public void setExecucao(boolean execucao) {
        this.execucao = execucao;
    }

    public String getMensagemRetorno() {
        return mensagemRetorno;
    }

    public void setMensagemRetorno(String mensagemRetorno) {
        this.mensagemRetorno = mensagemRetorno;
    }
}
