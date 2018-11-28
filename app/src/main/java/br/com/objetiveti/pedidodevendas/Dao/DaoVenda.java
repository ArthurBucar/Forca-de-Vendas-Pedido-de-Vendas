package br.com.objetiveti.pedidodevendas.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.objetiveti.pedidodevendas.Database.DBMain;
import br.com.objetiveti.pedidodevendas.Models.Venda;

/**
 * Created by Arthur Bucar on 10/7/2017.
 */

public class DaoVenda {
    public double totalAux;

    public List<Venda> getSetVendaList(Context context, int qtd){
        DBMain banco = new DBMain(context);
        banco.openDataBase();
        SQLiteDatabase db = banco.getReadableDatabase();
        List<Venda> listAux = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT h.Loja as NomeFantasia, TotalPedido as LimCredito, Endereco, Telefone\n" +
                "from Pedido_Venda_Cabecalho h\n" +
                "inner join Cliente c on h.Loja = NomeFantasia",null);
        if(c.moveToFirst()){
            do{
                //assing valuesw
                String nome = c.getString(c.getColumnIndex("NomeFantasia"));
                String endereco = c.getString(c.getColumnIndex("Endereco"));
                String telefone = c.getString(c.getColumnIndex("Telefone"));
                double valor = c.getDouble(c.getColumnIndex("LimCredito"));
                double ghost = c.getDouble(c.getColumnIndex("LimCredito"));
//                double total = 0;
//                for (int i = 0; i < listAux.size(); i++){
//                    total += listAux.get(i).getValor();
//                }
                //Do something Here with values
                Venda cp = new Venda(nome, endereco, telefone, valor, ghost);
                listAux.add(cp);

            }while (c.moveToNext() && listAux.size() < qtd);
        }
        c.close();
        db.close();
        return (listAux);
    }


    public List<Venda> getSetVendaList(Context context){
        DBMain banco = new DBMain(context);
        banco.openDataBase();
        SQLiteDatabase db = banco.getReadableDatabase();
        List<Venda> listAux = new ArrayList<>();
        String fuckthat = null;
        Venda venda;
        double auxTotalAux = 0;

        Cursor c = db.rawQuery("SELECT h.Loja as NomeFantasia, TotalPedido as LimCredito, Endereco, Telefone\n" +
                "from Pedido_Venda_Cabecalho h\n" +
                "inner join Cliente c on h.Loja = NomeFantasia",null);
        Cursor d = db.rawQuery("select sum(TotalPedido) as total from Pedido_Venda_Cabecalho",null);
        d.moveToFirst();

        if(c.moveToFirst()){
            do{
                //assing valuesw
                String nome = c.getString(c.getColumnIndex("NomeFantasia"));
                String endereco = c.getString(c.getColumnIndex("Endereco"));
                String telefone = c.getString(c.getColumnIndex("Telefone"));
                double valor = c.getDouble(c.getColumnIndex("LimCredito"));
                double ghost = d.getDouble(d.getColumnIndex("total"));

                auxTotalAux = d.getDouble(d.getColumnIndex("total"));

                venda = new Venda(nome, endereco, telefone, valor, ghost);
                listAux.add(venda);

            }while(c.moveToNext());
        }
        c.close();
        d.close();
        db.close();

        this.totalAux = auxTotalAux;

        return (listAux);
    }

    public String getTotalAux() {
        String retos = " ";
        retos = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(totalAux);

        return retos;
    }
}

//    String[] nome = new String[]{"1000 GRAUS", "ACACIA VEICULOS", "AQUI LAVA JATO","1000 GRAUS", "ACACIA VEICULOS", "AQUI LAVA JATO"};
//    String[] endereco = new String[]{"Rua Almirante Rufino 1540", "Av Jose Bastos 3133","Rua Almirante Rufino 1540", "Av Jose Bastos 3133"};
//    String[] telefone = new String[]{"(85) 986734748", "(85) 987378783", "(85) 98483899","(85) 986734748", "(85) 987378783", "(85) 98483899"};
//    String[] valor = new String[]{"R$ 1024,00", "R$ 10,00", "R$ 10,00","R$ 10,00", "R$ 10,00", "R$ 10,00"};
//    List<Venda> listAux = new ArrayList<>();
//
//        for (int i = 0; i < qtd; i++){
//        Venda v = new Venda(nome[i % nome.length], endereco[i % endereco.length], telefone[i % telefone.length], valor[i % valor.length]);
//        listAux.add(v);
//        }
////            List<Rota> listAux = new ArrayList<>();
////            Rota c = new Rota("iadiwjidjai", "OJAOWJDOAWD", "OJDAOWDOJAWOD");
////            listAux.add(c);
//        return(listAux);
