package com.app.cg.pedglacee.conexao_network;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Created by LEANDRO on 15/06/2017.
 */

public class ExecuteDB extends AsyncTask<String, Void, ResultSet> {

    private Connection connection;
    private String query;

    public ExecuteDB(Connection connection, String query) {
        this.connection = connection;
        this.query = query;
    }

    @Override
    protected ResultSet doInBackground(String... params) {
        ResultSet rs = null;
        try {
            rs = connection.prepareStatement(query).executeQuery();
        } catch (Exception ex) {

        } finally {
            try {
                connection.close();
            } catch (Exception ex) {

            }
        }
        return rs;
    }
}
