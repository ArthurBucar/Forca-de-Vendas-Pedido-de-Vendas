package br.com.objetiveti.pedidodevendas.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.objetiveti.pedidodevendas.Database.DBMain;
import br.com.objetiveti.pedidodevendas.Models.Pedido;

/**
 * Created by Arthur Bucar on 10/2/2017.
 */

public class DaoPedido {
    int id = 1;
    int novoID = 1;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Pedido> getSetPedidoLista(Context context, int qtd){
        DBMain banco = new DBMain(context);
        banco.openDataBase();
        SQLiteDatabase db = banco.getReadableDatabase();
        List<Pedido> listAux = new ArrayList<>();

        SimpleDateFormat sdff = new SimpleDateFormat("dd/MM/yyyy");
        String diaAtual = (sdff.format(new Date()));

        Cursor c = db.rawQuery("select NumeroPV ,Loja, Emissao, NumeroProtheus, CondPagto, TotalPedido from Pedido_Venda_Cabecalho",null);

        if(c.moveToFirst()){
            do{
                //assing values
                String nNome = c.getString(c.getColumnIndex("Loja"));
                String dataPed = c.getString(c.getColumnIndex("Emissao"));
                String numPed = c.getString(c.getColumnIndex("NumeroPV"));
                int format = Integer.parseInt(numPed);
                String numPedFormart = String.format("%06d", format);
                String numPrePed = c.getString(c.getColumnIndex("NumeroProtheus"));
                double numSub = c.getDouble(c.getColumnIndex("TotalPedido"));
                Pedido cp = new Pedido(nNome, dataPed, numPedFormart, numPrePed, numSub);
                listAux.add(cp);

            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return (listAux);
    }
}
