package com.app.cg.pedglacee.classes;

/**
 * Created by LEANDRO on 13/06/2017.
 */

public class ItensPedido {

    private String sDescricao;
    private int iQuantidade;

    public ItensPedido(String sDescricao, int iQuantidade) {

        this.sDescricao = sDescricao;
        this.iQuantidade = iQuantidade;
    }

    public String getsDescricao() {
        return sDescricao;
    }

    public void setsDescricao(String sDescricao) {
        this.sDescricao = sDescricao;
    }

    public int getiQuantidade() {
        return iQuantidade;
    }

    public void setiQuantidade(int iQuantidade) {
        this.iQuantidade = iQuantidade;
    }
}
