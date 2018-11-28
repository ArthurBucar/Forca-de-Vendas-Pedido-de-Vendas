package br.com.objetiveti.pedidodevendas.Models;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.io.Serializable;

/**
 * Created by Arthur Bucar on 10/13/2017.
 */

public class Produto_Num  extends RecyclerView.Adapter implements Serializable{
    private String valorUnitarioProduto;
    private String quantidadeProduto;
    private String subtotalProduto;
    private String Codigo;
    private String Descricao;
    private double PrecoPadrao;
    private String NomeProduto;
    private String CodProduto;
    private double Preco;

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public double getPrecoPadrao() {
        return PrecoPadrao;
    }

    public void setPrecoPadrao(double precoPadrao) {
        PrecoPadrao = precoPadrao;
    }

    public String getNomeProduto() {
        return NomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        NomeProduto = nomeProduto;
    }

    public String getCodProduto() {
        return CodProduto;
    }

    public void setCodProduto(String codProduto) {
        CodProduto = codProduto;
    }

    public double getPreco() {
        return Preco;
    }

    public void setPreco(double preco) {
        Preco = preco;
    }

    public Produto_Num(String nome, String codigo, double preco){}

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public Produto_Num(String vu, String qp, String stb){
        valorUnitarioProduto = vu;
        quantidadeProduto = qp;
        subtotalProduto = stb;
    }

    public Produto_Num() {
    }

    public String getValorUnitarioProduto(){return valorUnitarioProduto;}

    public void setValorUnitarioProduto(String valorUnitarioProduto) {this.valorUnitarioProduto = valorUnitarioProduto;}

    public String getQuantidadeProduto(){return quantidadeProduto;}

    public void setQuantidadeProduto(String quantidadeProduto) {this.quantidadeProduto = quantidadeProduto;}

    public String getSubtotalProduto(){return subtotalProduto;}

    public void setSubtotalProduto(String subtotalProduto) {this.subtotalProduto = subtotalProduto;}

}
