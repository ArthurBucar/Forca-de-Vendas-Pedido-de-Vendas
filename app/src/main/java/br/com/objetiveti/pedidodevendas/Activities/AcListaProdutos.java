package br.com.objetiveti.pedidodevendas.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import br.com.objetiveti.pedidodevendas.Dao.DaoProdutos;
import br.com.objetiveti.pedidodevendas.Models.Produto;
import br.com.objetiveti.pedidodevendas.Adapters.ProdutoAdapter;
import br.com.objetiveti.pedidodevendas.R;

public class AcListaProdutos extends AppCompatActivity {
    private RecyclerView pRecyclerView;
    private List<Produto> pList = new ArrayList<>();
    private View v;
    private RecyclerView.LayoutManager pLayoutManager;
    private View view;
    public ProdutoAdapter produtoAdapter;
    public EditText editText;
    public Produto produto = new Produto();
    public float recebeLimite;
    public double codigo;
    public int codigoARTHUR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);

        ActionBar actionBar = getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

//        Intent intent1 = getIntent();
//        Bundle bd1 = intent1.getExtras();
//        if(bd1 != null)
//        {
//         codigoARTHUR = (int) bd1.get("codigoARTHUR");
//        }


        //  Bundle bundle = getIntent().getExtras();
//
//        if (bundle != null){
//            bundle.getString("lista");
//        }
//
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null) {
            String getNome = (String) bd.get("nome");
           // recebeLimite = (float) bd.get("limteDeCredito");
            codigoARTHUR = (int)bd.get("eitaCARAI");
        }

        //View v = getLayoutInflater().inflate(R.layout.activity_lista_produtos, null);
        pRecyclerView = (RecyclerView) findViewById(R.id.rvListProdutos);

        //final ProdutoAdapter  produtoAdapterDetalhe = new ProdutoAdapter(getBaseContext(), pList);

        LinearLayoutManager llm = new LinearLayoutManager(getBaseContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        pRecyclerView.setLayoutManager(llm);

        DaoProdutos daoProdutos = new DaoProdutos(codigoARTHUR);

        pList = daoProdutos.getSetProduto_Lista(getBaseContext(),0);
        produtoAdapter = new ProdutoAdapter(this, pList);
        pRecyclerView.setAdapter(produtoAdapter);
        produtoAdapter.setOnItemClickListener(new ProdutoAdapter.MyViewHolder.ItemClickListener() {
            public void onItemClick(int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("produto", pList.get(position));
                intent.putExtras(bundle);
                setResult(Activity.RESULT_OK, intent);
             //   Toast.makeText(getApplicationContext(), "SUCKS !", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        // return true so that the menu pop up is opened
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (android.R.id.home == item.getItemId()){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}