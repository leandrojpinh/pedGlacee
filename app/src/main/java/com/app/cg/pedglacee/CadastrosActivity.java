package com.app.cg.pedglacee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.app.cg.pedglacee.adaptadores.MenuCadastroAdapter;
import com.app.cg.pedglacee.adaptadores.MenuGarcomAdapter;

public class CadastrosActivity extends AppCompatActivity {

    private String[] lDesc = {"Refeições", "Itens", "Lanches", "Bebidas", "Mesa"};
    private int [] lImgs = {R.drawable.ic_add, R.drawable.ic_action_name,
            R.drawable.ic_add,R.drawable.ic_add,R.drawable.ic_add};
    private ListView lvMenu;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastros);
        lvMenu = (ListView) findViewById(R.id.lvCadastros);
        MenuCadastroAdapter mca = new MenuCadastroAdapter(this, lDesc, lImgs);

        lvMenu.setAdapter(mca);

        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        i = new Intent(getApplicationContext(), CadastroRefeicao.class);
                        startActivity(i);
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
