package br.com.objetiveti.pedidodevendas.Activities;

import android.app.ActionBar;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.com.objetiveti.pedidodevendas.Database.DBMain;
import br.com.objetiveti.pedidodevendas.Dao.DaoFormasPagamentos;
import br.com.objetiveti.pedidodevendas.Dao.DaoPedido;
import br.com.objetiveti.pedidodevendas.Models.FormasPagamento;
import br.com.objetiveti.pedidodevendas.Models.Pedido;
import br.com.objetiveti.pedidodevendas.R;
import br.com.objetiveti.pedidodevendas.Services.ServiceAPI;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AcFormaDePagamento extends AppCompatActivity {
    private static final String TAG = "arthur";
    private List<FormasPagamento> vList;
    private List<Pedido> cList;
    android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
    DBMain db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forma_de_pagamento);

        db = new DBMain(this);
        ActionBar actionBar = getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setElevation(0);
        String getValorTotal = null;
        String getNome = null;
        String getVALOOR = null;
        double recebeTotalNumero = 0;
        double valorSemFiltroCash = 0;
        final double recebeANDAMENTO = 0;
        String nomeCliente = null;
        String nomeFULL = null;
        String valorFULL = null;

        final double[] valorTotalMuda = {0};

        final int rotator = 1;

        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        final String dateString = sdf.format(date);

        final TextView txtTESTE = (TextView) findViewById(R.id.txtTESTE);
        final TextView CondPagamento = (TextView) findViewById(R.id.cond_pagamento_valor);
        final TextView valorTotalFinalDevera = (TextView) findViewById(R.id.item_preco);
        //final TextView valorTotalBaixo = (TextView) findViewById(R.id.txtTotalFaltante);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            bundle.getString("lista");
        }

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if (bd != null) {
            nomeCliente = String.valueOf(bd.get("nomeCliente"));
            getNome = String.valueOf(bd.get("ValorFormaPagamento"));
            getValorTotal = String.valueOf(bd.get("valorTotalfinal"));
            recebeTotalNumero = (double) (bd.get("recebeValorNumeroFinal"));
            valorSemFiltroCash = (double) (bd.get("enviarValorDoTotalSemFiltro"));

            nomeFULL = nomeCliente;
            valorFULL = String.valueOf(recebeTotalNumero);
            CondPagamento.setText(getNome);
            valorTotalFinalDevera.setText(getValorTotal);

            //valorTotalBaixo.setText(getValorTotal);
            //txtTESTE.setText(" "+ recebeTotalNumero);
        }
        final EditText primeiroTotal = (EditText) findViewById(R.id.txtvalorPrimeFormaPagamento);
        final EditText segundoTotal = (EditText) findViewById(R.id.txtsegundaFormPagamento);
        final EditText txtsegundaFormPagamento2 = (EditText) findViewById(R.id.txtsegundaFormPagamento2);
        final EditText txtsegundaFormPagamento3 = (EditText) findViewById(R.id.txtsegundaFormPagamento3);
        final EditText txtsegundaFormPagamento4 = (EditText) findViewById(R.id.txtsegundaFormPagamento4);

        final Button button = (Button) findViewById(R.id.bnt_forma_paganto);
        final Button button1 = (Button) findViewById(R.id.bnt_forma_paganto1);
        final Button button2 = (Button) findViewById(R.id.bnt_forma_paganto2);
        final Button button3 = (Button) findViewById(R.id.bnt_forma_paganto3);
        final Button button4 = (Button) findViewById(R.id.bnt_forma_paganto4);
        button.setText("Escolher");
        button1.setText("Escolher");
        button2.setText("Escolher");
        button3.setText("Escolher");
        button4.setText("Escolher");

        final TextView totalRecebido = (TextView) findViewById(R.id.txtTotalRecebido);
        final TextView totalFaltante = (TextView) findViewById(R.id.txtTotalFaltante);

        TextView brons = null;

        totalFaltante.setText(getValorTotal);
        final double valorTotalFaltante = recebeTotalNumero;

        final String primeiraMetade;
        primeiraMetade = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(recebeTotalNumero / 2);
        final double valor1 = recebeTotalNumero / 2;

        String segundaMetade = "";
        segundaMetade = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(0);
        final double valor2 = recebeTotalNumero / 2;
//
//        String terceiraMetade = "";
//        terceiraMetade = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(0);
//
//        String quartaMetade = "";
//        terceiraMetade = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(0);
//
//        String quintaMetade = "";
//        terceiraMetade = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(0);

        final double completo = valorSemFiltroCash;
        //completo = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(valorSemFiltroCash);

        SimpleMaskFormatter sssp = new SimpleMaskFormatter("R$ N.NNN,NN");
        MaskTextWatcher maskTextWatcher = new MaskTextWatcher(segundoTotal, sssp);
        //segundoTotal.addTextChangedListener(maskTextWatcher);
        MaskTextWatcher maskTextWatcher1 = new MaskTextWatcher(txtsegundaFormPagamento2, sssp);
        txtsegundaFormPagamento2.addTextChangedListener(maskTextWatcher1);
        MaskTextWatcher maskTextWatcher2 = new MaskTextWatcher(txtsegundaFormPagamento3, sssp);
        txtsegundaFormPagamento3.addTextChangedListener(maskTextWatcher2);
        MaskTextWatcher maskTextWatcher3 = new MaskTextWatcher(txtsegundaFormPagamento4, sssp);
        txtsegundaFormPagamento4.addTextChangedListener(maskTextWatcher3);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        final String resultadoItens = preferences.getString("quantidade", "");
        final String resultadoNome = preferences.getString("nome", "");
        final String resultadoQuantidade = preferences.getString("quantidade", "");
        final String resultadoPreco = preferences.getString("preco", "");

        //pagamentoPrimeiro.setText(ret);
        //pagamentoSegundo.setText(segundaMetade);
