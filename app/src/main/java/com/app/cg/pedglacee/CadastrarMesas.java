package com.app.cg.pedglacee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CadastrarMesas extends AppCompatActivity {

    private EditText codigoMesa;
    private EditText capacidade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_mesas);

        this.codigoMesa = (EditText) findViewById(R.id.etCodMesa);
        this.capacidade = (EditText) findViewById(R.id.etCapacidade);

    }
    public void salvar(View view){

    }
    public void limparCampo(View view){

    }
}
