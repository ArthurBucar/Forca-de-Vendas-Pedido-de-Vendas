package br.com.objetiveti.pedidodevendas.Models;

/**
 * Created by Arthur Bucar on 10/30/2017.
 */

public class CondicaoDePagamento {
    private String Codigo;
    private String Tipo;
    private String Condicao;
    private String Descricao;

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getCondicao() {
        return Condicao;
    }

    public void setCondicao(String condicao) {
        Condicao = condicao;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }
}
