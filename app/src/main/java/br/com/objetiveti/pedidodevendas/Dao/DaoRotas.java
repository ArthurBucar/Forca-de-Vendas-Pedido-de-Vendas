package br.com.objetiveti.pedidodevendas.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.objetiveti.pedidodevendas.Database.DBMain;
import br.com.objetiveti.pedidodevendas.Models.Rota;

/**
 * Created by Arthur Bucar on 10/2/2017.
 */

public class DaoRotas {

    private String dv;
    private String fq;
    private String sm;
    private int diaAtualNumero = 0;
    String weekDayu = "";

    public String weekDay(Calendar cal) {
        return new DateFormatSymbols().getWeekdays()[cal.get(Calendar.DAY_OF_WEEK)];
    }

    public List<Rota> getSetRotaList(Context context, int qtd){
        DBMain banco = new DBMain(context);
        banco.openDataBase();
        SQLiteDatabase db = banco.getReadableDatabase();
        List<Rota> listAux = new ArrayList<>();

        Cursor c = db.rawQuery("select NomeFantasia, Endereco, Telefone, DV, FQ, SM from Cliente order by NomeFantasia",null);

        if(c.moveToFirst()){
            do{
                //assing valuesw
                String nomeCliente = c.getString(c.getColumnIndex("NomeFantasia"));
                String enderecoCliente = c.getString(c.getColumnIndex("Endereco"));
                String telefoneCliente = c.getString(c.getColumnIndex("Telefone"));

                String dias = c.getString(c.getColumnIndex("DV"));
                String frequencia = c.getString(c.getColumnIndex("FQ"));
                String semana = c.getString(c.getColumnIndex("SM"));
                //Do something Here with values
                Rota cp = new Rota(nomeCliente, enderecoCliente, telefoneCliente, dias, frequencia, semana);

//                Log.e("UIA9", frequencia);

                Calendar calendar = Calendar.getInstance();
                int ano = calendar.get(Calendar.YEAR);
                int mes = calendar.get(Calendar.MONTH)+1;
                int dia = calendar.get(Calendar.DAY_OF_MONTH);
                int hora = calendar.get(Calendar.HOUR_OF_DAY);
                int minuto = calendar.get(Calendar.MINUTE);
                Log.i("diaa", String.valueOf(dia));

                if (dia >= 1 && dia <= 7){
                    diaAtualNumero = 1;
                } else if (dia >= 8 && dia <= 15){
                    diaAtualNumero = 2 ;
                } else if (dia >= 16 && dia <= 23){
                    diaAtualNumero = 3 ;
                } else if (dia >= 24 && dia <= 31){
                    diaAtualNumero = 4;
                }

                if (weekDay(Calendar.getInstance()).equals("Monday")){
                    weekDayu = "SEG";
                } else if (weekDay(Calendar.getInstance()).equals("Tuesday")) {
                    weekDayu = "TER";
                } else if (weekDay(Calendar.getInstance()).equals("Wednesday")) {
                    weekDayu = "QUA";
                } else if (weekDay(Calendar.getInstance()).equals("Thursday")) {
                    weekDayu = "QUI";
                } else if (weekDay(Calendar.getInstance()).equals("Friday")) {
                    weekDayu = "SEX";
                } else if (weekDay(Calendar.getInstance()).equals("Saturday")) {
                    weekDayu = "SAB";
                } else if (weekDay(Calendar.getInstance()).equals("Sunday")) {
                    weekDayu = "DOM";
                }

                Log.e("aaaaa", String.valueOf(diaAtualNumero));
                Log.e("aaaaa", String.valueOf(frequencia));
                Log.e("aaaaa", String.valueOf(dia));
                Log.e("aaaaa", String.valueOf(weekDayu));

                if (frequencia.equals("S")){
                    listAux.add(cp);
                    if (weekDayu.equals(dias)){
                        Log.e("UIAA", dias);
                        listAux.add(cp);
                    }
                }
                if (frequencia.equals("Q")){
                    listAux.add(cp);
                    if (semana.equals(1)){
                        if (diaAtualNumero % 2 == 1){
                            if(weekDayu.equals(dias)){
                                Log.e("UIAA", dias);
                                listAux.add(cp);
                            }
                        }
                    }
                }
                if (frequencia.equals("M")){
                    listAux.add(cp);
                    if (semana.equals(1)){
                        Log.e("UIAA", dias);
                        listAux.add(cp);
                    }
                }
                //listAux.add(cp);

            }while (c.moveToNext() && listAux.size() < qtd);
            //while(c.moveToNext())
        }
        c.close();
        db.close();
        return (listAux);
    }

