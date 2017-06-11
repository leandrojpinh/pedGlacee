package com.app.cg.pedglacee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class NovoPedidoActivity extends AppCompatActivity {

    private Intent i;
    private EditText edtMesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_pedido);

        InicializarComponentes();

        //Receber valores da listView
        i = getIntent();
        edtMesa.setText(i.getStringExtra("edtMesa"));
    }

    private void InicializarComponentes() {
        edtMesa = (EditText) findViewById(R.id.edtMesa);
    }
}
