package br.com.objetiveti.pedidodevendas.Activities;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.objetiveti.pedidodevendas.Models.Cliente;
import br.com.objetiveti.pedidodevendas.Dao.DaoProdutos;
import br.com.objetiveti.pedidodevendas.Models.Produto;
import br.com.objetiveti.pedidodevendas.Adapters.ProdutoAdapter;
import br.com.objetiveti.pedidodevendas.Adapters.ProdutoAdapterDetalhe;
import br.com.objetiveti.pedidodevendas.Adapters.ProdutoNumAdapter;
import br.com.objetiveti.pedidodevendas.Models.Produto_Num;
import br.com.objetiveti.pedidodevendas.R;

public class AcListaProdutosValores extends AppCompatActivity {
    private Cliente cliente;
    private RecyclerView pRecyclerView;
    private RecyclerView cRecyclerView;
    private List<Produto> pList = new ArrayList<>();
    private List<Produto_Num> cNumList = new ArrayList<>();
    private View v;
    private RecyclerView.LayoutManager pLayoutManager;
    private RecyclerView.LayoutManager cLayoutManager;
    private View view;
    public ProdutoAdapter produtoAdapter;
    public ProdutoNumAdapter produtoNumAdapter;
    public EditText editText;
    public Produto produto = new Produto();
    String itemEscolhio;
    public Produto_Num produto_num = new Produto_Num();
    private Boolean clicou = true;
    private String getQtd;
    private String getNome;
    private String getTotal;
    private String getNomeProduto;
    private String limiteCreditoRecebe;
    Double recebeTotalNumero;
    int recebeHaaa;

    private String precoValorUnitario;

    private String quantidarValor;
    private String subtotalValor;

    private String nomeProduto;
    private String codProduto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__produtos__valores);

        ActionBar actionBar = getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setElevation(0);

        final TextView itemLimiteCredito = (TextView) findViewById(R.id.item_preco);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null) {
            getNome = (String) bd.get("nome");
            getQtd = (String) bd.get("qtd");
            getTotal = (String) bd.get("total");
            getNomeProduto = (String) bd.get("nomeProduto");
            recebeTotalNumero = (Double) bd.get("resultaldoTotalNumerico");
            limiteCreditoRecebe = (String) bd.get("recebeLimiteDeCredito");
            recebeHaaa = (int) bd.get("codigoooo");

            precoValorUnitario = (String) bd.get("precoValorUnitario");
            quantidarValor = (String) bd.get("quantidarValor");
            subtotalValor = (String) bd.get("subtotalValor");

            nomeProduto = (String) bd.get("nomeProduto");
            codProduto = (String) bd.get("codProduto");

            limiteCreditoRecebe = ((limiteCreditoRecebe.replace(".",",")));
            getSupportActionBar().setTitle(getNome);

            Log.e("testeQTD", String.valueOf(recebeHaaa));
        }

//        Intent int1 = new Intent(getBaseContext(), ProdutoAdapterDetalhe.class);
//        int1.putExtra("ValorFormaPagamento", precoValorUnitario);
//        startActivity(int1);

        itemLimiteCredito.setText("R$ "+limiteCreditoRecebe+"0");

        //FAZER OS TEXTOS E BOTÃO SUMIR
        //final Button button1 = (Button) findViewById(R.id.bnt_deletar);

        final Button button = (Button) findViewById(R.id.bnt_escolher_pagamento);

        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(AcListaProdutosValores.this);
        View mView = getLayoutInflater().inflate(R.layout.dialogo_pagamento, null);

        final TextView tv_dias = (TextView) mView.findViewById(R.id.dias);
        final TextView tvAvista = (TextView) mView.findViewById(R.id.avista);
        final TextView tv_trintadias = (TextView) mView.findViewById(R.id.trintadias);
//        final TextView tv_vinteoito = (TextView) mView.findViewById(R.id.vinteoito);
        final TextView tv_quarentacinco = (TextView) mView.findViewById(R.id.quarentacinco);
//        final TextView tv_quinze = (TextView) mView.findViewById(R.id.quinze);
        final TextView tv_trintasixten = (TextView) mView.findViewById(R.id.trintasixten);
//        final TextView tv_vinte = (TextView) mView.findViewById(R.id.vinte);
//        final TextView tv_tres = (TextView) mView.findViewById(R.id.tres);
        final Button bnt_cancela = (Button) mView.findViewById(R.id.bnt_cancelar_pagamento);

        final TextView numProdutos = (TextView) findViewById(R.id.numProdutos);
        numProdutos.setText(getNomeProduto);
        final TextView numItens = (TextView) findViewById(R.id.numItens);
        numItens.setText(getQtd);
        final TextView numTotalFinal = (TextView) findViewById(R.id.numTotalfinal);
        numTotalFinal.setText(getTotal);

        mBuilder.setView(mView);
        button.setText("Escolher");
        button.setOnClickListener(new View.OnClickListener() {
            final AlertDialog dialog = mBuilder.create();
            @Override
            public void onClick(View view) {
                tv_dias.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button)findViewById(R.id.bnt_escolher_pagamento)).setText("30/60/90");
                        dialog.dismiss();
                    }
                });

                tvAvista.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button)findViewById(R.id.bnt_escolher_pagamento)).setText("A VISTA");
                        dialog.dismiss();
                    }
                });

                tv_trintadias.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button)findViewById(R.id.bnt_escolher_pagamento)).setText("30 DIAS");
                        dialog.dismiss();
                    }
                });