    public List<Rota> getSetRotaList(Context context){
        DBMain banco = new DBMain(context);
        banco.openDataBase();
        SQLiteDatabase db = banco.getReadableDatabase();
        List<Rota> listAux = new ArrayList<>();

        Cursor c = db.rawQuery("select NomeFantasia, Endereco, Telefone, DV, FQ, SM from Cliente order by NomeFantasia",null);
        //Cursor d = db.rawQuery("select DV, FQ, SM from Cliente",null);

        if(c.moveToFirst()){
            do{
                //assing valuesw
                String nomeCliente = c.getString(c.getColumnIndex("NomeFantasia"));
                String enderecoCliente = c.getString(c.getColumnIndex("Endereco"));
                String telefoneCliente = c.getString(c.getColumnIndex("Telefone"));

                String dias = c.getString(c.getColumnIndex("DV"));
                String frequencia = c.getString(c.getColumnIndex("FQ"));
                String semana = c.getString(c.getColumnIndex("SM"));

                //Do something Here with values
                Rota cp = new Rota(nomeCliente, enderecoCliente, telefoneCliente, dias, frequencia, semana);


                Calendar calendar = Calendar.getInstance();
                int ano = calendar.get(Calendar.YEAR);
                int mes = calendar.get(Calendar.MONTH)+1;
                int dia = calendar.get(Calendar.DAY_OF_MONTH);
                int hora = calendar.get(Calendar.HOUR_OF_DAY);
                int minuto = calendar.get(Calendar.MINUTE);
                Log.i("diaa", String.valueOf(dia));

                if (dia >= 1 && dia <= 7){
                    diaAtualNumero = 1;
                } else if (dia >= 8 && dia <= 15){
                    diaAtualNumero = 2 ;
                } else if (dia >= 16 && dia <= 23){
                    diaAtualNumero = 3 ;
                } else if (dia >= 24 && dia <= 31){
                    diaAtualNumero = 4;
                }


                if (weekDay(Calendar.getInstance()).equals("Monday")){
                    weekDayu = "SEG";
                } else if (weekDay(Calendar.getInstance()).equals("Tuesday")) {
                    weekDayu = "TER";
                } else if (weekDay(Calendar.getInstance()).equals("Wednesday")) {
                    weekDayu = "QUA";
                } else if (weekDay(Calendar.getInstance()).equals("Thursday")) {
                    weekDayu = "QUI";
                } else if (weekDay(Calendar.getInstance()).equals("Friday")) {
                    weekDayu = "SEX";
                } else if (weekDay(Calendar.getInstance()).equals("Saturday")) {
                    weekDayu = "SAB";
                } else if (weekDay(Calendar.getInstance()).equals("Sunday")) {
                    weekDayu = "DOM";
                }

                if (frequencia.equals("S")){
                    listAux.add(cp);
                    if (weekDayu.equals(dias)){
                     listAux.add(cp);
                    }
                }
                if (frequencia.equals("Q")){
                    listAux.add(cp);
                    if (semana.equals(1)){
                        if (diaAtualNumero % 2 == 1){
                            if(weekDayu.equals(dias)){
                                listAux.add(cp);
                            }
                        }
                    }
                }
                if (frequencia.equals("M")){
                    listAux.add(cp);
                    if (semana.equals(1)){
                        listAux.add(cp);
                    }
                }

               //listAux.add(cp);

            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return (listAux);
    }
}








//    String[] models = new String[]{"1000 GRAUS", "ACACIA VEICULOS", "AQUI LAVA JATO","1000 GRAUS", "ACACIA VEICULOS", "AQUI LAVA JATO"};
//    String[] brands = new String[]{"Rua Almirante Rufino 1540", "Av Jose Bastos 3133","Rua Almirante Rufino 1540", "Av Jose Bastos 3133"};
//    String[] asaps = new String[]{"(85) 986734748", "(85) 987378783", "(85) 98483899","(85) 986734748", "(85) 987378783", "(85) 98483899"};
//    List<Rota> listAux = new ArrayList<>();
//
//        for (int i = 0; i < qtd; i++){
//        Rota c = new Rota(models[i % models.length], brands[i % brands.length], asaps[i % asaps.length]);
//        listAux.add(c);
//        }
////            List<Rota> listAux = new ArrayList<>();
////            Rota c = new Rota("iadiwjidjai", "OJAOWJDOAWD", "OJDAOWDOJAWOD");
////            listAux.add(c);
//        return(listAux);
