package br.com.objetiveti.pedidodevendas.Models;

import android.util.Log;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Arthur Bucar on 10/2/2017.
 */

public class Pedido implements Serializable {
    private String nNome;
    private String nPedido;
    private String nPrePedido;
    //private String subTotal;
    private String dataPed;
    private String numPed;
    private String numPrePed;
    private double numSub;



    public Pedido(String ccp, String dp, String np, String npp, double ns ){
        nNome = ccp;
        dataPed = dp;
        numPed = np;
        numPrePed = npp;
        numSub = ns;
    }

    public String getNumSubReal(){
        String ret = "";
        //ret = "R$ " + String.format("%.00d", Preco);
        ret = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(numSub);
        Log.e("dads", String.valueOf(numSub));
        return ret;
    }

    public String getnNome() {
        return nNome;
    }

    public void setnNome(String nNome) {
        this.nNome = nNome;
    }

    public String getnPedido() {
        return nPedido;
    }

    public void setnPedido(String nPedido) {
        this.nPedido = nPedido;
    }

    public String getnPrePedido() {
        return nPrePedido;
    }

    public void setnPrePedido(String nPrePedido) {
        this.nPrePedido = nPrePedido;
    }

    public String getDataPed() {
        return dataPed;
    }

    public void setDataPed(String dataPed) {
        this.dataPed = dataPed;
    }

    public String getNumPed() {

        return numPed;
    }

    public void setNumPed(String numPed) {
        this.numPed = numPed;
    }

    public String getNumPrePed() {
        return numPrePed;
    }

    public void setNumPrePed(String numPrePed) {
        this.numPrePed = numPrePed;
    }

    public double getNumSub() {
        return numSub;
    }

    public void setNumSub(double numSub) {
        this.numSub = numSub;
    }
}
