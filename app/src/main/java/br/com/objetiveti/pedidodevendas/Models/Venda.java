package br.com.objetiveti.pedidodevendas.Models;

import android.content.Context;
import android.util.Log;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Arthur Bucar on 10/7/2017.
 */

public class Venda implements Serializable{
    public String nome;
    public String endereco;
    public String telefone;
    public double valor;
    public double ghost;
    public double total;
    private Context context;

    public Venda(String nn, String ee, String tt, double vv, double gg){
        this.nome = nn;
        this.endereco = ee;
        this.telefone = tt;
        this.valor = vv;
        this.ghost = gg;
        this.total = gg;
        Log.i("gucci", String.valueOf(ghost));
    }

    public Venda() {
        total = 500;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getGhost() {
        String retos = " ";
        retos = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(ghost);
        return retos;
    }

    public void setGhost(double ghost) {
        this.ghost = ghost;
    }


    public String getNome(){return nome;}

    public void setNome(String nome){this.nome= nome;}

    public String getEndereco(){return endereco;}

    public void setEndereco(String endereco) {this.endereco= endereco;}

    public String getTelefone(){return telefone;}

    public void setTelefone(String telefone) {this.telefone= telefone;}

    public String getValorReal(){
        String ret = " ";
        ret = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(valor);
        return ret;
    }

    public double getValor(){return valor;}

    public void setValor(double valor) {this.valor = valor;}

}
