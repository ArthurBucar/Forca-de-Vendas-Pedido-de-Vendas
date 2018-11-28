package br.com.objetiveti.pedidodevendas.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.objetiveti.pedidodevendas.Database.DBMain;
import br.com.objetiveti.pedidodevendas.Models.Produto;

/**
 * Created by Arthur Bucar on 10/11/2017.
 */

public class DaoProdutosDetalhe {
    public List<Produto> getSetProduto_Lista(Context context, int qtd){
        DBMain banco = new DBMain(context);
        banco.openDataBase();
        SQLiteDatabase db = banco.getReadableDatabase();
        List<Produto> listAux = new ArrayList<>();

        Cursor c = db.rawQuery("select PrecoPadrao, Descricao, Codigo from Produto order by Codigo",null);

        if(c.moveToFirst()){
            do{
                //assing values
                String nome = c.getString(c.getColumnIndex("Codigo"));
                String codigo = c.getString(c.getColumnIndex("Descricao"));
                double preco = c.getDouble(c.getColumnIndex("PrecoPadrao"));
                //Do something Here with values
                Produto cp = new Produto(nome,codigo,preco);
                listAux.add(cp);

            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return (listAux);

    }
}
//        String[] NomeProduto = new String[]{"ADAT. BOINA DUPLA FACE","ADITIVO 200L", "ALUMAX 20L", "ALUMAX 5L", "ALUMAX 5L CX 4 UNID", "ALUMAX ULTRA 20L"};
//        String[] CodProduto = new String[]{"RV05015", "PA12001", "PA08004", "PA08005", "PA08006", "PA08021"};
//        String[] Preco = new String[]{"R$ 25,00", "R$ 280,00", "R$ 49,00", "R$ 15,50", "R$ 68,00", "R$ 66,60"};
