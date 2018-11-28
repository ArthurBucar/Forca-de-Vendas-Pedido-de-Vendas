package br.com.objetiveti.pedidodevendas.Models;

/**
 * Created by Arthur Bucar on 10/27/2017.
 */

public class TabelaDePreco_Itens {
    private String Codigo;
    private String CodigoProduto;
    private float Preco;

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getCodigoProduto() {
        return CodigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        CodigoProduto = codigoProduto;
    }

    public float getPreco() {
        return Preco;
    }

    public void setPreco(float preco) {
        Preco = preco;
    }
}
