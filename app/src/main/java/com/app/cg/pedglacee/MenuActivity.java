package com.app.cg.pedglacee;

import android.app.ListActivity;
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
    private int [] lImgs = {R.drawable.ic_novo_pedido, R.drawable.ic_action_name};
    private ListView lvMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        lvMenu = (ListView) findViewById(R.id.lvMenu);
        MenuGarcomAdapter mga = new MenuGarcomAdapter(this, lDesc, lImgs);

        lvMenu.setAdapter(mga);

        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), lvMenu.getItemIdAtPosition(position) + "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