//                tv_vinteoito.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        ((Button)findViewById(R.id.bnt_escolher_pagamento)).setText("28 DIAS");
//                        dialog.dismiss();
//                    }
//                });

                tv_quarentacinco.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button)findViewById(R.id.bnt_escolher_pagamento)).setText("45 DIAS");
                        dialog.dismiss();
                    }
                });

//                tv_quinze.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        ((Button)findViewById(R.id.bnt_escolher_pagamento)).setText("15 DIAS");
//                        dialog.dismiss();
//                    }
//                });

                tv_trintasixten.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button)findViewById(R.id.bnt_escolher_pagamento)).setText("30/60");
                        dialog.dismiss();
                    }
                });

//                tv_vinte.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        ((Button)findViewById(R.id.bnt_escolher_pagamento)).setText("20 DIAS");
//                        dialog.dismiss();
//                    }
//                });

//                tv_tres.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        ((Button)findViewById(R.id.bnt_escolher_pagamento)).setText("3 DIAS");
//                        dialog.dismiss();
//                    }
//                });

                bnt_cancela.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button)findViewById(R.id.bnt_escolher_pagamento)).setText("Escolher");
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        Button bntFechar = (Button) findViewById(R.id.bnt_forma_pagamento);
        bntFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button.getText() == "Escolher"){
                    Toast.makeText(getApplicationContext(), "Escolha uma Forma de Pagamento", Toast.LENGTH_SHORT).show();
                } else if (button.getText() != "Escolher"){
                    //Toast.makeText(getApplicationContext(), "Carry On", Toast.LENGTH_SHORT).show()

                    Intent intent = new Intent(getBaseContext(), AcFormaDePagamento.class);
                    intent.putExtra("ValorFormaPagamento", button.getText());
                    intent.putExtra("valorTotalfinal", numTotalFinal.getText());
                    intent.putExtra("recebeValorNumeroFinal", recebeTotalNumero);
                    startActivity(intent);
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getBaseContext(), AcListaProdutos.class);
                startActivity(in);
            }
        });

        pRecyclerView = (RecyclerView) findViewById(R.id.rvListProdutos);

        ProdutoAdapterDetalhe produtoAdapterDetalhe = new ProdutoAdapterDetalhe(getBaseContext(), pList);

        LinearLayoutManager llm = new LinearLayoutManager(getBaseContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        pRecyclerView.setLayoutManager(llm);

        DaoProdutos daoProdutos = new DaoProdutos(recebeHaaa);

        pList = daoProdutos.getSetProduto_Lista(getBaseContext(),0);
        produtoAdapterDetalhe = new ProdutoAdapterDetalhe(this, pList);
        pRecyclerView.setAdapter(produtoAdapterDetalhe);
        //
        pList.clear();
        if (pList != null){
            pList.add(produto);
        }

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Deseja cancelar este pedido?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(AcListaProdutosValores.this, AcMain.class);
                AcListaProdutosValores.this.startActivity(i);
                Toast.makeText(getApplicationContext(), "Pedido cancelado.", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add, menu);
        // return true so that the menu pop up is opened
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

//        Bundle bundle = getIntent().getExtras();
//
//        if (bundle != null){
//            bundle.getString("lista");
//        }
//
//        pRecyclerView = (RecyclerView) findViewById(R.id.rvListProdutos);
//
//        ProdutoAdapter produtoAdapter = new ProdutoAdapter(getBaseContext(), pList);
//
//        LinearLayoutManager llm = new LinearLayoutManager(getBaseContext());
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//        pRecyclerView.setLayoutManager(llm);
//
//        FonteProdutos fonteProdutos = new FonteProdutos();
//
//        pList = fonteProdutos.getSetProduto_Lista(getBaseContext(),6);
//        produtoAdapter = new ProdutoAdapter.MyViewHolder(this, pList);
//        pRecyclerView.setAdapter(produtoAdapter);





//CLICK NO DIALOG
//                mView.setOnClickListener(new View.OnClickListener(){
//                    @Override
//                    public void onClick(View view) {
//                        ((Button)findViewById(R.id.bnt_escolher_pagamento)).setText("A VISTA");
//                        itemEscolhio = "tv_dias";
//                        dialog.dismiss();
//                    }
//                });


//    public void bnt_cancelar_pagamento(View view){
//        Button button = (Button) findViewById(R.id.bnt_cancelar_pagamento);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ((TextView)findViewById(R.id.dias)).setText("Hello");
//            }
//        });
//    }
