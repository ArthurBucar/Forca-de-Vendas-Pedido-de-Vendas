package br.com.objetiveti.pedidodevendas.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.objetiveti.pedidodevendas.Database.DBMain;
import br.com.objetiveti.pedidodevendas.Models.Produto;

/**
 * Created by Arthur Bucar on 10/11/2017.
 */
public class DaoProdutos {
   private int codigoRecebe;
    String chaveo;
    public DaoProdutos(int codigoARTHUR) {
        this.codigoRecebe = codigoARTHUR;
    }


    public List<Produto> getSetProduto_Lista(Context context, int qtd){
        DBMain banco = new DBMain(context);
        banco.openDataBase();

        SQLiteDatabase db = banco.getReadableDatabase();
        List<Produto> listAux = new ArrayList<>();
        String qrySql = "";

        int codigoCliente = codigoRecebe;
        if (codigoCliente <=9){
            chaveo = ("0"+codigoRecebe);
        } else {
            chaveo = String.valueOf(codigoCliente);
        }
        Log.e("tagCliente", String.valueOf(chaveo));

        //Log.e("tagRecebeCodigo", String.valueOf(codigo));

        qrySql += "select p.codigo codProduto, p.descricao descProduto,\n" +
                "case when t.Preco not null then t.Preco else p.precopadrao end precoProduto\n" +
                "from produto p\n" +
                "left outer join cliente c\n" +
                "on 1=1\n" +
                "and c.codigo = '"+chaveo+"' \n" +
                "left outer join tabela_preco_itens t\n" +
                "on 1=1\n" +
                "and c.tabelaPreco = t.codigo\n" +
                "and p.codigo = t.codigoProduto\n" +
                "order by 1,2,3";

        Cursor c = db.rawQuery(qrySql,null);
        if(c.moveToFirst()){
            do{
                //assing values
                String nome = c.getString(c.getColumnIndex("codProduto"));
                String codigo = c.getString(c.getColumnIndex("descProduto"));
                double preco = c.getDouble(c.getColumnIndex("precoProduto"));
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
