package br.com.objetiveti.pedidodevendas.Activities;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.objetiveti.pedidodevendas.Dao.DaoRotas;
import br.com.objetiveti.pedidodevendas.Dao.DaoVenda;
import br.com.objetiveti.pedidodevendas.Fragments.InicioFragment;
import br.com.objetiveti.pedidodevendas.R;
import br.com.objetiveti.pedidodevendas.Models.Rota;
import br.com.objetiveti.pedidodevendas.Models.Venda;
import br.com.objetiveti.pedidodevendas.Adapters.VendaAdapter;

public class AcMinhasVendas extends AppCompatActivity {
    private RecyclerView vRecyclerView;
    private List<Venda> vList = new ArrayList<>();
    private View v;
    private InicioFragment.OnFragmentInteractionListener vListener;
    private RecyclerView.LayoutManager vLayoutManager;
    private View view;
    private VendaAdapter vendaAdapter;
    public TextView resto;
    private List<Rota> mostra;
    private String personID;

    private String teste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_vendas);

        ActionBar actionBar = getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            bundle.getString("lista");
        }

        DaoVenda daoVenda = new DaoVenda();
        vList = daoVenda.getSetVendaList(getApplicationContext(),3);

        final View v = getLayoutInflater().inflate(R.layout.activity_minhas_vendas, null);
        vRecyclerView = (RecyclerView) findViewById(R.id.rvList02);

        VendaAdapter vendaAdapter = new VendaAdapter(getBaseContext(), vList);

        LinearLayoutManager llm = new LinearLayoutManager(getBaseContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        vRecyclerView.setLayoutManager(llm);

        DaoRotas daoRotas = new DaoRotas();

        vList = daoVenda.getSetVendaList(getApplicationContext());
        vendaAdapter = new VendaAdapter(this, vList);

        teste = daoVenda.getTotalAux();
        String recebeTotal = String.valueOf(teste);

        Bundle bundle1 = new Bundle();
        bundle1.putString("edttext", recebeTotal);
        InicioFragment fragobj = new InicioFragment();
        fragobj.setArguments(bundle1);

        Log.i("zika", String.valueOf(recebeTotal));

        resto = (TextView)findViewById(R.id.valorTotal);
        resto.setText(String.valueOf(recebeTotal));

        vRecyclerView.setAdapter(vendaAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, AcMinhasVendas.class);
        startActivity(intent);
        finish();
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch (item.getItemId()) {
//            case android.R.id.home:
//
//                Bundle args = new Bundle();
//                args.putString("my_custom_object", teste);
//                InicioFragment fragment = new InicioFragment();
//                fragment.setArguments(args);
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment_container, fragment);
//                transaction.commit();
//
//
//                Log.i("tagfuck", String.valueOf(args));
//                finish();
//
//                break;
//
//            default:break;
//        }
//
//        return true;
//    }
}
