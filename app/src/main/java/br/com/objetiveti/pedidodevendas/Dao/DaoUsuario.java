package br.com.objetiveti.pedidodevendas.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.objetiveti.pedidodevendas.Database.DBMain;
import br.com.objetiveti.pedidodevendas.Models.Usuario;

/**
 * Created by Arthur Bucar on 24/01/2018.
 */

public class DaoUsuario {

    public List<Usuario> getLista_Usuario(Context context, int qtd){
        DBMain banco = new DBMain(context);
        banco.openDataBase();
        SQLiteDatabase db = banco.getReadableDatabase();
        List<Usuario> listAux = new ArrayList<>();

        //order by APELIDO_FANTASIA DESC
        Cursor c = db.rawQuery("select login, senha from Usuario",null);

        if(c.moveToFirst()){
            do{
                //assing values
                String uLogin = c.getString(c.getColumnIndex("login"));
                String uSenha = c.getString(c.getColumnIndex("senha"));
                //Do something Here with values
                Usuario usuario = new Usuario(uLogin, uSenha);
                listAux.add(usuario);

            }while(c.moveToNext());
        }
        c.close();
        db.close();

        return(listAux);
    }
}
