package br.com.objetiveti.pedidodevendas.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.objetiveti.pedidodevendas.Database.DBMain;
import br.com.objetiveti.pedidodevendas.Fragments.RelatoriosFragment;

/**
 * Created by Arthur Bucar on 27/02/2018.
 */

public class DaoTotalVendas {
    public float getTotalVendasRelatorio(Context context, int qtd){
        DBMain banco = new DBMain(context);
        banco.openDataBase();
        SQLiteDatabase db = banco.getReadableDatabase();
        List<Float> listAux = new ArrayList<>();

        //order by APELIDO_FANTASIA DESC
        Cursor c = db.rawQuery("select Total from Pedido_Venda_Itens",null);
        float result = 0;
        if(c.moveToFirst()){
            do{
                //assing values
                float MetaDia = c.getFloat(c.getColumnIndex("Total"));
                float a = MetaDia;

                result = result + a;
                //Do something Here with values
                RelatoriosFragment cp = new RelatoriosFragment(result);
                Log.e("testeResult", String.valueOf(result));

                listAux.add(result);

            }while(c.moveToNext());
        }
        c.close();
        db.close();

        return(result);
    }
}
