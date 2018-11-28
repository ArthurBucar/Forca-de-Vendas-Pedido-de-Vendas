package br.com.objetiveti.pedidodevendas.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.objetiveti.pedidodevendas.Database.DBMain;
import br.com.objetiveti.pedidodevendas.Models.Cliente;

/**
 * Created by Arthur Bucar on 10/4/2017.
 */

public class DaoClientes {
    public List<Cliente> getSetCliente_Lista(Context context, int qtd){
        DBMain banco = new DBMain(context);
        banco.openDataBase();
        SQLiteDatabase db = banco.getReadableDatabase();
        List<Cliente> listAux = new ArrayList<>();

        //order by APELIDO_FANTASIA DESC
        Cursor c = db.rawQuery("select NomeFantasia, Bloqueado, LimCredito, Codigo from Cliente",null);

        if(c.moveToFirst()){
            do{
                //assing values
                String cNome = c.getString(c.getColumnIndex("NomeFantasia"));
                String cStatus = c.getString(c.getColumnIndex("Bloqueado"));
                float cLimiteCredito = c.getFloat(c.getColumnIndex("LimCredito"));
                double codigo = c.getDouble(c.getColumnIndex("Codigo"));
                //Do something Here with values
                Cliente cp = new Cliente(cNome, cStatus, cLimiteCredito, codigo);

                listAux.add(cp);

            }while(c.moveToNext());
        }
        c.close();
        db.close();

        return(listAux);
    }
}