package br.com.objetiveti.pedidodevendas.Models;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Arthur Bucar on 10/11/2017.
 */

public class ProdutoDetalhe extends RecyclerView.Adapter implements Serializable{
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

    // public Produto(Context baseContext, List<Produto> pList){}

    public ProdutoDetalhe(String pn, String pc, double pp){
        NomeProduto = pn;
        CodProduto = pc;
        Preco = pp;
    }

    public ProdutoDetalhe(){    }

    public String getPrecoReal(){
        String ret = "";
        //ret = "R$ " + String.format("%.00d", Preco);
        ret = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(Preco);
        return ret;
    }

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
}
