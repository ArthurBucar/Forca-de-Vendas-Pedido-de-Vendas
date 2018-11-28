package br.com.objetiveti.pedidodevendas.Models;

/**
 * Created by Arthur Bucar on 10/27/2017.
 */

public class Vendedor {
    private String Codigo;
    private String Nome;
    private String NomeReduz;
    private String Bloqueado;


    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getNome() {
        return Nome;
    }
    public void setNome(String nome) {
        Nome = nome;
    }

    public String getNomeReduz() {
        return NomeReduz;
    }

    public void setNomeReduz(String nomeReduz) {
        NomeReduz = nomeReduz;
    }

    public String getBloqueado() {
        return Bloqueado;
    }

    public void setBloqueado(String bloqueado) {
        Bloqueado = bloqueado;
    }

}
