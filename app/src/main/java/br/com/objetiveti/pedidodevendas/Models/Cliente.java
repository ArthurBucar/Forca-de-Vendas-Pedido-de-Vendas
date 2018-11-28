package br.com.objetiveti.pedidodevendas.Models;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Arthur Bucar on 10/4/2017.
 */

public class Cliente extends RecyclerView.Adapter implements Serializable{
    private double Codigo;
    private String Loja;
    private String CPF_CNPJ;
    private String RazaoSocial;
    private String NomeFantasia;
    private String TipoPessoa;
    private String Endereco;
    private String Bairro;
    private String Cidade;
    private String UF;
    private String CEP;
    private String Telefone;
    private String Email;
    private String Contato;
    private String TabelaPreco;
    private float LimCredito;
    private String Vendedor;
    private String Bloqueado;
    private String cNome;
    private String cStatus;
    private float recebeuMeta;

    View view;

    public Cliente(){}

    public Cliente(String cn, String cs, float cLimiteCredito, double codigo){
        cNome = cn;
        cStatus = cs;
        LimCredito = cLimiteCredito;
        Codigo = codigo;
    }

    public Cliente(float metaDia) {
        recebeuMeta = metaDia;
    }

    public float getRecebeuMeta() {
        return recebeuMeta;
    }

    public void setRecebeuMeta(float recebeuMeta) {
        this.recebeuMeta = recebeuMeta;
    }


    public String getTotalAux() {
        String retos = " ";
        retos = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(recebeuMeta);

        Log.i("getTotalAux", String.valueOf(retos));
        return retos;
    }

    public double getCodigo() {
        return Codigo;
    }

    public void setCodigo(double codigo) {
        Codigo = codigo;
    }

    public String getLoja() {
        return Loja;
    }

    public void setLoja(String loja) {
        Loja = loja;
    }

    public String getCPF_CNPJ() {
        return CPF_CNPJ;
    }

    public void setCPF_CNPJ(String CPF_CNPJ) {
        this.CPF_CNPJ = CPF_CNPJ;
    }

    public String getRazaoSocial() {
        return RazaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        RazaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return NomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        NomeFantasia = nomeFantasia;
    }

    public String getTipoPessoa() {
        return TipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        TipoPessoa = tipoPessoa;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String bairro) {
        Bairro = bairro;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getContato() {
        return Contato;
    }

    public void setContato(String contato) {
        Contato = contato;
    }

    public String getTabelaPreco() {
        return TabelaPreco;
    }

    public void setTabelaPreco(String tabelaPreco) {
        TabelaPreco = tabelaPreco;
    }

    public float getLimCredito() {
        return LimCredito;
    }

    public void setLimCredito(float limCredito) {
        LimCredito = limCredito;
    }

    public String getVendedor() {
        return Vendedor;
    }

    public void setVendedor(String vendedor) {
        Vendedor = vendedor;
    }

    public String getBloqueado() {
        return Bloqueado;
    }

    public void setBloqueado(String bloqueado) {
        Bloqueado = bloqueado;
    }

    public String getcNome(){return cNome;}

    public void setcNome(String cNome) {this.cNome = cNome;}

    public String getcStatus(){return cStatus;}

    public void setcStatus(String cStatus) {this.cStatus = cStatus;}

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}