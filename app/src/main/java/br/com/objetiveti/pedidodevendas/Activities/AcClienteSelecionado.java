package br.com.objetiveti.pedidodevendas.Activities;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.objetiveti.pedidodevendas.Models.Cliente;
import br.com.objetiveti.pedidodevendas.Dao.DaoProdutos;
import br.com.objetiveti.pedidodevendas.Models.Produto;
import br.com.objetiveti.pedidodevendas.Adapters.ProdutoAdapterDetalhe;
import br.com.objetiveti.pedidodevendas.R;

import static java.lang.String.valueOf;

public class AcClienteSelecionado extends AppCompatActivity{
    static final int identifier = 1;
    public int contador = 1;
    private RecyclerView pRecyclerView;
    private List<Produto> pList = new ArrayList<>();
    public Produto produto = new Produto();
    public Cliente cliente = new Cliente();
    public TextView cNome;
    public String nomeEmpresa;
    public String recebeLimite;
    public int codigoRecebeu;
    public int codigofuckthat;
    int recebeItens = 0;
    String bodyBuilder;
    String ret = "";
    double recebevalorTotalDinheiroSemfiltro = 0;
    double recebeTotalNumero;
    int quantidadeEnviar;

    TextView numProdutosSelect;
    TextView numItensSelect;
    TextView numTotalSelect;

    public String a;
    public String b;
    public String c;

