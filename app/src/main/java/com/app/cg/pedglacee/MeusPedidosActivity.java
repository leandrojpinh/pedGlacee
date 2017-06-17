package com.app.cg.pedglacee;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.app.cg.pedglacee.adaptadores.MeusPedidosAdapter;
import com.app.cg.pedglacee.classes.BasePedido;
import com.app.cg.pedglacee.conexao_network.Conexao;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MeusPedidosActivity extends AppCompatActivity {

    //private ListView lvMeusPedidos;
    private SwipeMenuListView lvMeusPedidos;
    private MeusPedidosAdapter adapter;
    private List<BasePedido> bBasePedido;
    private String data;
    private BasePedido pedido;
    private Intent iDetalhe;
    private static final String TAG = "MeusPedidosActivity";
    String url = "", parametros = "", sIdUsuario = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_pedidos);

        sIdUsuario = getIntent().getStringExtra("idusuario").toString();
        lvMeusPedidos = (SwipeMenuListView) findViewById(R.id.lvMeusPedidos);
        bBasePedido = new ArrayList<>();
        try {
            //Verificação da rede
            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            if(networkInfo != null && networkInfo.isConnected()) {
                url = "http://apppedglace.xyz/login/cadastro/pedido/lista_pedidos.php";
                parametros = "usuario=" + sIdUsuario;
                new MeusPedidosActivity.SolicitaDados().execute(url);
            } else {
                Toast.makeText(getApplicationContext(), "Você não está conectado à rede", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem verComanda = new SwipeMenuItem(getApplicationContext());
                verComanda.setBackground(new ColorDrawable(Color.rgb(0x22, 0xA7, 0xF0)));
                verComanda.setWidth(150);
                verComanda.setIcon(R.drawable.ic_ver_comanda);
                menu.addMenuItem(verComanda);

                SwipeMenuItem encaminhadoItem = new SwipeMenuItem(getApplicationContext());
                encaminhadoItem.setBackground(new ColorDrawable(Color.rgb(0xCF, 0x00, 0x0F)));
                encaminhadoItem.setWidth(150);
                encaminhadoItem.setIcon(R.drawable.ic_delete);
                menu.addMenuItem(encaminhadoItem);
            }
        };

        lvMeusPedidos.setMenuCreator(creator);

        lvMeusPedidos.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // preparacao
                        iDetalhe = new Intent(getApplicationContext(), DetalhePedidoActivity.class);
                        iDetalhe.putExtra("prato", bBasePedido.get(position).getsPrato());
                        iDetalhe.putExtra("comanda", bBasePedido.get(position).getsComanda());
                        startActivity(iDetalhe);
                        break;
                    case 1:
                        // encaminhado
                        Log.d(TAG, "onMenuItemClick: clicked item " + index);
                        Toast.makeText(getApplicationContext(), "Removendo pedido...", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }

    private class SolicitaDados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            return Conexao.postDados(params[0], parametros);
        }

        @Override
        protected void onPostExecute(String resultado) {
            if(resultado.length() > 0) {
                Toast.makeText(getApplicationContext(), "Carregando...", Toast.LENGTH_LONG).show();

                try {
                JSONArray jArray = new JSONArray(resultado);
                JSONObject jObj = null;

                bBasePedido.clear();

                for(int i = 0; i < jArray.length(); i++) {
                    jObj = jArray.getJSONObject(i);

                    String comanda = jObj.getString("idpedidos");
                    String refeicao = jObj.getString("descricao");
                    String mesa = jObj.getString("codigo");

                    //N -> Novo Pedido | E -> Em preparação | P -> Pronto | C -> Concluído
                    String status = jObj.getString("status");
                    if(status.equals("N")) status = "Novo Pedido";
                    else if(status.equals("E")) status = "Em Preparação";
                    else if(status.equals("P")) status = "Pronto";
                    else if(status.equals("C")) status = "Concluído";

                    bBasePedido.add(new BasePedido(refeicao, comanda, mesa, status, Integer.parseInt(comanda)));

                }
                if(bBasePedido.size() > 0) {
                    adapter = new MeusPedidosAdapter(getApplicationContext(), bBasePedido);
                    lvMeusPedidos.setAdapter(adapter);
                } else
                    Toast.makeText(getApplicationContext(), "Nenhum pedido encontrado.", Toast.LENGTH_LONG).show();

                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "Erro ao listar Pedidos.", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}