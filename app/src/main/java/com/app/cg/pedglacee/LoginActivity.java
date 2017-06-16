package com.app.cg.pedglacee;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.cg.pedglacee.classes.Login;
import com.app.cg.pedglacee.conexao_network.Conexao;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;


public class LoginActivity extends AppCompatActivity { //implements LoaderCallbacks<Cursor> {

    private EditText edtLogin, edtSenha;
    private Button btnLogar;

    String url = "", parametros = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        InicializarComponentes();

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Verificação da rede
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if(networkInfo != null && networkInfo.isConnected()) {
                    String login = edtLogin.getText().toString();
                    String senha = edtSenha.getText().toString();

                    if(login.isEmpty() || senha.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Nenhum campo deve estar vazio.", Toast.LENGTH_LONG).show();
                    } else {
                        url = "http://apppedglace.xyz/login/logar.php";
                        parametros = "login=" + login + "&senha=" + senha;
                        new SolicitaDados().execute(url);
                    }

                    /*if(login.isEmpty() || senha.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Nenhum campo deve estar vazio.", Toast.LENGTH_LONG).show();
                    } else {
                        Login lg = new Login();
                        lg.setLogin(login);
                        lg.setSenha(senha);
                        if(lg.Logar()) {
                            Intent i = new Intent(LoginActivity.this, MenuActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext(), "Usuário ou Senha incorretos.", Toast.LENGTH_LONG).show();
                        }
                    }*/
                } else {
                    Toast.makeText(getApplicationContext(), "Você não está conectado à rede", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void InicializarComponentes() {
        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnLogar = (Button) findViewById(R.id.btnLogar);
    }

    private class SolicitaDados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            return Conexao.postDados(params[0], parametros);
        }

        @Override
        protected void onPostExecute(String resultado) {
            if(resultado.contains("login_ok")) {
                Intent i = new Intent(LoginActivity.this, NovoPedidoActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), "Usuário ou Senha estão incorretos.", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}

