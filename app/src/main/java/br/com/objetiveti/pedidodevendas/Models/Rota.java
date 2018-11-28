package br.com.objetiveti.pedidodevendas.Models;

import java.io.Serializable;

public class Rota implements Serializable{
    private String nomeCliente;
    private String enderecoCliente;
    private String telefoneCliente;
    private String Dias;
    private String Frequencia;
    private String Semana;
    public String DiasTodos;

    public Rota(String nc, String ec, String tc, String dias, String frequencia, String semana) {
        nomeCliente = nc;
        enderecoCliente = ec;
        telefoneCliente = tc;
        Dias = dias;
        Frequencia = frequencia;
        Semana = semana;
    }

    public Rota() {
    }

    public String getDiasTodos() {
        return DiasTodos;
    }

    public void setDiasTodos(String diasTodos) {
        DiasTodos = diasTodos;
    }

    public String getDias() {
        return Dias;
    }

    public void setDias(String dias) {
        Dias = dias;
    }

    public String getFrequencia() {
        return Frequencia;
    }

    public void setFrequencia(String frequencia) {
        Frequencia = frequencia;
    }

    public String getSemana() {
        return Semana;
    }

    public void setSemana(String semana) {
        Semana = semana;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(String enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }
}
