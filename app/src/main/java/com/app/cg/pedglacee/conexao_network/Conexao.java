package com.app.cg.pedglacee.conexao_network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by LEANDRO on 15/06/2017.
 */

public class Conexao {

    public static String postDados(String urlUsuario, String parametrosUsuario) {
        URL url;
        HttpURLConnection connection = null;

        try {
            url = new URL(urlUsuario); // Setando url do usuário
            connection = (HttpURLConnection) url.openConnection(); //Iniciando conexao

            //Propriedades de configuração
            connection.setRequestMethod("POST");

            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); //tipo de envio de dados - formulário
            connection.setRequestProperty("Content-Lenght", "" + Integer.toString(parametrosUsuario.getBytes().length)); // tamamho da informação
            connection.setRequestProperty("Content-Language", "pt-BR"); //idioma

            connection.setUseCaches(false); // armazenar nada em cache
            connection.setDoInput(true); // entrada de dados
            connection.setDoOutput(true);  // saída de dados

            /*//solicitação - versão antiga
            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.writeBytes(parametrosUsuario);
            dataOutputStream.flush();
            dataOutputStream.close();*/

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            outputStreamWriter.write(parametrosUsuario);
            outputStreamWriter.flush();


            //Obtendo dados atraves da solicitação
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String linha;
            StringBuffer resposta = new StringBuffer();

            while ((linha = bufferedReader.readLine()) != null) {
                resposta.append(linha);
                resposta.append('\r');
            }

            bufferedReader.close();
            return resposta.toString();

        } catch (Exception erro) {
            return  null;

        } finally {
            if (connection != null)
                connection.disconnect();
        }
    }
}
