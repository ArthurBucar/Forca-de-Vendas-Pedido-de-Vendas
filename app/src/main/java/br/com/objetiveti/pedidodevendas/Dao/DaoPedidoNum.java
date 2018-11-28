package br.com.objetiveti.pedidodevendas.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.objetiveti.pedidodevendas.Database.DBMain;
import br.com.objetiveti.pedidodevendas.Models.Pedido_Num;

/**
 * Created by Arthur Bucar on 10/3/2017.
 */

public class DaoPedidoNum {
    public List<Pedido_Num> getSetPedido_NumLista(Context context, int qtd){
        DBMain banco = new DBMain(context);
        banco.openDataBase();
        SQLiteDatabase db = banco.getReadableDatabase();
        List<Pedido_Num> listAux = new ArrayList<>();

        Cursor c = db.rawQuery("select Emissao, NumeroPV, NumeroProtheus, NotaFiscal from Pedido_Venda_Cabecalho order by Emissao",null);

        if(c.moveToFirst()){
            do{
                //assing values
                String dataPed = c.getString(c.getColumnIndex("Emissao"));
                String numPed = c.getString(c.getColumnIndex("NumeroPV"));
                String numPrePed = c.getString(c.getColumnIndex("NumeroProtheus"));
                String numSub = c.getString(c.getColumnIndex("NotaFiscal"));
                Log.e("testePersistir", dataPed);
                //Do something Here with values
                Pedido_Num pedido_num = new Pedido_Num(dataPed,numPed,numPrePed,numSub);
                listAux.add(pedido_num);

            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return listAux;
    }

}
//    String[] dataPed = new String[]{"17/08/2017","17/08/2017","17/08/2017"};
//    String[] numPed = new String[]{"000000","000000","000000"};
//    String[] numPrePed = new String[]{"000001","000002","000003"};
//    String[] numSub = new String[]{"R$ 330,00","R$ 580,00","R$ 250,00"};
//
//    List<Pedido_Num> listAux = new ArrayList<>();
//        for (int i = 0; i < qtd; i++){
//        Pedido_Num o = new Pedido_Num(dataPed[i % dataPed.length], numPed[i % numPed.length], numPrePed[i % numPrePed.length], numSub[i % numSub.length]);
//        listAux.add(o);
//        }