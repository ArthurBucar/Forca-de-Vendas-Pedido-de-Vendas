package br.com.objetiveti.pedidodevendas.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.objetiveti.pedidodevendas.Database.DBMain;
import br.com.objetiveti.pedidodevendas.Models.FormasPagamento;

/**
 * Created by Arthur Bucar on 15/03/2018.
 */

public class DaoFormasPagamentos {
    public List<FormasPagamento> getSetListaFormas(Context context, int qtd){
        DBMain banco = new DBMain(context);
        banco.openDataBase();
        SQLiteDatabase db = banco.getReadableDatabase();
        List<FormasPagamento> listAux = new ArrayList<>();

        Cursor c = db.rawQuery("select Condicao from Condicao_Pagamento",null);

        if(c.moveToFirst()){
            do{
                //assing values
                String condicao = c.getString(c.getColumnIndex("Condicao"));
                FormasPagamento cp = new FormasPagamento(condicao);
                listAux.add(cp);

            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return (listAux);
    }
}
