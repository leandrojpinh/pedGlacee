package com.app.cg.pedglacee;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.app.cg.pedglacee.adaptadores.MenuGarcomAdapter;

public class MenuActivity extends AppCompatActivity {

    private String[] lDesc = {"Novo Pedido", "Meus Pedidos"};
    private int [] lImgs = {R.drawable.ic_add, R.drawable.ic_action_name};
    private ListView lvMenu;
    private MenuGarcomAdapter mga;
    private String sIdUser = "";
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        sIdUser = getIntent().getStringExtra("idusuario").toString();
        InicializarComponentes();

        //Setando valores na lista
        mga = new MenuGarcomAdapter(this, lDesc, lImgs);

        //Passando os valores para a lista
        lvMenu.setAdapter(mga);

        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        i = new Intent(getApplicationContext(), MesasActivity.class);
                        i.putExtra("idusuario", sIdUser);
                        startActivity(i);
                        break;
                    case 1:
                        i = new Intent(getApplicationContext(), MeusPedidosActivity.class);
                        startActivity(i);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void InicializarComponentes() {
        lvMenu = (ListView) findViewById(R.id.lvMenu);
    }
}
