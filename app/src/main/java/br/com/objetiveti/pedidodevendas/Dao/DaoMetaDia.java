package br.com.objetiveti.pedidodevendas.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.objetiveti.pedidodevendas.Database.DBMain;
import br.com.objetiveti.pedidodevendas.Models.Cliente;

/**
 * Created by Arthur Bucar on 26/02/2018.
 */

public class DaoMetaDia {
    public float getMetaDia(Context context, int qtd){
        DBMain banco = new DBMain(context);
        banco.openDataBase();
        SQLiteDatabase db = banco.getReadableDatabase();
        List<Float> listAux = new ArrayList<>();

        String qrySql = "";

        qrySql += "SELECT V.MetaMensal\n" +
                "FROM Usuario U, Vendedor V\n" +
                "WHERE 1=1\n" +
                "AND U.CodVend = V.Codigo\n" +
                "AND U.Ativado = 'S'";

        //Cursor c = db.rawQuery("select MetaMensal from Vendedor",null);
        Cursor c = db.rawQuery(qrySql,null);
        float result = 0;
        if(c.moveToFirst()){
            do{
                //assing values
                float MetaDia = c.getFloat(c.getColumnIndex("MetaMensal"));
                float a = MetaDia;

                result = result + a;
                //Do something Here with values
                Cliente cp = new Cliente(result);
                Log.e("owww", String.valueOf(result));

                listAux.add(result);

            }while(c.moveToNext());
        }
        c.close();
        db.close();

        return(result);
    }

    public float getMetaMensal(Context context, int qtd){
        DBMain banco = new DBMain(context);
        banco.openDataBase();
        SQLiteDatabase db = banco.getReadableDatabase();
        List<Float> listAux = new ArrayList<>();

        //order by APELIDO_FANTASIA DESC
        Cursor c = db.rawQuery("select MetaMensal from Vendedor",null);
        float result = 0;
        if(c.moveToFirst()){
            do{
                //assing values
                float MetaDia = c.getFloat(c.getColumnIndex("MetaMensal"));
                result = MetaDia;
                //Do something Here with values
                Cliente cp = new Cliente(result);

                listAux.add(result);

            }while(c.moveToNext());
        }
        c.close();
        db.close();

        return(result);
    }
}