//        txtsegundaFormPagamento2.setText(terceiraMetade);
//        txtsegundaFormPagamento3.setText(quartaMetade);
//        txtsegundaFormPagamento4.setText(quintaMetade);

        Log.e("TAG1", segundaMetade);
        Log.e("TAG1", String.valueOf(completo));

        final double total = recebeTotalNumero;

        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(AcFormaDePagamento.this);
        final AlertDialog.Builder mmBuilder = new AlertDialog.Builder(AcFormaDePagamento.this);
        final AlertDialog.Builder mmmBuilder = new AlertDialog.Builder(AcFormaDePagamento.this);
        final AlertDialog.Builder mmmmBuilder = new AlertDialog.Builder(AcFormaDePagamento.this);
        final AlertDialog.Builder mmmmmBuilder = new AlertDialog.Builder(AcFormaDePagamento.this);

        View mView = getLayoutInflater().inflate(R.layout.dialogo_forma_pagamento, null);
        View mmView = getLayoutInflater().inflate(R.layout.dialogo_forma_pagamento, null);
        View mmmView = getLayoutInflater().inflate(R.layout.dialogo_forma_pagamento, null);
        View mmmmView = getLayoutInflater().inflate(R.layout.dialogo_forma_pagamento, null);
        View mmmmmView = getLayoutInflater().inflate(R.layout.dialogo_forma_pagamento, null);

        final TextView cartao = (TextView) mView.findViewById(R.id.cartao);
        final TextView cartao_debito = (TextView) mView.findViewById(R.id.cartao_debito);
        final TextView cheque = (TextView) mView.findViewById(R.id.cheque);
        final TextView dinheiro = (TextView) mView.findViewById(R.id.dinheiro);
        final TextView deposito = (TextView) mView.findViewById(R.id.deposito);

        final TextView cartao1 = (TextView) mmView.findViewById(R.id.cartao);
        final TextView cartao_debito1 = (TextView) mmView.findViewById(R.id.cartao_debito);
        final TextView cheque1 = (TextView) mmView.findViewById(R.id.cheque);
        final TextView dinheiro1 = (TextView) mmView.findViewById(R.id.dinheiro);
        final TextView deposito1 = (TextView) mmView.findViewById(R.id.deposito);

        final TextView cartao2 = (TextView) mmmView.findViewById(R.id.cartao);
        final TextView cartao_debito2 = (TextView) mmmView.findViewById(R.id.cartao_debito);
        final TextView cheque2 = (TextView) mmmView.findViewById(R.id.cheque);
        final TextView dinheiro2 = (TextView) mmmView.findViewById(R.id.dinheiro);
        final TextView deposito2 = (TextView) mmmView.findViewById(R.id.deposito);

        final TextView cartao3 = (TextView) mmmmView.findViewById(R.id.cartao);
        final TextView cartao_debito3 = (TextView) mmmmView.findViewById(R.id.cartao_debito);
        final TextView cheque3 = (TextView) mmmmView.findViewById(R.id.cheque);
        final TextView dinheiro3 = (TextView) mmmmView.findViewById(R.id.dinheiro);
        final TextView deposito3 = (TextView) mmmmView.findViewById(R.id.deposito);

        final TextView cartao4 = (TextView) mmmmmView.findViewById(R.id.cartao);
        final TextView cartao_debito4 = (TextView) mmmmmView.findViewById(R.id.cartao_debito);
        final TextView cheque4 = (TextView) mmmmmView.findViewById(R.id.cheque);
        final TextView dinheiro4 = (TextView) mmmmmView.findViewById(R.id.dinheiro);
        final TextView deposito4 = (TextView) mmmmmView.findViewById(R.id.deposito);

        final Button bnt_cancela_form_pagamento = (Button) mView.findViewById(R.id.bnt_cancelar_pagamento);
        final Button bnt_cancela_form_pagamento1 = (Button) mmView.findViewById(R.id.bnt_cancelar_pagamento);
        final Button bnt_cancela_form_pagamento2 = (Button) mmmView.findViewById(R.id.bnt_cancelar_pagamento);
        final Button bnt_cancela_form_pagamento3 = (Button) mmmmView.findViewById(R.id.bnt_cancelar_pagamento);
        final Button bnt_cancela_form_pagamento4 = (Button) mmmmmView.findViewById(R.id.bnt_cancelar_pagamento);

        final TextView texto_formade_pagamento1 = (TextView) findViewById(R.id.texto_formade_pagamento1);
        final TextView text_data_pagamento1 = (TextView) findViewById(R.id.data_pagamento1);
        final TextView text_data_pagamento = (TextView) findViewById(R.id.data_pagamento);
        text_data_pagamento.setText(dateString);
        text_data_pagamento1.setText(dateString);
        final View text_vw_divider = (View) findViewById(R.id.vw_divider);


        final TextView data_pagamento2 = (TextView) findViewById(R.id.data_pagamento2);
        data_pagamento2.setText(dateString);
        final TextView texto_formade_pagamento2 = (TextView) findViewById(R.id.texto_formade_pagamento2);
        final View vw_divider2 = (View) findViewById(R.id.vw_divider2);
        final Button bnt_escolher3 = (Button) findViewById(R.id.bnt_forma_paganto2);

        final TextView data_pagamento3 = (TextView) findViewById(R.id.data_pagamento3);
        data_pagamento3.setText(dateString);

        final Button bnt_forma_paganto3 = (Button) findViewById(R.id.bnt_forma_paganto3);
        final TextView texto_formade_pagamento3 = (TextView) findViewById(R.id.texto_formade_pagamento3);
        final View vw_divider3 = (View) findViewById(R.id.vw_divider3);

        final TextView data_pagamento4 = (TextView) findViewById(R.id.data_pagamento4);
        data_pagamento4.setText(dateString);
        final Button bnt_forma_paganto4 = (Button) findViewById(R.id.bnt_forma_paganto4);
        final TextView texto_formade_pagamento4 = (TextView) findViewById(R.id.texto_formade_pagamento4);
        final View vw_divider4 = (View) findViewById(R.id.vw_divider4);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

