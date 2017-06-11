package com.app.cg.pedglacee.adaptadores;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.cg.pedglacee.R;
import com.app.cg.pedglacee.classes.Mesa;

import java.util.List;

/**
 * Created by LEANDRO on 11/06/2017.
 */

public class MesaAdapter extends BaseAdapter {

    private Context cContexto;
    private List<Mesa> lMesas;

    public MesaAdapter(Context cContexto, List<Mesa> lMesas) {
        this.cContexto = cContexto;
        this.lMesas = lMesas;
    }

    @Override
    public int getCount() {
        return lMesas.size();
    }

    @Override
    public Object getItem(int position) {
        return lMesas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(cContexto, R.layout.row_mesas, null);
        TextView txtMesa = (TextView)v.findViewById(R.id.txtMesa);

        //setando os txts
        txtMesa.setText(lMesas.get(position).getsMesa());

        v.setTag(lMesas.get(position).getiId());

        int qtdPessoas = lMesas.get(position).getiQtdPessoas();

        if(qtdPessoas <= 0) {
            v.setBackgroundColor(Color.GREEN);
        }else if(qtdPessoas < 4 )
            v.setBackgroundColor(Color.YELLOW);
        else
            v.setBackgroundColor(Color.BLUE);
        return v;
    }
}
