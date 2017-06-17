package com.app.cg.pedglacee;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.cg.pedglacee.conexao_network.Conexao;

public class CadastrarMesas extends AppCompatActivity {


    private Intent i;
    private String sIdUsuario = "";
    String url = "", parametros = "";
    private EditText codigoMesa;
    private EditText capacidade;
    private Button salvar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_mesas);

        InicializarComponentes();

        i = getIntent();
        codigoMesa.setText(i.getStringExtra("codigoMesa"));
        sIdUsuario = i.getStringExtra("idusuario");

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verificação da rede
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if(networkInfo != null && networkInfo.isConnected()) {
                    String codigo = codigoMesa.getText().toString();
                    String capacidadePessoas =  capacidade.getText().toString();

                    if(codigo.isEmpty() || capacidadePessoas.isEmpty() ) {
                        Toast.makeText(getApplicationContext(), "Nenhum campo deve estar vazio.", Toast.LENGTH_LONG).show();
                    } else {
                        url = "http://apppedglace.xyz/login/cadastro/mesas/novaMesa.php";
                        parametros = "usuario=" + sIdUsuario + "&codigoMesa=" + codigo + "&capacidade=" + capacidadePessoas;
                        new NovoPedidoActivity.SolicitaDados().execute(url);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Você não está conectado à rede", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void InicializarComponentes() {
        codigoMesa = (EditText) findViewById(R.id.etCodMesa);
        capacidade = (EditText) findViewById(R.id.etCapacidade);
        salvar = (Button) findViewById(R.id.btSalvar);

    }

    private class SolicitaDados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            return Conexao.postDados(params[0], parametros);
        }

        @Override
        protected void onPostExecute(String resultado) {
            if(resultado.contains("pedido_ok")) {
                Toast.makeText(getApplicationContext(), "Pedido realizado. [Novo Pedido]", Toast.LENGTH_LONG).show();
                Intent i = new Intent(NovoPedidoActivity.this, MeusPedidosActivity.class);
                i.putExtra("idusuario", sIdUsuario);
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), "Erro ao inserir pedido.", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();

    }
}
