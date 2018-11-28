package br.com.objetiveti.pedidodevendas.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.objetiveti.pedidodevendas.Database.DBMain;
import br.com.objetiveti.pedidodevendas.Models.Clientec;

/**
 * Created by Arthur Bucar on 10/4/2017.
 */

public class DaoClientesc {
    public List<Clientec> getSetCliente_Listac(Context context, int qtd){
        DBMain banco = new DBMain(context);
        banco.openDataBase();
        SQLiteDatabase db = banco.getReadableDatabase();
        List<Clientec> listAux = new ArrayList<>();

        //order by APELIDO_FANTASIA DESC
        Cursor c = db.rawQuery("select NomeFantasia, Bloqueado, LimCredito, Codigo, CPF_CNPJ, RazaoSocial, Endereco, Bairro, Cidade, CEP, UF, Telefone, Contato, Email, TipoPessoa from Cliente",null);

        if(c.moveToFirst()){
            do{
                //assing values
                String cNome = c.getString(c.getColumnIndex("NomeFantasia"));
                String cStatus = c.getString(c.getColumnIndex("Bloqueado"));
                float cLimiteCredito = c.getFloat(c.getColumnIndex("LimCredito"));
                double codigo = c.getDouble(c.getColumnIndex("Codigo"));
                String cCPNJ_CPF = c.getString(c.getColumnIndex("CPF_CNPJ"));
                String cRazaoSocial = c.getString(c.getColumnIndex("RazaoSocial"));
                String cEndereco = c.getString(c.getColumnIndex("Endereco"));
                String cBairro = c.getString(c.getColumnIndex("Bairro"));
                String cCidade = c.getString(c.getColumnIndex("Cidade"));
                String cCEP = c.getString(c.getColumnIndex("CEP"));
                String cUF = c.getString(c.getColumnIndex("UF"));
                String cTelefone = c.getString(c.getColumnIndex("Telefone"));
                String cContato = c.getString(c.getColumnIndex("Contato"));
                String cEmail = c.getString(c.getColumnIndex("Email"));
                String cTipoDePessoa = c.getString(c.getColumnIndex("TipoPessoa"));
                //Do something Here with values
                Clientec cp = new Clientec(cNome, cStatus, cLimiteCredito, codigo, cCPNJ_CPF, cRazaoSocial, cEndereco, cBairro, cCidade, cCEP, cUF, cTelefone, cContato, cEmail, cTipoDePessoa);

                listAux.add(cp);

            }while(c.moveToNext());
        }
        c.close();
        db.close();

        return(listAux);
    }
}
//    String[] cNome = new String[]{"1000 GRAUS", "6 BOCAS AUTO PEÇAS", "800 AUTOS OLIVEIRA"};
//    String[] cStatus = new String[]{"Sem limite disponível", "Bloqueado", "Disponível"};
//
//    List<Cliente> listAux = new ArrayList<>();
//
//        for (int i = 0; i < qtd; i++){
//        Cliente cp = new Cliente(cNome[i % cNome.length], cStatus[i % cStatus.length]);
//        listAux.add(cp);
//        }