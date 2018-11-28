package br.com.objetiveti.pedidodevendas.Models;

import java.util.Date;

/**
 * Created by Arthur Bucar on 10/27/2017.
 */

public class PedidoDeVenda_Cabecalho {
    private String NumeroPV;
    private String NumeroProtheus;
    private String Cliente;
    private String Loja;
    private Date Emissao;
    private String Hora;
    private String Vencedor;
    private String CondPagto;

    public String getNumeroPV() {
        return NumeroPV;
    }

    public void setNumeroPV(String numeroPV) {
        NumeroPV = numeroPV;
    }

    public String getNumeroProtheus() {
        return NumeroProtheus;
    }

    public void setNumeroProtheus(String numeroProtheus) {
        NumeroProtheus = numeroProtheus;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String cliente) {
        Cliente = cliente;
    }

    public String getLoja() {
        return Loja;
    }

    public void setLoja(String loja) {
        Loja = loja;
    }

    public Date getEmissao() {
        return Emissao;
    }

    public void setEmissao(Date emissao) {
        Emissao = emissao;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public String getVencedor() {
        return Vencedor;
    }

    public void setVencedor(String vencedor) {
        Vencedor = vencedor;
    }

    public String getCondPagto() {
        return CondPagto;
    }

    public void setCondPagto(String condPagto) {
        CondPagto = condPagto;
    }

    public String getNotaFiscal() {
        return NotaFiscal;
    }

    public void setNotaFiscal(String notaFiscal) {
        NotaFiscal = notaFiscal;
    }

    private String NotaFiscal;
}