//        SimpleMaskFormatter simpleMaskFormatter = new SimpleMaskFormatter("R$ N.NNN,NN");
//        MaskTextWatcher mtw4 = new MaskTextWatcher(primeiroTotal, simpleMaskFormatter);
//        primeiroTotal.addTextChangedListener(mtw4);

        fab.setVisibility(View.INVISIBLE);

        data_pagamento2.setVisibility(View.INVISIBLE);
        txtsegundaFormPagamento2.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        texto_formade_pagamento2.setVisibility(View.INVISIBLE);
        vw_divider2.setVisibility(View.INVISIBLE);

        data_pagamento3.setVisibility(View.INVISIBLE);
        txtsegundaFormPagamento3.setVisibility(View.INVISIBLE);
        bnt_forma_paganto3.setVisibility(View.INVISIBLE);
        texto_formade_pagamento3.setVisibility(View.INVISIBLE);
        vw_divider3.setVisibility(View.INVISIBLE);

        data_pagamento4.setVisibility(View.INVISIBLE);
        txtsegundaFormPagamento4.setVisibility(View.INVISIBLE);
        bnt_forma_paganto4.setVisibility(View.INVISIBLE);
        texto_formade_pagamento4.setVisibility(View.INVISIBLE);
        vw_divider4.setVisibility(View.INVISIBLE);

        mBuilder.setView(mView);
        mmBuilder.setView(mmView);
        mmmBuilder.setView(mmmView);
        mmmmBuilder.setView(mmmmView);
        mmmmmBuilder.setView(mmmmmView);

        final String formaCredito = "CARTÃO DE CRÉDITO";
        final String formaDebido = "CARTÃO DE DÉBITO";
        final String formaCheque = "CHEQUE";
        final String formaDinheiro = "DINHEIRO";
        final String formaDeposito = "DEPÓSITO";
        final int[] contador = {0};

        switch (getNome) {
            case "30/60/90":
                fab.setVisibility(View.VISIBLE);
                texto_formade_pagamento1.setVisibility(View.INVISIBLE);
                text_data_pagamento1.setVisibility(View.INVISIBLE);
                segundoTotal.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                text_vw_divider.setVisibility(View.INVISIBLE);
                primeiroTotal.setText("" + completo);
                break;
            case "A VISTA":
                fab.setVisibility(View.VISIBLE);
                texto_formade_pagamento1.setVisibility(View.INVISIBLE);
                text_data_pagamento1.setVisibility(View.INVISIBLE);
                segundoTotal.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                text_vw_divider.setVisibility(View.INVISIBLE);
                primeiroTotal.setText("" + completo);
                break;
            case "30 DIAS":
                fab.setVisibility(View.VISIBLE);
                texto_formade_pagamento1.setVisibility(View.INVISIBLE);
                text_data_pagamento1.setVisibility(View.INVISIBLE);
                segundoTotal.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                text_vw_divider.setVisibility(View.INVISIBLE);
                primeiroTotal.setText("" + completo);
                break;
            case "28 DIAS":
                fab.setVisibility(View.VISIBLE);
                texto_formade_pagamento1.setVisibility(View.INVISIBLE);
                text_data_pagamento1.setVisibility(View.INVISIBLE);
                segundoTotal.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                text_vw_divider.setVisibility(View.INVISIBLE);
                primeiroTotal.setText("" + completo);
                break;
            case "45 DIAS":
                fab.setVisibility(View.VISIBLE);
                texto_formade_pagamento1.setVisibility(View.INVISIBLE);
                text_data_pagamento1.setVisibility(View.INVISIBLE);
                segundoTotal.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                text_vw_divider.setVisibility(View.INVISIBLE);
                primeiroTotal.setText("" + completo);
                break;
            case "30/60":
                fab.setVisibility(View.VISIBLE);
                texto_formade_pagamento1.setVisibility(View.INVISIBLE);
                text_data_pagamento1.setVisibility(View.INVISIBLE);
                segundoTotal.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                text_vw_divider.setVisibility(View.INVISIBLE);
                primeiroTotal.setText("" + completo);
                break;
            case "15 DIAS":
                fab.setVisibility(View.VISIBLE);
                texto_formade_pagamento1.setVisibility(View.INVISIBLE);
                text_data_pagamento1.setVisibility(View.INVISIBLE);
                segundoTotal.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                text_vw_divider.setVisibility(View.INVISIBLE);
                primeiroTotal.setText("" + completo);
                break;
            case "20 DIAS":
                fab.setVisibility(View.VISIBLE);
                texto_formade_pagamento1.setVisibility(View.INVISIBLE);
                text_data_pagamento1.setVisibility(View.INVISIBLE);
                segundoTotal.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                text_vw_divider.setVisibility(View.INVISIBLE);
                primeiroTotal.setText("" + completo);
                break;
            case "3 DIAS":
                fab.setVisibility(View.VISIBLE);
                texto_formade_pagamento1.setVisibility(View.INVISIBLE);
                text_data_pagamento1.setVisibility(View.INVISIBLE);
                segundoTotal.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                text_vw_divider.setVisibility(View.INVISIBLE);
                primeiroTotal.setText("" + completo);
                break;
            default:
                Toast.makeText(getApplicationContext(), "erro...", Toast.LENGTH_SHORT).show();
                break;
        }

        final int SHORT_DELAY = 2000; // 2 seconds

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contador[0] = contador[0] + 1;

                Log.i("contador", String.valueOf(contador[0]));

                if (contador[0] == 1) {
                    texto_formade_pagamento1.setVisibility(View.VISIBLE);
                    text_data_pagamento1.setVisibility(View.VISIBLE);
                    segundoTotal.setVisibility(View.VISIBLE);
                    button1.setVisibility(View.VISIBLE);
                    text_vw_divider.setVisibility(View.VISIBLE);
                    // primeiroTotal.setText(""+completo);
                } else if (contador[0] == 2) {
//                    texto_formade_pagamento2.setVisibility(View.VISIBLE);
//                    data_pagamento2.setVisibility(View.VISIBLE);
//                    txtsegundaFormPagamento2.setVisibility(View.VISIBLE);
//                    texto_formade_pagamento2.setVisibility(View.VISIBLE);
//                    bnt_escolher3.setVisibility(View.VISIBLE);
//                    vw_divider2.setVisibility(View.VISIBLE);
                } else if (contador[0] == 3) {
//                    data_pagamento3.setVisibility(View.VISIBLE);
//                    txtsegundaFormPagamento3.setVisibility(View.VISIBLE);
//                    bnt_forma_paganto3.setVisibility(View.VISIBLE);
//                    texto_formade_pagamento3.setVisibility(View.VISIBLE);
//                    vw_divider3.setVisibility(View.VISIBLE);
                } else if (contador[0] == 4) {
//                    data_pagamento4.setVisibility(View.VISIBLE);
//                    txtsegundaFormPagamento4.setVisibility(View.VISIBLE);
//                    bnt_forma_paganto4.setVisibility(View.VISIBLE);
//                    texto_formade_pagamento4.setVisibility(View.VISIBLE);
//                    vw_divider4.setVisibility(View.VISIBLE);
                }

            }
        });
        segundoTotal.setText("" + 0);
        final double finalValorSemFiltroCash = valorSemFiltroCash;
        final String finalGetValorTotal = getValorTotal;
        final String finalGetValorTotal1 = getValorTotal;
        primeiroTotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public double valorTotalMuda;

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                totalRecebido.setText(charSequence);
                if (totalRecebido.getText().toString().length() == 0) {
                    Toast.makeText(AcFormaDePagamento.this, "Digite o valor da primeira forma", Toast.LENGTH_SHORT).show();
                } else {
                    valorTotalMuda = Double.parseDouble(totalRecebido.getText().toString());
                    String ret = "";
                    ret = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valorTotalMuda);
                    totalRecebido.setText(ret);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        segundoTotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            double valorTotalMuda;
            double valorDoPrimeiroEdit;

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                totalRecebido.setText(charSequence);
                if (totalRecebido.getText().toString().length() == 0) {
                    Toast.makeText(AcFormaDePagamento.this, "Digite o valor da segunda forma", Toast.LENGTH_SHORT).show();
                } else {
                    valorTotalMuda = Double.parseDouble(totalRecebido.getText().toString());
                    valorDoPrimeiroEdit = Double.valueOf(primeiroTotal.getText().toString());
                    String ret = "";
                    ret = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valorTotalMuda + valorDoPrimeiroEdit);
                    totalRecebido.setText(ret);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        //primeiroTotal.setText(getValorTotal);
        final double finalRecebeTotalNumero = recebeTotalNumero;
        final double finalValorTotalMuda = valorTotalMuda[0];
        final double finalValorSemFiltroCash1 = valorSemFiltroCash;
        final double pegando1 = Double.valueOf(primeiroTotal.getText().toString());
        double pegando2 = Double.valueOf(segundoTotal.getText().toString());
        final double restanteee = valorSemFiltroCash - pegando1;
        button.setOnClickListener(new View.OnClickListener() {
            final AlertDialog dialog = mBuilder.create();

            @Override
            public void onClick(View view) {
                cartao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double a = Double.parseDouble(primeiroTotal.getText().toString());
                        String theyNoew = "";
                        theyNoew = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(a);
                        String theyCrash = "";
                        theyCrash = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(finalValorSemFiltroCash - a);
                        ((Button) findViewById(R.id.bnt_forma_paganto)).setText("CARTÃO DE CRÉDITO");
                        if (button1.getText() != "Escolher") {
                            totalFaltante.setText("R$ " + (a - total) + ",00");
                            dialog.dismiss();
                        } else {
                            totalRecebido.setText(theyNoew);
                            totalFaltante.setText(theyCrash);
                            dialog.dismiss();
                        }
                        if (button.getText().equals(button1.getText())) {
                            Toast.makeText(getApplicationContext(), "As formas de pagamento não pode ser a mesma, escolha outra.", Toast.LENGTH_LONG).show();
                            button.setText("Escolher");
                        }
                        if (button.getText() != "Escolher" && button1.getVisibility() == View.INVISIBLE) {
                            totalRecebido.setText(theyNoew);
                            totalFaltante.setText(theyCrash);
                            dialog.dismiss();
                        }
                    }
                });

                cartao_debito.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double a = Double.parseDouble(primeiroTotal.getText().toString());
                        String theyNoew = "";
                        theyNoew = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(a);
                        String theyCrash = "";
                        theyCrash = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(finalValorSemFiltroCash - a);
                        ((Button) findViewById(R.id.bnt_forma_paganto)).setText("CARTÃO DE DÉBITO");
                        if (button1.getText() != "Escolher") {
                            totalFaltante.setText("R$ " + (a - total) + ",00");
                            dialog.dismiss();
                        } else {
                            totalRecebido.setText(theyNoew);
                            totalFaltante.setText(theyCrash);
                            dialog.dismiss();
                        }
                        if (button.getText().equals(button1.getText())) {
                            Toast.makeText(getApplicationContext(), "As formas de pagamento não pode ser a mesma, escolha outra.", Toast.LENGTH_LONG).show();
                            button.setText("Escolher");
                        }
                        if (button.getText() != "Escolher" && button1.getVisibility() == View.INVISIBLE) {
                            totalRecebido.setText(theyNoew);
                            totalFaltante.setText(theyCrash);
                            dialog.dismiss();
                        }
                    }
                });

                cheque.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double a = Double.parseDouble(primeiroTotal.getText().toString());
                        String theyNoew = "";
                        theyNoew = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(a);
                        String theyCrash = "";
                        theyCrash = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(finalValorSemFiltroCash - a);
                        ((Button) findViewById(R.id.bnt_forma_paganto)).setText("CHEQUE");
                        if (button1.getText() != "Escolher") {
                            totalFaltante.setText("R$ " + (a - total) + ",00");
                            dialog.dismiss();
                        } else {
                            totalRecebido.setText(theyNoew);
                            totalFaltante.setText(theyCrash);
                            dialog.dismiss();
                        }
                        if (button.getText().equals(button1.getText())) {
                            Toast.makeText(getApplicationContext(), "As formas de pagamento não pode ser a mesma, escolha outra.", Toast.LENGTH_LONG).show();
                            button.setText("Escolher");
                        }
                        if (button.getText() != "Escolher" && button1.getVisibility() == View.INVISIBLE) {
                            totalRecebido.setText(theyNoew);
                            totalFaltante.setText(theyCrash);
                            dialog.dismiss();
                        }
                    }
                });

                dinheiro.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double a = Double.parseDouble(primeiroTotal.getText().toString());
                        String theyNoew = "";
                        theyNoew = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(a);
                        String theyCrash = "";
                        theyCrash = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(finalValorSemFiltroCash - a);
                        ((Button) findViewById(R.id.bnt_forma_paganto)).setText("DINHEIRO");
                        if (button1.getText() != "Escolher") {
                            totalFaltante.setText("R$ " + (a - total) + ",00");
                            dialog.dismiss();
                        } else {
                            totalRecebido.setText(theyNoew);
                            totalFaltante.setText(theyCrash);
                            dialog.dismiss();
                        }
                        if (button.getText().equals(button1.getText())) {
                            Toast.makeText(getApplicationContext(), "As formas de pagamento não pode ser a mesma, escolha outra.", Toast.LENGTH_LONG).show();
                            button.setText("Escolher");
                        }
                        if (button.getText() != "Escolher" && button1.getVisibility() == View.INVISIBLE) {
                            totalRecebido.setText(theyNoew);
                            totalFaltante.setText(theyCrash);
                            dialog.dismiss();
                        }
                    }
                });

                deposito.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double a = Double.parseDouble(primeiroTotal.getText().toString());
                        String theyNoew = "";
                        theyNoew = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(a);
                        String theyCrash = "";
                        theyCrash = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(finalValorSemFiltroCash - a);
                        ((Button) findViewById(R.id.bnt_forma_paganto)).setText("DEPÓSITO");
                        if (button1.getText() != "Escolher") {
                            totalFaltante.setText("R$ " + (a - total) + ",00");
                            dialog.dismiss();
                        } else {
                            totalRecebido.setText(theyNoew);
                            totalFaltante.setText(theyCrash);
                            dialog.dismiss();
                        }
                        if (button.getText().equals(button1.getText())) {
                            Toast.makeText(getApplicationContext(), "As formas de pagamento não pode ser a mesma, escolha outra.", Toast.LENGTH_LONG).show();
                            button.setText("Escolher");
                        }
                        if (button.getText() != "Escolher" && button1.getVisibility() == View.INVISIBLE) {
                            totalRecebido.setText(theyNoew);
                            totalFaltante.setText(theyCrash);
                            dialog.dismiss();
                        }
                    }
                });

                bnt_cancela_form_pagamento.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button) findViewById(R.id.bnt_forma_paganto)).setText("Escolher");
                        if (button1.getText() == "Escolher") {
                            totalRecebido.setText("R$ " + (valorTotalFaltante - total) + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante) + ",00");
                            dialog.dismiss();
                        } else {
                            totalRecebido.setText("R$ " + (valorTotalFaltante - valor1) + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante + -valor1) + ",00");
                            dialog.dismiss();
                        }
                    }
                });
                dialog.show();
            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            final AlertDialog dialog1 = mmBuilder.create();

            @Override
            public void onClick(View view) {
                cartao1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double b = Double.parseDouble(segundoTotal.getText().toString());
                        double a = Double.parseDouble(primeiroTotal.getText().toString());
                        double c = a + b;
                        String recebidoEdit = "";
                        recebidoEdit = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(a + b);
                        String faltandoEdit = "";
                        faltandoEdit = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(finalValorSemFiltroCash - c);
                        String faltandoEdit2 = "";
                        faltandoEdit2 = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(b);
                        ((Button) findViewById(R.id.bnt_forma_paganto1)).setText("CARTÃO DE CRÉDITO");
                        if (button.getText() == "Escolher") {
                            totalRecebido.setText("R$ " + valor2 + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - valor1) + ",00");
                            dialog1.dismiss();
                        } else {
                            totalRecebido.setText(recebidoEdit);
                            totalFaltante.setText(faltandoEdit);
                            dialog1.dismiss();
                        }
                        if (button1.getText().equals(button.getText())) {
                            totalFaltante.setText(faltandoEdit2);
                            Toast.makeText(getApplicationContext(), "As formas de pagamento não pode ser a mesma, escolha outra.", Toast.LENGTH_LONG).show();
                            button1.setText("Escolher");
                        }
                    }
                });

                cartao_debito1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double b = Double.parseDouble(segundoTotal.getText().toString());
                        double a = Double.parseDouble(primeiroTotal.getText().toString());
                        double c = a + b;
                        String recebidoEdit = "";
                        recebidoEdit = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(a + b);
                        String faltandoEdit = "";
                        faltandoEdit = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(finalValorSemFiltroCash - c);
                        String faltandoEdit2 = "";
                        faltandoEdit2 = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(b);
                        ((Button) findViewById(R.id.bnt_forma_paganto1)).setText("CARTÃO DE DÉBITO");
                        if (button.getText() == "Escolher") {
                            totalRecebido.setText("R$ " + valor2 + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - valor1) + ",00");
                            dialog1.dismiss();
                        } else {
                            totalRecebido.setText(recebidoEdit);
                            totalFaltante.setText(faltandoEdit);
                            dialog1.dismiss();
                        }
                        if (button1.getText().equals(button.getText())) {
                            totalFaltante.setText(faltandoEdit2);
                            Toast.makeText(getApplicationContext(), "As formas de pagamento não pode ser a mesma, escolha outra.", Toast.LENGTH_LONG).show();
                            button1.setText("Escolher");
                        }
                    }
                });

                cheque1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double b = Double.parseDouble(segundoTotal.getText().toString());
                        double a = Double.parseDouble(primeiroTotal.getText().toString());
                        double c = a + b;
                        String recebidoEdit = "";
                        recebidoEdit = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(a + b);
                        String faltandoEdit = "";
                        faltandoEdit = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(finalValorSemFiltroCash - c);
                        String faltandoEdit2 = "";
                        faltandoEdit2 = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(b);
                        ((Button) findViewById(R.id.bnt_forma_paganto1)).setText("CHEQUE");
                        if (button.getText() == "Escolher") {
                            totalRecebido.setText("R$ " + valor2 + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - valor1) + ",00");
                            dialog1.dismiss();
                        } else {
                            totalRecebido.setText(recebidoEdit);
                            totalFaltante.setText(faltandoEdit);
                            dialog1.dismiss();
                        }
                        if (button1.getText().equals(button.getText())) {
                            totalFaltante.setText(faltandoEdit2);
                            Toast.makeText(getApplicationContext(), "As formas de pagamento não pode ser a mesma, escolha outra.", Toast.LENGTH_LONG).show();
                            button1.setText("Escolher");
                        }
                    }
                });

                dinheiro1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double b = Double.parseDouble(segundoTotal.getText().toString());
                        double a = Double.parseDouble(primeiroTotal.getText().toString());
                        double c = a + b;
                        String recebidoEdit = "";
                        recebidoEdit = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(a + b);
                        String faltandoEdit = "";
                        faltandoEdit = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(finalValorSemFiltroCash - c);
                        String faltandoEdit2 = "";
                        faltandoEdit2 = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(b);
                        ((Button) findViewById(R.id.bnt_forma_paganto1)).setText("DINHEIRO");
                        if (button.getText() == "Escolher") {
                            totalRecebido.setText("R$ " + valor2 + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - valor1) + ",00");
                            dialog1.dismiss();
                        } else {
                            totalRecebido.setText(recebidoEdit);
                            totalFaltante.setText(faltandoEdit);
                            dialog1.dismiss();
                        }
                        if (button1.getText().equals(button.getText())) {
                            totalFaltante.setText(faltandoEdit2);
                            Toast.makeText(getApplicationContext(), "As formas de pagamento não pode ser a mesma, escolha outra.", Toast.LENGTH_LONG).show();
                            button1.setText("Escolher");
                        }
                    }
                });

                deposito1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double b = Double.parseDouble(segundoTotal.getText().toString());
                        double a = Double.parseDouble(primeiroTotal.getText().toString());
                        double c = a + b;
                        String recebidoEdit = "";
                        recebidoEdit = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(a + b);
                        String faltandoEdit = "";
                        faltandoEdit = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(finalValorSemFiltroCash - c);
                        String faltandoEdit2 = "";
                        faltandoEdit2 = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(b);
                        ((Button) findViewById(R.id.bnt_forma_paganto1)).setText("DEPÓSITO");
                        if (button.getText() == "Escolher") {
                            totalRecebido.setText("R$ " + valor2 + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - valor1) + ",00");
                            dialog1.dismiss();
                        } else {
                            totalRecebido.setText(recebidoEdit);
                            totalFaltante.setText(faltandoEdit);
                            dialog1.dismiss();
                        }
                        if (button1.getText().equals(button.getText())) {
                            totalFaltante.setText(faltandoEdit2);
                            Toast.makeText(getApplicationContext(), "As formas de pagamento não pode ser a mesma, escolha outra.", Toast.LENGTH_LONG).show();
                            button1.setText("Escolher");
                        }
                    }
                });

                bnt_cancela_form_pagamento1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button) findViewById(R.id.bnt_forma_paganto1)).setText("Escolher");
                        if (button.getText() == "Escolher") {
                            totalRecebido.setText("R$ " + (valorTotalFaltante - total) + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante) + ",00");
                            dialog1.dismiss();
                        } else {
                            totalRecebido.setText("R$ " + (valorTotalFaltante - valor2) + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante + -valor2) + ",00");
                            dialog1.dismiss();
                        }
                    }
                });
                dialog1.show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            final AlertDialog dialog2 = mmmBuilder.create();

            @Override
            public void onClick(View view) {
                cartao2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button) findViewById(R.id.bnt_forma_paganto2)).setText("CARTÃO DE CRÉDITO");
                        if (button.getText() == "Escolher") {
                            totalRecebido.setText("R$ " + valor2 + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - valor1) + ",00");
                            dialog2.dismiss();
                        } else {
                            totalRecebido.setText("R$ " + total + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - total) + ",00");
                            dialog2.dismiss();
                        }
                        if (button1.getText().equals(button.getText())) {
                            Toast.makeText(getApplicationContext(), "As formas de pagamento não pode ser a mesma, escolha outra.", Toast.LENGTH_LONG).show();
                            button1.setText("Escolher");
                        }
                    }
                });

                cartao_debito2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button) findViewById(R.id.bnt_forma_paganto2)).setText("CARTÃO DE DÉBITO");
                        if (button.getText() == "Escolher") {
                            totalRecebido.setText("R$ " + valor2 + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - valor1) + ",00");
                            dialog2.dismiss();
                        } else {
                            totalRecebido.setText("R$ " + total + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - total) + ",00");
                            dialog2.dismiss();
                        }
                        if (button1.getText().equals(button.getText())) {
                            Toast.makeText(getApplicationContext(), "As formas de pagamento não pode ser a mesma, escolha outra.", Toast.LENGTH_LONG).show();
                            button1.setText("Escolher");
                        }
                    }
                });

                cheque2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button) findViewById(R.id.bnt_forma_paganto2)).setText("CHEQUE");
                        if (button.getText() == "Escolher") {
                            totalRecebido.setText("R$ " + valor2 + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - valor1) + ",00");
                            dialog2.dismiss();
                        } else {
                            totalRecebido.setText("R$ " + total + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - total) + ",00");
                            dialog2.dismiss();
                        }
                        if (button1.getText().equals(button.getText())) {
                            Toast.makeText(getApplicationContext(), "As formas de pagamento não pode ser a mesma, escolha outra.", Toast.LENGTH_LONG).show();
                            button1.setText("Escolher");
                        }
                    }
                });

                dinheiro2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button) findViewById(R.id.bnt_forma_paganto2)).setText("DINHEIRO");
                        if (button.getText() == "Escolher") {
                            totalRecebido.setText("R$ " + valor2 + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - valor1) + ",00");
                            dialog2.dismiss();
                        } else {
                            totalRecebido.setText("R$ " + total + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - total) + ",00");
                            dialog2.dismiss();
                        }
                        if (button1.getText().equals(button.getText())) {
                            Toast.makeText(getApplicationContext(), "As formas de pagamento não pode ser a mesma, escolha outra.", Toast.LENGTH_LONG).show();
                            button1.setText("Escolher");
                        }
                    }
                });

                deposito2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button) findViewById(R.id.bnt_forma_paganto2)).setText("DEPÓSITO");
                        if (button.getText() == "Escolher") {
                            totalRecebido.setText("R$ " + valor2 + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - valor1) + ",00");
                            dialog2.dismiss();
                        } else {
                            totalRecebido.setText("R$ " + total + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - total) + ",00");
                            dialog2.dismiss();
                        }
                        if (button1.getText().equals(button.getText())) {
                            Toast.makeText(getApplicationContext(), "As formas de pagamento não pode ser a mesma, escolha outra.", Toast.LENGTH_LONG).show();
                            button1.setText("Escolher");
                        }
                    }
                });

                bnt_cancela_form_pagamento2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button) findViewById(R.id.bnt_forma_paganto2)).setText("Escolher");
                        if (button.getText() == "Escolher") {
                            totalRecebido.setText("R$ " + (valorTotalFaltante - total) + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante) + ",00");
                            dialog2.dismiss();
                        } else {
                            totalRecebido.setText("R$ " + (valorTotalFaltante - valor2) + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante + -valor2) + ",00");
                            dialog2.dismiss();
                        }
                    }
                });
                dialog2.show();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            final AlertDialog dialog3 = mmmmBuilder.create();

            @Override
            public void onClick(View view) {
                cartao3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button) findViewById(R.id.bnt_forma_paganto3)).setText("CARTÃO DE CRÉDITO");
                        if (button.getText() == "Escolher") {
                            totalRecebido.setText("R$ " + valor2 + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - valor1) + ",00");
                            dialog3.dismiss();
                        } else {
                            totalRecebido.setText("R$ " + total + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - total) + ",00");
                            dialog3.dismiss();
                        }
                        if (button1.getText().equals(button.getText())) {
                            Toast.makeText(getApplicationContext(), "As formas de pagamento não pode ser a mesma, escolha outra.", Toast.LENGTH_LONG).show();
                            button1.setText("Escolher");
                        }
                    }
                });

                cartao_debito3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button) findViewById(R.id.bnt_forma_paganto3)).setText("CARTÃO DE DÉBITO");
                        if (button.getText() == "Escolher") {
                            totalRecebido.setText("R$ " + valor2 + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - valor1) + ",00");
                            dialog3.dismiss();
                        } else {
                            totalRecebido.setText("R$ " + total + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - total) + ",00");
                            dialog3.dismiss();
                        }
                        if (button1.getText().equals(button.getText())) {
                            Toast.makeText(getApplicationContext(), "As formas de pagamento não pode ser a mesma, escolha outra.", Toast.LENGTH_LONG).show();
                            button1.setText("Escolher");
                        }
                    }
                });

                cheque3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button) findViewById(R.id.bnt_forma_paganto3)).setText("CHEQUE");
                        if (button.getText() == "Escolher") {
                            totalRecebido.setText("R$ " + valor2 + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - valor1) + ",00");
                            dialog3.dismiss();
                        } else {
                            totalRecebido.setText("R$ " + total + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - total) + ",00");
                            dialog3.dismiss();
                        }
                        if (button1.getText().equals(button.getText())) {
                            Toast.makeText(getApplicationContext(), "As formas de pagamento não pode ser a mesma, escolha outra.", Toast.LENGTH_LONG).show();
                            button1.setText("Escolher");
                        }
                    }
                });

                dinheiro3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button) findViewById(R.id.bnt_forma_paganto3)).setText("DINHEIRO");
                        if (button.getText() == "Escolher") {
                            totalRecebido.setText("R$ " + valor2 + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - valor1) + ",00");
                            dialog3.dismiss();
                        } else {
                            totalRecebido.setText("R$ " + total + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - total) + ",00");
                            dialog3.dismiss();
                        }
                        if (button1.getText().equals(button.getText())) {
                            Toast.makeText(getApplicationContext(), "As formas de pagamento não pode ser a mesma, escolha outra.", Toast.LENGTH_LONG).show();
                            button1.setText("Escolher");
                        }
                    }
                });

                deposito3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button) findViewById(R.id.bnt_forma_paganto3)).setText("DEPÓSITO");
                        if (button.getText() == "Escolher") {
                            totalRecebido.setText("R$ " + valor2 + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - valor1) + ",00");
                            dialog3.dismiss();
                        } else {
                            totalRecebido.setText("R$ " + total + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - total) + ",00");
                            dialog3.dismiss();
                        }
                        if (button1.getText().equals(button.getText())) {
                            Toast.makeText(getApplicationContext(), "As formas de pagamento não pode ser a mesma, escolha outra.", Toast.LENGTH_LONG).show();
                            button1.setText("Escolher");
                        }
                    }
                });

                bnt_cancela_form_pagamento3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button) findViewById(R.id.bnt_forma_paganto3)).setText("Escolher");
                        if (button.getText() == "Escolher") {
                            totalRecebido.setText("R$ " + (valorTotalFaltante - total) + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante) + ",00");
                            dialog3.dismiss();
                        } else {
                            totalRecebido.setText("R$ " + (valorTotalFaltante - valor2) + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante + -valor2) + ",00");
                            dialog3.dismiss();
                        }
                    }
                });
                dialog3.show();
            }
        });


        button4.setOnClickListener(new View.OnClickListener() {
            final AlertDialog dialog4 = mmmmmBuilder.create();

            @Override
            public void onClick(View view) {
                cartao4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button) findViewById(R.id.bnt_forma_paganto4)).setText("CARTÃO DE CRÉDITO");
                        if (button.getText() == "Escolher") {
                            totalRecebido.setText("R$ " + valor2 + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - valor1) + ",00");
                            dialog4.dismiss();
                        } else {
                            totalRecebido.setText("R$ " + total + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - total) + ",00");
                            dialog4.dismiss();
                        }
                        if (button1.getText().equals(button.getText())) {
                            Toast.makeText(getApplicationContext(), "As formas de pagamento não pode ser a mesma, escolha outra.", Toast.LENGTH_LONG).show();
                            button1.setText("Escolher");
                        }
                    }
                });

                cartao_debito4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button) findViewById(R.id.bnt_forma_paganto4)).setText("CARTÃO DE DÉBITO");
                        if (button.getText() == "Escolher") {
                            totalRecebido.setText("R$ " + valor2 + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - valor1) + ",00");
                            dialog4.dismiss();
                        } else {
                            totalRecebido.setText("R$ " + total + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - total) + ",00");
                            dialog4.dismiss();
                        }
                        if (button1.getText().equals(button.getText())) {
                            Toast.makeText(getApplicationContext(), "As formas de pagamento não pode ser a mesma, escolha outra.", Toast.LENGTH_LONG).show();
                            button1.setText("Escolher");
                        }
                    }
                });

                cheque4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button) findViewById(R.id.bnt_forma_paganto4)).setText("CHEQUE");
                        if (button.getText() == "Escolher") {
                            totalRecebido.setText("R$ " + valor2 + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - valor1) + ",00");
                            dialog4.dismiss();
                        } else {
                            totalRecebido.setText("R$ " + total + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - total) + ",00");
                            dialog4.dismiss();
                        }
                        if (button1.getText().equals(button.getText())) {
                            Toast.makeText(getApplicationContext(), "As formas de pagamento não pode ser a mesma, escolha outra.", Toast.LENGTH_LONG).show();
                            button1.setText("Escolher");
                        }
                    }
                });

                dinheiro4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button) findViewById(R.id.bnt_forma_paganto4)).setText("DINHEIRO");
                        if (button.getText() == "Escolher") {
                            totalRecebido.setText("R$ " + valor2 + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - valor1) + ",00");
                            dialog4.dismiss();
                        } else {
                            totalRecebido.setText("R$ " + total + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - total) + ",00");
                            dialog4.dismiss();
                        }
                        if (button1.getText().equals(button.getText())) {
                            Toast.makeText(getApplicationContext(), "As formas de pagamento não pode ser a mesma, escolha outra.", Toast.LENGTH_LONG).show();
                            button1.setText("Escolher");
                        }
                    }
                });

                deposito4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button) findViewById(R.id.bnt_forma_paganto4)).setText("DEPÓSITO");
                        if (button.getText() == "Escolher") {
                            totalRecebido.setText("R$ " + valor2 + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - valor1) + ",00");
                            dialog4.dismiss();
                        } else {
                            totalRecebido.setText("R$ " + total + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante - total) + ",00");
                            dialog4.dismiss();
                        }
                        if (button1.getText().equals(button.getText())) {
                            Toast.makeText(getApplicationContext(), "As formas de pagamento não pode ser a mesma, escolha outra.", Toast.LENGTH_LONG).show();
                            button1.setText("Escolher");
                        }
                    }
                });

                bnt_cancela_form_pagamento4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button) findViewById(R.id.bnt_forma_paganto4)).setText("Escolher");
                        if (button.getText() == "Escolher") {
                            totalRecebido.setText("R$ " + (valorTotalFaltante - total) + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante) + ",00");
                            dialog4.dismiss();
                        } else {
                            totalRecebido.setText("R$ " + (valorTotalFaltante - valor2) + ",00");
                            totalFaltante.setText("R$ " + (valorTotalFaltante + -valor2) + ",00");
                            dialog4.dismiss();
                        }
                    }
                });
                dialog4.show();
            }
        });

