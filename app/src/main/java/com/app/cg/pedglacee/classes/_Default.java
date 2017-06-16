package com.app.cg.pedglacee.classes;

/**
 * Created by Adriano on 15/06/2017.
 */

public class _Default {
    protected String _mensagem;
    protected  boolean _status;

    public String get_mensagem() {
        return _mensagem;
    }

    public void set_mensagem(String _mensagem) {
        this._mensagem = _mensagem;
    }

    public boolean is_status() {
        return _status;
    }

    public void set_status(boolean _status) {
        this._status = _status;
    }

    public _Default() {
        this._status = true;

        this._mensagem = "";
    }
}
