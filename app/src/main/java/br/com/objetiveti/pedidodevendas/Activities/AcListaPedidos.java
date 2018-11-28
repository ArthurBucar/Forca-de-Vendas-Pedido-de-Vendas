package br.com.objetiveti.pedidodevendas.Activities;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.objetiveti.pedidodevendas.Dao.DaoPedido;
import br.com.objetiveti.pedidodevendas.Fragments.InicioFragment;
import br.com.objetiveti.pedidodevendas.Models.Pedido;
import br.com.objetiveti.pedidodevendas.Adapters.PedidoAdapter;
import br.com.objetiveti.pedidodevendas.R;

public class AcListaPedidos extends AppCompatActivity {
    private RecyclerView oRecyclerView;
    private List<Pedido> pList = new ArrayList<>();
    private View v;
    private InicioFragment.OnFragmentInteractionListener pListener;
    private RecyclerView.LayoutManager pLayoutManager;
    private View view;
    private PedidoAdapter oOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos_actitivty);
        ActionBar actionBar = getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            bundle.getString("lista");
        }

        View v = getLayoutInflater().inflate(R.layout.activity_lista_pedidos_actitivty, null);
        oRecyclerView = (RecyclerView) findViewById(R.id.rvListOrder);

        PedidoAdapter pedidoAdapter = new PedidoAdapter(getBaseContext(), pList);

        LinearLayoutManager llm = new LinearLayoutManager(getBaseContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        oRecyclerView.setLayoutManager(llm);


        DaoPedido daoPedido = new DaoPedido();

        pList = daoPedido.getSetPedidoLista(getBaseContext(), 3);
        pedidoAdapter = new PedidoAdapter(this, pList);
        oRecyclerView.setAdapter(pedidoAdapter);
        
    }
}
//        setSupportActionBar(toolbar);
