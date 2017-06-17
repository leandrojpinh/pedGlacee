package com.app.cg.pedglacee.classes;

import java.util.ArrayList;

/**
 * Created by LEANDRO on 11/06/2017.
 */

public class Prato {
    private ArrayList<String> lPrato;

    public Prato() {
        this.lPrato = new ArrayList<>();
    }

    public void setsPrato(String sPrato) {
        if(!sPrato.equals(""))
            this.lPrato.add(sPrato);
    }

    public ArrayList<String> getPratos() {
        return lPrato;
    }
}
