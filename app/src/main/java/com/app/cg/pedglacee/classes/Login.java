package com.app.cg.pedglacee.classes;

import android.content.Intent;

import com.app.cg.pedglacee.arquivos_default.DefaultClass;
import com.app.cg.pedglacee.conexao_network.DB;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by LEANDRO on 15/06/2017.
 */

public class Login extends DefaultClass {

    private int id;
    private String usuario, login, senha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Login() {
        super();
        this.id = -1;
        this.usuario = "";
        this.login = "";
        this.senha = "";
    }

    public Boolean Logar() {
        DB db = new DB();
        try {
            ResultSet rs = db.execute("SELECT * FROM usuario where login = '" + getLogin() + "' AND senha = '" + getSenha() + "'");
            if(rs != null)
                return true;
        } catch (Exception ex) {
            this.mensagem = ex.getMessage();
            this.status = false;
        }
        return false;
    }

    public ArrayList<Login> getListaLogin() {
        DB db = new DB();
        ArrayList<Login> lista = new ArrayList<>();

        try {
            ResultSet rs = db.execute("SELECT * FROM usuario");
            if(rs != null) {
                while (rs.next()) {
                    Login obj = new Login();
                    obj.setId(rs.getInt("id"));
                    obj.setUsuario(rs.getString("usuario"));
                    obj.setLogin(rs.getString("login"));
                    obj.setSenha(rs.getString("senha"));
                    lista.add(obj);
                    obj = null;
                }
            }
        } catch (Exception ex) {
            this.mensagem = ex.getMessage();
            this.status = false;
        }
        return lista;
    }

    public void Salvar() {
        String comando = "";

        if(this.getId() == -1) {
            comando = String.format("INSERT INTO usuario (usuario, login, senha) values ('%s', '%s', '%s')", this.getUsuario(), this.getLogin(), this.getSenha());
        } else {
            comando = String.format("UPDATE usuario SET usuario = '%s', login  = '%s', senha = '%s' where id =  = '%d'", this.getUsuario(), this.getLogin(), this.getSenha(), this.getId());
        }

        DB db = new DB();
        db.execute(comando);
        this.mensagem = db.getMensagem();
        this.status = db.getStatus();
    }

    public void Remover() {
        String comando = String.format("DELETE FROM usuario where id =  = '%d'", this.getId());

        DB db = new DB();
        db.execute(comando);
        this.mensagem = db.getMensagem();
        this.status = db.getStatus();
    }

}