    String getNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_selecionado);

        ActionBar actionBar = getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setElevation(0);
        final Button button = (Button) findViewById(R.id.bnt_escolher_teleselecionado);

        numProdutosSelect = (TextView) findViewById(R.id.numProdutoSelecionados);
        numItensSelect = (TextView) findViewById(R.id.numItensSelecionado);
        numTotalSelect = (TextView) findViewById(R.id.numTotalSelecionado);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null) {
            getNome = (String) bd.get("nome");
            nomeEmpresa = (String)bd.get("nome");
            getSupportActionBar().setTitle(getNome);
            recebeLimite = (String) bd.get("limteDeCredito");
            codigoRecebeu = (int) bd.get("codigo");
            codigofuckthat = (int) bd.get("codioooo");
        }

        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(AcClienteSelecionado.this);
        View mView = getLayoutInflater().inflate(R.layout.dialogo_pagamento, null);

        final TextView tv_dias = (TextView) mView.findViewById(R.id.dias);
        final TextView tvAvista = (TextView) mView.findViewById(R.id.avista);
        final TextView tv_trintadias = (TextView) mView.findViewById(R.id.trintadias);
        final TextView tv_quarentacinco = (TextView) mView.findViewById(R.id.quarentacinco);
        final TextView tv_trintasixten = (TextView) mView.findViewById(R.id.trintasixten);
        final Button bnt_cancela = (Button) mView.findViewById(R.id.bnt_cancelar_pagamento);

        mBuilder.setView(mView);
        button.setText("Escolher");
        button.setOnClickListener(new View.OnClickListener() {
            final AlertDialog dialog = mBuilder.create();
            @Override
            public void onClick(View view) {
                tv_dias.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button)findViewById(R.id.bnt_escolher_teleselecionado)).setText("30/60/90");
                        dialog.dismiss();
                    }
                });

                tvAvista.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button)findViewById(R.id.bnt_escolher_teleselecionado)).setText("A VISTA");
                        dialog.dismiss();
                    }
                });

                tv_trintadias.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button)findViewById(R.id.bnt_escolher_teleselecionado)).setText("30 DIAS");
                        dialog.dismiss();
                    }
                });

                tv_quarentacinco.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button)findViewById(R.id.bnt_escolher_teleselecionado)).setText("45 DIAS");
                        dialog.dismiss();
                    }
                });

                tv_trintasixten.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button)findViewById(R.id.bnt_escolher_teleselecionado)).setText("30/60");
                        dialog.dismiss();
                    }
                });

                bnt_cancela.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Button)findViewById(R.id.bnt_escolher_teleselecionado)).setText("Escolher");
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        Button bntFechar = (Button) findViewById(R.id.bnt_5);
        bntFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button.getText() == "Escolher"){
                    Toast.makeText(getApplicationContext(), "Escolha uma Forma de Pagamento", Toast.LENGTH_SHORT).show();
                } if (pList.size() == 0){
                    Toast.makeText(getApplicationContext(), "Adicione Produtos", Toast.LENGTH_SHORT).show();
                } else if (button.getText() != "Escolher"){
                    Intent intent = new Intent(getBaseContext(), AcFormaDePagamento.class);
                    intent.putExtra("nomeCliente", getNome);
                    intent.putExtra("ValorFormaPagamento", button.getText());
                    intent.putExtra("valorTotalfinal", bodyBuilder);

                    intent.putExtra("recebeValorNumeroFinal", recebeTotalNumero);

                    intent.putExtra("enviarValorDoTotalSemFiltro", recebevalorTotalDinheiroSemfiltro);

                    startActivity(intent);
                }
            }
        });

        final TextView itemLimite = (TextView) findViewById(R.id.item_preco);
        String valorTotal =(recebeLimite.replace(".", ","));
        itemLimite.setText("R$ " + valorTotal + "0");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cNome = (TextView) view.findViewById(R.id.cnome);
                Intent in = new Intent(getBaseContext(), AcListaProdutos.class);
                in.putExtra("eitaCARAI", codigofuckthat);
                startActivityForResult(in, identifier);
            }
        });

        atualizaProduto();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {

        ImageView imageView = findViewById(R.id.imagemseta);
        TextView textView6 = findViewById(R.id.textView6);
        cNome = (TextView) findViewById(R.id.cnome);
        // Check which request we're responding to
        if (requestCode == identifier) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

                pRecyclerView = (RecyclerView) findViewById(R.id.rvListProdutos);

                ProdutoAdapterDetalhe produtoAdapterDetalhe = new ProdutoAdapterDetalhe(getBaseContext(), pList);

                LinearLayoutManager llm = new LinearLayoutManager(getBaseContext());
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                pRecyclerView.setLayoutManager(llm);

                DaoProdutos daoProdutos = new DaoProdutos(codigoRecebeu);

               // pList = fonteProdutos.getSetProduto_Lista(getBaseContext(),0);
                produtoAdapterDetalhe = new ProdutoAdapterDetalhe(this, pList);
                pRecyclerView.setAdapter(produtoAdapterDetalhe);

                if (pList != null){
                    imageView.setVisibility(View.INVISIBLE);
                    textView6.setVisibility(View.INVISIBLE);
                }
                produto = (Produto)data.getSerializableExtra("produto");
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AcClienteSelecionado.this);
                    mBuilder.setCancelable(false);
                View mView = getLayoutInflater().inflate(R.layout.diaglog_item, null);
                final TextView dNomeProduto = (TextView)mView.findViewById(R.id.dNomeProduto);
                dNomeProduto.setText(produto.getNomeProduto());
                Log.e("tstes", produto.getNomeProduto());
                final TextView dCodProduto = (TextView)mView.findViewById(R.id.dCodProduto);
                dCodProduto.setText(produto.getCodProduto());
                Log.e("tstes", produto.getCodProduto());
                final TextView dPrecoProduto = (TextView)mView.findViewById(R.id.dPrecoProduto);
                dPrecoProduto.setText(produto.getPrecoReal());
                final TextView quantidade = (TextView)mView.findViewById(R.id.quantidade);
                quantidadeEnviar = Integer.parseInt(quantidade.getText().toString());
               // quantidade.setText(produto.getQuantidade());
                final TextView subtotal = (TextView)mView.findViewById(R.id.dPrecoProduto02);

                final TextView qtd_final = (TextView) findViewById(R.id.tv_descricaoProduto);
                String nomeMadrugada = dNomeProduto.getText().toString();
                subtotal.setText(produto.getPrecoReal());

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                quantidade.setText(""+contador);
                Button bnt_pedido_ok = (Button) mView.findViewById(R.id.bnt_ok_pedido);
                final Button mais1 = (Button) mView.findViewById(R.id.bnt_mais);
                final Button menos1 = (Button) mView.findViewById(R.id.bnt_menos);
                Button mais10 = (Button) mView.findViewById(R.id.bnt_maismais);
                Button menos10 = (Button) mView.findViewById(R.id.bnt_menosmenos);
                Button mais100 = (Button) mView.findViewById(R.id.bnt_maismaismais);
                Button menos100 = (Button) mView.findViewById(R.id.bnt_menosmenosmenos);

                mais1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dPrecoProduto.setText(produto.getPrecoReal());
                        contador = contador+1;
                        quantidade.setText(""+contador);
                        String teste = dPrecoProduto.getText().toString().replace("R$","");
                        dPrecoProduto.setText("R$"+ teste);
                        teste.replace(',', '.');
                        String teste1 = ((subtotal.getText().toString().replace("R$","")));
                        teste1.replace(',','.');
                        double valor1 = Double.parseDouble(teste.replace(',','.'));
                       // String ret = "";
                        ret = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(valor1  * contador);
                        Log.e("taaaaaa", ret);
                        subtotal.setText(""+ret);
                    }
                });

                menos1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (contador > 0){
                            dPrecoProduto.setText(produto.getPrecoReal());
                            contador --;
                            quantidade.setText(""+contador);
                            String teste = dPrecoProduto.getText().toString().replace("R$","");
                            dPrecoProduto.setText("R$"+ teste);
                            teste.replace(',', '.');
                            String teste1 = ((subtotal.getText().toString().replace("R$","")));
                            teste1.replace(',','.');
                            double valor1 = Double.parseDouble(teste.replace(',','.'));
                         //   String ret = "";
                            ret = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(valor1  * contador);
                            valor1 = valor1;
                            subtotal.setText(""+ ret);
                        }
                    }
                });

                mais10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dPrecoProduto.setText(produto.getPrecoReal());
                        contador = contador+10;
                        quantidade.setText(""+contador);
                        String teste = dPrecoProduto.getText().toString().replace("R$","");
                        dPrecoProduto.setText("R$"+ teste);
                        teste.replace(',', '.');
                        String teste1 = ((subtotal.getText().toString().replace("R$","")));
                        teste1.replace(',','.');
                        double valor1 = Double.parseDouble(teste.replace(',','.'));
                      //  String ret = "";
                        ret = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(valor1  * contador);
                        subtotal.setText(""+ ret);
                    }
                });

                menos10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (contador > 0){
                            dPrecoProduto.setText(produto.getPrecoReal());
                            contador = contador - 10;
                            if (contador < 0)
                                contador = 0;
                            quantidade.setText("" + contador);
                            String teste = dPrecoProduto.getText().toString().replace("R$", "");
                            dPrecoProduto.setText("R$" + teste);
                            teste.replace(',', '.');
                            String teste1 = ((subtotal.getText().toString().replace("R$", "")));
                            teste1.replace(',', '.');
                            double valor1 = Double.parseDouble(teste.replace(',', '.'));
                         //   String ret = "";
                            ret = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(valor1  * contador);
                            subtotal.setText("" + ret);
                            Log.e("teste", teste1);
                        }
                    }
                });

                mais100.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dPrecoProduto.setText(produto.getPrecoReal());
                        contador = contador + 100;
                        quantidade.setText(""+contador);
                        String teste = dPrecoProduto.getText().toString().replace("R$","");
                        dPrecoProduto.setText("R$"+ teste);
                        teste.replace(',', '.');
                        String teste1 = ((subtotal.getText().toString().replace("R$","")));
                        teste1.replace(',','.');
                        double valor1 = Double.parseDouble(teste.replace(',','.'));
                       // String ret = "";
                        ret = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(valor1  * contador);
                        subtotal.setText(""+ ret);
                    }
                });

                menos100.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (contador > 0){
                            dPrecoProduto.setText(produto.getPrecoReal());
                            contador = contador - 100;
                            if (contador < 0)
                                contador = 0;
                            quantidade.setText("" + contador);

                            String teste = dPrecoProduto.getText().toString().replace("R$", "");
                            dPrecoProduto.setText("R$" + teste);
                            teste.replace(',', '.');
                            String teste1 = ((subtotal.getText().toString().replace("R$", "")));
                            teste1.replace(',', '.');
                            double valor1 = Double.parseDouble(teste.replace(',', '.'));

                            ret = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(valor1  * contador);
                            subtotal.setText("" + ret);
                            Log.e("teste", teste1);
                        }
                    }
                });
                a = String.valueOf(dPrecoProduto.getText());
                b = String.valueOf(quantidade.getText());
                c = String.valueOf(subtotal.getText());
                final int position = 0;
                final ProdutoAdapterDetalhe detalhe = produtoAdapterDetalhe;
                int b = 1;

                detalhe.setOnItemClickListener(new ProdutoAdapterDetalhe.ItemClickListener() {
                    @Override
                    public void onItemClick(final int positionL) {
//                        android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(getApplicationContext());
//                        alertDialog.setTitle("Deseja Deletar esse Item ? ");
//                        alertDialog.setMessage("Se sim, clicar em 'DELETAR'");
//                        alertDialog.setPositiveButton("CANCELAR", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.cancel();
//                            }
//                        });
//                        alertDialog.setNegativeButton("DELETAR", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int position) {
//                                Toast.makeText(getApplicationContext(), "Item Deletado com sucesso ", Toast.LENGTH_SHORT).show();
//                                pList.remove(positionL);
//                                detalhe.notifyItemRemoved(positionL);
//                            }
//                        });
//                        android.app.AlertDialog dialog = alertDialog.create();
//                        dialog.show();
                        LayoutInflater li = getLayoutInflater();
                        final View view1 = li.inflate(R.layout.diaglog_item1, null);
                        final Dialog dialogEdit = new Dialog(AcClienteSelecionado.this);
                        final android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(AcClienteSelecionado.this);

                        alertDialog.setTitle("Deseja Deletar ou Editar esse Item ? ");
                        alertDialog.setMessage("Se sim, clicar em 'DELETAR' ou 'EDITAR'");
                        alertDialog.setPositiveButton("EDITAR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                detalhe.delete(positionL);
                                dialogEdit.setContentView(R.layout.diaglog_item1);
                                final TextView txtNome = dialogEdit.findViewById(R.id.dNomeProduto);
                                final TextView txtCodigo = dialogEdit.findViewById(R.id.dCodProduto);
                                final TextView txtPreco = dialogEdit.findViewById(R.id.dPrecoProduto);
                                final TextView txtQuantidade = dialogEdit.findViewById(R.id.quantidade);
                                final TextView txtSubtotal = dialogEdit.findViewById(R.id.dPrecoProduto02);

                                final String nome = dNomeProduto.getText().toString();
                                final String codigo = dCodProduto.getText().toString();
                                String preco = dPrecoProduto.getText().toString();
                                txtNome.setText(nome);
                                txtCodigo.setText(codigo);
                                txtPreco.setText(preco);

                                Button bnt_pedido_ok = dialogEdit.findViewById(R.id.bnt_ok_pedido);
                                final Button mais1 = dialogEdit.findViewById(R.id.bnt_mais);
                                final Button menos1 = dialogEdit.findViewById(R.id.bnt_menos);
                                Button mais10 = dialogEdit.findViewById(R.id.bnt_maismais);
                                Button menos10 = dialogEdit.findViewById(R.id.bnt_menosmenos);
                                Button mais100 = dialogEdit.findViewById(R.id.bnt_maismaismais);
                                Button menos100 = dialogEdit.findViewById(R.id.bnt_menosmenosmenos);

                                mais1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        contador = contador+1;
                                        txtQuantidade.setText(""+contador);
                                        String teste = dPrecoProduto.getText().toString().replace("R$","");
                                        txtSubtotal.setText("R$"+ teste);
                                        double valor1 = Double.parseDouble(teste.replace(',','.'));
                                        ret = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(valor1  * contador);
                                        txtSubtotal.setText(""+ret);
                                    }
                                });

                                menos1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (contador > 0) {
                                            contador--;
                                            txtQuantidade.setText("" + contador);
                                            String teste = dPrecoProduto.getText().toString().replace("R$", "");
                                            txtSubtotal.setText("R$" + teste);
                                            double valor1 = Double.parseDouble(teste.replace(',', '.'));
                                            ret = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valor1 * contador);
                                            txtSubtotal.setText("" + ret);
                                        }
                                    }
                                });

                                mais10.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        contador = contador+10;
                                        txtQuantidade.setText(""+contador);
                                        String teste = dPrecoProduto.getText().toString().replace("R$","");
                                        txtSubtotal.setText("R$"+ teste);
                                        double valor1 = Double.parseDouble(teste.replace(',','.'));
                                        ret = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(valor1  * contador);
                                        txtSubtotal.setText(""+ret);
                                    }
                                });

                                menos10.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (contador > 0) {
                                            contador = contador -10;
                                            txtQuantidade.setText("" + contador);
                                            String teste = dPrecoProduto.getText().toString().replace("R$", "");
                                            txtSubtotal.setText("R$" + teste);
                                            double valor1 = Double.parseDouble(teste.replace(',', '.'));
                                            ret = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valor1 * contador);
                                            txtSubtotal.setText("" + ret);
                                        }
                                    }
                                });

                                mais100.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        contador = contador+100;
                                        txtQuantidade.setText(""+contador);
                                        String teste = dPrecoProduto.getText().toString().replace("R$","");
                                        txtSubtotal.setText("R$"+ teste);
                                        double valor1 = Double.parseDouble(teste.replace(',','.'));
                                        ret = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(valor1  * contador);
                                        txtSubtotal.setText(""+ret);
                                    }
                                });

                                menos100.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (contador > 0) {
                                            contador = contador -100;
                                            txtQuantidade.setText("" + contador);
                                            String teste = dPrecoProduto.getText().toString().replace("R$", "");
                                            txtSubtotal.setText("R$" + teste);
                                            double valor1 = Double.parseDouble(teste.replace(',', '.'));
                                            ret = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valor1 * contador);
                                            txtSubtotal.setText("" + ret);
                                        }
                                    }
                                });

                                bnt_pedido_ok.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Produto prod = new Produto();
                                        String teste = dPrecoProduto.getText().toString().replace("R$", "");
                                        double valor1 = Double.parseDouble(teste.replace(',','.'));
                                        prod.setNomeProduto(txtNome.getText().toString());
                                        prod.setCodProduto(txtCodigo.getText().toString());
                                        prod.setPreco(valor1);
                                        prod.setQtd(contador);
                                        Toast.makeText(AcClienteSelecionado.this, "Item Editado com sucesso", Toast.LENGTH_SHORT).show();
                                        detalhe.add(prod);
                                        atualizaProduto();
                                        dialogEdit.dismiss();
                                    }
                                });
                                dialogEdit.show();
                            }
                        });
                        alertDialog.setNegativeButton("DELETAR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int position) {
                                Toast.makeText(AcClienteSelecionado.this, "Item Deletado com sucesso", Toast.LENGTH_SHORT).show();
                                detalhe.delete(positionL);
                                atualizaProduto();
                            }
                        });

                        android.app.AlertDialog dialogbuilder = alertDialog.create();
                        dialogbuilder.show();

                        atualizaProduto();
                        Log.d("logTESTE", "entroiaqui");
                    }
                });

                b =  detalhe.getItemCount();

                final int finalB = b +1;
                bnt_pedido_ok.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {

                        produto.setQtd(contador);
                        detalhe.add(produto);
                        dialog.dismiss();
                        recebeItens = produto.getQtd();
                        int valor = 0;
                        int valorMoney =0;
                        for (int i = 0; i < pList.size(); i++){
                            valor += pList.get(i).getQtd();
                            valorMoney += pList.get(i).getPrecoBruto();
                        }
                        bodyBuilder = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(valorMoney);
                        numProdutosSelect.setText(""+ finalB);
                        numItensSelect.setText(""+ valor);
                        numTotalSelect.setText(bodyBuilder);

                        SharedPreferences prefs = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed = prefs.edit();

                        recebevalorTotalDinheiroSemfiltro = valorMoney;

                        ed.putString("itens", quantidade.getText().toString());
                        ed.putString("nome", dNomeProduto.getText().toString());
                        ed.putString("quantidade", quantidade.getText().toString());
                        ed.putString("preco", dPrecoProduto.getText().toString());
                        ed.commit();

                        contador = 1;
                    }
                });
                dialog.show();
            }
        }
    }

    public void atualizaProduto(){
        int valor = 0;
        int valorMoney =0;
        for (int i = 0; i < pList.size(); i++){
            valor += pList.get(i).getQtd();
            valorMoney += pList.get(i).getPrecoBruto();
        }
        bodyBuilder = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(valorMoney);
        numItensSelect.setText(""+ valor);
        numTotalSelect.setText(bodyBuilder);
        numProdutosSelect.setText(""+ pList.size());
    }
//
//    String produtoNum = numProdutosSelect.getText().toString();
//    String itensNum = numItensSelect.getText().toString();
//    String totalNum = numTotalSelect.getText().toString();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add, menu);

        // return true so that the menu pop up is opened
        return true;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Deseja cancelar este pedido?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(AcClienteSelecionado.this, AcMain.class);
                AcClienteSelecionado.this.startActivity(i);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        int i = item.getItemId();
//        if(i==R.id.menu_add){
//            Intent intent = new Intent(this, NovoPedidoActivity.class);
//            startActivityForResult(intent, 1);
//            //Toast.makeText(NovoPedidoActivity.this, "Linkar a Activity", Toast.LENGTH_SHORT).show();
////            Intent intent = new Intent(getBaseContext(), ListaClientesActivity.class);
////            startActivity(intent);
//        }
//        if (android.R.id.home == item.getItemId()){
//            finish();
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
//intent.putExtra("nome", nomeCliente.getText());
//                        Intent intent = new Intent(getBaseContext(), Lista_Produtos_ValoresActivity.class);
//                        startActivity(intent);
//Toast.makeText(getApplicationContext(), "SUCKS !", Toast.LENGTH_SHORT).show();