//        if (button.getText() == "Escolher" && button1.getText() == "Escolher" && button2.getText() == "Escolher"){
//           // Toast.makeText(getApplicationContext(), "isso aqui tá uma porra.", Toast.LENGTH_SHORT).show();
//            totalRecebido.setText("R$ "+(valorTotalFaltante - total));
//            totalFaltante.setText("R$ "+(getValorTotal));
//        }

        primeiroTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                primeiroTotal.setFocusableInTouchMode(true);

            }
        });

        segundoTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                segundoTotal.setFocusableInTouchMode(true);
            }
        });

        txtsegundaFormPagamento2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtsegundaFormPagamento2.setFocusableInTouchMode(true);
            }
        });

        txtsegundaFormPagamento3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtsegundaFormPagamento3.setFocusableInTouchMode(true);
            }
        });

        txtsegundaFormPagamento4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtsegundaFormPagamento4.setFocusableInTouchMode(true);
            }
        });

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date hora = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
        final String dataFormatada = format.format(hora);

        Button bnt_confirmacao = (Button) findViewById(R.id.bnt_fechar_forma_de_pagamento);
        final String finalValorFULL = valorFULL;
        final String finalNomeFULL1 = getValorTotal;

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        final String codigoVendedorBanco = sharedPreferences.getString("codigo", "");

        DaoFormasPagamentos daoFormasPagamentos = new DaoFormasPagamentos();
        DaoPedido daoPedido = new DaoPedido();
        vList = daoFormasPagamentos.getSetListaFormas(getApplicationContext(), 0);
        cList = daoPedido.getSetPedidoLista(getApplicationContext(), 0);

