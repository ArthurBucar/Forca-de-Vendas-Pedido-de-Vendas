package br.com.objetiveti.pedidodevendas.Activities;

import android.app.ActionBar;
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
import android.app.SearchManager;


import java.util.ArrayList;
import java.util.List;

import br.com.objetiveti.pedidodevendas.Models.Cliente;
import br.com.objetiveti.pedidodevendas.Adapters.ClienteAdapter;
import br.com.objetiveti.pedidodevendas.Fragments.ClientesFragment;
import br.com.objetiveti.pedidodevendas.Dao.DaoClientes;
import br.com.objetiveti.pedidodevendas.R;

public class AcListaClientes extends AppCompatActivity{
    private RecyclerView cRecyclerView;
    private List<Cliente> cList = new ArrayList<>();
    private View v;
    private ClientesFragment.OnFragmentInteractionListener cListener;
    private RecyclerView.LayoutManager cLayoutManager;
    private View view;
    private ClienteAdapter clienteAdapter;
    EditText editText;

    private EditText nome;
    private EditText status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);

        ActionBar actionBar = getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            bundle.getString("lista");
        }

        View v = getLayoutInflater().inflate(R.layout.activity_lista_clientes, null);
        cRecyclerView = (RecyclerView) findViewById(R.id.rvListCliente);

        ClienteAdapter clienteAdapter = new ClienteAdapter(getBaseContext(), cList);

        LinearLayoutManager llm = new LinearLayoutManager(getBaseContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        cRecyclerView.setLayoutManager(llm);

        DaoClientes daoClientes = new DaoClientes();

        cList = daoClientes.getSetCliente_Lista(getBaseContext(),3);
        clienteAdapter = new ClienteAdapter(this, cList);
        cRecyclerView.setAdapter(clienteAdapter);


        nome = (EditText) findViewById(R.id.cnome);
        status = (EditText) findViewById(R.id.cstatus);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

// SEARCHVIEW FAIL
//    private void handleIntent(Intent intent) {
//
//        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//            String query = intent.getStringExtra(SearchManager.QUERY);
//            //use the query to search your data somehow
//        }
//    }

// SEARCHVIEW ONCREATE
//    SearchManager searchManager =
//            (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//    SearchView searchView =
//            (SearchView) menu.findItem(R.id.action_search).getActionView();
//        searchView.setSearchableInfo(
//                searchManager.getSearchableInfo(getComponentName()));