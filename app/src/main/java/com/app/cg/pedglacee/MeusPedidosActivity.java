package com.app.cg.pedglacee;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.app.cg.pedglacee.adaptadores.MeusPedidosAdapter;
import com.app.cg.pedglacee.classes.BasePedido;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

public class MeusPedidosActivity extends AppCompatActivity {

    //private ListView lvMeusPedidos;
    private SwipeMenuListView lvMeusPedidos;
    private MeusPedidosAdapter adapter;
    private List<BasePedido> bBasePedido;
    private BasePedido pedido;
    private Intent iDetalhe;
    private static final String TAG = "MeusPedidosActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_pedidos);

        lvMeusPedidos = (SwipeMenuListView) findViewById(R.id.lvMeusPedidos);

        bBasePedido = new ArrayList<>();
        //pode adicionar de um banco tbm

        bBasePedido.add(new BasePedido("Assado de Panela", "00245", "001", "Em análise", 1));
        bBasePedido.add(new BasePedido("Moqueca de arraia", "00246", "001", "Em preparação", 2));
        bBasePedido.add(new BasePedido("Frango ao molho", "00247", "002", "Encaminhado", 3));
        bBasePedido.add(new BasePedido("Assado de Panela", "00248", "002", "Em análise", 4));
        bBasePedido.add(new BasePedido("Cupim ao forno", "00249", "002", "Entregue", 5));
        bBasePedido.add(new BasePedido("Cupim ao forno", "00250", "003", "Entregue", 6));
        bBasePedido.add(new BasePedido("Assado de panela", "00251", "003", "Entregue", 7));
        bBasePedido.add(new BasePedido("Bife ao molho", "00252", "004", "Encaminhado", 8));
        bBasePedido.add(new BasePedido("Moqueca de arraia", "00253", "004", "Encaminhado", 9));
        bBasePedido.add(new BasePedido("Moqueca de arraia", "00254", "004", "Encaminhado", 10));

        adapter = new MeusPedidosAdapter(getApplicationContext(), bBasePedido);
        lvMeusPedidos.setAdapter(adapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "preparacao" item
                SwipeMenuItem preparacaoItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                preparacaoItem.setBackground(new ColorDrawable(Color.rgb(0xF5, 0xAB, 0x35)));
                // set item width
                preparacaoItem.setWidth(170);
                // set item title
                //analiseItem.setTitle("Open");
                // set item title fontsize
                //analiseItem.setTitleSize(18);
                // set item title font color
                //analiseItem.setTitleColor(Color.WHITE);
                preparacaoItem.setIcon(R.drawable.ic_preparacao);
                // add to menu
                menu.addMenuItem(preparacaoItem);

                SwipeMenuItem encaminhadoItem = new SwipeMenuItem(getApplicationContext());
                encaminhadoItem.setBackground(new ColorDrawable(Color.rgb(0x22, 0xA7, 0xF0)));
                encaminhadoItem.setWidth(170);
                encaminhadoItem.setIcon(R.drawable.ic_encaminhado);
                menu.addMenuItem(encaminhadoItem);

                SwipeMenuItem entregueItem = new SwipeMenuItem(getApplicationContext());
                entregueItem.setBackground(new ColorDrawable(Color.rgb(0x2E, 0xCC, 0x71)));
                entregueItem.setWidth(170);
                entregueItem.setIcon(R.drawable.ic_entregue);
                menu.addMenuItem(entregueItem);

                /*// create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_phone);
                // add to menu
                menu.addMenuItem(deleteItem);*/
            }
        };

        lvMeusPedidos.setMenuCreator(creator);

        lvMeusPedidos.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // preparacao
                        Log.d(TAG, "onMenuItemClick: clicked item " + index);
                        pedido = bBasePedido.get(position);
                        pedido.setsStatus("Pedido em preparação");
                        bBasePedido.set(position, pedido);
                        adapter.notifyDataSetChanged();
                        lvMeusPedidos.setAdapter(adapter);
                        Toast.makeText(getApplicationContext(), "Pedido em preparação", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        // encaminhado
                        Log.d(TAG, "onMenuItemClick: clicked item " + index);
                        Toast.makeText(getApplicationContext(), "Pedido encaminhado", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        // entregue
                        Log.d(TAG, "onMenuItemClick: clicked item " + index);
                        Toast.makeText(getApplicationContext(), "Pedido entregue", Toast.LENGTH_SHORT).show();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

        lvMeusPedidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                iDetalhe = new Intent(getApplicationContext(), DetalhePedidoActivity.class);
                iDetalhe.putExtra("prato", bBasePedido.get(position).getsPrato());
                iDetalhe.putExtra("comanda", bBasePedido.get(position).getsComanda());
                startActivity(iDetalhe);
            }
        });
    }
}