//        db.openDataBase();
//        SQLiteDatabase database = db.getReadableDatabase();
//        Cursor cursor = database.rawQuery("SELECT v.Codigo as Codigo, v.Nome as Nome, u.login login\n" +
//                "from Vendedor v\n" +
//                "inner join Usuario u on Nome = login",null);

        final String finalGetNome = getNome;
        final String finalNomeCliente = nomeCliente;
        final String finalGetValorTotal2 = getValorTotal;
        final double finalValorSemFiltroCash3 = valorSemFiltroCash;
        bnt_confirmacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(view.getContext());
                alertDialog.setTitle("Deseja Finalizar ? ");
                alertDialog.setMessage("Se sim, clicar em 'CONFIRMAR'");
                alertDialog.setPositiveButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.setNegativeButton("CONFIRMAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int position) {
                        double valorDigitado = Double.parseDouble((primeiroTotal.getText().toString()));
                        double valorDigitado2 = Double.parseDouble(segundoTotal.getText().toString());

                        if (valorDigitado != finalValorSemFiltroCash && segundoTotal.getVisibility() == View.INVISIBLE) {
                            Toast.makeText(AcFormaDePagamento.this, "Valores não batem.", Toast.LENGTH_SHORT).show();
                        } else if (segundoTotal.getVisibility() == View.VISIBLE && valorDigitado + valorDigitado2 == finalValorSemFiltroCash || segundoTotal.getVisibility() == View.INVISIBLE && valorDigitado == finalValorSemFiltroCash) {
                            //  Toast.makeText(view.getContext(), "Item Deletado com sucesso", Toast.LENGTH_SHORT).show();
                            final AlertDialog.Builder mBuilder = new AlertDialog.Builder(AcFormaDePagamento.this);
                            View mView = getLayoutInflater().inflate(R.layout.dialogo_confirmacao, null);
                            mBuilder.setView(mView);
                            final AlertDialog dialog3 = mBuilder.create();
                            final int MILISEGUNDOS = 2000;
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    String formarPagto = " ";
                                    db.openDataBase();
                                    String numPed = String.format("%06d", cList.size());
                                    String itensReult = String.format("%03d", cList.size());
                                    String UM = " ";
                                    String descProduto = " ";
                                    String FormaPagamento = " ";
                                    boolean resultadoPedidoCabecalho = db.insertData(numPed, finalNomeCliente, dateString, dataFormatada, codigoVendedorBanco, finalGetNome, finalValorSemFiltroCash3);
                                    boolean resultadoPedidoPgto = db.insertPedidoVendaPagto(numPed, formarPagto, finalGetValorTotal2);
                                    boolean resultadoPedidoItens = db.insertPedidoVendaItens(numPed, itensReult, resultadoNome, descProduto, UM, resultadoQuantidade, resultadoPreco, finalGetValorTotal2);
                                    int valorVAI = 1;
                                    if (resultadoPedidoCabecalho == true && resultadoPedidoPgto == true && resultadoPedidoItens) {
                                        valorVAI += 1;
                                        Toast.makeText(getApplication(), "Pedido efetuado com sucesso", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplication(), "Erro", Toast.LENGTH_SHORT).show();
                                    }
                                    //@TODO aqui inicio o service
                                    ServiceAPI serviceAPI = ServiceAPI.RETROFIT.create(ServiceAPI.class);
                                    //@TODO CRIO A CHAMADA
                                    serviceAPI.getProdutos().enqueue(new Callback<ResponseBody>() {
                                        @Override
                                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                            //@TODO ESTOU PEGANDO PELO RESPONSE SEU JSON PORQUE TO COM PREGUICA DE MONTAR OBJETO :>3
                                            ResponseBody responseBody = response.body();
                                            try {
                                                //@TODO PEGO O JSON E LANCE NESSE JSON OBJETC
                                                JSONObject jsonObject = new JSONObject(responseBody.string().toString());
                                                //@TODO AI MANDO PARA O ARRAY
                                                JSONArray jsonArray = jsonObject.getJSONArray("PRODUTO");
                                                for (int i = 0 ; i < jsonArray.length(); i++){
                                                    //@TODO E PEGO CADA OBJETO DO SEU ARRAY
                                                    JSONObject OBJETOS = jsonArray.getJSONObject(i);
                                                    //@TODO  AQUI ESTASO ELES
                                                    String codigo = OBJETOS.getString("CODIGO");
                                                    String descricao = OBJETOS.getString("DESCRICAO");
                                                    String precoPadrao = OBJETOS.getString("PRECOPADRAO");
                                                    Log.e("LogJsonURL", codigo);
                                                    Log.e("LogJsonURL", descricao);
                                                    Log.e("LogJsonURL", precoPadrao);
                                                }

                                            } catch (IOException e) {
                                                //@TODO CASO DER ERRO
                                                Toast.makeText(AcFormaDePagamento.this, "Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                e.printStackTrace();
                                            } catch (JSONException e) {
                                                Toast.makeText(AcFormaDePagamento.this, "Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                e.printStackTrace();
                                            }

                                        }

                                        @Override
                                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                                            Toast.makeText(AcFormaDePagamento.this, "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    Intent in = new Intent(getBaseContext(), AcMain.class);
                                    in.putExtra("enviarReczi", valorVAI);
                                    startActivity(in);
                                    dialog3.dismiss();
                                }
                            }, MILISEGUNDOS);
                            dialog3.show();
                        } else {
                            Toast.makeText(AcFormaDePagamento.this, "Valores não batem.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                android.app.AlertDialog dialog1 = alertDialog.create();
                dialog1.show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}


//                                    GithubUserAPI githubUserAPI = GithubUserAPI.retrofit.create(GithubUserAPI.class);
//                                    final Call<UsuarioGit> call = githubUserAPI.getUsuario("ammenendez");
//                                    call.enqueue(new Callback<UsuarioGit>() {
//                                        @Override
//                                        public void onResponse(Call<UsuarioGit> call, Response<UsuarioGit> response) {
//                                            int code = response.code();
//                                            if (code == 200) {
//                                                UsuarioGit usuarioGit = response.body();
//                                                Toast.makeText(getBaseContext(), "Nome do usuário: " + usuarioGit.name, Toast.LENGTH_LONG).show();
//                                            } else {
//                                                Toast.makeText(getBaseContext(), "Falha: " + String.valueOf(code), Toast.LENGTH_LONG).show();
//                                            }
//                                        }
//
//                                        @Override
//                                        public void onFailure(Call<UsuarioGit> call, Throwable t) {
//                                            Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
//                                        }
//                                    });