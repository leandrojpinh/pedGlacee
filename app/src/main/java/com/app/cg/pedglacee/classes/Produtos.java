package com.app.cg.pedglacee.classes;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Adriano on 15/06/2017.
 */

public class Produtos extends _Default {
    private int id;
    private String descricao;
    private String tipoProduto;
    private double valor;

    public Produtos(){
        super();
        this.id = -1;
        this.descricao = "";
        this.tipoProduto = "";
        this.valor = 0;
    }

    public ArrayList<Produtos> getList(){
        DB db = new DB();
        ArrayList<Produtos> lista = new ArrayList<>();
        try {
            ResultSet resultSet = db.select("SELECT * FROM refeicao");
            if(resultSet != null){
                while(resultSet .next()){
                    Produtos obj = new Produtos();
                    obj.setId(resultSet.getInt("id"));
                    obj.setDescricao(resultSet.getString("descricao"));
                    obj.setTipoProduto(resultSet.getString("tipoproduto"));
                    obj.setValor(resultSet.getDouble("valor"));
                    lista.add(obj);
                    obj = null;
                }
            }
        }catch (Exception ex){
            this._mensagem = ex.getMessage();
            this._status =false;
        }
        return lista;
    }

    public void adicionar(){
        String comando = "";
        if(this.getId() == -1){
            comando = String.format("INSERT INTO refeicao (descricao,tipoproduto,valor) VALUES ('%s','%s', %f);",
                    this.getDescricao(),this.getTipoProduto(),this.getValor());
        }else{
            comando = String.format("UPDATE refeicao SET  descricao = '%s',tipoproduto = '%s',valor = %f WHERE id = %d);",
                    this.getDescricao(),this.getTipoProduto(),this.getValor(),this.getId());
        }
        DB db = new DB();
        db.execute(comando);
        this._mensagem = db._mensagem;
        this._status = db._status;
    }
    public void apagar(){
        String comando = String.format("DELETE FROM refeicao WHERE id = %d);",this.getId());

        DB db = new DB();
        db.execute(comando);
        this._mensagem = db._mensagem;
        this._status = db._status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
