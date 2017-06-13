package com.app.cg.pedglacee;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.app.cg.pedglacee.classes.Prato;

import java.util.ArrayList;

public class NovoPedidoActivity extends AppCompatActivity {

    private Intent i;
    private EditText edtMesa;
    private Spinner spnPrato, spnSuco;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_pedido);

        InicializarComponentes();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.pratos));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnPrato.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.sucos));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnSuco.setAdapter(adapter2);
        //Receber valores da listView
        i = getIntent();
        edtMesa.setText(i.getStringExtra("edtMesa"));
    }

    private void InicializarComponentes() {
        edtMesa = (EditText) findViewById(R.id.edtMesa);
        spnPrato = (Spinner) findViewById(R.id.spnPrato);
        spnSuco = (Spinner) findViewById(R.id.spnSuco);
    }
}
