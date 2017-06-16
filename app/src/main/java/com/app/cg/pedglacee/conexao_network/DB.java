package com.app.cg.pedglacee.conexao_network;

import com.app.cg.pedglacee.arquivos_default.DefaultClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * Created by LEANDRO on 15/06/2017.
 */

public class DB extends DefaultClass implements Runnable {

    private Connection conn;
    private String host = "localhost", db = "pedglace", user = "root", pass = "123456", url = "jdbc:postgresql://%s:%d/%s";
    private int port = 5432;

    public DB() {
        super();
        this.url = String.format(this.url, this.host, this.port, this.db);
        run();
        this.disconecta();

    }

    @Override
    public void run() {
        //TODO conectar
        try {
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager.getConnection(this.url, this.user, this.pass);
        } catch (Exception e) {
            this.mensagem = e.getMessage();
            this.status = false;
        }
    }

    private void disconecta() {
        if(this.conn != null) {
            try {
                this.conn.close();
            } catch (Exception e) {
                this.mensagem = e.getMessage();
                this.status = false;
            } finally {
                this.conn = null;
            }

        }
    }

    public ResultSet select(String query) {
        ResultSet rs = null;
        try {
            rs = new ExecuteDB(this.conn, query).execute().get();
        } catch (Exception ex) {
            this.status = false;
            this.mensagem = ex.getMessage();
        }
        return rs;
    }

    public ResultSet execute(String query) {
        ResultSet rs = null;
        try {
            rs = new ExecuteDB(this.conn, query).execute().get();
        } catch (Exception ex) {
            this.status = false;
            this.mensagem = ex.getMessage();
        }
        return rs;
    }
}
