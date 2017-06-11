package com.app.cg.pedglacee.classes;

/**
 * Created by LEANDRO on 11/06/2017.
 */

public class BasePedido {

    private String sPrato;
    private String sComanda;
    private String sMesa;
    private String sStatus;
    private int iId;

    public BasePedido(String sPrato, String sComanda, String sMesa, String sStatus, int iId) {
        this.sPrato = sPrato;
        this.sComanda = sComanda;
        this.sMesa = sMesa;
        this.sStatus = sStatus;
        this.iId = iId;
    }

    public String getsPrato() {
        return sPrato;
    }

    public void setsPrato(String sPrato) {
        this.sPrato = sPrato;
    }

    public String getsComanda() {
        return sComanda;
    }

    public void setsComanda(String sComanda) {
        this.sComanda = sComanda;
    }

    public String getsMesa() {
        return sMesa;
    }

    public void setsMesa(String sMesa) {
        this.sMesa = sMesa;
    }

    public String getsStatus() {
        return sStatus;
    }

    public void setsStatus(String sStatus) {
        this.sStatus = sStatus;
    }

    public int getiId() {
        return iId;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }
}
