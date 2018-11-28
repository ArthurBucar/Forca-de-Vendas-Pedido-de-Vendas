package br.com.objetiveti.pedidodevendas.Models;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.io.Serializable;

/**
 * Created by Arthur Bucar on 15/03/2018.
 */

public class FormasPagamento extends RecyclerView.Adapter implements Serializable {
    private String condicao;

    public FormasPagamento(String condicao) {
        condicao = condicao;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
