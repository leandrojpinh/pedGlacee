package com.app.cg.pedglacee;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.app.cg.pedglacee.adaptadores.MesaAdapter;
import com.app.cg.pedglacee.adaptadores.MeusPedidosAdapter;
import com.app.cg.pedglacee.classes.BasePedido;
import com.app.cg.pedglacee.classes.Mesa;
import com.app.cg.pedglacee.conexao_network.Conexao;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MesasActivity extends AppCompatActivity {

    //private ListView lvMesas;
    //private SwipeMenuListView lvMeusPedidos;
    private MesaAdapter adapter;
    private List<Mesa> lMesas;
    private Mesa oMesa;
    private ListView lvMesas;
    private String sIdUser = "";
    private static final String TAG = "MesasActivity";
    String url = "", parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesas);

        sIdUser = getIntent().getStringExtra("idusuario").toString();

        InicializarComponentes();

        lMesas = new ArrayList<>();
        try {
            //Verificação da rede
            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            //se tiver conectado
            if(networkInfo != null && networkInfo.isConnected()) {
                //acessar a url abaixo
                url = "http://apppedglace.xyz/login/cadastro/mesas/lista_mesas.php";
                //com os parâmetros seguintes
                parametros = "usuario=" + sIdUser;
                //esse método vai nessa url
                new MesasActivity.SolicitaDados().execute(url);
            } else {
                Toast.makeText(getApplicationContext(), "Você não está conectado à rede", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        lvMesas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "Item selecionado " +view.getTag(), Toast.LENGTH_LONG).show();
                Mesa mesa = lMesas.get(position);

                if(mesa.getiQtdPessoas() == 4) {
                    Toast.makeText(getApplicationContext(), "A Messa: " + lMesas.get(position).getsMesa() + " está Cheia!", Toast.LENGTH_SHORT).show();
                } else {
                    if(mesa.getiQtdPessoas() >= 1)
                        Toast.makeText(getApplicationContext(), "A Messa: " + lMesas.get(position).getsMesa() + " contém " + mesa.getiQtdPessoas() + " pessoas!", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(getApplicationContext(), NovoPedidoActivity.class);
                    i.putExtra("edtMesa", lMesas.get(position).getsMesa());
                    startActivity(i);
                }
            }
        });
    }

    private void InicializarComponentes() {
        lvMesas = (ListView) findViewById(R.id.lvMesas);
    }

    private class SolicitaDados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            //aqui de fato ele vai na web; pela classe de conexao
            return Conexao.postDados(params[0], parametros);
        }

        @Override
        protected void onPostExecute(String resultado) {
            //depois de conectar, executar e trouxer o resultado da busca ele vai tratar abaixo
            if(resultado.length() > 0) {
                Toast.makeText(getApplicationContext(), "Carregando...", Toast.LENGTH_LONG).show();

                try {
                    JSONArray jArray = new JSONArray(resultado);
                    JSONObject jObj = null;

                    lMesas.clear();

                    //recebendo os dados da consulta
                    for(int i = 0; i < jArray.length(); i++) {
                        jObj = jArray.getJSONObject(i);

                        String cod = jObj.getString("codigo");
                        String id = jObj.getString("idmesas");
                        String qtdLugares = jObj.getString("qtd_lugares");

                        lMesas.add(new Mesa(cod, Integer.parseInt(id), Integer.parseInt(qtdLugares)));

                    }
                    if(lMesas.size() > 0) {
                        adapter = new MesaAdapter(getApplicationContext(), lMesas);
                        lvMesas.setAdapter(adapter);
                    } else
                        Toast.makeText(getApplicationContext(), "Nenhuma mesa cadastrada.", Toast.LENGTH_LONG).show();

                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "Erro ao listar Mesas.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
