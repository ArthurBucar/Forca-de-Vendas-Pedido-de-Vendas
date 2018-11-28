package br.com.objetiveti.pedidodevendas.Activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import br.com.objetiveti.pedidodevendas.Dao.DaoRotas;
import br.com.objetiveti.pedidodevendas.Fragments.InicioFragment;
import br.com.objetiveti.pedidodevendas.R;
import br.com.objetiveti.pedidodevendas.Models.Rota;
import br.com.objetiveti.pedidodevendas.Adapters.RotaAdapter;

public class AcListaRotas extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView mRecyclerView;
    private List<Rota> mList = new ArrayList<>();
    private View v;
    private InicioFragment.OnFragmentInteractionListener mListener;
    private RecyclerView.LayoutManager mLayoutManager;
    private View view;
    String chavao = null;
    private RotaAdapter mCar;
    ImageButton myImagemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_rotas);

        ActionBar actionBar = getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            bundle.getString("lista");
        }
//        View view = inflater.inflate(R.layout.activity_lista_rotas, null);
//        mRecyclerView.setHasFixedSize(true);

        View v = getLayoutInflater().inflate(R.layout.activity_lista_rotas, null);
        mRecyclerView = (RecyclerView) findViewById(R.id.rvList1);

        RotaAdapter adapter = new RotaAdapter(getBaseContext(), mList);

        LinearLayoutManager llm = new LinearLayoutManager(getBaseContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        DaoRotas daoRotas = new DaoRotas();

        mList = daoRotas.getSetRotaList(getApplicationContext());
        adapter = new RotaAdapter(this, mList);
        mRecyclerView.setAdapter(adapter);

    //int teste = Log.i("teste", String.valueOf(savedInstanceState.getSerializable()));
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(AcListaRotas.this, AcMaps.class);
        startActivity(intent);
    }

}

