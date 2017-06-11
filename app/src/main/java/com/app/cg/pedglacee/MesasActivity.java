package com.app.cg.pedglacee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.app.cg.pedglacee.adaptadores.MesaAdapter;
import com.app.cg.pedglacee.classes.Mesa;

import java.util.ArrayList;
import java.util.List;

public class MesasActivity extends AppCompatActivity {

    //private ListView lvMesas;
    //private SwipeMenuListView lvMeusPedidos;
    private MesaAdapter adapter;
    private List<Mesa> lMesas;
    private Mesa oMesa;
    private ListView lvMesas;
    private static final String TAG = "MesasActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesas);

        lvMesas = (ListView) findViewById(R.id.lvMesas);

        lMesas = new ArrayList<>();
        //pode adicionar de um banco tbm

        lMesas.add(new Mesa("001", 1, 0));
        lMesas.add(new Mesa("002", 2, 0));
        lMesas.add(new Mesa("003", 3, 1));
        lMesas.add(new Mesa("004", 4, 4));
        lMesas.add(new Mesa("005", 5, 4));
        lMesas.add(new Mesa("006", 6, 2));
        lMesas.add(new Mesa("007", 7, 0));
        lMesas.add(new Mesa("008", 8, 0));
        lMesas.add(new Mesa("009", 9, 0));
        lMesas.add(new Mesa("010", 10, 4));

        adapter = new MesaAdapter(getApplicationContext(), lMesas);
        lvMesas.setAdapter(adapter);

        lvMesas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "Item selecionado " +view.getTag(), Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), NovoPedidoActivity.class);
                i.putExtra("edtMesa", lMesas.get(position).getsMesa());
                startActivity(i);
            }
        });
    }
}
