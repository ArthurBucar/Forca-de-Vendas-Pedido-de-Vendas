package br.com.objetiveti.pedidodevendas.Models;

/**
 * Created by Arthur Bucar on 10/27/2017.
 */

public class TabelaDePreco_Cabecalho {
    private String Codigo;
    private String Descricao;

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
}
