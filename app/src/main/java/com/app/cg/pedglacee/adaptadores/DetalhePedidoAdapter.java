package com.app.cg.pedglacee.adaptadores;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.app.cg.pedglacee.R;
import com.app.cg.pedglacee.classes.BasePedido;
import com.app.cg.pedglacee.classes.ItensPedido;

import java.util.List;

/**
 * Created by LEANDRO on 13/06/2017.
 */

public class DetalhePedidoAdapter extends BaseAdapter {

    private Context cContexto;
    private List<ItensPedido> lItem;

    public DetalhePedidoAdapter(Context pContexto, List<ItensPedido> item) {
        this.cContexto = pContexto;
        this.lItem = item;
    }

    @Override
    public int getCount() {
        return lItem.size();
    }

    @Override
    public Object getItem(int position) {
        return lItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(cContexto, R.layout.row_detalhe_pedido, null);
        try {

            TextView txtQtd = (TextView) v.findViewById(R.id.txtQtd);
            TextView txtItem = (TextView) v.findViewById(R.id.txtItem);

            //setando os txts
            txtQtd.setText(lItem.get(position).getiQuantidade() + "x");
            txtItem.setText(lItem.get(position).getsDescricao());

            v.setTag(lItem.get(position).getsDescricao());
        } catch (Exception ex) {
            String a = ex.getMessage();

        }

        return v;
    }
}
