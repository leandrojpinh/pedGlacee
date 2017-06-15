package com.app.cg.pedglacee.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.cg.pedglacee.R;

/**
 * Created by LEANDRO on 10/06/2017.
 */

public class MenuCadastroAdapter extends ArrayAdapter<String> {

    private Context c;
    private String[] lDescricoes;
    private int [] idImagens;
    private LayoutInflater inflater;

    public MenuCadastroAdapter(Context context, String[] descricoes, int [] idImages) {
        super(context, R.layout.row_menu_cadastros, descricoes);

        this.c = context;
        this.lDescricoes = descricoes;
        this.idImagens = idImages;
    }

    public class Menu {
        TextView txtMenu;
        ImageView imgMenu;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_menu_cadastros, null);
        }

        Menu m = new Menu();
        m.txtMenu = (TextView) convertView.findViewById(R.id.txtMenu);
        m.imgMenu = (ImageView) convertView.findViewById(R.id.imgMenu);

        m.txtMenu.setText(lDescricoes[position]);
        m.imgMenu.setImageResource(idImagens[position]);

        return convertView;
    }
}
