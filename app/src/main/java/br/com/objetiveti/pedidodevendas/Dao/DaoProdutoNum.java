package br.com.objetiveti.pedidodevendas.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.objetiveti.pedidodevendas.Database.DBMain;
import br.com.objetiveti.pedidodevendas.Models.Produto_Num;

/**
 * Created by Arthur Bucar on 10/13/2017.
 */

public class DaoProdutoNum {
    public List<Produto_Num> getSetProdutoNum(Context context, int qtd){

        DBMain banco = new DBMain(context);
        banco.openDataBase();
        SQLiteDatabase db = banco.getReadableDatabase();
        List<Produto_Num> listAux = new ArrayList<>();

        Cursor c = db.rawQuery("select PrecoPadrao, Descricao, Codigo from Produto order by Codigo",null);

        if(c.moveToFirst()){
            do{
                //assing values
                String codigo = c.getString(c.getColumnIndex("Codigo"));
                String nome = c.getString(c.getColumnIndex("Descricao"));
                double preco = c.getDouble(c.getColumnIndex("PrecoPadrao"));
                //Do something Here with values
                Produto_Num cp = new Produto_Num(nome,codigo,preco);
                listAux.add(cp);

            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return (listAux);

    }
}

//    String[] valorUnitarioProduto = new String[]{"Valor Unitário"};
//    String[] quantidadeProduto  = new String[]{"Quantidade"};
//    String[] subtotalProduto  = new String[]{"Subtotal"};
//    List<Produto_Num> listAux = new ArrayList<>();
//        for (int i = 0; i < qtd; i++){
//        Produto_Num o = new Produto_Num (valorUnitarioProduto[i % valorUnitarioProduto.length], quantidadeProduto[i % quantidadeProduto.length], subtotalProduto[i % subtotalProduto.length]);
//        listAux.add(o);
//        }
//        return listAux;