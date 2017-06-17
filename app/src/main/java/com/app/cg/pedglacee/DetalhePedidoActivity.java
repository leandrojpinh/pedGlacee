package com.app.cg.pedglacee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.cg.pedglacee.adaptadores.DetalhePedidoAdapter;
import com.app.cg.pedglacee.classes.ItensPedido;

import java.util.ArrayList;
import java.util.List;

public class DetalhePedidoActivity extends AppCompatActivity {

    private Intent i;
    private TextView txtPrato, txtComanda;
    private List<ItensPedido> lItens;
    private DetalhePedidoAdapter adapter;
    private ListView lvDetalhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_pedido);

        try {
            txtPrato = (TextView) findViewById(R.id.txtPrato);
            txtComanda = (TextView) findViewById(R.id.txtComanda);
            lvDetalhe = (ListView) findViewById(R.id.lvDetalhe);

            i = getIntent();

            String a = i.getStringExtra("prato");
            String b = i.getStringExtra("comanda");
            txtPrato.setText(a);
            txtComanda.setText(b);

            lItens = new ArrayList<>();
            lItens.add(new ItensPedido("Arroz", 1));
            lItens.add(new ItensPedido("Macarrão", 1));
            lItens.add(new ItensPedido("Feijão", 1));
            lItens.add(new ItensPedido("Farofa", 1));
            lItens.add(new ItensPedido("Salada", 1));

            adapter = new DetalhePedidoAdapter(getApplicationContext(), lItens);
            lvDetalhe.setAdapter(adapter);

            lvDetalhe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), lItens.get(position).getsDescricao(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
