package br.com.objetiveti.pedidodevendas.Models;

/**
 * Created by Arthur Bucar on 10/27/2017.
 */

public class PedidoDeVenda_Itens {
    private String NumeroPV;
    private String Item;
    private String Produto;
    private String DescProduto;
    private String UM;
    private double Quantidade;
    private double Preco;
    private double Total;

    public String getNumeroPV() {
        return NumeroPV;
    }

    public void setNumeroPV(String numeroPV) {
        NumeroPV = numeroPV;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }

    public String getProduto() {
        return Produto;
    }

    public void setProduto(String produto) {
        Produto = produto;
    }

    public String getDescProduto() {
        return DescProduto;
    }

    public void setDescProduto(String descProduto) {
        DescProduto = descProduto;
    }

    public String getUM() {
        return UM;
    }

    public void setUM(String UM) {
        this.UM = UM;
    }

    public double getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(float quantidade) {
        Quantidade = quantidade;
    }

    public double getPreco() {
        return Preco;
    }

    public void setPreco(float preco) {
        Preco = preco;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(float total) {
        Total = total;
    }
}
