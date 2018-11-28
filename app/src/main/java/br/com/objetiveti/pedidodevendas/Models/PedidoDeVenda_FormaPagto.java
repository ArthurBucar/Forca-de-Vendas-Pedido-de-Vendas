package br.com.objetiveti.pedidodevendas.Models;

/**
 * Created by Arthur Bucar on 10/27/2017.
 */

public class PedidoDeVenda_FormaPagto {
    private String NumeroPV;
    private String FormaPagto;
    private double Valor;

    public String getNumeroPV() {
        return NumeroPV;
    }

    public void setNumeroPV(String numeroPV) {
        NumeroPV = numeroPV;
    }

    public String getFormaPagto() {
        return FormaPagto;
    }

    public void setFormaPagto(String formaPagto) {
        FormaPagto = formaPagto;
    }

    public double getValor() {
        return Valor;
    }

    public void setValor(double valor) {
        Valor = valor;
    }

}
