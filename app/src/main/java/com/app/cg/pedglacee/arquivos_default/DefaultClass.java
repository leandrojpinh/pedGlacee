package com.app.cg.pedglacee.arquivos_default;

/**
 * Created by LEANDRO on 15/06/2017.
 */

public class DefaultClass {

    protected String mensagem;
    protected boolean status;

    public DefaultClass() {
        this.mensagem = "";
        this.status = true;
    }

    public String getMensagem() {
        return mensagem;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
