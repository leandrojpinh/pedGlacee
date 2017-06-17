package com.app.cg.pedglacee;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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

import com.app.cg.pedglacee.classes.Prato;
import com.app.cg.pedglacee.conexao_network.Conexao;

import java.util.ArrayList;

public class NovoPedidoActivity extends AppCompatActivity {

    private Intent i;
    private EditText edtMesa;
    private Spinner spnPrato, spnSuco;
    private Button btnPedir;
    private TextView txtValor;
    private String sIdUsuario = "";
    String url = "", parametros = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_pedido);

        InicializarComponentes();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.pratos));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnPrato.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.sucos));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnSuco.setAdapter(adapter2);
        //Receber valores da listView
        i = getIntent();
        edtMesa.setText(i.getStringExtra("idmesa"));
        sIdUsuario = i.getStringExtra("idusuario");

        btnPedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verificação da rede
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if(networkInfo != null && networkInfo.isConnected()) {
                    String mesa = edtMesa.getText().toString();
                    String prato = 1+"";//spnPrato.getSelectedItem().toString();
                    //String suco = spnSuco.getSelectedItem().toString();
                    String valor = txtValor.getText().toString();

                    if(mesa.isEmpty() || prato.isEmpty()|| valor.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Nenhum campo deve estar vazio.", Toast.LENGTH_LONG).show();
                    } else {
                        url = "http://apppedglace.xyz/login/cadastro/pedido/novo_pedido.php";
                        parametros = "usuario=" + sIdUsuario + "&mesa=" + mesa + "&prato=" + prato + "&valor=" + valor;
                        new NovoPedidoActivity.SolicitaDados().execute(url);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Você não está conectado à rede", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void InicializarComponentes() {
        edtMesa = (EditText) findViewById(R.id.edtMesa);
        spnPrato = (Spinner) findViewById(R.id.spnPrato);
        spnSuco = (Spinner) findViewById(R.id.spnSuco);
        btnPedir = (Button) findViewById(R.id.btnPedir);
        txtValor = (TextView) findViewById(R.id.txtValor);
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
