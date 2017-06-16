package com.app.cg.pedglacee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.cg.pedglacee.classes.Refeicao;

public class CadastroRefeicao extends AppCompatActivity {
    private Refeicao refeicao;
    private EditText descricao;
    private EditText tipoProduto;
    private EditText valor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_refeicao);

        this.refeicao = new Refeicao();
        this.descricao = (EditText) findViewById(R.id.etDescricao);
        this.tipoProduto = (EditText) findViewById(R.id.etTipoProduto);
        this.valor = (EditText) findViewById(R.id.etValor);

        Intent intent = getIntent();
        if (intent != null){
            Bundle bundle = intent.getExtras();
            if (bundle != null){
                this.refeicao.setId(bundle.getInt("id"));
                this.descricao.setText(bundle.getString("descricao"));
                this.tipoProduto.setText(bundle.getString("tipoproduto"));
                this.valor.setText(bundle.getString("valor"));
            }
        }
    }
    public void adicionar(View view){
        this.refeicao.setDescricao(this.descricao.getText().toString());
        this.refeicao.setTipoProduto(this.tipoProduto.getText().toString());
        this.refeicao.setValor(Double.parseDouble(valor.getText().toString()));
        refeicao.adicionar();

        Toast.makeText(this,this.refeicao.get_mensagem(),Toast.LENGTH_LONG).show();
        if (refeicao.is_status())
            finish();
    }
    public void limpar(View view){

    }
}
