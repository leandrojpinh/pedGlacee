package com.app.cg.pedglacee;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.cg.pedglacee.conexao_network.Conexao;

public class CadastrarBebidas extends AppCompatActivity {
    private Intent i;
    String url = "", parametros = "";
    private EditText descricao;
    private EditText valor;
    private Button salvar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_bebidas);

        InicializarComponentes();

        i = getIntent();

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verificação da rede
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if(networkInfo != null && networkInfo.isConnected()) {
                    String desc = descricao.getText().toString();
                    String vlr =  valor.getText().toString();

                    if(desc.isEmpty() || vlr.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Nenhum campo deve estar vazio.", Toast.LENGTH_LONG).show();
                    } else {
                        url = "http://apppedglace.xyz/login/cadastro/bebidas/cadastroBebidas.php";
                        parametros = "&descricao=" + desc + "&valor" + vlr;
                        new CadastrarBebidas.SolicitaDados().execute(url);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Você não está conectado à rede", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void InicializarComponentes() {
        descricao = (EditText) findViewById(R.id.etDescricao);
        valor = (EditText) findViewById(R.id.etValor);
        salvar = (Button) findViewById(R.id.btAdicionarBebidas);

    }

    private class SolicitaDados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            return Conexao.postDados(params[0], parametros);
        }

        @Override
        protected void onPostExecute(String resultado) {
            if(resultado.contains("cadastro_ok")) {
                Toast.makeText(getApplicationContext(), "Cadastro da Bebida realizado. [Novo Cadastro]", Toast.LENGTH_LONG).show();
                Intent i = new Intent(CadastrarBebidas.this, CadastrosActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), "Erro ao inserir Bebida.", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();

    }
}

