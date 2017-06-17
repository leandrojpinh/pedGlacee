package com.app.cg.pedglacee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.cg.pedglacee.classes.Produtos;

public class CadastroProdutos extends AppCompatActivity {
    private EditText descricao;
    private EditText tipoProduto;
    private EditText valor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produtos);

        this.descricao = (EditText) findViewById(R.id.etDescricao);
        this.tipoProduto = (EditText) findViewById(R.id.etTipoProduto);
        this.valor = (EditText) findViewById(R.id.etValor);


    }
    public void adicionar(View view){

    }
    public void limpar(View view){

    }
}
