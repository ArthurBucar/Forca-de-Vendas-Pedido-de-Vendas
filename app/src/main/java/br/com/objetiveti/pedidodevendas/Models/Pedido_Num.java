package br.com.objetiveti.pedidodevendas.Models;

import android.content.Context;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Arthur Bucar on 10/3/2017.
 */

public class Pedido_Num implements Serializable{
    private String dataPed;
    private String numPed;
    private String numPrePed;
    private String numSub;

    public Pedido_Num(Context baseContext, List<Pedido> pList){}

    public Pedido_Num(String dp, String nmp, String npred, String numb){
        dataPed = dp;
        numPed = nmp;
        numPrePed = npred;
        numSub = numb;
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

    public String getNumSub() {
        return numSub;
    }

    public void setNumSub(String numSub) {
        this.numSub = numSub;
    }
}
